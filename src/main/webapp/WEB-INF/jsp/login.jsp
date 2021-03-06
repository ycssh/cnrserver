﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>拧螺丝-运营管理平台</title>
    <link href="<c:url value='/static/css/login.css'/>" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/js/CryptoJS/components/core.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/CryptoJS/rollups/aes.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/CryptoJS/rollups/md5.js"></script>
    <STYLE>
        body {
            background: #ebebeb;
            font-family: "Helvetica Neue", "Hiragino Sans GB", "Microsoft YaHei", "\9ED1\4F53", Arial, sans-serif;
            color: #222;
            font-size: 12px;
        }

        * {
            padding: 0px;
            margin: 0px;
        }

        .top_div {
            background: #008ead;
            width: 100%;
            height: 400px;
        }

        .ipt {
            border: 1px solid #d3d3d3;
            padding: 10px 10px;
            width: 190px;
            border-radius: 4px;
            padding-left: 35px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
            -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
            -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
            transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
        }

        .ipt:focus {
            border-color: #66afe9;
            outline: 0;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6)
        }

        .u_logo {
            background: url("<%=basePath%>static/images/login/username.png") no-repeat;
            padding: 10px 10px;
            position: absolute;
            top: 43px;
            left: 40px;

        }

        .p_logo {
            background: url("<%=basePath%>static/images/login/password.png") no-repeat;
            padding: 10px 10px;
            position: absolute;
            top: 12px;
            left: 40px;
        }

        a {
            text-decoration: none;
        }

        .tou {
            background: url("<%=basePath%>static/images/login/tou.png") no-repeat;
            width: 97px;
            height: 92px;
            position: absolute;
            top: -87px;
            left: 140px;
        }

        .left_hand {
            background: url("<%=basePath%>static/images/login/left_hand.png") no-repeat;
            width: 32px;
            height: 37px;
            position: absolute;
            top: -38px;
            left: 150px;
        }

        .right_hand {
            background: url("<%=basePath%>static/images/login/right_hand.png") no-repeat;
            width: 32px;
            height: 37px;
            position: absolute;
            top: -38px;
            right: -64px;
        }

        .initial_left_hand {
            background: url("<%=basePath%>static/images/login/hand.png") no-repeat;
            width: 30px;
            height: 20px;
            position: absolute;
            top: -12px;
            left: 100px;
        }

        .initial_right_hand {
            background: url("<%=basePath%>static/images/login/hand.png") no-repeat;
            width: 30px;
            height: 20px;
            position: absolute;
            top: -12px;
            right: -112px;
        }

        .left_handing {
            background: url("<%=basePath%>static/images/login/left-handing.png") no-repeat;
            width: 30px;
            height: 20px;
            position: absolute;
            top: -24px;
            left: 139px;
        }

        .right_handinging {
            background: url("<%=basePath%>static/images/login/right_handing.png") no-repeat;
            width: 30px;
            height: 20px;
            position: absolute;
            top: -21px;
            left: 210px;
        }

    </STYLE>

    <SCRIPT type="text/javascript">
        $(function () {
            if(window.location.href!=top.location.href){
                top.location.href=window.location.href
            }
//            if(window.location.href==top.win)
            //得到焦点
            $("#password").focus(function () {
                $("#left_hand").animate({
                    left: "150",
                    top: " -38"
                }, {
                    step: function () {
                        if (parseInt($("#left_hand").css("left")) > 140) {
                            $("#left_hand").attr("class", "left_hand");
                        }
                    }
                }, 2000);
                $("#right_hand").animate({
                    right: "-64",
                    top: "-38px"
                }, {
                    step: function () {
                        if (parseInt($("#right_hand").css("right")) > -70) {
                            $("#right_hand").attr("class", "right_hand");
                        }
                    }
                }, 2000);
            });
            //失去焦点
            $("#password").blur(function () {
                $("#left_hand").attr("class", "initial_left_hand");
                $("#left_hand").attr("style", "left:100px;top:-12px;");
                $("#right_hand").attr("class", "initial_right_hand");
                $("#right_hand").attr("style", "right:-112px;top:-12px");
            });
        });
    </SCRIPT>

    <META name="GENERATOR" content="MSHTML 11.00.9600.17496">
</HEAD>
<BODY>
<div id="top" style="height: 45px;background-color: #222;">
    <img src="<%=basePath%>static/images/logo.png" alt="" style="margin-left: 30%"/>
    <span style="color:white;font-size: 18px;padding-top: 11px;padding-bottom: 11px;">运营管理平台</span>
