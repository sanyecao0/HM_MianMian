<%@ page import="java.io.File" %><%--
  Created by IntelliJ IDEA.
  User: violet
  Date: 2023/6/6
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <title>黑马面面后台管理系统 | Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
</head>
<script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
<script>
    $(function() {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
    window.onload = function () {
        if (window.parent.window != window) {
            window.top.location = "${pageContext.request.contextPath}/login.jsp";
        }
    }
</script>
<body>
<div class="shell">
    <div id="img-box">
        <img src="img/5.jpg" alt="">
    </div>
    <form action="${pageContext.request.contextPath}/authentication?operation=login" method="post">
        <div id="form-body">
            <div id="welcome-lines">
                <div id="w-line-1">OVERRIDE SYSTEM</div>
                <div id="w-line-2">登录系统</div>
            </div>
            <div id="input-area">
                <div class="f-inp form-group has-feedback">
                    <input type="email" name="email" class="form-controls" placeholder="Email" value="admin@itcast.cn">
                    <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                </div>
                <div class="f-inp form-group has-feedback">
                    <input type="password" name="password" class="form-controls" placeholder="Password" value="123">
                    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                </div>
            </div>
<%--            <div id="submit-button-cvr">--%>
<%--                <button type="submit" id="submit-button">LOGIN</button>--%>
<%--            </div>--%>
<%--            <div class="row">--%>
<%--                --%>
<%--            </div>--%>
            <div class="col-xs-8">
                <div class="checkbox icheck text-center">
                    <label style="color: red">${error}</label>
                </div>
            </div>
            <div id="submit-button-cvr">
                <button type="submit" id="submit-button">LOGIN</button>
            </div>
            <div class="social-auth-links text-center">
                <p>- 或者 -</p>
                <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-qq"></i> 腾讯QQ用户登录</a>
                <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-weixin"></i> 微信用户登录</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>
