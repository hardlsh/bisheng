<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../basepath.jsp"%>

<script src="${basePath}/resources/js/jquery.form.js" type="text/javascript"></script>

<ol class="breadcrumb">
  <li class="active">展位管理</li>
</ol>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box grey-cascade">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>展位管理
				</div>
				<div class="actions">
					<button id="addBooth" class="btn btn-sm blue" type="button">新增展位</button>
					<shiro:hasRole  name="管理员">
						<button id="delBooth" class="btn btn-sm red" type="button">删除展位</button>
					</shiro:hasRole>
				</div>
			</div>
			<div class="portlet-body">
			
				<div class="table-toolbar">
					<div class="with-border">
						<div class="row">
							<div class="col-md-6">
								<h4>筛选条件</h4>
							</div>
						</div>
						<hr>
					</div>
					<div style="padding: 10px;background-color: #f7f7f7">
					<form id='filter_form' href="##" method="post">
						<!-- 隐藏的复位按钮 -->
						<input type="reset" style="display: none;" />
						<div class="form-group input-inline">
							城市:<input id="city" name="city" class="form-control input-inline input-sm" maxlength="15" 
								placeholder="例如:上海" type="text"/>
							展馆:
								 <hz:authExhibitSelect id="exhibitIdList" name="exhibitIdList"
	                                  classes="multiselect form-control"
	                                  multiple="multiple"
	                                  hasAll="false"
	                                  onchange="boothHelper.loadBooth(this);"
	                                  style="width: 110px;height: 28px;display: block;"/>&nbsp;
							展位: 
								<hz:multipleSelect dictCode="" id="boothIdList" name="boothIdList" 
									classes="multiselect form-control input-inline" multiple="multiple"
									hasAll="false"
									style="width: 110px;height: 28px;display: none;" />&nbsp;
						</div>
						<div class="row">
						<div class="form-group  input-inline">
							<div class="col-md-12">
								<button id="btnRefresh" class="btn btn-sm green" type="button">搜索</button>
								<a class="btn btn-sm red" id="btnEmpty"><i class="icon-trash"></i>清空条件</a>
								<button id="doExport" class="btn btn-sm green" type="button">导出明细</button>
	                        </div>
						</div>
						</div>
					</form>
					</div>
				</div>
				
				<!-- 下载对应form -->
				<form id="down_form" href="##">
					<!-- 隐藏域 -->
					<input type="hidden" name="boothName" id="dnBoothName"/>
					<input type="hidden" name="xCount" id="dnXCount"/>
					<input type="hidden" name="yCount" id="dnYCount"/>
				</form>
				
				<table class="table table-striped table-bordered table-hover" id="boothTable">
					<thead>
						<tr>
							<th>展位ID</th>
							<th>展位名称</th>
							<th>所属展馆</th>
							<th>展位编号</th>
							<th>X轴字数(行数)</th>
							<th>Y轴字数(列数)</th>
							<th>展位状态</th>
							<th>导入标识</th>
							<th>文字模板</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>

<!-- 查看文字导入,弹出框 -->
<div class="modal" id="wordContentModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="height:560px;background-color:white;overflow:auto;min-width:750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">文字模板展示</h4>
            </div>
            <div class="modal-body">
                	<label id="wordContent" class="control-label">文字模板内容展示</label>
            </div>
            <div class="modal-footer -align-center">
                <button type="button" class="btn default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
</div>

<!-- 查看二维码,弹出框 -->
<div class="modal" id="QRCodeModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">生成二维码展示</h4>
            </div>
            <div class="modal-body">
                	<div id="QRCode"></div>
            </div>
            <div class="modal-footer -align-center">
                <button type="button" class="btn default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
</div>