</div>
<div id="center">
    <form action="" method="post" id="loginForm" class="loginForm">
        <div style="background: rgb(255, 255, 255);/* margin: -100px auto auto; */border: 1px solid rgb(231, 231, 231);margin-top: 143px;float: right;margin-right: 100px;border-image: none;width: 300px;height: 300px;text-align: center;">

            <p style="padding: 30px 0px 10px; position: relative;"><span class="u_logo"></span> <input class="ipt"
                                                                                                       type="text"
                                                                                                       placeholder="请输入用户名"
                                                                                                       id="username"
                                                                                                       name="username"
                                                                                                       value="">
            </p>
            <p style="position: relative;"><span class="p_logo"></span>
                <input class="ipt" type="password" placeholder="请输入密码" name="password" id="password" value="">
            </p>

            <c:if test="${jcaptchaEbabled}">
                <P style="padding: 10px 0px 10px; position: relative;text-align: left;padding-left: 30px;">
                    <INPUT class="ipt" style="width: 100px" id="jcaptchaCode" name="jcaptchaCode" type="text"
                           placeholder="请输入验证码" value="">
                    <img id="jcaptcha" src="${pageContext.request.contextPath}/jcaptcha.jpg"
                         style="margin-bottom:-15px;margin-left: 5px;" title="点击更换验证码"/>
                </P>
            </c:if>
            <DIV style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
                <P style="margin: 0px 35px 20px 45px;"><SPAN style="float: right;">
              <A style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;"
                 id="loginBtn" href="#">登录</A>
           </span></p></div>
            <div>
                <p style="position: relative;"><span style="color:red">
							${error}
							<c:if test="${not empty param.kickout}">您的账号已在其他地点登陆。</c:if>
							<c:if test="${not empty param.maxonline}">在线用户已经达到最大值，请联系管理员。</c:if>
						</span> <span class="loginError tip11" id="hint_msg_checkcode"></span></p>
            </div>
        </div>
    </form>
</div>
</div>

<%--
<form action="" method="post" id="loginForm" class="loginForm">
    <DIV class="top_div"></DIV>
    <DIV style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 200px; text-align: center;">
        <DIV style="width: 165px; height: 96px; position: absolute;">
            <DIV class="tou"></DIV>
            <DIV class="initial_left_hand" id="left_hand"></DIV>
            <DIV class="initial_right_hand" id="right_hand"></DIV>
        </DIV>
        <P style="padding: 30px 0px 10px; position: relative;"><SPAN
                class="u_logo"></SPAN> <INPUT class="ipt" type="text" placeholder="请输入用户名" id="username" name="username"
                                              value="">
        </P>
        <P style="position: relative;"><SPAN class="p_logo"></SPAN>
            <INPUT class="ipt" type="password" placeholder="请输入密码" name="password" id="password" value="">
        </P>
        <c:if test="${jcaptchaEbabled}">
            <P style="padding: 10px 0px 10px; position: relative;text-align: left;padding-left: 30px;float: left;">
                <INPUT class="ipt" style="width: 100px" id="jcaptchaCode" name="jcaptchaCode" type="text"
                       placeholder="请输入验证码" value="">
                <img id="jcaptcha" src="${pageContext.request.contextPath}/jcaptcha.jpg"
                     style="margin-top:1px;margin-left: 5px;" title="点击更换验证码"/>
            </P>
        </c:if>
        <p style="position: relative;"><span style="color:red">
							${error}
							<c:if test="${not empty param.kickout}">您的账号已在其他地点登陆。</c:if>
							<c:if test="${not empty param.maxonline}">在线用户已经达到最大值，请联系管理员。</c:if>
						</span> <span class="loginError tip11" id="hint_msg_checkcode"></span></p>
        <DIV style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
            <P style="margin: 0px 35px 20px 45px;"><SPAN style="float: right;">
              <A style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;"
                 id="loginBtn" href="#">登录</A>
           </SPAN></P></DIV>
    </DIV>
</form>--%>
<script type="text/javascript">

    $(function () {
        $(document).on("click", "#loginBtn", function () {
            login();
        });
        $(document).keyup(function (event) {
            if (event.keyCode == 13) {
                login();
            }
        });
        if ("${error}") {
            $("#password").val("");
        }
        $("#jcaptcha").click(function () {
            $("#jcaptcha").attr("src", "${pageContext.request.contextPath}/jcaptcha.jpg?time=" + new Date());
        });
    });

    function login() {
        var userName = $.trim($("#username").val());
        var passWord = $.trim($("#password").val());
        if (userName == "" || passWord == "") {
            alert("请输入用户名和密码！");
            return false;
        }
        if ($("#jcaptcha").size() > 0 && $("#jcaptchaCode").val() == "") {
            alert("请输入验证码！");
            return false;
        }
        $.ajax({
            type: "POST",
            url: "<c:url value='/login/aes'/>",
            timeout: 600000,
            success: function (data) {
                aesKey = data;
            },
            async: false
        });
        $("#password").val(Encrypt(passWord));
        $("#loginForm").submit();
    }
    var aesKey;
    function Encrypt(word) {
        var key = CryptoJS.enc.Utf8.parse(aesKey);
        var iv = CryptoJS.enc.Utf8.parse(aesKey);
        var srcs = CryptoJS.enc.Utf8.parse(word);
        var encrypted = CryptoJS.AES.encrypt(srcs, key, {iv: iv, mode: CryptoJS.mode.CBC});
        return encrypted.toString();
    }
    function load() {
        if (window.parent && window.parent.length > 0 && window.top.document.URL != document.URL) {
            window.top.location = document.URL;
        }
    }
</script>
</BODY>
</HTML>
