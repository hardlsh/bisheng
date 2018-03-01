<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../basepath.jsp"%>

<script src="${basePath}/resources/js/jquery.form.js" type="text/javascript"></script>

<ol class="breadcrumb">
  <li class="active">文字出库管理</li>
</ol>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box grey-cascade">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>文字出库管理
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
							文字:
	                        	<input id="wordQuery" name="wordStr" class="form-control input-inline input-sm" maxlength="1000" 
								placeholder="请输入要查询的文字,支持批量文字" onkeyup="value=value.replace(/[^\u4e00-\u9fa5]/,'')" type="text" style="width:240px" />
							出库日期:
							<input class="form-control input-inline input-sm date-picker" id="updateDateStrMin" name="updateDateStrMin"
								   size="10" type="text" data-date-format="yyyy-mm-dd" placeholder="开始日期"/>
							至:
							<input class="form-control input-inline input-sm date-picker" id="updateDateStrMax" name="updateDateStrMax"
								   size="10" type="text" data-date-format="yyyy-mm-dd" placeholder="结束日期"/>
							现有库存:
							<input class="form-control input-inline input-sm" id="totalCountMin" name="totalCountMin"
								   size="10" type="text" placeholder="最小库存"/>
							至:
							<input class="form-control input-inline input-sm" id="totalCountMax" name="totalCountMax"
								   size="10" type="text" placeholder="最大库存"/>
						</div>
						<div class="row">
						<div class="form-group  input-inline">
							<div class="col-md-12">
								<button id="btnRefresh" class="btn btn-sm green" type="button">搜索</button>
								<a class="btn btn-sm red" id="btnEmpty"><i class="icon-trash"></i>清空条件</a>
								<button id="btnUpdate" class="btn btn-sm blue" type="button">出库</button>
								<button id="doExport" class="btn btn-sm green" type="button">导出明细</button>
	                        </div>
						</div>
						</div>
					</form>
					</div>
				</div>
				
				<table class="table table-striped table-bordered table-hover" id="wordTable">
					<thead>
						<tr>
							<th><input id="checkAll" type="checkbox" name="checkBox">全选</th>
							<th>文字</th>
							<th>所属展馆</th>
							<th>所属展位</th>
							<th>现有库存</th>
							<th>出库数量</th>
							<th>最近出库日期</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>

<!-- 批量出库,弹出框 -->
<div class="modal" id="updateModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">文字批量出库</h4>
            </div>
            <div class="modal-body">
                <label id="prompt" class="control-label">您选择修改上海展馆,共选择了99个字!</label>
               	<form action="##" class="form-horizontal form-row-sepe" id="updateForm">
               		<!-- 隐藏的复位按钮 -->
					<input id="resetUpdate" type="reset" style="display: none;" />
                   	<input type="hidden" id="exhibitIdUpdate" name="exhibitId"/>
					<input type="hidden" id="exhibitNameUpdate" name="exhibitName"/>
                  	<input type="hidden" id="wordStrUpdate" name="wordIdStr"/>
					<div class="row">
						<div class="form-group">
							<label
								class="control-label col-md-3 col-sm-3 col-xs-8">出库数量<span
								class="required"> * </span></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input id="updateCount" name="count"
									class="form-control input-inline input-sm" maxlength="15"
									placeholder="请输入数字" type="text"
									onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
									/>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<label
									class="control-label col-md-3 col-sm-3 col-xs-8">出库日期<span
									class="required"> * </span></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input class="form-control input-inline input-sm date-picker" id="updateDateStr" name="updateDateStr"
									   size="10" type="text" data-date-format="yyyy-mm-dd" placeholder="出库日期"/>
							</div>
						</div>
					</div>
				</form>
            </div>
            <div class="modal-footer -align-center">
            	<button type="button" class="btn blue" id="confirmUpdate">确认</button>
                <button type="button" class="btn default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
</div>

