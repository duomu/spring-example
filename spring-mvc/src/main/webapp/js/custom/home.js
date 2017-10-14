$(function () {
    //给ajax请求设置csrf token
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajaxSetup({
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        }
    });

    $("#add_log").click(function () {
        window.location.href=ctx+"/log/addLogPage/";
    });

    $("#update_log").click(function () {
        var size=$("input[type='checkbox']:checked").size();
        if(size<=0 || size>1){
            alert("请选择一条日志！");
            return;
        }
        var id=$("input[type='checkbox']:checked").val();

        window.location.href=ctx+"/log/updateLogPage/"+id;
    });

    $("check_log").click(function () {
        var id=$(this).parent().find("input[type='checkbox']:checked").val();
        window.location.href=ctx+"/log/updateLogPage/"+id;
    });

    $("#delete_log").click(function () {
        var size=$("input[type='checkbox']:checked").size();
        if(size<=0){
            alert("请至少选择一条日志！");
            return;
        }

        var ids=[];
       $("input[type='checkbox']:checked").each(function () {
           ids.push($(this).val());
       });

        var idsStr=ids.toString();

        // var idsJson=JSON.stringify(ids);
        var csrfName=$("#csrfToken").attr("name");
        var csrfToken=$("#csrfToken").attr("value");

        $.ajax({
            url:"/log/deleteLogByIds",
            type:"POST",
            data:{
                "ids":idsStr,
            },
            dataType: "json",
            success:function (data) {
                if(data){
                    if(data.code==0){
                        alert(data.message);
                        window.location.href=ctx+"/user/loginPage/";
                        return;
                    }else{
                        alert(data.message);
                        window.location.href=ctx+"/log/listLogByPage/";
                        return;
                    }
                }
            },
            error:function (msg) {
                // alert(msg);
                // return;
            }
        });
    });

    //分页查询
    $("#prevPage").click(function () {
        if(environment.currentPage>1){
            environment.currentPage-=1;
            document.location.href=ctx+"/log/listLogByPage?currentPage="+environment.currentPage;
            // $.get("/listLogByPage", {"currentPage":environment.currentPage, "pageSize":10},
            //     function (data) {});
        }
    });

    $("#nextPage").click(function () {
        if(environment.currentPage<environment.pageCount){
            environment.currentPage+=1;
            document.location.href=ctx+"/log/listLogByPage?currentPage="+environment.currentPage;
            // $.get("/listLogByPage", {"currentPage":environment.currentPage, "pageSize":10},
            //     function (data) {});
        }
    });
    
});
