var datatables;
$(document).ready(function () {

    dataTableInit();
    $("#userRolemodal").on("hidden.bs.modal", function () {
        $(this).removeData("bs.modal");
    });

    $('#table_server tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            datatables.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        var icon = "";
        var nTrs = datatables.fnGetNodes();// fnGetNodes获取表格所有行，nTrs[i]表示第i行tr
        for (var i = 0; i < nTrs.length; i++) {
            if ($(nTrs[i]).hasClass('selected')) {// 相当于$(tr)
                var t = datatables.fnGetData(nTrs[i]);
                icon = t.icon;
                break;
            }
        }
        $("#iconpreview").attr("src", "http://file.test.ningluosi.com/" + icon);
    });

    //初始化上传文件表格
    $(".dt-merge-grid").css("width", "100%").css("margin", "0");
    $(document).on("click", "#queryBtn", function () {
        datatables.fnDraw();
    });
});


function dataTableInit() {
    datatables = $('#table_server').dataTable({
        "pagingType": "simple_numbers",//设置分页控件的模式
        searching: false,//屏蔽datatales的查询框
        aLengthMenu: [10],//设置一页展示10条记录
        ordering: false, showRowNumber: true,
        "bLengthChange": false,//屏蔽tables的一页展示多少条记录的下拉列表
        "oLanguage": {  //对表格国际化
            "sLengthMenu": "每页显示 _MENU_条",
            "sZeroRecords": "没有找到符合条件的数据",
            //  "sProcessing": "&lt;img src=’./loading.gif’ /&gt;",
            "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
            "sInfoEmpty": "",
            "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "前一页",
                "sNext": "后一页",
                "sLast": "尾页"

            }
        },
        ajax: {// 类似jquery的ajax参数，基本都可以用。
            type: "post",// 后台指定了方式，默认get，外加datatable默认构造的参数很长，有可能超过get的最大长度。
            url: basePath + "sessions/list",
            dataSrc: "rows",// 默认data，也可以写其他的，格式化table的时候取里面的数据
            data: function (d) {// d 是原始的发送给服务器的数据，默认很长。
                var param = {};// 因为服务端排序，可以新建一个参数对象
                param.start = d.start;// 开始的序号
                param.length = d.length;// 要取的数据的
                var formData = $("#filter_form").serializeArray();// 把form里面的数据序列化成数组
                formData.forEach(function (e) {
                    param[e.name] = e.value;
                });
                return param;// 自定义需要传递的参数。
            },
        },
        "serverSide": true,//打开后台分页
        "columns": [
            {data: "sessionId", visible: false},// 字段名字和返回的json序列的key对应
            {data: "userName"},// 字段名字和返回的json序列的key对应
            {data: "host"},
            {data: "loginTime"},
            {
                "data": "sessionId", "render": function (data, type, full, meta) {
                return '<button type="button" onclick="deleteRow(\'' + data + '\')" class="btn btn-danger btn-xs" title="强制下线"><span class="glyphicon glyphicon-remove"></span></button>';
            }, width: "30px"
            }
        ]
    });
}
function deleteRow(selectId) {
    $.confirm({
        content: "确定刪除？",
        confirm: function () {
            $.post(basePath + "sessions/" + selectId + "/forceLogout",
                {}, function (data) {
                    if (data.success) {
                        $.alert("确定删除强制退成功");
                        datatables.fnDraw();
                    } else {
                        $.alert(data.msg);
                    }
                }, "json").error(function (data) {
                $.alert(data.responseText);
            });
        },
        cancel: function () {
        }
    });
}
