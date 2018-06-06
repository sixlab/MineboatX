$(function () {
    showdown.setOption('omitExtraWLInCodeBlocks', true);
    showdown.setOption('prefixHeaderId', "");
    showdown.setOption('ghCompatibleHeaderId', true);
    showdown.setOption('headerLevelStart', 3);
    showdown.setOption('parseImgDimensions', true);
    showdown.setOption('simplifiedAutoLink', true);
    showdown.setOption('literalMidWordUnderscores', true);
    showdown.setOption('strikethrough', true);
    showdown.setOption('tables', true);
    showdown.setOption('ghCodeBlocks', true);
    showdown.setOption('tasklists', true);
    showdown.setOption('smoothLivePreview', true);
    showdown.setOption('encodeEmails', true);
    showdown.setOption('encodeEmails', true);
    showdown.setOption('encodeEmails', true);
    showdown.setOption('extensions', []);
    showdown.setOption('sanitize', false);
    var converter = new showdown.Converter();

    $("#saveBtn").click(function () {
        var fileId = $("#fileId").val();
        var content = $("#editor textarea").val();
        localStorage.setItem(fileId, content);
        $("#buttons").slideDown(50);
    });

    $("#submitBtn").click(function () {
        var fileId = $("#fileId").val();
        var content = $("#editor textarea").val();
        $.ajax({
            url: "/auth/article/submit?_t=" + (new Date().getTime()),
            data: {
                fileId: fileId,
                content: content
            },
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    alert("成功");
                    location.href="/auth/article/list/10"
                } else {
                    alert("失败");
                }
            },
            error: function (err) {
                console.log(err)
            }
        });
    });

    $("#imageList").click(function () {
        $.ajax({
            url: "/auth/images/list?_t=" + (new Date().getTime()),
            data: {
                path: $("#fileId").val()
            },
            type: "get",
            success: function (data) {
                setHtml(data);
            },
            error: function (err) {
                console.log(err)
            }
        });
    });

    $(document).on('input propertychange', '#inputText', function () {
        var html = converter.makeHtml($("#inputText").val());
        setHtml(html);
    });

    $("#inputText").trigger("propertychange");

    function setHtml(html) {
        $("#preview").contents().find("body").html(html);
    }
});