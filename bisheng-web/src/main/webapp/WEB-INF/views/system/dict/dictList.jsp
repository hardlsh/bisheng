<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../basepath.jsp"%>
<ol class="breadcrumb">
  <li class="active">字典管理</li>
</ol>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box grey-cascade">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>字典管理
				</div>
				
				
				<div class="actions">
					<a href="javascript:showModel();" class="btn blue" id="addDic"><i class="icon-plus"></i>新增</a>
				</div>
				
				
			</div>
			
			<div class="portlet-body">
				<div class="table-toolbar">
					<form id='filter_form' href="##" method="post">
						<div class="form-group">
							字典值
							<input id="dictCode" name="dictCode"  class="form-control input-sm input-inline" size="12" placeholder="字典值" />
						</div>
						
						<button id="go_search" class="btn btn-sm green" type="button">搜索</button>
						
					</form>
				</div>
			</div>
				
			<div class="portlet-body">
				<table class="table table-striped table-bordered table-hover" id="dictTable">
					<thead>
						<tr>
							<th>字典编号</th>
							<th>字典名称</th>
							<th>字典描述</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>

<!-- add modal content -->
<div class="modal" id="dicModal" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">新增字典</h4>
			</div>
			<div class="modal-body">
				<form action="##" class="form-horizontal form-row-sepe" id="dicFrom">
					<div class="form-group">
						<label class="control-label col-md-3">字典类型</label>
						<div class="col-md-4">
							<input type="text" name="dictCode" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">字典名称</label>
						<div class="col-md-4">
							<input type="text" name="dictName" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">字典描述</label>
						<div class="col-md-8">
							<textarea name="remark" class="form-control" maxlength="225" rows="2" ></textarea>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn blue" onclick="saveDic();">保存</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- add modal content -->
<div class="modal" id="updateModal" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">修改字典</h4>
			</div>
			<div class="modal-body">
				<form action="##" class="form-horizontal form-row-sepe" id="updateFrom">
					<div class="form-group">
						<label class="control-label col-md-3">字典类型</label>
						<div class="col-md-4">
							<input type="text" name="dictCode" readonly="readonly" id="dictCode" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">字典名称</label>
						<div class="col-md-4">
							<input type="text" name="dictName" id="dictName" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">字典描述</label>
						<div class="col-md-8">
							<textarea name="remark" class="form-control" id="remark" maxlength="225" rows="2" ></textarea>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn blue" onclick="updateDic();">保存</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<script>
	$(function() {
		tradeTable();//加载表格
	});
	
	function showModel(){
		$('#dicModal').modal('show');
	}

	//表格配置
	function tradeTable() {
		var url = "${basePath}/dict/getDictList.do"; //表格数据远程地址
		var colArray = [
				{"data" : "dictCode"},
				{"data" : "dictName"},
				{"data" : "remark"},
				{"data" : function(e) {
					var url = '<a class="btn default btn-xs purple " href="javascript:showUpdate(\''+e.dictCode+'\');">修改</a>';
					url = url + '<a class="ajaxify btn default btn-xs purple " href="${basePath}/dict/dictItem.do?dictCode='+e.dictCode+'">修改字典项</a>';
					return url;
				}}
			];
		TablePagination.init(url, colArray, "dictTable");//最后一个参数为表的ID
	}
	
	function saveDic(){
		var param = $("#dicFrom").serialize();
		$.ajax({
			url : "${basePath}/dict/addDict.do",
			data : param,
			dataType: "json",
			type : "POST",
			success : function(data) {
				if(data.resultCode!='0000'){
					bootbox.alert(data.resultMsg);
				}else{
					$.alert(data.resultMsg);
					$("#dicFrom")[0].reset();
					$("#dictTable").DataTable().draw();//点搜索重新绘制table。
					$('#dicModal').modal('hide');
				}
			}
		});
	}
	
	function showUpdate(dictCode) {
		$.ajax({
			url : "${basePath}/dict/getDict.do",
			data : {dictCode:dictCode},
			dataType: "json",
			type : "POST",
			success : function(data) {
				if(data.resultCode =='0000'){
					$("#dictCode").val(data.data.dictCode);
					$("#dictName").val(data.data.dictName);
					$("#remark").val(data.data.remark);
					$('#updateModal').modal('show');
				}else{
					bootbox.alert(data.resultMsg);
				}
			}
		});
	}
	
	function updateDic(){
		var param = $("#updateFrom").serialize();
		$.ajax({
			url : "${basePath}/dict/updateDict.do",
			data : param,
			dataType: "json",
			type : "POST",
			success : function(data) {
				if(data.resultCode!='0000'){
					bootbox.alert(data.resultMsg);
				}else{
					$.alert(data.resultMsg);
					$("#updateFrom")[0].reset();
					$('#updateModal').modal('hide');
					$("#dictTable").DataTable().draw();//点搜索重新绘制table。
				}
			}
		});
	}
	
</script>