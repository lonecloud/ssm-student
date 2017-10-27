<%--
  Created by IntelliJ IDEA.
  User: lonecloud
  Date: 2017/10/26
  Time: 下午10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>登录界面</title>
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
</head>

<style>
    .col-center-block {
        float: none;
        display: block;
        margin-left: auto;
        margin-right: auto;
    }
    .logo{
        position: absolute;
        /*background-position: 0 -96px;*/
        width: 25px;
        height: 25px;
        font-size: 0;
        background-image: url(${pageContext.request.contextPath}/assets/logo.jpg);
    }
</style>
</head>

<body>
<div class="container">
    <div class="row myCenter">
        <div class="col-xs-6 col-md-4 col-center-block">
            <form class="form-signin" method="post" action="${pageContext.request.contextPath}/user/doLogin">
                <h2 class="form-signin-heading ">请登录${param.msg}</h2>
                <input type="hidden" name="location" id="location">
                <label for="username" class="sr-only">用户名</label>
                <input type="text" name="username" id="username" class="form-control" placeholder="用户名" required autofocus>
                <label for="inputPassword" class="sr-only">密码</label>
                <input type="password" id="inputPassword" name="password" class="form-control" placeholder="密码" required>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me">
                        记住我 </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit" id="submitForm">登录</button>
            </form>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/assets/js/jquery-2.1.4.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>

</html>
