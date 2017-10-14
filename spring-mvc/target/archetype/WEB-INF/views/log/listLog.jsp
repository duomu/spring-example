<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <sec:csrfMetaTags/>
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
    <script src="${ctx}/js/custom/home.js"></script>

    <%--全局变量--%>
    <script type="text/javascript">
        var ctx='${ctx}';
        var environment={
            currentPage: ${currentPage},
            pageCount:${pageCount}
        }

    </script>
</head>
<body>
<div class="blog-masthead">
    <div class="container">
        <nav class="blog-nav">
            <a class="blog-nav-item active" href="#">Home</a>
            <a class="blog-nav-item" href="#">New features</a>
            <a class="blog-nav-item" href="#">Press</a>
            <a class="blog-nav-item" href="#">New hires</a>
            <a class="blog-nav-item" href="#">About</a>
            <a class="blog-nav-item" href="${ctx}/user/logout">Logout</a>
            <%--<a class="blog-nav-item" href="${ctx}/j_spring_security_logout" id="logout">Logout</a>--%>
        </nav>
    </div>
</div>
<div class="container">

    <div class="blog-header">
        <h1 class="blog-title">The Bootstrap Blog</h1>
        <p class="lead blog-description">The official example template of creating a blog with Bootstrap.</p>
    </div>

    <div class="row">
        <div class="col-sm-8 blog-main">
            <!--存储CSRF token-->
            <input id="csrfToken" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div>
                <a id="add_log" href="#" >添加</a>&nbsp;&nbsp;&nbsp; <a id="update_log" href="#" >修改</a>&nbsp;&nbsp;&nbsp; <a id="delete_log" href="#" >删除</a>
            </div>
            <table class="table table-striped table-bordered table-hover" style="width: 800px; margin: 0 auto;">
                <thead>
                    <tr>
                        <th>&nbsp;</th>
                        <th>标题</th>
                        <th>内容</th>
                        <th>创建时间</th>
                        <th>最后更新时间</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="log" items="${logs}" varStatus="status">
                        <tr>
                            <td><input type="checkbox" value="${log.id}"></td>
                            <td>${log.title}<a id="check_log" href="#"></a></td>
                            <td>${log.content}</td>
                            <td><fmt:formatDate value="${log.createTime}" pattern="yyyy年MM月dd日"/></td>
                            <td><fmt:formatDate value="${log.updateTime}" pattern="yyyy年MM月dd日"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <nav>
                <ul class="pager">
                    <li><a href="#" id="prevPage">Previous</a></li>
                    <li><a href="#" id="nextPage">Next</a></li>
                </ul>
            </nav>
            <input type="hidden" name="currentPage" value="${currentPage}">
        </div><!-- /.blog-main -->
    </div><!-- /.row -->

</div><!-- /.container -->

<footer class="blog-footer">
    <p>Blog template built for <a href="http://getbootstrap.com">Bootstrap</a> by <a href="https://twitter.com/mdo">@mdo</a>.</p>
    <p>
        <a href="#">Back to top</a>
    </p>
</footer>
</body>
</html>