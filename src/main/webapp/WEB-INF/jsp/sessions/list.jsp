<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<span class="ui-group ui-search-group">
                    <label class="ui-ctrl ui-select" style="width: 70px;padding: 0 0px 0 10px;">用户名</label>
					<esui-text-box class="search-box ui-ctrl ui-textbox" id="ctrl-e-ticketNo"
								   data-ctrl-view-context="e">
							<input class="search-box" data-ui-type="TextBox" name="username" placeholder="请输入用户名搜索"
								   style="width: 150px;">
							</esui-text-box>
					<span data-ui-type="Select" data-ui-id="status" data-ui-value="@status"
						  style="display: none; width: 179px;" tabindex="0" id="ctrl-e-status" data-ctrl-id="status"
						  data-ctrl-view-context="e" class="ui-ctrl ui-select"><span id="ctrl-e-status-text"
																					 class="ui-select-text">CHOOSE</span></span>
					<input type="button" class="ui-ctrl ui-button" id="queryBtn" value="搜索">
						</span>
			</div>
		</form>
		<table id="table_server" class="uk-table uk-table-hover uk-table-striped" cellspacing="0" width="100%">
			<thead>
			<tr>
						<th>sessionId</th>
						<th>登录帐号</th>
						<th>登录IP</th>
						<th>登录时间</th>
						<th>操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<script type="text/javascript" src="<%=basePath%>static/DataTables-1.10.12/media/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/DataTables-1.10.12/media/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/DataTables-1.10.12/media/js/dataTables.uikit.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/craftpip-jquery-confirm/jquery-confirm.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/nls/sessions.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/nice-validator-0.10.11/jquery.validator.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/nice-validator-0.10.11/local/zh-CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/jquery.form.js"></script>
</body>
</html>