<script type="text/javascript">

	var wordOutHelper = {
		
		init : function () {
			var me = wordOutHelper;
			
			$('#btnRefresh').click(me.btnRefresh);//搜索按钮
			$('#btnEmpty').click(me.btnEmpty);//清空条件
			$('#btnUpdate').click(me.btnUpdate);// 批量出库按钮
			$('#checkAll').click(me.checkAllFunc);// 文字出库表上的全选
			$('#confirmUpdate').click(me.confirmUpdate);// 出库弹出框上的确认按钮
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
		},
		//初始化Table,加载表格
		wordTable : function () {
			var me = wordOutHelper;
			
			var url = "${basePath}/word/getWordOutList.do"; //表格数据远程地址
			var colArray = [
					{"data" : function(e) {
						return '<input type="checkbox" class="checkboxes" name="chb1" data-id="'
						+ e.wordId + '" onclick="wordOutHelper.checkAll()" />';
					},"bSortable" : false},
					{"data" : "word","bSortable" : false},
					{"data" : "exhibitName","bSortable" : false},
                	{"data" : "boothNameStr","bSortable" : false},
					{"data" : "totalCount","bSortable" : false},
                	{"data" : "outTotalCount","bSortable" : false},
					{"data" : "outDate","bSortable" : false}
				];
			TablePaginationSort.initCustom(url, colArray, "wordTable","filter_form");
		},
		// 查询
		btnRefresh : function() {
			var me = wordOutHelper;
			//展馆为必选项
			var exhibitIdList = $("#exhibitIdList").val();

			if (exhibitIdList == null || exhibitIdList == "") {
				bootbox.alert("必填字段不能为空");
				return;
			}
			$("#wordTable").DataTable().destroy();//先删除表格,因为表格不能重复初始化
			$("#checkAll").prop("checked", false);
			$("#checkAll").parent().removeClass("checked");
			me.wordTable();
		},
		// 清空筛选条件
		btnEmpty : function() {
			$("#reset").trigger("click");
			$(".multiselect-container li").removeClass('active');
    		$("select[ multiple='multiple']").multiselect( 'clearSelection');
		},
		//全选
		checkAllFunc : function() {
			var checked = '';
			if ($('#checkAll').is(':checked')) {
				checked = 'checked';
			}
			$('[name="chb1"]').each(function(index, entity) {
				$(entity).prop('checked', checked);
			});
		},
		// 批量出库
		btnUpdate : function() {
			var updateWordIds = []; // 批量出库时对应的id数组
			var exhibitIdList = $("#exhibitIdList").val();
			if (exhibitIdList == null || exhibitIdList == "") {
				bootbox.alert("批量出库时,请选择一个展馆");
				return;
			} else if (exhibitIdList.indexOf(",") != -1) {
				bootbox.alert("批量出库时,请选择一个展馆");
				return;
			}
			$('[name="chb1"]').each(function(index, entity) {
				if ($(entity).is(':checked')) {
					updateWordIds.push($(entity).data('id'));
				}
			});
			if (updateWordIds.length == 0) {
				bootbox.alert('请选择要批量出库的文字');
				return;
			}
		
			// 获取展馆名称
            $.ajax({
                type: "POST",
                url: "${basePath}/word/getExhibitName.do",
                dataType: "json",
                data: {
                	exhibitId : exhibitIdList[0],
                },
                async: false,
                type : "POST",
                success: function (data) {
                	// 为隐藏域赋值
        			$("#exhibitIdUpdate").val(exhibitIdList[0]);
        			$("#exhibitNameUpdate").val(data.data.exhibitName);
        			$("#wordStrUpdate").val(updateWordIds.join(","));
                    if (data.resultCode == '0000') {
                    	var html = "您选择修改:" + data.data.exhibitName + "，共选择了" + updateWordIds.length + "个字。";
                        $("#prompt").html(html);
                        $("#updateDateStr").datepicker('setDate',new Date());
                        $('#updateModal').modal('show');
                    } else {
                        bootbox.alert("批量出库错误:" + data.resultMsg);
                    }
                }
            });
		},
		// 批量出库确认
		confirmUpdate : function () {
		    var updateCount = $('#updateCount').val();
		    var updateDateStr = $('#updateDateStr').val();
		    if ('' == updateCount) {
                bootbox.alert('请输入修改数量');
                return;
			}
			if (null == updateDateStr || '' == updateDateStr) {
                bootbox.alert('请选择修改日期');
                return;
			}
			$("#confirmUpdate").attr({"disabled":"disabled"});
			$.ajax({
		        url : "${basePath}/word/batchOutWord.do",
		        data : $("#updateForm").serialize(),
		        dataType: "json",
		        type : "POST",
		        async: false,
		        success : function(data) {
		        	$('#updateModal').modal('hide');
		        	$("#confirmUpdate").removeAttr("disabled");
		        	$("#resetUpdate").trigger("click");// 清空弹出框内容
		            if(data.resultCode =='0000'){
		            	$("#wordTable").DataTable().draw();
		            	$("#checkAll").prop("checked", false);
		            	$("#checkAll").parent().removeClass("checked");
		            	$.alert("处理成功！")
		            }else{
		            	bootbox.alert(data.resultMsg);
		            }
		        }
		    });
		},
        //导出明细
        doExport : function(){
            $("#filter_form").attr('action','${basePath}/word/exportWord.do');
            $("#filter_form").submit();
        },
        // 为日期赋值
        updateDateDefault : function () {
            $("#updateDateStrMin").datepicker('setDate','-7d');
            $("#updateDateStrMax").datepicker('setDate',new Date());
        }
		
	};

	$(function() {
        ComponentsPickers.init();//日期插件初始化
        //wordOutHelper.updateDateDefault();
		wordOutHelper.init();
	});

</script>