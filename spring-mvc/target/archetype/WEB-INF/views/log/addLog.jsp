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
    <link rel="stylesheet" href="${ctx}/css/blog.css" media="screen"/>

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

    <div class="blog-header">
        <h1 class="blog-title">Create New Blog</h1>
    </div>

    <div class="row">
        <div class="col-sm-8 blog-main">
            <form role="form" method="post" action="${ctx}/log/addLog">
                <!--存储CSRF token-->
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="id" value="${log.id}">
                <div class="form-group">
                    <label for="title">标题</label>
                    <input type="text" class="form-control" id="title" name="title" value="${log.title}" placeholder="请输入标题">
                </div>
                <div class="form-group">
                    <label for="content">内容</label>
                    <input type="textarea" class="form-control" id="content" name="content" value="${log.content}" placeholder="请输入内容">
                </div>
                <button type="submit" class="btn btn-default">提交</button>&nbsp;&nbsp;<a href="javascript:history.go(-1);" class="btn">返回</a>
                <p>${message}</p>
            </form>
        </div><!-- /.blog-main -->
    </div><!-- /.row -->
    <br/><br/>
</div><!-- /.container -->

<footer class="blog-footer">
    <p>Blog template built for <a href="http://getbootstrap.com">Bootstrap</a> by <a href="https://twitter.com/mdo">@mdo</a>.</p>
    <p>
        <a href="#">Back to top</a>
    </p>
</footer>
</body>
</html>