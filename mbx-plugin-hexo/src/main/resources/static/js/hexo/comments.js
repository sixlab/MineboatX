function MbxComment(path, token, id) {
    fetch('https://api.sixlab.cn/hexo/comments/list/' + path + "?token=" + token, {
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, same-origin, *omit
        method: 'GET', // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, cors, *same-origin
        redirect: 'follow', // manual, *follow, error
        referrer: 'no-referrer', // *client, no-referrer
    })
        .then(function (response) {
            return response.text();
        })
        .then(function (myHTML) {
            if(myHTML){
                document.getElementById(id).innerHTML = myHTML;
            }else{
                document.getElementById(id).innerHTML = '';
            }
        });
}