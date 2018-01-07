<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../basepath.jsp"%>
<ol class="breadcrumb">
  <li class="active">角色管理</li>
</ol>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box grey-cascade">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>角色管理
				</div>
				<div class="actions">
					<button id="addRole" class="btn btn-sm blue" type="button">新增角色</button>
				</div>
			</div>
			<div class="portlet-body">
				<form id='filter_form' href="##"><!-- 空form -->
				</form>
				<table class="table table-striped table-bordered table-hover" id="roleTable">
					<thead>
						<tr>
							<th>角色id</th>
							<th>角色名称</th>
							<th>角色状态</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>

<!-- add modal content -->
<!-- 新增角色  弹窗 -->
<div class="modal" id="addRoleModal" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">新增角色</h4>
			</div>
			<div class="modal-body">
				<div class="table-toolbar" style="padding: 10px;">
				<form id='addRole_form' href="##">
					<div class="form-group">
						<div>
						角色名称: 
							<input type="text" id="addRoleName" name="roleName" class="form-control input-inline">
						</div>
					</div>
					<div class="form-group">
						角色描述:
							<textarea name="remark" class="form-control input-inline" maxlength="225" rows="2" 
							style="width:80%;overflow:auto;resize:none;" ></textarea>
					</div>	
				</form>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="addSave" class="btn default">保  存</button>
				<button type="button" class="btn default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<!-- 修改角色  弹窗 -->
<div class="modal" id="updateRoleModal" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">修改角色和状态</h4>
			</div>
			<div class="modal-body">
				<div class="table-toolbar" style="padding: 10px;">
				<form id='updateRole_form' href="##">
					<!-- 隐藏域,存储roleId-->
					<input type="hidden" name="roleId" id="updateRoleId"> 
					<div class="form-group input-inline">
						角色名称:
							<input type="text"  class="form-control input-sm input-inline input-sm" 
							name="roleName" id="updateRoleName" size="12">
					</div>
					<div class="form-group input-inline">
						角色备注: 
							<input type="text" class="form-control input-sm input-inline input-sm" 
							name="remark" id="updateRemark" size="50%">
					</div>
					<div class="form-group input-inline">
						角色状态: 
							<hz:multipleSelect name="status" id="updateStatus"  dictCode="AVAIL_STATUS" value=""  
								classes="bs-select form-control input-sm input-inline" hasAll="true" placeholder="请选择"/>
					</div>
				</form>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="updateSave" class="btn default">保  存</button>
				<button type="button" class="btn default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">

	var roleHelper = {
		availStatusEnum : '${availStatusEnum}',
		
		init : function () {
			var me = roleHelper;
			
			$('#addRole').click(me.addRole);//新增角色按钮
			$('#addSave').click(me.addSave);//新增角色保存按钮
			$('#updateSave').click(me.updateSave);//修改角色保存按钮
			
			//初始化角色Table
			me.roleTable();
		},
		//初始化角色Table,加载表格
		roleTable : function () {
			var me = roleHelper;
			
			var url = "${basePath}/role/getRoleList.do"; //表格数据远程地址
			var colArray = [
					{"data" : "roleId","bSortable" : false},
					{"data" : "roleName","bSortable" : false},
					{"data" : function (e) {
						return myJson.getText(e.status,
								me.availStatusEnum);
					},"bSortable" : false},
					{"data" : function(e) {
						return'<div style="min-width:110px;"><a class="ajaxify btn default btn-xs purple" href="${basePath}/role/toUpdateRole.do?roleId='+e.roleId+'">修改权限</a>'
						+'<button class="btn default btn-xs purple" onclick="roleHelper.updateRole(\'' + e.roleId + '\');">修改角色</button></div>';
					},"bSortable" : false}
				];
			TablePaginationSort.initCustom(url, colArray, "roleTable","filter_form");//最后一个参数formid,此处为空form
		},
		//新增用户
		addRole : function () {
			$("#addRole_form")[0].reset();
			$('#addRoleModal').modal('show');// 显示增加角色对应的弹窗
		},
		// 新增用户  保存
		addSave : function () {
			var addRoleName = $('#addRoleName').val();
			if (addRoleName == "") {
				bootbox.alert("角色名称不能为空!");
				return;
			}
			$("#addSave").attr({"disabled":"disabled"});
			var param = $("#addRole_form").serialize();
			$.ajax({
				url : "${basePath}/role/addRole.do",
				data : param,
				dataType: "json",
				type : "POST",
				success : function(data) {
					$("#addSave").removeAttr("disabled");
					
					if (data.resultCode != '0000') {
	                    bootbox.alert(data.resultMsg);
	                } else {
	                    $.alert(data.resultMsg);
	                    $("#addRole_form")[0].reset();
	                    $("#roleTable").DataTable().draw();// 重新绘制table
	                    $('#addRoleModal').modal('hide');
	                }
				}
			});
		},
		//修改角色  显示弹窗
		updateRole : function (roleId) {
			$("#updateRoleId").attr("value", roleId);
			
			$.ajax({
				url : "${basePath}/role/queryRoleById.do",
				data : {
					roleId : roleId
				},
				dataType: "json",
				type : "POST",
				success : function(data) {
					if(data.resultCode !='0000'){
						bootbox.alert(data.resultMsg);
					}else{
						$("#updateRole_form")[0].reset();
						$("#updateRoleName").attr("value", data.data.roleName);
						$("#updateRemark").attr("value", data.data.remark);
						$('#updateStatus').attr("value", data.data.status);
						$('#updateRoleModal').modal('show');// 显示修改角色对应的弹窗
					}
				}
			});
		},
		//修改角色  保存
		updateSave : function () {
			var updateRoleName = $('#updateRoleName').val();
			var updateStatus = $('#updateStatus').val();
			if (updateRoleName == "" || updateStatus =="") {
				bootbox.alert("角色名称,角色状态不能为空!");				
				return;
			}
			
			$("#updateSave").attr({"disabled":"disabled"});
			var param = $("#updateRole_form").serialize();
			$.ajax({
				url : "${basePath}/role/updateRole.do",
				data : param,
				dataType: "json",
				type : "POST",
				success : function(data) {
					$("#updateSave").removeAttr("disabled");
					
					if (data.resultCode != '0000') {
	                    bootbox.alert(data.resultMsg);
	                } else {
	                    $.alert(data.resultMsg);
	                    $("#updateRole_form")[0].reset();
	                    $("#roleTable").DataTable().draw();// 重新绘制table
	                    $('#updateRoleModal').modal('hide');
	                }
				}
			});
			
		}
	};


	$(function() {
		roleHelper.init();
	});

</script>