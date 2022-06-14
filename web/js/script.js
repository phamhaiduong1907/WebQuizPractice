document.getElementById("loginButton").addEventListener("click", function () {
    document.querySelector(".popup").style.display = "flex";
    document.querySelector(".popup__login-form").style.display = "block";
    document.querySelector(".popup__signup-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "none";
});

document.querySelector(".close").addEventListener("click", function () {
    document.querySelector(".popup").style.display = "none";
});

document.querySelector(".popup__signup a").addEventListener("click", function () {
    document.querySelector(".popup__login-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "none";
    document.querySelector(".popup__signup-form").style.display = "block";
});

document.querySelector(".popup__reset a").addEventListener("click", function () {
    document.querySelector(".popup__login-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "block";
    document.querySelector(".popup__signup-form").style.display = "none";
});

document.querySelector(".popup__signup-form i").addEventListener("click", function () {
    document.querySelector(".popup__login-form").style.display = "block";
    document.querySelector(".popup__signup-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "none";
});

document.querySelector(".popup__reset-form i").addEventListener("click", function () {
    document.querySelector(".popup__login-form").style.display = "block";
    document.querySelector(".popup__signup-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "none";
});

function pagger(id, pageindex, totalpage, gap, url, querystring)
{
    querystring = "&" + querystring;
    var container = document.getElementById(id);
    var result = '';
    if (pageindex - gap > 1)
        result += '<a href="' + url + '?page=1' + querystring + '">' + 'First' + '</a>';

    for (var i = pageindex - gap; i < pageindex; i++)
        if (i > 0)
            result += '<a href="' + url + '?page=' + i + querystring + '">' + i + '</a>';

    result += '<span>' + pageindex + '</span>';

    for (var i = pageindex + 1; i <= pageindex + gap; i++)
        if (i <= totalpage)
            result += '<a href="' + url + '?page=' + i + querystring + '">' + i + '</a>';

    if (pageindex + gap < totalpage)
        result += '<a href="' + url + '?page=' + totalpage + querystring + '">' + 'Last' + '</a>';

    container.innerHTML = result;
}


function disableButton(buttonID) {
    var button = document.getElementById(buttonID);
    button.disabled = true;
    button.innerHTML = 'Registered';
}




