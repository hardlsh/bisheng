<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../basepath.jsp"%>

<ol class="breadcrumb">
  <li class="active">展位文字</li>
</ol>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box grey-cascade">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>查找文字
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
								展馆:<span style="color: red;"> * </span>
									 <hz:authExhibitSelect id="exhibitIdList" name="exhibitIdList"
	                                   classes="multiselect form-control"
	                                   multiple="multiple"
	                                   hasAll="false"
	                                   onchange="boothWordHelper.loadBooth(this);"
	                                   style="width: 110px;height: 28px;display: block;"/>&nbsp;
								展位: 
									<hz:multipleSelect dictCode="" id="boothIdList" name="boothIdList" 
										classes="multiselect form-control input-inline" multiple="multiple" 
										style="width: 110px;height: 28px;display: none;" 
										hasAll="false" />&nbsp;
								 文字:<span style="color: red;"> * </span>
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
				
				<table class="table table-striped table-bordered table-hover" id="boothWordTable">
					<thead>
						<tr>
							<th>文字Id</th>
							<th>文字</th>
							<th>数量</th>
							<th>模板名称</th>
							<th>行</th>
							<th>列</th>
							<th>编号</th>
							<th>现有库存</th>
							<th>所属展馆</th>
							<th>所属展位</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">

	var boothWordHelper = {
		
		init : function () {
			var me = boothWordHelper;	
			
			$('#btnEmpty').click(me.btnEmpty);//清空条件
			$('#btnRefresh').click(me.btnRefresh);//搜索按钮
			
			//初始化Table		修改为在调用的时候,再初始化
			//me.boothWordTable();
			
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
			me.loadBooth();
		},
		//初始化Table,加载表格
		boothWordTable : function () {
			var url = "${basePath}/boothWord/getBoothWordList.do"; //表格数据远程地址
			var colArray = [
					{"data" : "boothWordId","bSortable" : false},
					{"data" : "word","bSortable" : false},
					{"data" : "searchTotal","bSortable" : false},
					{"data" : "templetName","bSortable" : false},
					{"data" : "xAxis","bSortable" : false},
					{"data" : "yAxis","bSortable" : false},
					{"data" : "number","bSortable" : false},
					{"data" : "totalCount","bSortable" : false},
					{"data" : "exhibitName","bSortable" : false},
					{"data" : "boothName","bSortable" : false}
				];
			TablePaginationSort.initCustom(url, colArray, "boothWordTable","filter_form");//最后一个参数formid,此处为空form
		},
		//点击开始查询时,再刷新表格
		btnRefresh : function() {
			var me = boothWordHelper;
			
			//展馆和文字为必选项
			var exhibitIdList = $("#exhibitIdList").val();
			var wordQuery = $("#wordQuery").val();

			if (exhibitIdList == null || exhibitIdList == "" ||
					wordQuery == null || wordQuery == "") {
				bootbox.alert("请输入您要查询的汉字");
				return;
			}
			$("#boothWordTable").DataTable().destroy();//先删除表格,因为表格不能重复初始化
			me.boothWordTable();
		},
		//清空筛选条件
		btnEmpty : function() {
			$("input[type=reset]").trigger("click");
			$(".multiselect-container li").removeClass('active');
    		$("select[ multiple='multiple']").multiselect( 'clearSelection');
    		
    		//清空展位
    		var options ="[";
            options = options+"]";
            $('#boothIdList').multiselect( 'dataprovider',eval(options));
		},
		// 根据展馆,加载展位
		loadBooth : function() {
			$("#boothIdList option").remove();
            $("#boothIdList").append('<option value="">全部</option>');
            $("#boothIdList").val("").trigger('change');
            
            var params = {};
            var exhibitIdArr = $('#exhibitIdList').val();
            if (exhibitIdArr == null || exhibitIdArr == "") {
            	var options ="[]";			
            	$('#boothIdList').multiselect( 'dataprovider',eval(options));
            	return;
            }
			params.exhibitIdArr = exhibitIdArr.join(",");
			
			$.ajax({
		        url : "${basePath}/booth/getAuthBoothForTag.do",
		        data : params,
		        dataType: "json",
		        type : "POST",
		        success : function(data) {
		            if(data.resultCode =='0000'){
		            	var options ="[";
		            	$.each(data.data, function (index, entity) {
		                    options=options+"{label: '"+entity.boothName+"',title:'"+ entity.boothName+"',value:'"+entity.boothId+"'}";
		                    if(index!=data.data.length-1){
		                        options=options+",";
		                    }
                        });
		                options = options+"]";
		                $('#boothIdList').multiselect( 'dataprovider',eval(options));
		            }
		        }
		    });
		}
		
	};


	$(function() {
		boothWordHelper.init();
	});

</script>