<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../basepath.jsp"%>

<script src="${basePath}/resources/js/jquery.form.js" type="text/javascript"></script>

<ol class="breadcrumb">
  <li class="active">文字出入库管理</li>
</ol>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box grey-cascade">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>文字出入库管理
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
						<input id="reset" type="reset" style="display: none;" />
						<div class="form-group input-inline">
							展馆:<span style="color: red;"> * </span>
								 <hz:authExhibitSelect id="exhibitIdList" name="exhibitIdList"
	                                  classes="multiselect form-control"
	                                  multiple="multiple"
	                                  hasAll="false"
	                                  placeholder="请选择"
	                                  style="width: 110px;height: 28px;display: block;"/>&nbsp;
	                                               出入库类型:         
	                        <hz:multipleSelect id="type" name="operateType"
									dictCode="WORD_OPERATE_TYPE"
									classes="bs-select form-control input-sm input-inline "
									hasAll="false" placeholder="请选择" />
						</div>
						<div class="form-group input-inline">
							操作日期:
							<input class="form-control input-inline input-sm date-picker" id="updateDateStrMin" name="updateDateStrMin"
		                                   size="10" type="text" data-date-format="yyyy-mm-dd" placeholder="开始日期"/>
									至：
							<input class="form-control input-inline input-sm date-picker" id="updateDateStrMax" name="updateDateStrMax"
                                  size="10" type="text" data-date-format="yyyy-mm-dd" placeholder="结束日期"/>
							文字:
	                        	<input id="wordQuery" name="wordStr" class="form-control input-inline input-sm" maxlength="1000" 
								placeholder="请输入要查询的文字,支持批量文字" onkeyup="value=value.replace(/[^\u4e00-\u9fa5]/,'')" type="text" style="width:240px" />
						</div>
						<div class="row">
						<div class="form-group  input-inline">
							<div class="col-md-12">
								<button id="btnRefresh" class="btn btn-sm green" type="button">搜索</button>
								<a class="btn btn-sm red" id="btnEmpty"><i class="icon-trash"></i>清空条件</a>
	                        </div>
						</div>
						</div>
					</form>
					</div>
				</div>
				
				<table class="table table-striped table-bordered table-hover" id="wordTable">
					<thead>
						<tr>
							<th>文字ID</th>
							<th>文字</th>
							<th>所属展馆</th>
							<th>操作类型</th>
							<th>数量</th>
							<!-- <th>操作人</th> -->
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">

	var wordOperateHelper = {
		wordOperateTypeEnum : '${wordOperateTypeEnum}',
		
		init : function () {
			var me = wordOperateHelper;
			
			$('#btnRefresh').click(me.btnRefresh);//搜索按钮
			$('#btnEmpty').click(me.btnEmpty);//清空条件
			
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
		},
		//初始化Table,加载表格
		wordTable : function () {
			var me = wordOperateHelper;
			
			var url = "${basePath}/word/getWordOperateList.do"; //表格数据远程地址
			var colArray = [
					{"data" : "wordId","bSortable" : false},
					{"data" : "word","bSortable" : false},
					{"data" : "exhibitName","bSortable" : false},
					{"data" : function(e) {
						return myJson.getText(e.type, me.wordOperateTypeEnum);
						},
						"bSortable" : false},
					{"data" : "operateCount","bSortable" : false}
				];
			TablePaginationSort.initCustom(url, colArray, "wordTable","filter_form");
		},
		// 查询文字存量
		btnRefresh : function() {
			var me = wordOperateHelper;
			//展馆为必选项
			var exhibitIdList = $("#exhibitIdList").val();

			if (exhibitIdList == null || exhibitIdList == "") {
				bootbox.alert("必填字段不能为空");
				return;
			}
			$("#wordTable").DataTable().destroy();//先删除表格,因为表格不能重复初始化
			me.wordTable();
		},
		// 清空筛选条件
		btnEmpty : function() {
			$("#reset").trigger("click");
			$(".multiselect-container li").removeClass('active');
    		$("select[ multiple='multiple']").multiselect( 'clearSelection');
		},
		// 为日期赋值
		updateDateDefault : function () {
			$("#updateDateStrMin").datepicker('setDate','-7d');
			$("#updateDateStrMax").datepicker('setDate',new Date());
		}
		
	};

	$(function() {
		ComponentsPickers.init();//日期插件初始化
		wordOperateHelper.updateDateDefault();// 先赋值,再初始化表格
		wordOperateHelper.init();
	});

</script>