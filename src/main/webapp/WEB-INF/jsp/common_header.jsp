<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
    Object o = request.getSession().getAttribute("currUser");

    if(o==null){
        response.sendRedirect(request.getContextPath()+"/control/login/publish");
    }else{
    }
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "">

<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=basePath%>js/DataTables-1.10.12/media/css/dataTables.uikit.min.css" rel="stylesheet">
<link href="<%=basePath%>js/DataTables-1.10.12/media/css/uikit.min.css" rel="stylesheet">
<link href="<%=basePath%>js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>js/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet">
<link href="<%=basePath%>js/craftpip-jquery-confirm/jquery-confirm.min.css" rel="stylesheet">
<link href="<%=basePath%>css/commons.css" rel="stylesheet">
<link href="<%=basePath%>js/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<script type="text/javascript">
    var basePath = "<%=basePath%>";
</script>