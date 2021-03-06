<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <link href="<%=basePath%>static/DataTables-1.10.12/media/css/dataTables.uikit.min.css" rel="stylesheet">
    <link href="<%=basePath%>static/DataTables-1.10.12/media/css/uikit.min.css" rel="stylesheet">
    <link href="<%=basePath%>static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>static/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=basePath%>static/craftpip-jquery-confirm/jquery-confirm.min.css" rel="stylesheet">
    <link href="<%=basePath%>static/commons.css" rel="stylesheet">
    <link href="<%=basePath%>static/baiduStyle/main.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>static/nice-validator-0.10.11/jquery.validator.css">
    <script type="text/javascript">
        var basePath = "<%=basePath%>";
    </script>

    <style type="text/css">
        body {
            font-family: "微软雅黑", "Open Sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
        }

        th, td {
            white-space: nowrap;
        }
    </style>
</head>
<body>
<div class="panel panel-default" style="margin-bottom:0px;height:100%">
    <div class="panel-body">
        <form style="margin: 15px 0;" id="filter_form">
            <div class="ui-row">
                <input type="button" class="ui-primary-button ui-ctrl ui-button skin-primary skin-primary-button"
                       id="btnAdd" value="新增用户">
                <span class="ui-group ui-search-group">
                    <label class="ui-ctrl ui-select" style="width: 70px;padding: 0 0px 0 10px;">用户名</label>
					<esui-text-box class="search-box ui-ctrl ui-textbox" id="ctrl-e-ticketNo"
                                   data-ctrl-view-context="e">
							<input class="search-box" data-ui-type="TextBox" name="username" placeholder="请输入用户名搜索"
                                   style="width: 150px;">
							</esui-text-box>
                    <label class="ui-ctrl ui-select" style="width: 70px;padding: 0 0px 0 10px;">姓名</label>
					<esui-text-box class="search-box ui-ctrl ui-textbox" id="ctrl-e-ticketNo"
                                   data-ctrl-view-context="e">
							<input class="search-box" data-ui-type="TextBox" name="name" placeholder="请输入姓名搜索"
                                   style="width: 150px;">
							</esui-text-box>

					<span data-ui-type="Select" data-ui-id="status" data-ui-value="@status"
                          style="display: none; width: 179px;" tabindex="0" id="ctrl-e-status" data-ctrl-id="status"
                          data-ctrl-view-context="e" class="ui-ctrl ui-select"><span id="ctrl-e-status-text"
                                                                                     class="ui-select-text">CHOOSE</span></span>
					<input type="button" class="ui-ctrl ui-button" id="queryBtn" value="搜索">
					<input type="button" class="ui-ctrl ui-button" id="resetBtn" value="重置">
						</span>
            </div>
        </form>
        <table id="table_server" class="uk-table uk-table-hover uk-table-striped" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>id</th>
                <th>姓名</th>
                <th>用户名</th>
                <th>电话</th>
                <th>操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>


<!-- 新增用户模态对话框 -->
<div class="modal" id="userAddmodal" data-backdrop="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">用户管理</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="userAddForm"
                      action="<%=basePath%>user/create">
                    <input type="hidden" name="id" id="id"/>
                    <div class="form-group">
                        <label for="inputName"
                               class="col-sm-2 control-label no-padding-right">姓名</label>
                        <div class="col-sm-10">
                            <input type="text" name="name" style="width:70%"
                                   class="form-control" id="inputName" placeholder="输入姓名"
                                   data-rule="required;length(1~16)">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputUsername"
                               class="col-sm-2 control-label no-padding-right">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" name="username" style="width:70%"
                                   class="form-control" id="inputUsername" placeholder="输入登录帐号"
                                   data-rule="required;length(5~16)">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPhone"
                               class="col-sm-2 control-label no-padding-right">电话</label>
                        <div class="col-sm-10">
                            <input type="text" name="phone" style="width:70%"
                                   class="form-control" id="inputPhone" placeholder="输入联系电话">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="btnUserAdd">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<!-- 新增用户模态对话框 -->
<div class="modal" id="userRolemodal" data-backdrop="false">
    <div class="modal-dialog">
        <div class="modal-content">
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

    <script type="text/javascript" src="<%=basePath%>static/DataTables-1.10.12/media/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/DataTables-1.10.12/media/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/DataTables-1.10.12/media/js/dataTables.uikit.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/craftpip-jquery-confirm/jquery-confirm.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/nls/user.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/nice-validator-0.10.11/jquery.validator.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/nice-validator-0.10.11/local/zh-CN.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/js/jquery.form.js"></script>
</body>
</html>