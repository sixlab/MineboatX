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
                if(data.success && data.data.username){
                    alert(data)
                }else{
                    alert("失败：")
                    alert(data)
                }
            },
            error: function (data) {
                console.log(data);
            }
        });
        return false;
    })
});