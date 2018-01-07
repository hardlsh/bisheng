

/**
 * 地区管理控制对象
 * 主要负责地区树新增、修改、删除、查看等操作控制
 * @author lihao
 */
var areaManager = function() {
	/**f
	 * 地区树实例
	 */
	var _zTree;
	
	/**
	 * 地区新增、修改时弹出窗体的内容HTML
	 */
	var popHtml =
			'<hr>'+
			'<form id="areaForm" name="areaForm" method="post" action="">'+
			'	<table align="center">'+
			'		<tr>'+
			'			<td>父节点:</td>'+
			'			<td id="parentName"></td>'+
			'		</tr>'+
			'		<tr>'+
			'			<td>地区Id:</td>'+
			'			<td>'+
			'				<input type="text" id="areaId" name="areaId" maxLength="10" size="42" />'+
			'			</td>'+
			'		</tr>'+
			'		<tr>'+
			'			<td>地区名称:</td>'+
			'			<td>'+
			'				<input type="text" id="areaName" name="areaName" maxLength="32" size="42" />'+
			'			</td>'+
			'		</tr>'+
			'		<tr>'+
			'			<td>简称:</td>'+
			'			<td>'+
			'				<input type="text" id="shortName" name="shortName" maxLength="10" size="42" />'+
			'			</td>'+
			'		</tr>'+
			'		<tr>'+
			'			<td>级别:</td>'+
			'			<td>'+
			'				<input type="text" id="level" name="level" maxLength="5" size="42" placeholder="1:省/直辖市, 2:地级市, 3:县级市" />'+
			'			</td>'+
			'		</tr>'+
			'		<tr>'+
			'			<td>拼音:</td>'+
			'			<td>'+
			'				<input type="text" id="pinyin" name="pinyin" maxLength="32" size="42" />'+
			'			</td>'+
			'		</tr>'+
			'		<tr>'+
			'			<td>简拼:</td>'+
			'			<td>'+
			'				<input type="text" id="jianpin" name="jianpin" maxLength="32" size="42" />'+
			'			</td>'+
			'		</tr>'+
			'		<tr>'+
			'			<td>首字母:</td>'+
			'			<td>'+
			'				<input type="text" id="firstChar" name="firstChar" maxLength="5" size="42" placeholder="大写英文字母"/>'+
			'			</td>'+
			'		</tr>'+
			'		<tr>'+
			'			<td>备注:</td>'+
			'			<td>'+
			'				<input type="text" id="remark" name="remark" maxLength="32" size="42" />'+
			'			</td>'+
			'		</tr>'+
			'		<tr>'+
			'			<td>状态:</td>'+
			'			<td>'+
			'				<input type="radio" id="status1" name="status" value="1" />激活&nbsp;'+
			'				<input type="radio" id="status2" name="status" value="2" checked="checked" />冻结'+
			'			</td>'+
			'		</tr>'+
			'	</table>'+
			'</form>';

	
	/**
	 * 配置
	 */
	var _setting = {
			async:{
				//启用异步加载
				enable:true,
				//异步请求地址
				url:environment.basePath + "/area/getAllAreaList.do",
				//数据类型 json、xml、html
				dataType:"json",
				dataFilter:function(treeId, parentNode, responseData){
					if (responseData.code < 0) {
						alert(responseData.msg);
						return "";
					}
					return responseData.data;
				}
			},
			data:{
				key:{
					//当model类中name或其他字段不对应时可用此方法，但是zNodes节点中name的属性也要改成于model类一致
					name:"areaName",
					//节点链接的目标 URL 的属性名称,不想实现点击节点跳转的功能时，直接修改此属性为其他不存在的属性名称
					pinyin:null
				},
				simpleData:{
					enable:true,
					idKey:"areaId",
					pIdKey:"parentId"
				}
			},
			edit:{
				enable:true,
				//删除按钮的Title辅助信息
				removeTitle:"删除地区",
				//编辑名称按钮的Title辅助信息
				renameTitle:"编辑地区名称",
				//节点编辑名称input初次显示时，设置txt内容是否为全选状态
				editNameSelectAll:true
			},
			view:{
				//设置是否显示节点图片，默认true显示
				showIcon:true,
				//节点之间的连线，默认为true有
				showLine:true,
				//zTree节点展开、折叠时的动画速度，""表示关闭，默认fast，还有slow,normal,也可以直接输入数字
				expandSpeed:"show",
				//用于当鼠标移动到节点上时，显示用户自定义控件，显示隐藏状态同zTree
				addHoverDom:_addHoverDom,
				//用于鼠标移出节点时，隐藏用户自定义控件，显示隐藏状态同zTree内容的编辑、删除按钮
				removeHoverDom:_removeHoverDom,
				//设置是否允许同时选中多个节点
				selectedMulti:false
			},
			callback:{
				//加载成功后调用
				onAsyncSuccess:function(){
					//展开全部节点
					_zTree.expandAll(true);
				},
				//点击删除时触发，用来提示用户是否确定删除
				beforeRemove:function(treeId, treeNode){
					if(confirm("删除地区将会级联删除其子地区，你确定要删除吗？")){
						//检验数据
						if(treeNode.isParent){
					        alert("所删除地区包含子地区，不允许删除！");
					        return false;
					    }
						
						//异步请求 删除地区
						$.ajax({
							dataType: 'json',
							url: environment.basePath + "/area/deleteArea.do",
							data: {"areaId": treeNode.areaId},
							type: 'POST',
							beforeSend: function() {
							},
							success: function(data) {
								//错误等信息提示
								if(data.code < 0){
									alert(data.msg);
									return false;
								}
								
								//删除树节点
								_zTree.removeNode(treeNode);
								alert(data.msg);
								return true;
							},
							error: function() {
							}
						});//End...$.ajax
					}//End...if(confirm())
					return false;
				},
				//点击编辑时触发，用来判断该节点是否能编辑
				beforeEditName:function(treeId, treeNode){
					_doPopWin(treeNode, environment.basePath + "/area/updateArea.do", function(){
						//赋值
						var parentName = "空";
						if(treeNode.parentId != null && treeNode.parentId != ""){
							parentName = treeNode.getParentNode().areaName;
						}
						$("#parentName").html(parentName);
						$("#areaId").val(treeNode.areaId);
						$("#parentId").val(treeNode.parentId);
						$("#areaName").val(treeNode.areaName);
						$("#showOrder").val(treeNode.showOrder);
						$("#shortName").val(treeNode.shortName);
						$("#pinyin").val(treeNode.pinyin);
						$("#jianpin").val(treeNode.jianpin);
						$("#firstChar").val(treeNode.firstChar);
						$("#remark").val(treeNode.remark);
						$("#status" + treeNode.status).attr("checked",true);
					}, (treeNode.parentId == null ? "10000" : treeNode.parentId), treeNode.showOrder);
					
					//表示禁止编辑节点名称
					return false;
				}
			}
	};

	/**
	 * 用于当鼠标移动到节点上时，显示用户自定义控件
	 */
	function _addHoverDom(treeId, treeNode){
	    var sObj = $("#" + treeNode.tId + "_span");  
	    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;  
	    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
	        + "' title='添加子节点' onfocus='this.blur();'></span>";
	    sObj.after(addStr);
	    var btn = $("#addBtn_"+treeNode.tId);
	    if (btn) btn.bind("click", function(){
	    	var showOrder = (typeof treeNode.children == 'undefined' ? 0 : treeNode.children.length);
	    	_doPopWin(treeNode, environment.basePath + "/area/insertArea.do", function(){
	    		$("#parentName").html(treeNode.areaName);
	    	}, treeNode.areaId, showOrder);
	        return false;
	    });  
	}

	/**
	 * 用于鼠标移出节点时，隐藏用户自定义控件
	 */
	function _removeHoverDom(treeId,treeNode){  
	    $("#addBtn_"+treeNode.tId).unbind().remove();  
	}

	/**
	 * 新增/修改地区弹出窗体处理
	 * @param treeNode 选中的树节点
	 * @param requestUrl 请求地址
	 * @param fun
	 * @param pid 父id
	 */
	var _doPopWin = function(treeNode, requestUrl, fun, pid){
		//弹出窗体供用户新增节点信息
		art.dialog({
	            id: 'treeNodePopWinId',  
	            content: popHtml,
	            button: [
	                {
	                    name: '提交',  
	                    callback: function () {
							//检验数据
	                    	var areaId = $("#areaId").val();
	                    	var areaName = $("#areaName").val();
	                    	var parentId = pid;
	                    	var shortName = $("#shortName").val();
	                    	var level = $("#level").val();
	                    	var pinyin = $("#pinyin").val();
	                    	var jianpin = $("#jianpin").val();
	                    	var firstChar = $("#firstChar").val();
	                    	var status = $("input:radio[name='status']:checked").val();
	                    	var remark = $("#remark").val();
	                		if(areaId == null || areaId.length <= 0){
	                			alert("地区Id不允许为空");
	                			return false;
	                		}
	                		if(areaName == null || areaName.length <= 0){
	                			alert("地区名称不允许为空");
	                			return false;
	                		}
	                		if(shortName == null || shortName.length <= 0){
	                			alert("地区简称不允许为空");
	                			return false;
	                		}
	                		if(level == null || level.length <= 0){
	                			alert("地区级别不允许为空");
	                			return false;
	                		}
	                		if(pinyin == null || pinyin.length <= 0){
	                			alert("地区拼音不允许为空");
	                			return false;
	                		}
	                		if(jianpin == null || jianpin.length <= 0){
	                			alert("地区简拼不允许为空");
	                			return false;
	                		}
	                		if(firstChar == null || firstChar.length <= 0){
	                			alert("地区首字母不允许为空");
	                			return false;
	                		}
	                		if(status == null || status.length <= 0){
	                			alert("地区状态不允许为空");
	                			return false;
	                		}
	                		
	                		//异步请求 新增、修改地区
	                		$.ajax({
	                			dataType: 'json',
	                			url: requestUrl,
	                			data: {
	                				"areaId" : areaId,
	                				"areaName" : areaName,
	                				"parentId" : parentId,
	                				"shortName" : shortName,
	                				"level" : level,
	                				"pinyin" : pinyin,
	                				"jianpin" : jianpin,
	                				"firstChar" : firstChar,
	                				"status" : status,
	                				"remark" : remark
	                			},
	                			type: 'POST',
	                			beforeSend: function() {
	                			},
	                			success: function(data) {
	                				//错误等信息提示
	                				if(data.code < 0){
	                					alert(data.msg);
	                					return false;
	                				}
	                				
	                				//添加树节点
	                				//zTree.addNodes(treeNode, {id:(100), pId:treeNode.menuId, name:"新建子节点"});
	                				//刷新树
	                				_zTree.reAsyncChildNodes(null, "refresh");
	                				alert(data.msg);
	                			},
	                			error: function() {
	                			}
	                		});//End...$.ajax
	                    },  
	                    focus:true  
	                },  
	                {  
	                    name: '取消'
	                }
	            ]  
		});//End...art.dialog
		fun&fun();
	};

	/**
	 * 新增省级地区树节点
	 */
	var _addTopNode = function(){
		_doPopWin(null, environment.basePath + "/area/insertArea.do", function(){
			//赋值
			$("#parentName").html("空");
		}, 0, _zTree.getNodes().length);
	}
	
	return {
		/**
		 * 初始化
		 */
		init: function(){
			_zTree = $.fn.zTree.init($("#areaTree"), _setting);
		},
		
		/**
		 * 新增顶级地区树节点
		 */
		addTopNode: _addTopNode
		
	};//End...return
} ();//End...var areaManager

/**
 * 页面加载后执行
 */
$(function() {
	areaManager.init();
});