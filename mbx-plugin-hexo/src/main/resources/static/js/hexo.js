if (!window.MinesiteApi) {
    window.MinesiteApi = {};
}

function checkMinesiteApis(apiName, funcName, func) {
    if(!window[apiName]){
        window[apiName] = {};
    }

    if (!window[apiName][funcName]) {
        window[apiName][funcName] = func;
    }
}

checkMinesiteApis(window.MinesiteApi, "loadPage", function (selector, url, type, params) {
    if (!type) {
        type = "GET";
    }
    if (!params) {
        if (typeof type == 'string') {
            params = {};
        } else {
            params = type;
            type = "GET";
        }
    }
    $.ajax({
        url: url,
        data: params,
        type: type,
        success: function (data) {
            $(selector).html(data);
        },
        error: function () {

        }
    });
});

$(function () {
    $(document).off("click", ".mbx-pagination .active.num");
    $(document).on("click", ".mbx-pagination .active.num", function () {
        var pageNo = $(this).data("num");
        window.MinesitePageApi.queryPage(pageNo);
    });
});