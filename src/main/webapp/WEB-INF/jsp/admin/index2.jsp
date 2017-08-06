<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="description" content="Dashboard"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="shortcut icon" href="<%=basePath%>static/assets/img/favicon.png" type="image/x-icon">


    <!--Basic Styles-->
    <link href="<%=basePath%>static/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link id="bootstrap-rtl-link" href="" rel="stylesheet"/>
    <link href="<%=basePath%>static/assets/css/font-awesome.min.css" rel="stylesheet"/>
    <link href="<%=basePath%>static/assets/css/weather-icons.min.css" rel="stylesheet"/>

    <!--Fonts-->
    <%--<link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300" rel="stylesheet" type="text/css">--%>
    <%--<link href='http://fonts.googleapis.com/css?family=Roboto:400,300' rel='stylesheet' type='text/css'>--%>
    <!--Beyond styles-->
    <link id="beyond-link" href="<%=basePath%>static/assets/css/beyond.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>static/assets/css/demo.min.css" rel="stylesheet"/>
    <link href="<%=basePath%>static/assets/css/typicons.min.css" rel="stylesheet"/>
    <link href="<%=basePath%>static/assets/css/animate.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=basePath%>static/nice-validator-0.10.11/jquery.validator.css">
    <link href="<%=basePath%>static/craftpip-jquery-confirm/jquery-confirm.min.css" rel="stylesheet">
    <link id="skin-link" href="" rel="stylesheet" type="text/css"/>
    <script src="<%=basePath%>static/tabs/css/default.css"></script>
    <script src="<%=basePath%>static/assets/js/skins.min.js"></script>
    <script type="text/javascript">
        var basePath = "<%=basePath%>";
    </script>
    <style type="text/css">
        @media (min-width: 768px) {
            .navbar-nav > li {
                font-size: 18px;
                padding-top: 11px;
                padding-bottom: 11px;
            }
        }
    </style>
    <style type="text/css">
        body {
            font-family: "微软雅黑", "Open Sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
        }

        th, td {
            white-space: nowrap;
        }
    </style>
    <title>拧螺丝-运营管理平台</title>
</head>
<!-- /Head -->
<!-- Body -->
<body>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container" style="width: 100%;padding:0 20px;">
        <div class="navbar-collapse collapse" role="navigation">
            <a href="http://www.test.ningluosi.com" target="_blank" style="float: left"><img
                    src="<%=basePath%>static/images/logo.png" alt=""></a>

            <%--<table style="margin-left: 30px;margin-top: 5px;float: left;color: #fff3f3;">--%>
            <%--<tbody>--%>
            <%--<tr>--%>
            <%--<td>今日登录平台用户总数:</td>--%>
            <%--<td width="30px"><span id="ptTotal" style="color: #fff3f3;font-weight: bold"></span></td>--%>
            <%--<td>今日进入拧螺丝麻将用户总数:</td>--%>
            <%--<td><span id="nlsTotal" style="color: #fff3f3;font-weight: bold"></span></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td>拧螺丝麻将游戏中人数</td>--%>
            <%--<td colspan="3"><span id="gameTotal" style="color: #fff3f3;font-weight: bold"></span>(总数);--%>
            <%--<span id="zycTotal" style="color: #fff3f3;font-weight: bold"></span>(自由场),--%>
            <%--<span id="bxcTotal" style="color: #fff3f3;font-weight: bold"></span>(包厢场)</td>--%>
            <%--</tr>--%>
            <%--</tbody>--%>
            <%--</table>--%>
            <ul class="nav navbar-nav navbar-right hidden-sm">
                <li style="padding: 0"><a href="javascript:void(0)" style="color:#fff3f3">
                    平台总数:<span id="ptTotal" style="color: #fff3f3;font-weight: bold"></span></a></li>
                <li style="padding: 0"><a href="javascript:void(0)" style="color:#fff3f3">
                    拧螺丝总数:<span id="nlsTotal" style="color: #fff3f3;font-weight: bold"></span></a></li>
                <li style="padding: 0"><a href="javascript:void(0)" style="color:#fff3f3">
                    游戏中:<span id="gameTotal" style="color: #fff3f3;font-weight: bold"></span>(总数);
                    <span id="zycTotal" style="color: #fff3f3;font-weight: bold"></span>(自由场),
                    <span id="bxcTotal" style="color: #fff3f3;font-weight: bold"></span>(包厢场)</a></li>
                <li style="padding: 0">
                    <a href="#" style="color:#fff3f3">欢迎您,<%=SecurityUtils.getSubject().getPrincipal()%>
                    </a>
                </li>
                <li style="padding: 0"><a href="javascript:void(0)" id="changepwd" style="color:#fff3f3">修改密码</a></li>
                <li style="padding: 0"><a href="/logout" style="color:#fff3f3">退出</a></li>
            </ul>
        </div>
    </div>
