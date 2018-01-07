<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../basepath.jsp"%>
<script src="${basePath}/resources/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"
        type="text/javascript"></script>
<script src="${basePath}/resources/plugins/bootstrap-timepicker/js/bootstrap-timepicker.js"
        type="text/javascript"></script>
        
<ol class="breadcrumb">
  <li><a class="ajaxify" href="${basePath}/exhibit/toExhibit.do">展馆管理</a></li>
  <li class="active">新增展馆</li>
</ol>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box grey-cascade">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>展馆管理-新增展馆
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<div style="padding: 10px;background-color: #f7f7f7">
					<form id='filter_form' href="##" method="post">
						<div class="row" style="margin-bottom: 10px;">
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">展馆名称<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<input type="text" name="exhibitName" class="form-control" id="exhibitName" placeholder="展馆名称唯一"/>
									</div>
								</div>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">展馆code<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<input type="text" name="exhibitCode" class="form-control" id="exhibitCode" placeholder="展馆code唯一"/>
									</div>
								</div>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">联系方式<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<input type="text" name="phone" class="form-control" id="phone"/>
									</div>
								</div>
							</div>
						</div>	
						<div class="row" style="margin-bottom: 10px;">
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">所在省份<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
		                                <select class="bs-select form-control input-inline input-sm" id="provinceId">
											<option value="">请选择省份</option>
											<c:forEach items="${provinces}" var="item">
												<option value="${item.areaId}">${item.areaName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">所在城市<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<!-- 隐藏域 -->
										<input type="hidden" id="hideAreaId" name="areaId"/> 
										<input type="hidden" id="hideAreaName" name="areaName"/> 
										<select class="bs-select form-control input-inline input-sm"
											id="cityId" name="">
											<option value="">请选择</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="row" style="margin-bottom: 10px;">
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">展览开始时间<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<input type="text" name="startTime" class="form-control" id="startTime"/>
									</div>
								</div>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12">展览结束时间<span class="required"> * </span></label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<input type="text" name="endTime" class="form-control" id="endTime"/>
									</div>
								</div>
							</div>
						</div>
						<div class="row" style="margin-bottom: 10px;">
							<div class="col-md-8 col-sm-8 col-xs-12">
								<div class="form-group">
									<label class="control-label col-md-2 col-sm-2 col-xs-12">展馆地址<span class="required"> * </span></label>
									<div class="col-md-10 col-sm-10 col-xs-12">
										<input type="text" name="address" class="form-control" id="address"/>
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
var addExhibitHelper = {
		
	init : function () {
		var me = addExhibitHelper;
		$('#cancel').click(me.cancel);//取消按钮
		$('#provinceId').change(function(){// 省级地区选择框
			me.provinceChange();
		});
		
	},
	//取消, 返回
	cancel : function () {
		$('#body2').load("${basePath}/exhibit/toExhibit.do");
	},
	// 省级地区下拉选择框
	provinceChange : function () {
		var parentId = $("#provinceId").val();
		$("#cityId option").remove();
		$("#cityId").append('<option value="">请选择城市</option>');
		if (String(parentId) != "") {
			$.ajax({
				url : "${basePath}/area/queryCityArea.do",
				data : {
					parentId : parentId
				},
				dataType : "json",
				type : "POST",
				success : function(data) {
					if (data.resultCode != '0000') {
						bootbox.alert(data.resultMsg);
					} else {
						if (data.data.length > 0) {
							$.each(data.data, function(index, entity) {
								$("#cityId").append(
										'<option value="' + entity.areaId + '" data-id="' + entity.areaId + '" data-name="'+ entity.areaName +'">'
												+ entity.areaName
												+ '</option>');//赋值
							});
						}
					}
				}
			});
		}
	}
};

$(function(){
	addExhibitHelper.init();
	// 设置默认时间
	$("#startTime").val("09:00:00");
	$("#endTime").val("18:00:00");
});

/* 时间控件初始化 */
$('#startTime').timepicker({
        minuteStep: 30,
        template: 'modal',
        appendWidgetTo: 'body',
        showSeconds: true,
        showMeridian: false,
        showMeridian: false
    });
$('#endTime').timepicker({
    minuteStep: 30,
    template: 'modal',
    appendWidgetTo: 'body',
    showSeconds: true,
    showMeridian: false,
    showMeridian: false
});

/* 页面验证必填  */
var FormValidation = function () {
    var filterFromValidation = function () {
        var e = $("#filter_form");
        e.validate({
            errorElement: "span",
            errorClass: "help-block help-block-error", focusInvalid: !1, ignore: "",
            rules: {
            	exhibitName: {
                    required: true
                },
                exhibitCode: {
                    required: true
                },
                cityId: {
                    required: true
                },
                address: {
                    required: true
                },
                phone: {
                    required: true
                },
                startTime: {
                    required: true
                },
                endTime: {
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
            	saveExhibit();
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
function saveExhibit() {
	$('#save').attr({"disabled":"disabled"});
	var me = addExhibitHelper;
	var areaId = $('#cityId').find("option:selected").data('id');
	var areaName = $('#cityId').find("option:selected").data('name');
	$('#hideAreaId').val(areaId);
	$('#hideAreaName').val(areaName);
	var param = $("#filter_form").serialize();
	$.ajax({
		url : "${basePath}/exhibit/addSaveExhibit.do",
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
