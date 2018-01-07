<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../basepath.jsp"%>
<ol class="breadcrumb">
	<li class="active">用户管理</li>
</ol>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box grey-cascade">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>用户管理
				</div>
				<div class="actions">
					<button id="addUser" class="btn btn-sm blue" type="button">新增用户</button>
				</div>
			</div>
			<div class="portlet-body">
				<form id='filter_form' href="##"><!-- 空form --></form>
				<table class="table table-striped table-bordered table-hover"
					id="userTable">
					<thead>
						<tr>
							<th>用户ID</th>
							<th>用户名</th>
							<th>登录名称</th>
							<th>展馆权限</th>
							<th>角色权限</th>
							<th>用户状态</th>
							<th>邮箱</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>

<!-- add modal content -->
<!-- 查看展馆权限  弹窗 -->
<div class="modal" id="exhibitModal" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">展馆权限</h4>
			</div>
			<div class="modal-body">
				<form id='exhibit_form' href="##">
					<!-- 隐藏域,存储userId-->
					<input type="hidden" name="userId" id="exhibitUserId"> 
				</form>
				<table class="table table-striped table-bordered table-hover"
					id="exhibitTable">
					<thead>
						<tr>
							<th>展馆Id</th>
							<th>展馆名称</th>
							<th>地区名称</th>
							<th>展馆地址</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>

<!-- add modal content -->
<!-- 查看角色权限  弹窗 -->
<div class="modal" id="roleModal" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">角色权限</h4>
			</div>
			<div class="modal-body">
				<form id='role_form' href="##">
					<!-- 隐藏域,存储userId-->
					<input type="hidden" name="userId" id="roleUserId"> 
				</form>
				<table class="table table-striped table-bordered table-hover"
					id="roleTable">
					<thead>
						<tr>
							<th>角色id</th>
							<th>角色名称</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<!-- 修改密码和状态  弹窗 -->
