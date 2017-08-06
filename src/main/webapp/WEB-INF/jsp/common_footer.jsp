<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script type="text/javascript" src="<%=basePath%>js/DataTables-1.10.12/media/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/DataTables-1.10.12/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/DataTables-1.10.12/media/js/dataTables.uikit.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/craftpip-jquery-confirm/jquery-confirm.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>