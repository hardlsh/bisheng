<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../basepath.jsp"%>
<ol class="breadcrumb">
	 <li><a class="ajaxify" href="${basePath}/dict/list.do">字典列表</a></li>
     <li class="active">字典项管理</li>
</ol>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box grey-cascade">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>字典项管理
				</div>
				<div class="actions">
					<a href="javascript:showModel();" class="btn blue"><i class="icon-plus"></i>新增</a>
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-striped table-bordered table-hover" id="dictItemTable">
					<thead>
						<tr>
							<th>字典类别</th>
							<th>字典项编号</th>
							<th>字典项值</th>
							<th>关键字</th>
							<th>状态</th>
							<th>顺序</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${dictItems}" var="item">
							<tr class="odd gradeX">
								<td>${item.dictCode}</td>
								<td>${item.itemCode}</td>
								<td>${item.itemName}</td>
								<td>${item.keyWord}</td>
								<td>${item.status}</td>
								<td>${item.sequence}</td>
								<td><a class="btn default btn-xs purple" href="javascript:update('${item.dictCode}','${item.itemCode}');">修改</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<!-- add modal content -->
<div class="modal" id="dictItemModal" dictItem="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">新增字典项</h4>
			</div>
			<div class="modal-body">
				<form action="##" class="form-horizontal form-row-sepe" id="dictItemFrom">
					<div class="form-group">
						<label class="control-label col-md-3">字典项编号</label>
						<div class="col-md-4">
							<input type="text" name="itemCode" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">字典项值</label>
						<div class="col-md-4">
							<input type="text" name="itemName" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">关键字</label>
						<div class="col-md-4">
							<input type="text" name="keyWord" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">顺序</label>
						<div class="col-md-4">
							<input type="text" name="sequence" class="form-control">
						</div>
					</div>
					<input type="hidden" name="dictCode" value="${dictCode}">
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn default" data-dismiss="modal" >关闭</button>
				<button type="button" class="btn blue" id ="submit" onclick="saveDictItem();">保存</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- add modal content -->
<div class="modal" id="updateItemModal" dictItem="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">修改字典项</h4>
			</div>
			<div class="modal-body">
				<form action="##" class="form-horizontal form-row-sepe" id="updateItemFrom">
					<div class="form-group">
						<label class="control-label col-md-3">字典项编号</label>
						<div class="col-md-4">
							<input type="text" name="itemCode" class="form-control" id="itemCode">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">字典项值</label>
						<div class="col-md-4">
							<input type="text" name="itemName" class="form-control" id="itemName">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">关键字</label>
						<div class="col-md-4">
							<input type="text" name="keyWord" class="form-control" id="keyWord">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">顺序</label>
						<div class="col-md-4">
							<input type="text" name="sequence" class="form-control" id="sequence">
						</div>
					</div>
					<input type="hidden" name="dictCode" id="dictCode">
					<input type="hidden" name="oldItemCode" id="oldItemCode">
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn default" data-dismiss="modal" >关闭</button>
				<button type="button" class="btn blue" id ="submit" onclick="updateDictItem();">保存</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<script>
	function showModel(){
		$('#dictItemModal').modal('show');
	}
	function saveDictItem(){
		var param = $("#dictItemFrom").serialize();
		$.ajax({
			url : "${basePath}/dict/addDictItem.do",
			data : param,
			dataType: "json",
			type : "POST",
			success : function(data) {
				if(data.resultCode!='0000'){
					bootbox.alert(data.resultMsg);
				}else{
					$.alert(data.resultMsg);
					$("#dictItemFrom")[0].reset();
					$('#dictItemModal').modal('hide');
					var url ="${basePath}/dict/dictItem.do?dictCode=${dictCode}";
					$.get(url, function(data) {
						$('.page-content .page-content-body').html(data);
					});
				}
			}
		});
	}
	
	function update(dictCode, itemCode) {
		$.ajax({
			url : "${basePath}/dict/getDictItem.do",
			data : {dictCode:dictCode,itemCode:itemCode},
			dataType: "json",
			type : "POST",
			success : function(data) {
				if(data.resultCode =='0000'){
					$("#itemCode").val(data.data.itemCode);
					$("#itemName").val(data.data.itemName);
					$("#keyWord").val(data.data.keyWord);
					$("#sequence").val(data.data.sequence);
					$("#dictCode").val(data.data.dictCode);
					$("#oldItemCode").val(data.data.itemCode);
					$('#updateItemModal').modal('show');
				}else{
					bootbox.alert(data.resultMsg);
				}
			}
		});
	}
	
	function updateDictItem(){
		var param = $("#updateItemFrom").serialize();
		$.ajax({
			url : "${basePath}/dict/updateDictItem.do",
			data : param,
			dataType: "json",
			type : "POST",
			success : function(data) {
				if(data.resultCode!='0000'){
					bootbox.alert(data.resultMsg);
				}else{
					$.alert(data.resultMsg);
					$("#updateItemFrom")[0].reset();
					$('#updateItemModal').modal('hide');
					
					var url ="${basePath}/dict/dictItem.do?dictCode=${dictCode}";
					$.get(url, function(data) {
						$('.page-content .page-content-body').html(data);
					});
				}
			}
		});
	}
</script>