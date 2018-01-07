<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../basepath.jsp"%>
<ol class="breadcrumb">
  <li><a class="ajaxify" href="${basePath}/user/toUser.do">用户管理</a></li>
  <li class="active">修改用户</li>
</ol>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box grey-cascade">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>用户管理-修改用户
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
				<div class="with-border">
					<div class="row">
						<div class="col-md-6">
							<h4>修改展馆权限</h4>
						</div>
						<div class="col-md-6" align="right">
							<h4>
							<a href="javascript:;" id="searchExhibitMore" data-toggle="collapse" data-target="#exhibitMore" onclick="javascript:{var t=$(this).text(); if(t=='展开'){ $(this).text('收起');}else{$(this).text('展开');}}">展开</a>
							</h4>
						</div>
					</div>
					<hr>
				</div>
				<div id="exhibitMore" class="collapse" style="padding: 10px;background-color: #f7f7f7">
					<table class="table table-striped table-bordered table-hover table-condensed"
						id="exhibitTable">
						<thead>
							<tr>
								<th><input id="exhibitCheckAll" type="checkbox" name="exhibitCheckBox"></th>
								<th>展馆id</th>
								<th>展馆名称</th>
								<th>展馆code</th>
								<th>地区名称</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
				<div class="table-toolbar">
					<div style="padding: 10px;background-color: #f7f7f7">
					<form id='filter_form' href="##" method="post">
						<!-- 隐藏域 -->
						<input type="hidden" name="userId" value="${user.userId }">
						<input type="hidden" id="exhibitIdArr" name="exhibitIdArr">
						<div class="row" style="margin-bottom: 10px;">
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">用户名<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<input type="text" name="userName" class="form-control" id="userName" 
											value="${user.userName }" placeholder="用户名唯一"/>
									</div>
								</div>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">用户角色<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<hz:authRoleSelect id="roleIdList" name="roleIdList"
										   dictKey=""
		                                   classes="multiselect form-control"
		                                   multiple="multiple"
		                                   hasAll="true"
		                                    value="${user.roleIdStr}"
		                                   style="width: 110px;display: block;"/>
									</div>
								</div>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">手机号<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<input type="text" name="phone" class="form-control" id="phone" value="${user.phone }"/>
									</div>
								</div>
							</div>
						</div>
						<div class="row" style="margin-bottom: 10px;">
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">登录展示名称<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<input type="text" name="loginName" class="form-control" id="loginName" value="${user.loginName }"/>
									</div>
								</div>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">邮箱</label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<input type="text" name="email" class="form-control" id="email" value="${user.email }"/>
									</div>
								</div>
							</div>
						</div>
						<div class="row" style="margin-bottom: 10px;">
							<div class="col-md-8 col-sm-8 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-2 col-sm-2 col-xs-12">备注</label>
									<div class="col-md-10 col-sm-10 col-xs-12">
										<textarea name="remark" class="form-control" maxlength="225" rows="2" >${user.remark}</textarea>
									</div>
								</div>
							</div>
						</div>
							<div class="row">
							<div class="col-md-8 col-sm-8 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-2 col-sm-2 col-xs-12"></label>
									<div class="col-md-10 col-sm-10 col-xs-12">
										<!-- 只有使用submit提交,才会触发form的表单验证 -->
										<button type="submit" class="btn blue">保  存</button>
										<button type="button" id="cancel" class="btn blue">取  消</button>
									</div>
								</div>
							</div>
						</div>
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var updateUserHelper = {
		exhibitIdArr : [],//勾选的资金方产品
		
		init : function () {
			var me = updateUserHelper; 
			
			$('#exhibitCheckAll').click(me.exhibitCheckAllFunc);//展馆表里的全选按钮
			$("#cancel").click(me.cancel);// 保存按钮
			
			//初始化Table
			me.exhibitTable();
			
			//初始化下拉复选框
			$('.multiselect').multiselect({
                enableFiltering: true,
                nonSelectedText: '无选择',
                allSelectedText: "共选择",
                selectAllText: '全选',
                nSelectedText: "选择",
                buttonWidth: "100%",
                maxHeight: '300',
                buttonClass: 'btn btn-default input-sm',
                includeSelectAllOption: true
            });
		},
		//动态生成表格   展馆
		exhibitTable : function () {
			var url = "${basePath}/user/queryAllExhibitList.do"; //表格数据远程地址
			var colArray = [
					{"data" : function(e) {
							if (e.exhibitSel) {
								return '<input type="checkbox" class="checkboxes" checked="checked" name="chb1" data-id="'
								+ e.exhibitId + '"/>';
							}else {
								return '<input type="checkbox" class="checkboxes" name="chb1" data-id="'
								+ e.exhibitId + '"/>';
							}
						},
						"bSortable" : false
					},
					{"data" : "exhibitId",
						"bSortable" : false
					},
					{"data" : "exhibitName",
						"bSortable" : false
					},
					{"data" : "exhibitCode",
						"bSortable" : false
					},
					{"data" : "areaName",
						"bSortable" : false
					}
			];
			TablePaginationSort.initCustom(url, colArray, "exhibitTable",
			"user_form");
		},
		//展馆表上的全选
		exhibitCheckAllFunc : function() {
			var checked = '';
			if ($('#exhibitCheckAll').is(':checked')) {
				checked = 'checked';
			}
			$('[name="chb1"]').each(function(index, entity) {
				$(entity).prop('checked', checked);
			});
		},
		//取消
		cancel : function () {
			$('#body2').load("${basePath}/user/toUser.do");
		}
	};
	
	$(function() {
		updateUserHelper.init();
	});
	
	/* 页面验证必填  */
	var FormValidation = function () {
	    var filterFromValidation = function () {
	        var e = $("#filter_form");
	        e.validate({
	            errorElement: "span",
	            errorClass: "help-block help-block-error", focusInvalid: !1, ignore: "",
	            rules: {
	            	userName: {
	                    required: true
	                },
	                roleIdList: {
	                	required: true
	                },
	                phone: {
	                    required: true
	                },
	                loginName: {
	                    required: true
	                }
	            },
	            highlight: function (e) {
	                $(e).closest(".form-group").removeClass("has-success").addClass("has-error")
	            },
	            unhighlight: function (e) {
	                $(e).closest(".form-group").removeClass("has-error")
	            }, success: function (e, r) {
	                var i = $(r).parent(".input-icon").children("i");
	                $(r).closest(".form-group").removeClass("has-error").addClass("has-success"),
	                        i.removeClass("fa-warning").addClass("fa-check")
	            },
	            submitHandler: function (e) {
	            	saveUser();
	            }
	        })
	    };
	    return {
	        init: function () {
	        	filterFromValidation();
	        }
	    }
	}();
	jQuery(document).ready(function () {
	    FormValidation.init()
	});
	
	//保存
	function saveUser() {
		var me = updateUserHelper; 
		me.exhibitIdArr.length = 0;//先清空数组,再往里添加
		$('[name="chb1"]').each(function(index, entity) {
			if ($(entity).is(':checked')) {
				me.exhibitIdArr.push($(entity).data('id'));
			}
		});
		if (me.exhibitIdArr.length == 0) {
			bootbox.alert('请先选择要授权的展馆');
			return;
		}
		var roleIdList = $("#roleIdList").val();
		if (roleIdList == null || roleIdList == "") {
			bootbox.alert('请先选择要授权的角色');
			return;
		}
	
		//为隐藏域赋值
		$("#exhibitIdArr").val(me.exhibitIdArr);
		
		var userForm = $("#filter_form").serialize();
		
		$.ajax({
			url : "${basePath}/user/updateUser.do?",
			data : userForm,
			dataType: "json",
			type : "POST",
			success : function(data) {
				if(data.resultCode =='0000'){
					$.alert(data.resultMsg);
					me.cancel();
				}else{
					bootbox.alert(data.resultMsg);
				}
			}
		});
	}

</script>
