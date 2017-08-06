<%--
  Created by IntelliJ IDEA.
  User: ycssh
  Date: 2016/8/3
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
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
   <%-- <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300"
          rel="stylesheet" type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,300' rel='stylesheet' type='text/css'>--%>
    <!--Beyond styles-->
    <link id="beyond-link" href="<%=basePath%>static/assets/css/beyond.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>static/assets/css/demo.min.css" rel="stylesheet"/>
    <link href="<%=basePath%>static/assets/css/typicons.min.css" rel="stylesheet"/>
    <link href="<%=basePath%>static/assets/css/animate.min.css" rel="stylesheet"/>
    <title>首页</title>
    <script type="text/javascript">
        var basePath = "<%=basePath%>";
    </script>
</head>
<body style="padding: 20px;background-color: #fff">
<div class="row" style="height: 100%">
    <div class="col-xs-4" style="padding: 0">
        <div class="databox bg-white radius-bordered">
            <div class="databox-left bg-themesecondary">
                <div class="databox-piechart">
                    <div data-toggle="easypiechart" class="easyPieChart" data-barcolor="#fff" data-linecap="butt"
                         data-percent="50" data-animate="500" data-linewidth="3" data-size="47"
                         data-trackcolor="rgba(255,255,255,0.1)" style="width: 47px; height: 47px; line-height: 47px;">
                        <span class="white font-90"></span>
                        <canvas width="47" height="47"></canvas>
                    </div>
                </div>
            </div>
            <div class="databox-right">
                <a href="<%=basePath%>account/index">
                    <span class="databox-number themesecondary" id="totalaccount"></span>
                    <div class="databox-text darkgray">注册用户数</div>
                </a>
                <div class="databox-stat themesecondary radius-bordered">
                    <i class="stat-icon icon-lg fa fa-tasks"></i>
                </div>
            </div>
        </div>
        <div class="databox bg-white radius-bordered">
            <div class="databox-left bg-themeprimary">
                <div class="databox-piechart">
                    <div id="users-pie" data-toggle="easypiechart" class="easyPieChart" data-barcolor="#fff"
                         data-linecap="butt" data-percent="76" data-animate="500" data-linewidth="3" data-size="47"
                         data-trackcolor="rgba(255,255,255,0.1)" style="width: 47px; height: 47px; line-height: 47px;">
                        <span class="white font-90"></span>
                        <canvas width="47" height="47"></canvas>
                    </div>
                </div>
            </div>
            <div class="databox-right">
                <a href="<%=basePath%>payrecord/index">
                    <span class="databox-number themeprimary" id="totalPay"></span>
                    <div class="databox-text darkgray">充值总额</div>
                </a>
                <div class="databox-state bg-themeprimary">
                    <i class="fa fa-check"></i>
                </div>
            </div>
        </div>
        <div style="height:30%;" id="accountPie"></div>
        <div style="height:30%;" id="payPie"></div>
    </div>
    <div class="col-xs-8" id="accountLine" style="height:50%;padding: 5px;"></div>
    <div class="col-xs-8" id="payLine" style="height:50%;padding: 5px;">
    </div>
    <script src="<%=basePath%>static/assets/js/jquery.min.js"></script>
    <script src="<%=basePath%>static/echarts/echarts-all.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/nls/homepage.js"></script>
</body>
</html>
