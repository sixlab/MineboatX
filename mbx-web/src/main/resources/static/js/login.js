$(function () {

    $("#submitBtn").click(function () {
        $.ajax({
            url:"/login",
            dataType:"json",
            type:"post",
            contentType: "application/json",
            data:JSON.stringify({
                "username": $("#username").val(),
                "password": $("#password").val()
            }),
            success:function (data) {
                if(data.success && data.data.token){
                    alert("登录成功");
                    var url = $("#url").val();
                    if(url){
                        location.href = url;
                    }else{
                        location.href = "/auth/user/getInfo";
                    }
                }else{
                    alert("失败：" + data.message);
                }
            },
            error: function (data) {
                alert("错误");
                console.log(data);
            }
        });
        return false;
    })
});