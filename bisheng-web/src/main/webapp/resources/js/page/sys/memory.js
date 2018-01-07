/**
 * chaowei.hu 2016.03.30
 */
$(function() {
	tradeTable();// 加载表格
	$("#selectAll").selectAll();
	$("#releaseSel").click(function() {
		var keys = [];
		$("[name='chb']:checked").each(function () {
			keys.push($(this).val());
		});
		if(keys.length<=0){
			bootbox.alert("请选择待记录");
			return;
		}
		bootbox.confirm("确定要选中的缓存记录么?", function(result) {
			if(result){
				$.ajax({
					type : "post",
					url : basePath + "/memory/delSelect.do",
					data : {"keys" : keys.join(",")},
					dataType : "json",
					success : function(data) {
						if(data.resultCode =='0'){
							$.alert(data.resultMsg);
							$("#go_search").click();
						}else{
							bootbox.alert(data.resultMsg);
						}
					}
				});
			}
        });
	});

	$("#releaseAll").click(function() {
		bootbox.confirm("确定要清空所有的缓存么?", function(result) {
			if(result){
     			var url = basePath + "/memory/clearMemory.do";
    			$.get(url, function(data) {
    				if(data.resultCode =='0'){
    					$.alert(data.resultMsg);
    					$("#go_search").click();
    				}else{
    					bootbox.alert(data.resultMsg);
    				}
    			});
			}
         });
	});
});

// 表格配置
function tradeTable() {
	var url = basePath + "/memory/getList.do"; 
	var colArray = [
			{"data" : function(e) {
				return '<input type="checkbox" class="checkboxes" name="chb" value="'+ e.keyName + '"/>';
			},"bSortable" : false}, 
			{"data" : "keyName","bSortable" : false} 
		];
	TablePagination.init(url, colArray, "dataTable");// 最后一个参数为表的ID
}