<!-- 上传模板弹出框 -->
<div class="modal" id="importTemplet" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">上传模板</h4>
            </div>
            <div class="modal-body">
                <form id="uploadForm" action="${basePath}/booth/importTempletNew.do" class="form-horizontal" enctype="multipart/form-data" method="post">
					<!-- 隐藏域 -->
					<input type="hidden" name="boothId" id="upBoothId"/>
					<input type="hidden" name="boothName" id="upBoothName"/>
					<input type="hidden" name="xCount" id="upXCount"/>
					<input type="hidden" name="yCount" id="upYCount"/>
					<input type="hidden" name="wordSign" id="upWordSign"/>
					<div class="form-group">
						<label class="control-label col-md-1"/>
						<div class="col-md-8">
							<input type="file" style="width:350px;" name="multipartFile" id="file_id">
						</div>
						<div class="col-md-2">
							<button type="button" id="uploadFile" onclass="btn blue">上传</button>
							<button type="reset" id="uploadFormReset" style="display: none;"></button>
						</div>
					</div>
					<%--<div class="form-group input-inline">
						<label class="control-label col-md-1"/>
						<label class="control-label col-md-5 col-sm-5 col-xs-8">模板入库数量<span class="required"> * </span></label>
						<div class="col-md-6 col-sm-6 col-xs-12">
						<input id="templateCount" name="templateCount" class="form-control input-inline input-sm" maxlength="15" 
							placeholder="请输入数字" type="text" onkeyup="value=value.replace(/[^(\d)]/g,'')" />
						</div>
					</div>--%>
					<!-- 导入模板,默认为导入一份 -->
					<input id="templateCount" name="templateCount" type="hidden" value="1" />
				</form>
            </div>
            <div class="modal-footer -align-center">
                <button type="button" class="btn default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
</div>

<!-- 删除展位,弹出框 -->
<div class="modal" id="delBoothModal" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">删除展位</h4>
			</div>
			<div class="modal-body">
				<form id='del_form' href="##" method="post">
				</form>
				<table class="table table-striped table-bordered table-hover"
					   id="delBoothTable">
					<thead>
					<tr>
						<th>单选</th>
						<th>展位名称</th>
						<th>所属展馆</th>
					</tr>
					</thead>
				</table>
			</div>
			<div class="modal-footer -align-center">
				<button type="button" class="btn red" id="delBoothConfirm">确认删除</button>
				<button type="button" class="btn default" data-dismiss="modal">关闭</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
</div>

