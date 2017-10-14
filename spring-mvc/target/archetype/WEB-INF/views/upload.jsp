<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
</head>
<body>
<%--同步请求--%>
<form id="myform" method="post" action="${ctx}/user/syncUpload" enctype="multipart/form-data">
    <input id="picture" type="file" name="picture" accept="image/jpeg,image/png,image/gif"/><br/>
    <input id="uploadBtn" type="submit" value="提交"/>
</form>
<%--异步请求--%>
<%--<form id="myform" method="post" enctype="multipart/form-data">
    <input id="picture" type="file" name="picture" accept="image/jpeg,image/png,image/gif"/><br/>
    <input id="uploadBtn" type="button" value="提交"/>
</form>--%>

<script type="text/javascript">
    var ctx='${ctx}';

    $(document).ready(function () {
//        $("#uploadBtn").click(function () {
//            if(!$("input[type=file]").val()) {
//                alert("请选择文件！");
//                return;
//            }
//
//            //异步提交
//            var formData=new FormData($("#myform")[0]);
//            $.ajax({
//                url:ctx+"/user/upload",
//                data:formData,
//                type:"post",
//                processData:false,
//                contentType:false,
//                success:function (data) {
//                    if(data){
//                        alert(data.message);
//                    }
//                }
//            });
//        });

        var message='${message}';
        if(message) {
            alert(message);
        }
    });
</script>
</body>
</html>