var TablePagination = function () {
    var initTable = function (url, colArray, tableId, fromId) {
        var table = $('#' + tableId);
        // 表格开始
        table.dataTable({
            "serverSide": true,// 分页，取数据等等的都放到服务端去
            "ajax": {// 类似jquery的ajax参数，基本都可以用。
                'type': "post",// 后台指定了方式，默认get，外加datatable默认构造的参数很长，有可能超过get的最大长度。
                'url': url,
                'dataSrc': "data",// 默认data，也可以写其他的，格式化table的时候取里面的数据
                'data': function (d) {// d 是原始的发送给服务器的数据，默认很长。
                    var param = {};// 因为服务端排序，可以新建一个参数对象
                    param.start = d.start;// 开始的序号
                    param.length = d.length;// 要取的数据的
                    // param.order = d.order[0];
                    // console.log(d);
                    var form = $('#' + fromId);
                    var formData = form.serializeArray();// 把form里面的数据序列化成数组
                    formData.forEach(function (e) {
                        param[e.name] = e.value;
                    });
                    return param;// 自定义需要传递的参数。
                },
            },
            "language": {
                "emptyTable": "No data available in table",
                "info": "显示第 _START_ 到第 _END_ 总共 _TOTAL_ 条",
                "infoEmpty": "No records found",
                "infoFiltered": "(filtered1 from _MAX_ total records)",
                "lengthMenu": "_MENU_ 条记录",
                "zeroRecords": "No matching records found",
                "paginate": {
                    "previous": "上一页",
                    "next": "下一页",
                    "last": "最后一页",
                    "first": "第一页"
                }
            },
            "columns": colArray,// 列数据配置
            "lengthMenu": [[10, 30, 50, 100, 500], [10, 30, 50, 100, 500] // 页记录数
            ],
            // 初始化数据
            "pageLength": 10,
            "pagingType": "bootstrap_full_number",
            "bLengthChange": true,
            "bFilter": false, // 过滤功能
            "bSort": false,
            "drawCallback": function (setting) {
            	if (setting.json.alertMsg) {
                    bootbox.alert(setting.json.alertMsg);
                }
                $("#selectAll").prop("checked", false);
                $("#selectAll").parent().removeClass("checked");
            }
        });
    }

    return {
        // 入口方法
        init: function (url, colArray, tableId) {
            if (!jQuery().dataTable) {
                return;
            }
            initTable(url, colArray, tableId, 'filter_form');
            // 按钮做处理
            $('#go_search').unbind('click').click(function () {
                $("#" + tableId).DataTable().draw();// 点搜索重新绘制table。
                $("#selectAll").prop("checked", false);
                $("#selectAll").parent().removeClass("checked");
            });
        },
        initCustom: function (url, colArray, tableId, fromId) {
            if (!jQuery().dataTable) {
                return;
            }
            initTable(url, colArray, tableId, fromId);
        }
    };
}();