<script type="text/javascript">

	var boothHelper = {
		boothStatusEnum : '${boothStatusEnum}',
		boothWordSignEnum : '${boothWordSignEnum}',
		
		init : function () {
			var me = boothHelper;
			
			$('#addBooth').click(me.addBooth);// 新增展位按钮
            $('#delBooth').click(me.delBooth);// 删除展位按钮
			$('#uploadFile').click(me.uploadFile);// 上传文件
			$('#btnRefresh').click(me.btnRefresh);//搜索按钮
			$('#btnEmpty').click(me.btnEmpty);//清空条件
			$('#doExport').click(me.doExport);// 导出明细按钮
            $('#delBoothConfirm').click(me.delBoothConfirm);// 删除展位弹出框上的确认按钮
			
			$('.multiselect').multiselect({
	            enableFiltering: true,
	            nonSelectedText: '无选择',
	    		allSelectedText:"共选择",
	    		selectAllText: '全选',
	    		nSelectedText:"选择",
	    		buttonWidth:"110px",
	    		maxHeight: '300',
	    		buttonClass:'btn btn-default input-sm',
	            includeSelectAllOption: true
	        });
			
			//初始化Table
			me.boothTable();
            me.delBoothTable();
            me.loadBooth();
		},
		//初始化Table,加载表格
		boothTable : function () {
			var me = boothHelper;
			
			var url = "${basePath}/booth/getBoothList.do"; //表格数据远程地址
			var colArray = [
					{"data" : "boothId","bSortable" : false},
					{"data" : "boothName","bSortable" : false},
					{"data" : "exhibitName","bSortable" : false},
					{"data" : "sequence","bSortable" : false},
					{"data" : "xCount","bSortable" : false},
					{"data" : "yCount","bSortable" : false},
					{"data" : function (e) {
						return myJson.getText(e.status, me.boothStatusEnum);
						},"bSortable" : false
					},
					{"data" : function (e) {
						return myJson.getText(e.wordSign, me.boothWordSignEnum);
						},"bSortable" : false
					},
					{"data" : function (e) {
							if (e.wordSign == 0) {// 未导入
								return '<button class="btn btn-sm purple" disabled="disabled">查看 </button>';
							}
							return '<button class="btn btn-sm purple" id="wordContent'+e.boothId+'" onclick="boothHelper.queryWordContent(\''+e.boothId+'\')">查看 </button>';
						},"bSortable" : false
					},
					{"data" : function(e) {
						return '<div style="min-width:110px;"> '+
						'<button type="button" class="btn btn-sm purple" ' + 'onclick="boothHelper.downloadTemplet(\''+e.boothName+'\',\''+e.xCount+'\',\''+e.yCount+'\')">下载模板</button> ' +
						'<button type="button" class="btn btn-sm purple" ' + 'onclick="boothHelper.uploadTemplet(\''+e.boothId+'\',\''+e.boothName+'\',\''+e.xCount+'\',\''+e.yCount+'\',\''+e.wordSign+'\')">上传文字</button>'+
						'<a class="ajaxify btn btn-sm purple" href="${basePath}/booth/toUpdateBooth.do?boothId=' + e.boothId + '">修改展位</a> ' +
						'<button type="button" class="btn btn-sm blue" ' + 'onclick="boothHelper.createQRCode(\'' + e.boothId + '\')">生成二维码</button> ' +	
						'</div>';
					},"bSortable" : false}
				];
			TablePaginationSort.initCustom(url, colArray, "boothTable","filter_form");//最后一个参数formid,此处为空form
		},
        //初始化Table,加载表格
        delBoothTable : function () {
            var url = "${basePath}/booth/getBoothList.do"; //表格数据远程地址
            var colArray = [
                {"data" : function(e) {
                    return '<input type="radio" name="rad1" value="'+e.boothId+'"/>';
                }
                },
                {"data" : "boothName","bSortable" : false},
                {"data" : "exhibitName","bSortable" : false}
            ];
            TablePaginationSort.initCustom(url, colArray, "delBoothTable","del_form");//最后一个参数formid,此处为空form
        },
		//新增展馆
		addBooth : function () {
			$('#body2').load("${basePath}/booth/toAddBooth.do");
		},
        // 删除展位
        delBooth : function () {
            $('#delBoothTable').DataTable().draw();
            $("#delBoothModal").modal('show');
        },
        // 确认删除展位
        delBoothConfirm : function () {
            var boothId = "";
            $("[name='rad1']:checked").each(function() {
                if ($(this).attr('checked')) {
                    boothId = $(this).val();
                }
            });
            if (boothId == '') {
                bootbox.alert('请先选择要删除的展位');
                return;
            }
            $("#delBoothConfirm").attr({"disabled":"disabled"});
            $.ajax({
                url : "${basePath}/booth/deleteBooth.do",
                data: {boothId: boothId},
                dataType : "json",
                type : "POST",
                success : function(data) {
                    $("#delBoothConfirm").removeAttr("disabled");
                    $("#boothTable").DataTable().draw();// 点击确认删除后,重新绘制table
                    $('#delBoothModal').modal('hide');// 隐藏删除展位对应的弹窗
                    bootbox.alert(data.resultMsg);
                }
            });
        },
		// 查看文字导入
		queryWordContent : function(boothId) {
			$("#wordContent"+boothId).attr({"disabled":"disabled"});
			$.ajax({
				url : "${basePath}/booth/queryWordContent.do",
				data : {boothId : boothId},
				dataType: "json",
				type : "POST",
				success : function(data) {
					$("#wordContent"+boothId).removeAttr("disabled");
					if (data.resultCode != '0000') {
	                    bootbox.alert(data.resultMsg);
	                } else {
	                    $("#wordContent").html(data.data);
	        			$("#wordContentModal").modal('show');
	                }
				}
			});
		},
		// 生成二维码
		createQRCode : function(boothId) {
			$.ajax({
				url : "${basePath}/booth/toCreateCode.do",
				data : {boothId : boothId},
				dataType: "json",
				type : "POST",
				success : function(data) {
					if (data.resultCode != '0000') {
	                    bootbox.alert(data.resultMsg);
	                } else {
	                	var html = '<img src="data:image/png;base64,' + data.data + '"/>';
	                    $("#QRCode").html(html);
	        			$("#QRCodeModal").modal('show');
	                }
				}
			});
		},
		// 下载模板
		downloadTemplet : function (boothName, xCount, yCount) {
			if (boothName == null || boothName == '' ||
					xCount == null || xCount <= 0 ||
					yCount == null || yCount <= 0) {
				bootbox.alert("请确保展位名称、行字数、列字数,数据准确");
			}
			$("#dnBoothName").val(boothName);
			$("#dnXCount").val(xCount);
			$("#dnYCount").val(yCount);
			
			$("#down_form").attr('action','${basePath}/booth/downloadTemplet.do');
        	$("#down_form").submit();
		},
		// 上传文字
		uploadTemplet : function (boothId, boothName, xCount, yCount, wordSign) {
			// 为隐藏域赋值
			$("#upBoothId").val(boothId);
			$("#upBoothName").val(boothName);
			$("#upXCount").val(xCount);
			$("#upYCount").val(yCount);
			$("#upWordSign").val(wordSign);
			$("#uploadFormReset").click();
			if (wordSign == 1) {// 已经导入
				bootbox.confirm("该展位已经导入过模板了,重新导入将会覆盖原来导入数据,您确认继续操作吗？", function (result) {
					if (result) {
						$('#importTemplet').modal('show');
					}
				});
			} else {
				$('#importTemplet').modal('show');
			}
		},
		// 上传文件
		uploadFile : function () {
			var me = boothHelper;
			
			var file_id = $("#file_id").val();
			if(file_id == null || file_id == ""){
				bootbox.alert("上传文件不能为空！");
				return;
			}
			var suffix = file_id.substr(file_id.lastIndexOf(".")+1).toLowerCase();
			if( suffix!="xlsx"){
				bootbox.alert("请选择正确的excel类型!");
				return;
			}
			
			var templateCount = $("#templateCount").val();
			if (templateCount == null || templateCount == ""){
				bootbox.alert("模板入库数量不能为空！");
				return;
			}
			
			$.startMaskLayer();
			$("#uploadForm").ajaxSubmit({
				type:"post",
				dataType:"json",
				clearForm: true,
				resetForm: true,
				url:"${basePath}/booth/importTempletNew.do",
				success:function(data){
					$.endMaskLayer();
					if(data.resultCode=='0000'){
						$("#boothTable").DataTable().draw();// 重新绘制展位table
						bootbox.alert(data.resultMsg);
					}else{
						bootbox.alert("导入发生错误,原因如下:<br/>"+data.resultMsg);
					}
					$('#importTemplet').modal('hide');
				}
			});
		},
		// 根据展馆,加载展位
		loadBooth : function() {
			$("#boothIdList option").remove();
            $("#boothIdList").append('<option value="">全部</option>');
            $("#boothIdList").val("").trigger('change');
            
            var params = {};
            var exhibitIdArr = $('#exhibitIdList').val();
            if (exhibitIdArr == null || exhibitIdArr == "") {
            	var options ="[]";			
            	$('#boothIdList').multiselect( 'dataprovider',eval(options));
            	return;
            }
			params.exhibitIdArr = exhibitIdArr.join(",");
			
			$.ajax({
		        url : "${basePath}/booth/getAuthBoothForTag.do",
		        data : params,
		        dataType: "json",
		        type : "POST",
		        success : function(data) {
		            if(data.resultCode =='0000'){
		            	var options ="[";
		            	$.each(data.data, function (index, entity) {
		                    options=options+"{label: '"+entity.boothName+"',title:'"+ entity.boothName+"',value:'"+entity.boothId+"'}";
		                    if(index!=data.data.length-1){
		                        options=options+",";
		                    }
                        });
		                options = options+"]";
		                $('#boothIdList').multiselect( 'dataprovider',eval(options));
		            }
		        }
		    });
		},
		// 查询展馆
		btnRefresh : function() {
			$("#boothTable").DataTable().draw();// 点搜索重新绘制table。
		},
		// 清空筛选条件
		btnEmpty : function() {
			$("input[type=reset]").trigger("click");
			$(".multiselect-container li").removeClass('active');
    		$("select[ multiple='multiple']").multiselect( 'clearSelection');
		},
		//导出明细
		doExport : function(){
			$("#filter_form").attr('action','${basePath}/booth/exportBooth.do');
        	$("#filter_form").submit();
		}
		
	};

	$(function() {
		boothHelper.init();
	});

</script>