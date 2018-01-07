<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../basepath.jsp"%>

<link rel="stylesheet" type="text/css" href="${basePath}/resources/plugins/bootstrap-tree/css/style.min.css"/>
<script src="${basePath}/resources/plugins/bootstrap-tree/jstree.min.js"></script>
<ol class="breadcrumb">
  <li><a class="ajaxify" href="${basePath}/role/toRole.do">角色管理</a></li>
  <li class="active">角色权限管理</li>
</ol>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box grey-cascade">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>权限分配
				</div>
				<div class="actions">
					<button id="saveRole" class="btn btn-sm blue" type="button">保存</button>
				</div>
			</div>
			<div class="portlet-body">
				<div id="roleTree" class="tree-demo">
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">

var updateRoleHelper = {
	resourceIds : [],// 修改角色时勾选的资源id
	
	init : function () {
		var me = updateRoleHelper;
		
		$('#saveRole').click(me.saveRole);// 保存按钮
	},
	// 保存角色
	saveRole : function () {
		var me = updateRoleHelper;
		var $nodes = $('#roleTree').jstree().get_checked(true); //获取所有选中的节点对象
		var parentNode = "";// 父节点
		var sonNode = "";// 子节点
		for (var i=0; i<$nodes.length; i++) {
			sonNode = $nodes[i].id;
			me.resourceIds.push(sonNode);
			if ($('#roleTree').jstree().get_parent(sonNode) != "#") {// 一级菜单的父节点不要
				parentNode += $('#roleTree').jstree().get_parent(sonNode)+",";
			}
		}
		// 父节点去重
		var parentArr = parentNode.split(",");
		var arr = [];// 临时数组
		for (var i = 0; i < parentArr.length; i++) {
			if (arr.indexOf(parentArr[i]) == -1) {
				arr.push(parentArr[i]);
			}
		}
		var resourceIdStr = me.resourceIds.join(",") +"," + arr.join(",");
		
		var param = {
				resourceIdStr : resourceIdStr,
				roleId : '${roleId}'
				};
		$.ajax({
			url : "${basePath}/role/updateRoleResource.do",
			data : param,
			dataType: "json",
			type : "POST",
			success : function(data) {
				if(data.resultCode != '0000'){
					bootbox.alert(data.resultMsg);
				}else {
					$.alert(data.resultMsg);
					$('#body2').load("${basePath}/role/toRole.do.do");//操作成功, 返回角色管理页
				}
			}
		});
	}
};

$(function () {
	updateRoleHelper.init();
	
	var data = JSON.parse('${nodes}'); 
	$('#roleTree').jstree({
	    'plugins': ["wholerow", "checkbox", "types"],
	    'core': {
	        "themes" : {
	            "responsive": false
	        },    
	        'data': data
	    },
	    "types" : {
	        "default" : {
	            "icon" : "fa fa-folder icon-state-warning icon-lg"
	        },
	        "file" : {
	            "icon" : "fa fa-file icon-state-warning icon-lg"
	        }
	    }
	});
	
});

</script>