<div class="modal" id="resetPwdModal" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">修改密码和用户状态</h4>
			</div>
			<div class="modal-body">
			<div class="table-toolbar" style="padding: 10px;">
				<form id='resetPwd_form' href="##">
					<!-- 隐藏域,存储userId-->
					<input type="hidden" name="userId" id="resetUserId"> 
					<div class="form-group">
						<div>
						新密码:
							<input type="text" name="password" class="form-control input-inline" id="password">
						</div>
					</div>
					<div class="form-group">
						<div>
						确认新密码:
							<input type="text" class="form-control input-inline" id="confirmPwd">
						</div>
					</div>
					<div class="form-group">
						<div>
						用户状态:
							<hz:multipleSelect name="status" id="status"  dictCode="USER_STATUS" value=""  classes="bs-select form-control input-sm input-inline "
										  hasAll="true" placeholder="请选择"/>
						</div>
					</div>
				</form>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="resetPwdSave" class="btn default">保  存</button>
				<button type="button" class="btn default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var userHelper = {
		userStatusEnum : '${userStatusEnum}',
		
		init : function () {
			var me = userHelper;
			
			$('#addUser').click(me.addUser);//新增用户按钮
			$('#resetPwdSave').click(me.resetPwdSave);//修改密码和状态页面   保存按钮
			
			//初始化用户Table
			me.userTable();
			//初始化角色table
			me.roleTable();
			// 初始化展馆Table
			me.exhibitTable();
		},
		//初始化用户Table,加载表格
		userTable : function () {
			var me = userHelper;
			var url = "${basePath}/user/getUserList.do"; //表格数据远程地址
			var colArray = [
				{"data" : "userId",
					"bSortable" : false},
				{"data" : "userName",
						"bSortable" : false},
				{"data" : "loginName",
							"bSortable" : false},
				{"data" : function (e) {
					return '<button class="btn btn-sm purple" onclick="userHelper.queryExhibitList(\'' + e.userId + '\')">查看 </button>';
				},
					"bSortable" : false},
				{"data" : function (e) {
					return '<button class="btn btn-sm purple" onclick="userHelper.queryRoleList(\'' + e.userId + '\')">查看 </button>';
				},
					"bSortable" : false},
				{"data" : function (e) {
					return myJson.getText(e.status, me.userStatusEnum);
				},
					"bSortable" : false},
				{"data" : "email",
					"bSortable" : false},
				{"data" : function(e) {
					return'<div style="min-width:110px;"><a class="ajaxify btn default btn-xs purple " href="${basePath}/user/toUpdateUser.do?userId='+e.userId+'">修改</a>'
							+'<button class="btn default btn-xs purple" onclick="userHelper.resetPwd(\'' + e.userId + '\',\''+e.status+'\');">重置密码</button></div>';
				},
					"bSortable" : false}
			];
			TablePaginationSort.initCustom(url, colArray, "userTable", "filter_form");//最后一个参数为form的Id,此处为空form
		},
		//查看  弹出展馆数据
		queryExhibitList : function (userId) {
			//form 表单下的userid
			$("#exhibitUserId").attr("value",userId);//form 表单下的userid
			$("#exhibitTable").DataTable().draw();// 点搜索重新绘制table。
			$('#exhibitModal').modal('show');// 显示查看对应的弹窗
		},
		//查看  弹出角色数据权限列表信息
		queryRoleList : function (userId) {
			//form 表单下的userid
			$("#roleUserId").attr("value",userId);
			
			$("#roleTable").DataTable().draw();// 点搜索重新绘制table。
			$('#roleModal').modal('show');// 显示查看对应的弹窗
		},
		//动态生成表格,查看拥有权限的展馆列表信息
		exhibitTable : function (userId) {
			var url = "${basePath}/user/queryExhibitList.do";
			var colArray = [
					{"data" : "exhibitId", "bSortable" : false}, 
					{"data" : "exhibitName", "bSortable" : false}, 
					{"data" : "areaName", "bSortable" : false}, 
					{"data" : "address", "bSortable" : false}];
			TablePaginationSort.initCustom(url, colArray, "exhibitTable", "exhibit_form");
		},
		//动态生成表格,查询拥有权限的角色列表信息
		roleTable : function (userId) {
			var url = "${basePath}/user/queryRoleList.do";
			var colArray = [
					{"data" : "roleId", "bSortable" : false}, 
					{"data" : "roleName", "bSortable" : false}];
			TablePaginationSort.initCustom(url, colArray, "roleTable", "role_form");
		},
		//新增用户
		addUser : function () {
			$('#body2').load("${basePath}/user/addUser.do");
		},
		//修改密码和状态
		resetPwd : function (userId, status) {
			$("#resetPwd_form")[0].reset();// form表单重置
			$("#resetUserId").attr("value",userId);
			$("#status").attr("value",status);
			$('#resetPwdModal').modal('show');// 显示查看对应的弹窗
		},
		//修改密码和状态   保存
		resetPwdSave : function () {
			var password = $('#password').val();
			var confirmPwd = $('#confirmPwd').val();
			var status = $('#status').val();
			if (status == "") {
				bootbox.alert("修改状态为必填项,不能为空!");
				return;
			}
			if (password != confirmPwd) {
				bootbox.alert("两次密码输入不一致!");
				return;
			}
			
			$("#resetPwdSave").attr({"disabled":"disabled"});
			
			var param = $("#resetPwd_form").serialize();
			$.ajax({
				url : "${basePath}/user/resetPwd.do",
				data : param,
				dataType: "json",
				type : "POST",
				success : function(data) {
					$("#resetPwdSave").removeAttr("disabled");
					if (data.resultCode != '0000') {
	                    bootbox.alert(data.resultMsg);
	                } else {
	                    $.alert(data.resultMsg);
	                    $("#resetPwd_form")[0].reset();
	                    $("#userTable").DataTable().draw();//重新绘制table
	                    $('#resetPwdModal').modal('hide');
	                }
				}
			});
		}
		
	};
	
	
	$(function() {
		userHelper.init();
	});
	
</script>