</div>
<!-- /Navbar -->
<!-- Main Container -->
<div class="main-container container-fluid">
    <!-- Page Container -->
    <div class="page-container">
        <!-- Page Sidebar -->
        <div class="page-sidebar" id="sidebar">
            <!-- Sidebar Menu -->
            <ul class="nav sidebar-menu">
                <!--Dashboard-->
                <li class="active">
                    <a href="#" url="<%=basePath%>homepage/index" class="menu_menu">
                        <i class="menu-icon glyphicon glyphicon-home"></i>
                        <span class="menu-text"> 首页 </span>
                    </a>
                </li>
                <!--UI Elements-->
                <c:forEach var="res" items="${resources}">
                    <li>
                        <a href="#" class="menu-dropdown">
                            <i class="menu-icon fa fa-desktop"></i>
                            <span class="menu-text">${res.name}</span>
                            <i class="menu-expand"></i>
                        </a>
                        <ul class="submenu">
                            <c:forEach var="child" items="${res.children}">
                                <li>
                                    <a href="#" url="<%=basePath%>${child.url}" class="menu_menu">
                                        <span class="menu-text">${child.name}</span>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:forEach>
            </ul>
            <!-- /Sidebar Menu -->
        </div>
        <!-- /Page Sidebar -->
        <!-- Page Content -->
        <div class="page-content">

        </div>
        <!-- /Page Content -->

    </div>
    <!-- /Page Container -->
    <!-- Main Container -->

    <div class="page-content">
        <iframe id="contentIframe" src="<%=basePath%>homepage/index" width="100%" height="100%"></iframe>
    </div>
</div>


<!-- 修改密码对话框 -->
<div class="modal" id="modifyPwdModal" data-backdrop="false">
    <div class="modal-dialog" style="min-width: 700px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="modifyPwdForm" action="<%=basePath%>user/changePassword">
                    <input type="hidden" name="newpassword" id="newpassword"/>
                    <input type="hidden" name="oldpassword" id="oldpassword"/>
                    <div class="form-group">
                        <label for="password"
                               class="col-sm-2 control-label no-padding-right">原始密码</label>
                        <div class="col-sm-10">
                            <input type="password" name="password" style="width:70%"
                                   class="form-control" id="password" placeholder="输入原始密码"
                                   data-rule="required;length(6~20)">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="passworda"
                               class="col-sm-2 control-label no-padding-right">新密码</label>
                        <div class="col-sm-10">
                            <input type="password" name="passworda" style="width:70%"
                                   class="form-control" id="passworda" placeholder="输入新密码"
                                   data-rule="required;length(6~20)">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="passwordab"
                               class="col-sm-2 control-label no-padding-right">再次输入密码</label>
                        <div class="col-sm-10">
                            <input type="password" name="passwordab" style="width:70%"
                                   class="form-control" id="passwordab" placeholder="再次输入密码"
                                   data-rule="match[passworda];">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <input type="button" class="btn btn-primary" value="保存" id="btnModifyPwd"/>
                <input type="button" class="btn btn-default" data-dismiss="modal" value="关闭"/>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!--Basic Scripts-->
<script src="<%=basePath%>static/assets/js/jquery.min.js"></script>
<script src="<%=basePath%>static/assets/js/bootstrap.min.js"></script>
<script src="<%=basePath%>static/assets/js/slimscroll/jquery.slimscroll.min.js"></script>
<script src="<%=basePath%>static/assets/js/beyond.js"></script>
<script type="text/javascript" src="<%=basePath%>static/nice-validator-0.10.11/jquery.validator.js"></script>
<script type="text/javascript" src="<%=basePath%>static/nice-validator-0.10.11/local/zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/CryptoJS/components/core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/CryptoJS/rollups/aes.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/CryptoJS/rollups/md5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/nls/index.js"></script>
<script type="text/javascript" src="<%=basePath%>static/craftpip-jquery-confirm/jquery-confirm.min.js"></script>
</body>
<!--  /Body -->
</html>