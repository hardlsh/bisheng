<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../basepath.jsp"%>
<ol class="breadcrumb">
  <li><a class="ajaxify" href="${basePath}/resource/toResource.do">资源管理</a></li>
  <li class="active">新增资源</li>
</ol>
<div class="row col-md-12">
	<!-- BEGIN FORM-->
	<form id="filter_form" action="###" class="form-horizontal">
		<div class="form-group">
			<label class="control-label col-md-3">资源名称</label> 
			<div class="col-md-5">
				<input type="text" name="resourceName" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">资源类型</label>
			<div class="col-md-9">
				<div class="checkbox-list">
				<hz:multipleSelect name="resourceType" id="resourceType"  dictCode="RESOURCE_TYPE" value=""  
				classes="bs-select form-control input-sm input-inline" hasAll="true" placeholder="请选择"/>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">上级资源</label>
			<div class="col-md-9">
				<div class="checkbox-list">
	                <select class="bs-select form-control input-inline input-sm" 
	                	id="parentId" name="parentId"> <option value="">请选择</option>
					</select>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">资源地址</label> 
			<div class="col-md-9">
				<input id="resourcePath" name="resourcePath"  class="form-control input-sm input-inline" size="12"/>
			</div>	
		</div>	
		<div class="form-group">
			<label class="control-label col-md-3">备注</label> 
			<div class="col-md-9">
				<input id="remark" name="remark" class="form-control input-sm input-inline" size="12"/>
			</div>	
		</div>
		<div class="form-group">
			<label class="control-label col-md-3"></label> 
			<div class="col-md-9">
				<button type="button" id="save" class="btn blue">保  存</button>
				<button type="button" id="cancel" class="btn btn-default ">取  消</button>
			</div>	
		</div>
	</form>
	<!-- END FORM-->
</div>

<script type="text/javascript">
	var addResourceHelper = {
		init : function (){
			var me = addResourceHelper;
			$('#resourceType').change(me.resourceTypeChange);// 资源类型
			$('#save').click(me.save);// 保存按钮
			$('#cancel').click(me.cancel);// 取消按钮
		},
		// 资源类型
		resourceTypeChange : function () {
			var resourceType = $("#resourceType").val();
			if (resourceType == null || resourceType == "") {
				bootbox.alert("请选择资源类型");
				return;
			}
			$("#parentId option").remove();
			$("#parentId").append('<option value="">请选择上级资源</option>');
			if (String(resourceType) != "") {
				$.ajax({
					url : "${basePath}/resource/getResourceByType.do",
					data : {
						resourceType : resourceType
					},
					dataType : "json",
					type : "POST",
					success : function(data) {
						if (data.resultCode != '0000') {
							bootbox.alert(data.resultMsg);
						} else {
							if (data.data.length > 0) {
								$.each(data.data, function(index, entity) {
									$("#parentId").append(
											'<option value="' + entity.resourceId + '">'
													+ entity.resourceName
													+ '</option>');//赋值
								});
							}
						}
					}
				});
			}
		},
		//保存按钮
		save : function () {
			var me = addResourceHelper;
			var parentId = $('#parentId').val();
			var resourcePath = $('#resourcePath').val();
			var param = $('#filter_form').serialize();
			$.ajax({
				url : "${basePath}/resource/addResource.do",
				data : param,
				dataType : "json",
				type : "POST",
				success : function (data) {
					if(data.resultCode =='0000'){
						$('#cancel').click();
					}
					bootbox.alert(data.resultMsg);
				}
			});
		},
		// 取消按钮
		cancel : function () {
			$('#body2').load("${basePath}/resource/toResource.do");
		}
		
	}
	$(function () {
		addResourceHelper.init();
	});

</script>
