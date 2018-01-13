<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../basepath.jsp"%>

<style>
	li {list-style-type:none;}
	ul.arealist li{text-align:left;width:65px;overflow:hidden;margin:0 6px;color:#666}
	ul.arealist .openLi{text-align:left;width:60px;overflow:hidden;margin:0 6px;color:#666}
</style>

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

<script type="text/javascript">

	var exhibitHelper = {
		exhibitStatusEnum : '${exhibitStatusEnum}',
		
		init : function () {
			var me = exhibitHelper;
			
			$('#addExhibit').click(me.addExhibit);// 新增展馆按钮
			$('#btnRefresh').click(me.btnRefresh);//搜索按钮
			$('#btnEmpty').click(me.btnEmpty);//清空条件
			$('#doExport').click(me.doExport);// 导出明细按钮
			
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
						return'<div style="min-width:110px;"><a class="ajaxify btn default btn-xs purple"  href="${basePath}/exhibit/toUpdateExhibit.do?exhibitId=' + e.exhibitId + '">修改展馆</a></div>';
					},"bSortable" : false}
				];
			TablePaginationSort.initCustom(url, colArray, "exhibitTable","filter_form");//最后一个参数formid,此处为空form
		},
		//新增展馆
		addExhibit : function () {
			$('#body2').load("${basePath}/exhibit/toAddExhibit.do");
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