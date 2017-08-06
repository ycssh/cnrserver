var datatables ;
$(document).ready(function () {

    dataTableInit();
    $("#userRolemodal").on("hidden.bs.modal", function () {
        $(this).removeData("bs.modal");
    });

    $('#table_server tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
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
    } );

    //初始化上传文件表格
    $(".dt-merge-grid").css("width", "100%").css("margin", "0");
    $(document).on("click", "#queryBtn", function(){
        datatables.fnDraw();
    });
    $(document).on("click", "#resetBtn", function(){
        $("#filter_form")[0].reset();
    });

    $(document).on("click", "#btnAdd", function (e) {
        $('#roleAddmodal').modal('show');
        $("#roleAddForm")[0].reset();
    })

    $("#roleResmodal").on("hidden.bs.modal", function () {
        $(this).removeData("bs.modal");
    });
    $(document).on("click", "#btnRoleAdd", function () {
        $("#roleAddForm").trigger("validate");
    })

    $('#roleAddForm').validator({
        valid: function (form) {
            $.post($("#roleAddForm").attr("action"), $("#roleAddForm").serialize(), function (d) {
                if (d.success) {
                    $.alert(d.msg);
                    datatables.fnDraw();
                    $('#roleAddmodal').modal('hide');
                } else {
                    $.alert(d.msg);
                }
            }, "json").error(function (data) {
                $.alert(data.responseText);
            });
        }
    });
});


function dataTableInit(){
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
            url: basePath + "role/list",
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
            {data: "id", visible: false},// 字段名字和返回的json序列的key对应
            {data: "role"},// 字段名字和返回的json序列的key对应
            {data: "name"},
            {
                "data": "id", "render": function (data, type, full, meta) {
                return '<button type="button" onclick="deleteRow(\''+data+'\')" class="btn btn-danger btn-xs" title="删除"><span class="glyphicon glyphicon-trash"></span></button>&nbsp;'+
                    '<button type="button" onclick="editRes(\''+data+'\')" class="btn btn-primary btn-xs" title="编辑菜单"><span class="glyphicon glyphicon-th-list"></span></button>&nbsp;'+
                    '<button type="button" onclick="editRow(\''+data+'\')" class="btn btn-success btn-xs" title="编辑"><span class="glyphicon glyphicon-edit"></span></button>';
            }, width: "90px"
            }
        ]
    });
}

function deleteRow(selectId){
    $.confirm({
        content: "确定刪除？",
        confirm: function(){
            $.post(basePath + "role/delete/" + selectId,
                {}, function (data) {
                    if (data.success) {
                        $.alert("删除成功");
                        datatables.fnDraw();
                    } else {
                        $.alert(data.msg);
                    }
                }, "json");
        },
        cancel: function(){
        }
    });
}

function editRes(selectId){
    $("#roleResmodal").modal({
        remote: basePath + "role/resources/" + selectId
    });
}

function editRow(selectId){
    $("#roleAddForm")[0].reset();
    $.get(basePath + "role/edit/" + selectId,
        {}, function (data) {
            $("#id").val(data.id);
            $('#inputRole').val(data.role);
            $('#inputName').val(data.name);
            $('#roleAddmodal').modal('show');
        }, "json");
}


