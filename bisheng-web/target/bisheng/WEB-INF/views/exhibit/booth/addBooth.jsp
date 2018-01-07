<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../basepath.jsp"%>
        
<ol class="breadcrumb">
  <li><a class="ajaxify" href="${basePath}/booth/toBooth.do">展位管理</a></li>
  <li class="active">新增展位</li>
</ol>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box grey-cascade">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>展位管理-新增展位
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<div style="padding: 10px;background-color: #f7f7f7">
					<form id='filter_form' href="##" method="post">
						<div class="row" style="margin-bottom: 10px;">
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">展位名称<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<input type="text" name="boothName" class="form-control" id="boothName" placeholder="展位名称唯一"/>
									</div>
								</div>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">所属展馆<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<!-- 隐藏域 -->
										<input type="hidden" id="hideExhibitName" name="exhibitName"/> 
										<hz:authExhibitSelect id="exhibitId" name="exhibitId"
		                                   classes="bs-select form-control input-sm input-inline"
		                                   hasAll="true"
		                                   placeholder="选择展馆"
		                                   style="width: 110px;height: 28px;display: block;"/>
									</div>
								</div>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">展位编号<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
									<input id="sequence" name="sequence"
										class="form-control input-inline input-sm" maxlength="15"
										placeholder="请输入数字" type="text"
										onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
									/>
									</div>
								</div>
							</div>
						</div>
						<div class="row" style="margin-bottom: 10px;">
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">x轴字数<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<input type="text" name="xCount" class="form-control" id="xCount"/>
									</div>
								</div>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">y轴字数<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<input type="text" name="yCount" class="form-control" id="yCount"/>
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

var addBoothHelper = {
		
	init : function () {
		var me = addBoothHelper;
		$('#cancel').click(me.cancel);//取消按钮
	},
	//取消, 返回
	cancel : function () {
		$('#body2').load("${basePath}/booth/toBooth.do");
	}
};

$(function(){
	addBoothHelper.init();
});

/* 页面验证必填  */
var FormValidation = function () {
    var filterFromValidation = function () {
        var e = $("#filter_form");
        e.validate({
            errorElement: "span",
            errorClass: "help-block help-block-error", focusInvalid: !1, ignore: "",
            rules: {
            	boothName: {
                    required: true
                },
                exhibitId: {
                	required: true
                },
                sequence: {
                    required: true
                },
                xCount: {
                    required: true
                },
                yCount: {
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
            	saveBooth();
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
function saveBooth() {
	$('#save').attr({"disabled":"disabled"});
	var me = addBoothHelper;
	var exhibitName = $('#exhibitId option:selected').text();// 选中option的文本内容
	$("#hideExhibitName").val(exhibitName);// 为隐藏域赋值
	var param = $("#filter_form").serialize();
	$.ajax({
		url : "${basePath}/booth/addSaveBooth.do",
		data : param,
		dataType: "json",
		type : "POST",
		success : function(data) {
			$('#save').removeAttr("disabled");
			
			if (data.resultCode != '0000') {
                bootbox.alert(data.resultMsg);
            } else {
                $.alert(data.resultMsg);
				me.cancel();
            }
		}
	});
}

</script>
