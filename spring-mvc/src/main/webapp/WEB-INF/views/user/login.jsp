<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen"/>

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="${ctx}/css/signin.css" media="screen"/>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!--jquery must before bootstrap-->
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form class="form-signin" method="post" action="${ctx}/j_spring_security_check">
        <!--存储CSRF token-->
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputName" class="sr-only">Email address</label>
        <!--<input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>-->
        <%--<input type="text" id="inputName" name="userName" class="form-control" placeholder="Email address" required autofocus>--%>
        <input type="text" id="inputName" name="username" class="form-control" placeholder="Email address" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <%--<input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>--%>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
        <div class="checkbox">
            <%--<label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>--%>
            <input type="checkbox" name="_spring_security_remember_me" value="remember-me"> Remember me，valid in one week
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <br/>
        <%--<p>${message}</p>--%>
        <div class="error ${param.error==true?'':'hide'}">
            登录失败<br>
            ${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
        </div>
    </form>
</div> <!-- /container -->
</body>
</html>