var TablePaginationSort = function () {
    var initTable = function (url, colArray, tableId, fromId, hasDrawFunc, queryAfterFunc) {
        var show = [10, 30, 50, 100, 500];
        var table = $('#' + tableId);
        // 表格开始
        table.dataTable({
            "serverSide": true,// 分页，取数据等等的都放到服务端去
            "ajax": {// 类似jquery的ajax参数，基本都可以用。
                'type': "post",// 后台指定了方式，默认get，外加datatable默认构造的参数很长，有可能超过get的最大长度。
                'url': url,
                'dataSrc': "data",// 默认data，也可以写其他的，格式化table的时候取里面的数据
                'data': function (d) {// d 是原始的发送给服务器的数据，默认很长。
                    var param = {};// 因为服务端排序，可以新建一个参数对象
                    param.start = d.start;// 开始的序号
                    param.length = d.length;// 要取的数据的
                    if (d.order[0]) {
                        param.column = d.order[0].column;
                        param.dir = d.order[0].dir;

                        param.name = d.columns[d.order[0].column].name;
                    }
                    d.length = 10;
                    var form = $('#' + fromId);
                    var formData = form.serializeArray();// 把form里面的数据序列化成数组
                    var formParam = {};
                    formData.forEach(function (e) {
                        if (param[e.name] == null || param[e.name] == undefined
                            || param[e.name] == ''
                        ) {
                            param[e.name] = e.value;
                        } else {
                            param[e.name] = param[e.name] + "," + e.value;
                        }
                    });

                    return param;// 自定义需要传递的参数。
                },
            },
            "language": {
                "emptyTable": "未找到可用数据",
                "info": "显示第 _START_ 到第 _END_ 总共 _TOTAL_ 条",
                "infoEmpty": "没有找到记录",
                "infoFiltered": "(filtered1 from _MAX_ total records)",
                "lengthMenu": "显示_MENU_ 条记录",
                "zeroRecords": "No matching records found",
                "paginate": {
                    "previous": "上一页",
                    "next": "下一页",
                    "last": "最后一页",
                    "first": "第一页"
                }
            },
            "columns": colArray,// 列数据配置
            "lengthMenu": [show, show],
            // 初始化数据
            "pageLength": 10,
            "pagingType": "bootstrap_full_number",
            "bLengthChange": true,
            "bFilter": false, // 过滤功能
            "bSort": true, // 排序功能
            "order": [[0, 'desc']],
            "drawCallback": function (setting) {
                //_SUM_INFO = '';
                //if (setting.json.notice) _SUM_INFO = setting.json.notice
                //$(".dataTables_length").parent().next().html(_SUM_INFO);
                if (setting.json.notice) {
                    var html = "<label style='font-weight: normal;float: right;'>" + setting.json.notice + "</label>";
                    $('#' + tableId + '_wrapper').find("div[class='row']").first().children("div").last().html(html);
                }

                if (setting.json.alertMsg) {
                    bootbox.alert(setting.json.alertMsg);
                }
                $("#selectAll").prop("checked", false);
                $("#selectAll").parent().removeClass("checked");

                if (hasDrawFunc) {
                    queryAfterFunc();
                }
            }
        });// 表格结束

        $("#go_search").unbind('click').click(function () {
            $("#" + tableId).DataTable().draw();// 点搜索重新绘制table。
        });
        return table;
    }

    return {
        // 入口方法
        init: function (url, colArray, tableId) {
            if (!jQuery().dataTable) {
                return;
            }
            return initTable(url, colArray, tableId, 'filter_form', false, null);
        },
        initCustom: function (url, colArray, tableId, fromId) {
            if (!jQuery().dataTable) {
                return;
            }
            return initTable(url, colArray, tableId, fromId, false, null);
        },
        initCustomAsync: function (url, colArray, tableId, fromId, hasDrawFunc, queryAfterFunc) {
            if (!jQuery().dataTable) {
                return;
            }
            return initTable(url, colArray, tableId, fromId, hasDrawFunc, queryAfterFunc);
        }
    };
}();

var TablePaginationNoPage = function () {
    var initTable = function (url, colArray, tableId, fromId) {
        var table = $('#' + tableId);
        // 表格开始
        var oTable = table.dataTable({
            "ajax": {// 类似jquery的ajax参数，基本都可以用。
                'type': "post",// 后台指定了方式，默认get，外加datatable默认构造的参数很长，有可能超过get的最大长度。
                'url': url,
                'dataSrc': "data",// 默认data，也可以写其他的，格式化table的时候取里面的数据
                'data': function (d) {// d 是原始的发送给服务器的数据，默认很长。
                    var param = {};// 因为服务端排序，可以新建一个参数对象
                    param.start = d.start;// 开始的序号
                    param.length = d.length;// 要取的数据的
                    // param.order = d.order[0];
                    // console.log(d);
                    var form = $('#' + fromId);
                    var formData = form.serializeArray();// 把form里面的数据序列化成数组
                    formData.forEach(function (e) {
                        param[e.name] = e.value;
                    });
                    return param;// 自定义需要传递的参数。
                },
            },
            "language": {
                "aria": {
                    "sortAscending": ": activate to sort column ascending",
                    "sortDescending": ": activate to sort column descending"
                },
                "emptyTable": "No data available in table",
                "info": "Showing _START_ to _END_ of _TOTAL_ entries",
                "infoEmpty": "No entries found",
                "infoFiltered": "(filtered from _MAX_ total entries)",
                "lengthMenu": "_MENU_ entries",
                "search": "Search:",
                "zeroRecords": "Loading..."
            },
            "columns": colArray,// 列数据配置
            // 初始化数据
            serverSide: true,
            ordering: false,
            searching: false,
            scrollY: 200,
            scroller: {
                loadingIndicator: true
            },

            "dom": "<'row' <'col-md-12'B>><'table-scrollable't><'row'<'col-md-12'i>>"
        });
    }
    return {
        // 入口方法
        init: function (url, colArray, tableId) {
            if (!jQuery().dataTable) {
                return;
            }
            initTable(url, colArray, tableId, 'filter_form');
        }
    };
}();