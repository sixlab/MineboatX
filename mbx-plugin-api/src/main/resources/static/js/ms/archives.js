$(function () {
    checkMinesiteApis("MinesitePageApi", "queryPage", queryPage);

    function queryPage(pageNo) {
        if (pageNo && pageNo > 1) {
            location.href = "/page/" + pageNo;
        }else{
            location.href = "/archives";
        }
    }
});