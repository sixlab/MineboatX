$(function () {

    $("#submitBtn").click(function () {
        $.ajax({
            url:"/login",
            dataType:"json",
            type:"post",
            data:{
                "username": $("#username").val(),
                "password": $("#password").val()
            },
            success:function (data) {
                if(data.success && data.data.token){
                    alert("登录成功")
                    location.href = "/user/auth/getInfo";
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