<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../basepath.jsp"%>

<link rel="stylesheet" type="text/css" href="${basePath}/resources/plugins/bootstrap-tree/css/style.min.css"/>
<script src="${basePath}/resources/plugins/bootstrap-tree/jstree.min.js"></script>
<ol class="breadcrumb">
  <li><a class="ajaxify" href="${basePath}/resource/toResource.do">资源管理</a></li>
  <li class="active">资源管理</li>
</ol>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box grey-cascade">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>资源列表
				</div>
				<div class="actions">
					<button id="addResource" class="btn btn-sm blue" type="button">新增资源</button>
				</div>
			</div>
			<div class="portlet-body" style="float:left;">
				<div id="resourceTree" class="tree-demo" style="width:400px; float:left; display:inline" >
				</div>
				<div id="contextText" class="tree-demo" style="width:600px; float:left; display:inline" >
				
				</div>
				
			</div>
			
			
		</div>
	</div>
</div>

<script type="text/javascript">
	var resourceHelper = {
		resourceTypeEnum : '${resourceTypeEnum}',
		availStatusEnum : '${availStatusEnum}',
		
		init : function () {
			var me = resourceHelper;
			$('#addResource').click(me.addResource);// 新增资源按钮
			
		},
		// 新增资源
		addResource : function () {
			$('#body2').load("${basePath}/resource/toAddResource.do");
		},
		// 删除资源
		doDel : function () {
			var resourceId = $("#resourceId").val();
			$("#doDel").attr({"disabled":"disabled"});
			$.ajax({
				url : "${basePath}/resource/delResource.do",
				data : { resourceId : resourceId },
				dataType : "json",
				type : "POST",
				success : function(data) {
					bootbox.alert(data.resultMsg);
					$("#doDel").removeAttr("disabled");
					
					$('#body2').load("${basePath}/resource/toResource.do");
				}
			});
		}
	};
	
	$(function() {
		resourceHelper.init();
		
		//为jsTree节点绑定单击事件    双击事件,苹果电脑不支持
		$("#resourceTree").bind("click", function (e, data){
			var resourceId = $(e.target).parents('li').attr('id');
			$.ajax({
				url : "${basePath}/resource/getResourceById.do",
				data : {resourceId : resourceId},
				dataType : "json",
				type : "POST",
				success : function (data) {
					if(data.resultCode!='0000') {
						bootbox.alert(data.resultMsg);
					} else {
						var resource = data.data;
						var tableT = '<table class="table table-striped table-bordered table-hover table-condensed">'+
						'<tr>'+
						'<th>操作</th>'+
						'<th>id</th>'+
						'<th>资源名称</th>'+
						'<th>资源类型</th>'+
						'<th>parentId</th>'+
						'<th>资源地址</th>'+
						'<th>级别</th>'+
						'<th>序号</th>'+
						'<th>状态</th>'+
						'<th>备注</th>'+
						'</tr>'+
						'<tr>'+
						'<td>'+
						'<a class="ajaxify btn default btn-xs purple" href="${basePath}/resource/toUpdateResource.do?resourceId='+resource.resourceId+'">修改</a>'+
						'</td>'+
						'<td>'+ resource.resourceId +'</td>'+
						'<td>'+ resource.resourceName +'</td>'+
						'<td>'+ myJson.getText(resource.resourceType, resourceHelper.resourceTypeEnum) +'</td>'+
						'<td>'+ resource.parentId +'</td>'+
						'<td>'+ resource.resourcePath +'</td>'+
						'<td>'+ resource.level +'</td>'+
						'<td>'+ resource.sequence +'</td>'+
						'<td>'+ myJson.getText(resource.status, resourceHelper.availStatusEnum) +'</td>'+
						'<td>'+ resource.remark +'</td>'+
						'</tr>'+
						'</table>'+
						'<input type="hidden" name="resourceId" id="resourceId" value="'+ resource.resourceId +'">'	
						;
						$("#contextText").html(tableT);
						$("#doDel").click(resourceHelper.doDel);
					}
				}
			});
			
		});
		
		var data = JSON.parse('${nodes}'); 
		$('#resourceTree').jstree({
		    'plugins': ["wholerow", "types"],
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