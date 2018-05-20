$(function () {
    $(".comment-thumb").click(function () {
        var commentId = $(this).data("commentid");
        $.ajax({
            url: "/comment/thumb?_t=" + (new Date().getTime()),
            data: {
                commentId: commentId
            },
            dataType: "json",
            type: "post",
            success: function (data) {
                if(data.success){

                }else{

                }
            },
            error: function (err) {
                console.log(err)
            }
        });
        return false;
    });

    $(".comment-replay").click(function () {
        var commentId = $(this).data("commentid");
        alert(commentId);
        return false;
    });
});