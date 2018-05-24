$(function () {
    $("#upload-btn").click(function () {
        uploadPic();
    });

    function uploadPic() {
        var form = document.getElementById('uploadFrm'),
            formData = new FormData(form);
        $.ajax({
            url: "/auth/images/upload",
            type: "post",
            data: formData,
            processData: false,
            contentType: false,
            success: function (resp) {
                if (resp.success) {
                    alert("上传成功");
                }else{
                    alert(resp.data.msg);
                }
            },
            error: function (err) {
                alert("网络连接失败,稍后重试", err);
            }
        })
    }

});