/*
var toolbar = new Array();
var dataTable;
var options;
$(function () {
    var tablePrefix = "#table_server_";
    options = {
        serverSide: true,// 分页，取数据等等的都放到服务端去
        processing: true,// 载入数据的时候是否显示“载入中”
        pageLength: 10,// 首次加载的数据条数
        ordering: false,// 排序操作在服务端进行，所以可以关了。
        ajax: {// 类似jquery的ajax参数，基本都可以用。
            type: "post",// 后台指定了方式，默认get，外加datatable默认构造的参数很长，有可能超过get的最大长度。
            url: basePath + "role/list",
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
        columns: [// 对应上面thead里面的序列
            {data: "id", visible: false},// 字段名字和返回的json序列的key对应
            {data: "role",},// 字段名字和返回的json序列的key对应
            {data: "name",}
        ],
        initComplete: function (setting, json) {
            // 初始化完成之后替换原先的搜索框。
            // 本来想把form标签放到hidden_filter 里面，因为事件绑定的缘故，还是拿出来。
            $(tablePrefix + "filter").html("<form id='filter_form'>" + $("#hidden_filter").html() + "</form>");
        },
        language: {
            lengthMenu: '<select class="form-control input-xsmall">' + '<option value="5">5</option>' + '<option value="10">10</option>' + '<option value="20">20</option>' + '<option value="30">30</option>' + '<option value="40">40</option>' + '<option value="50">50</option>' + '</select>条记录',// 左上角的分页大小显示。
            processing: "载入中",// 处理页面数据的时候的显示
            paginate: {// 分页的样式文本内容。
                previous: "上一页",
                next: "下一页",
                first: "第一页",
                last: "最后一页"
            },

            zeroRecords: "没有内容",// table tbody内容为空时，tbody的内容。
            // 下面三者构成了总体的左下角的内容。
            info: "总共_PAGES_ 页,显示第_START_ 到第 _END_条,共_TOTAL_ 条 "// 左下角的信息显示，大写的词为关键字。
        },
        dom: '<"toolbar">rtip'
    }
    dataTable = $("#table_server").dataTable(options);
    toolbar[0] = '<div class="buttons-preview">';
    toolbar[1] = '<a href="javascript:void(0);" id="btnAdd" class="btn btn-primary">新增</a>';
    toolbar[2] = '<a href="javascript:void(0);" id="btnEdit" class="btn btn-primary">修改</a>';
    toolbar[3] = '<a href="javascript:void(0);" id="btnDel" class="btn btn-primary">删除</a>';
    toolbar[4] = '<a href="javascript:void(0);" id="btnRes" class="btn btn-primary">菜单管理</a>';
    toolbar[5] = '</div>';
    $("div.toolbar").html(toolbar.join(""));

    $(document).on("click", "#queryBtn", function () {
        query();
    });


    $(document).on("click", "#btnAdd", function (e) {
        $('#roleAddmodal').modal('show');
        $("#roleAddForm")[0].reset();
    })

    $(document).on("click", "#btnEdit", function (e) {
        var selectId = "";
        var nTrs = dataTable.fnGetNodes();// fnGetNodes获取表格所有行，nTrs[i]表示第i行tr
        for (var i = 0; i < nTrs.length; i++) {
            if ($(nTrs[i]).hasClass('selected')) {// 相当于$(tr)
                var t = dataTable.fnGetData(nTrs[i]);
                selectId = t.id;
                break;
            }
        }
        if (selectId != "") {
            $("#roleAddForm")[0].reset();
            $.get(basePath + "role/edit/" + selectId,
                {}, function (data) {
                    $("#id").val(data.id);
                    $('#inputRole').val(data.role);
                    $('#inputName').val(data.name);
                    $('#roleAddmodal').modal('show');
                }, "json");
        } else {
            Showbo.Msg.alert("请选择要编辑的列");
        }

    })
    $(document).on("click", "#btnDel", function (e) {
            var selectId = "";
            var nTrs = dataTable.fnGetNodes();// fnGetNodes获取表格所有行，nTrs[i]表示第i行tr
            for (var i = 0; i < nTrs.length; i++) {
                if ($(nTrs[i]).hasClass('selected')) {// 相当于$(tr)
                    var t = dataTable.fnGetData(nTrs[i]);
                    selectId = t.id;
                    break;
                }
            }
            if (selectId != "") {
                Showbo.Msg.confirm("确定删除？", function (p) {
                    if (p == "yes") {
                        $.post(basePath + "role/delete/" + selectId,
                            {}, function (data) {
                                if (data.success) {
                                    Showbo.Msg.alert("删除成功");
                                    query();
                                } else {
                                    Showbo.Msg.alert(data.msg);
                                }
                            }, "json");
                    }
                })
            } else {
                Showbo.Msg.alert("请选择要编辑的列");
            }

        }
    )

    /!**
     * 当modal关闭时，即把数据清除：
     *!/
    $("#roleResmodal").on("hidden.bs.modal", function () {
        $(this).removeData("bs.modal");
    });

    $(document).on("click", "#btnRes", function (e) {
        var selectId = "";
        var nTrs = dataTable.fnGetNodes();// fnGetNodes获取表格所有行，nTrs[i]表示第i行tr
        for (var i = 0; i < nTrs.length; i++) {
            if ($(nTrs[i]).hasClass('selected')) {// 相当于$(tr)
                var t = dataTable.fnGetData(nTrs[i]);
                selectId = t.id;
                break;
            }
        }
        if (selectId != "") {
            $("#roleResmodal").modal({
                remote: basePath + "role/resources/" + selectId
            });
        } else {
            Showbo.Msg.alert("请选择一条记录");
        }

    })

    $(document).on("click", "#btnRoleAdd", function () {
        $("#roleAddForm").trigger("validate");
    })


    $('#roleAddForm').validator({
        valid: function (form) {
            $.post($("#roleAddForm").attr("action"), $("#roleAddForm").serialize(), function (d) {
                if (d.success) {
                    Showbo.Msg.alert(d.msg);
                    query();
                    $('#roleAddmodal').modal('hide');
                } else {
                    Showbo.Msg.alert(d.msg);
                }
            }, "json").error(function (data) {
                Showbo.Msg.alert(data.responseText);
            });
        }
    });


    $('#table_server tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            dataTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

});

function query() {
    dataTable.fnDestroy();
    options.ajax.data = function (d) {// d 是原始的发送给服务器的数据，默认很长。
        var param = {};// 因为服务端排序，可以新建一个参数对象
        param.start = d.start;// 开始的序号
        param.length = d.length;// 要取的数据的
        var formData = $("#filter_form").serializeArray();// 把form里面的数据序列化成数组
        formData.forEach(function (e) {
            param[e.name] = e.value;
        });
        return param;// 自定义需要传递的参数。
    }
    $('#table_server').dataTable(options);

    $("div.toolbar").html(toolbar.join(""));

}*/
