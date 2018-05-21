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
            input: $("#editor textarea").val(),
            fileId: $("#fileId").val()
        },
        computed: {
            compiledMarkdown: function () {
                return converter.makeHtml(this.input)
            }
        },
        methods: {
            update: _.debounce(function (e) {
                this.input = e.target.value
            }, 300),
            updateInput: _.debounce(function (e) {
                this.fileId = e.target.value
            }, 300),
            save:function () {
                localStorage.setItem(this.fileId, this.input);
                $("#buttons").slideDown(50);
            },
            submit:function () {
                console.log(this.input);
                console.log(this.fileId);
            }
        }
    })
});