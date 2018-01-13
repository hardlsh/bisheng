<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../basepath.jsp"%>


<ol class="breadcrumb">
  <li class="active">展馆管理</li>
</ol>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box grey-cascade">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>展馆管理
				</div>
				<div class="actions">
					<button id="addExhibit" class="btn btn-sm blue" type="button">新增展馆</button>
                    <shiro:hasRole name="管理员">
                        <button id="delExhibit" class="btn btn-sm red" type="button">删除展馆</button>
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
							城市:<input id="city" name="city" class="form-control input-inline input-sm"
									  maxlength="15" placeholder="例如:上海" type="text"/>
							展馆:<hz:authExhibitSelect id="exhibitIdList" name="exhibitIdList"
	                                   classes="multiselect form-control"
	                                   multiple="multiple"
	                                   hasAll="false"
	                                   style="width: 110px;height: 28px;display: block;"/>&nbsp;
							展馆状态:<hz:multipleSelect name="exhibitStatus" id="exhibitStatus"  dictCode="EXHIBIT_STATUS"
													classes="bs-select form-control input-sm input-inline "
													hasAll="true" placeholder="请选择"/>
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

					<table class="table table-striped table-bordered table-hover"
						id="exhibitTable">
						<thead>
							<tr>
								<th>展馆ID</th>
								<th>展馆名称</th>
								<th>展馆code</th>
								<th>展馆所在地区</th>
								<th>展馆地址</th>
								<th>联系方式</th>
								<th>展馆开放状态</th>
								<th>展览开始时间</th>
								<th>展览结束时间</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>
					</div>
				</div>
			</div>
</div>

<!-- 删除展馆,弹出框 -->
<div class="modal" id="delExhibitModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">删除展馆</h4>
            </div>
            <div class="modal-body">
                <form id='del_form' href="##" method="post">
                </form>
                <table class="table table-striped table-bordered table-hover"
                       id="delExhibitTable">
                    <thead>
                    <tr>
                        <th>单选</th>
                        <th>展馆名称</th>
                        <th>展馆所在地区</th>
                        <th>联系方式</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="modal-footer -align-center">
                <button type="button" class="btn red" id="delExhibitConfirm">确认删除</button>
                <button type="button" class="btn default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
</div>

<script type="text/javascript">

	var exhibitHelper = {
		exhibitStatusEnum : '${exhibitStatusEnum}',
		
		init : function () {
			var me = exhibitHelper;
			
			$('#addExhibit').click(me.addExhibit);// 新增展馆按钮
            $('#delExhibit').click(me.delExhibit);// 删除展馆按钮
			$('#btnRefresh').click(me.btnRefresh);//搜索按钮
			$('#btnEmpty').click(me.btnEmpty);//清空条件
			$('#doExport').click(me.doExport);// 导出明细按钮
            $('#delExhibitConfirm').click(me.delExhibitConfirm);// 删除展馆弹出框上的确认按钮
			
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
			me.exhibitTable();
			me.delExhibitTable();
		},
		//初始化Table,加载表格
		exhibitTable : function () {
			var me = exhibitHelper;
			var url = "${basePath}/exhibit/getExhibitList.do"; //表格数据远程地址
			var colArray = [
					{"data" : "exhibitId","bSortable" : false},
					{"data" : "exhibitName","bSortable" : false},
					{"data" : "exhibitCode","bSortable" : false},
					{"data" : "areaName","bSortable" : false},
					{"data" : "address","bSortable" : false},
					{"data" : "phone","bSortable" : false},
					{"data" : function (e) {
						return myJson.getText(e.status, me.exhibitStatusEnum);
						},"bSortable" : false
					},
					{"data" : "startTime","bSortable" : false},
					{"data" : "endTime","bSortable" : false},
					{"data" : function(e) {
						return'<div style="min-width:110px;"><a class="ajaxify btn default btn-xs purple"  href="${basePath}/exhibit/toUpdateExhibit.do?exhibitId='
                            + e.exhibitId + '">修改展馆</a></div>';
					},"bSortable" : false}
				];
			TablePaginationSort.initCustom(url, colArray, "exhibitTable","filter_form");//最后一个参数formid,此处为空form
		},
        //初始化Table,加载表格
        delExhibitTable : function () {
            var url = "${basePath}/exhibit/getExhibitList.do"; //表格数据远程地址
            var colArray = [
                {"data" : function(e) {
                    return '<input type="radio" name="rad1" value="'+e.exhibitId+'"/>';
                    }
                },
                {"data" : "exhibitName","bSortable" : false},
                {"data" : "areaName","bSortable" : false},
                {"data" : "phone","bSortable" : false}
            ];
            TablePaginationSort.initCustom(url, colArray, "delExhibitTable","del_form");//最后一个参数formid,此处为空form
        },
		//新增展馆
		addExhibit : function () {
			$('#body2').load("${basePath}/exhibit/toAddExhibit.do");
		},
        // 删除展馆
        delExhibit : function () {
            $('#delExhibitTable').DataTable().draw();
            $("#delExhibitModal").modal('show');
        },
        // 确认删除展馆
        delExhibitConfirm : function () {
            var exhibitId = "";
            $("[name='rad1']:checked").each(function() {
                if ($(this).attr('checked')) {
                    exhibitId = $(this).val();
                }
            });
            if (exhibitId == '') {
                bootbox.alert('请先选择要删除的展馆');
                return;
            }
            $("#delExhibitConfirm").attr({"disabled":"disabled"});
            $.ajax({
                url : "${basePath}/exhibit/deleteExhibit.do",
                data: {exhibitId: exhibitId},
                dataType : "json",
                type : "POST",
                success : function(data) {
                    $("#delExhibitConfirm").removeAttr("disabled");
                    $("#exhibitTable").DataTable().draw();// 点击确认删除后,重新绘制table
                    $('#delExhibitModal').modal('hide');// 隐藏删除展馆对应的弹窗
                    bootbox.alert(data.resultMsg);
                }
            });
        },
		// 查询展馆
		btnRefresh : function() {
			$("#exhibitTable").DataTable().draw();// 点搜索重新绘制table。
		},
		// 清空筛选条件
		btnEmpty : function() {
			$("input[type=reset]").trigger("click");
			$(".multiselect-container li").removeClass('active');
    		$("select[ multiple='multiple']").multiselect( 'clearSelection');
		},
		//导出明细
		doExport : function(){
			$("#filter_form").attr('action','${basePath}/exhibit/exportExhibit.do');
        	$("#filter_form").submit();
		}
	};


	$(function() {
		exhibitHelper.init();
		$('#province_area').hide();
	});

</script>