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

    new Vue({
        el: '#root',
        data: {
            content: $("#editor textarea").val(),
            fileId: $("#fileId").val()
        },
        computed: {
            compiledMarkdown: function () {
                return converter.makeHtml(this.content)
            }
        },
        methods: {
            updateContent: _.debounce(function (e) {
                this.content = e.target.value
            }, 300),
            updateField: _.debounce(function (e) {
                this.fileId = e.target.value
            }, 300),
            save:function () {
                localStorage.setItem(this.fileId, this.content);
                $("#buttons").slideDown(50);
            },
            submit:function () {
                $.ajax({
                    url:"/auth/article/submit?_t=" + (new Date().getTime()),
                    data:{
                        fileId:this.fileId,
                        content:this.content
                    },
                    type:"post",
                    dataType:"json",
                    success:function(data){
                        if(data.success){
                            alert("成功");
                        } else{
                            alert("失败");
                        }
                    },
                    error:function (err) {
                        console.log(err)
                    }
                });
            }
        }
    })
});