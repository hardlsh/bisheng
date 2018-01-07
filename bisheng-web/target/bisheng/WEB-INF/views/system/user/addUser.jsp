<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../basepath.jsp"%>
<ol class="breadcrumb">
  <li><a class="ajaxify" href="${basePath}/user/toUser.do">用户管理</a></li>
  <li class="active">新增用户</li>
</ol>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box grey-cascade">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>用户管理-新增用户
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<div style="padding: 10px;background-color: #f7f7f7">
					<form id='filter_form' href="##" method="post">
						<div class="row" style="margin-bottom: 10px;">
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">用户名<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<input type="text" name="userName" class="form-control" id="userName" placeholder="用户名唯一"/>
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
		                                   value=""
		                                   style="width: 110px;display: block;"/>
									</div>
								</div>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">手机号<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<input type="text" name="phone" class="form-control" id="phone"/>
									</div>
								</div>
							</div>
						</div>
						<div class="row" style="margin-bottom: 10px;">
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">登录展示名称<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<input type="text" name="loginName" class="form-control" id="loginName"/>
									</div>
								</div>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">邮箱</label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<input type="text" name="email" class="form-control" id="email"/>
									</div>
								</div>
							</div>
						</div>
						<div class="row" style="margin-bottom: 10px;">
							<div class="col-md-8 col-sm-8 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-2 col-sm-2 col-xs-12">备注</label>
									<div class="col-md-10 col-sm-10 col-xs-12">
										<textarea name="remark" class="form-control" maxlength="225" rows="2" ></textarea>
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

var addUserHelper = {
		
	init : function () {
		var me = addUserHelper;
		$('#cancel').click(me.cancel);//取消按钮
		
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
	//取消, 返回用户页
	cancel : function () {
		$('#body2').load("${basePath}/user/toUser.do");
	}
};

$(function(){
	addUserHelper.init();
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
	$('#save').attr({"disabled":"disabled"});
	var me = addUserHelper;
	var param = $("#filter_form").serialize();
	$.ajax({
		url : "${basePath}/user/saveUser.do",
		data : param,
		dataType: "json",
		type : "POST",
		success : function(data) {
			$('#save').removeAttr("disabled");
			if (data.resultCode != '0000') {
                bootbox.alert(data.resultMsg);
            } else {
               // $.alert(data.resultMsg);
                bootbox.alert("用户密码默认为：000000，<br/>用户状态默认为'新建',需要'激活'后才能使用！");
				me.cancel();
            }
		}
	});
}

</script>
