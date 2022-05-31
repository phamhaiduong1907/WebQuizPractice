document.getElementById("loginButton").addEventListener("click", function () {
    document.querySelector(".popup").style.display = "flex";
    document.querySelector(".popup__login-form").style.display = "block";
    document.querySelector(".popup__signup-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "none";
})

document.querySelector(".close").addEventListener("click", function () {
    document.querySelector(".popup").style.display = "none";
})

document.querySelector(".popup__signup a").addEventListener("click", function () {
    document.querySelector(".popup__login-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "none";
    document.querySelector(".popup__signup-form").style.display = "block";
})

document.querySelector(".popup__reset a").addEventListener("click", function () {
    document.querySelector(".popup__login-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "block";
    document.querySelector(".popup__signup-form").style.display = "none";
})

document.querySelector(".popup__signup-form i").addEventListener("click", function () {
    document.querySelector(".popup__login-form").style.display = "block";
    document.querySelector(".popup__signup-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "none";
})

document.querySelector(".popup__reset-form i").addEventListener("click", function () {
    document.querySelector(".popup__login-form").style.display = "block";
    document.querySelector(".popup__signup-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "none";
})

function pagger(id, pageindex, totalpage, gap)
{
    var container = document.getElementById(id);
    var result = '';
    if (pageindex - gap > 1)
        result += '<a href="bloglist?page=1">' + 'First' + '</a>';

    for (var i = pageindex - gap; i < pageindex; i++)
        if (i > 0)
            result += '<a href="bloglist?page=' + i + '">' + i + '</a>';

    result += '<span>' + pageindex + '</span>';

    for (var i = pageindex + 1; i <= pageindex + gap; i++)
        if (i <= totalpage)
            result += '<a href="bloglist?page=' + i + '">' + i + '</a>';

    if (pageindex + gap < totalpage)
        result += '<a href="bloglist?page=' + totalpage + '">' + 'Last' + '</a>';

    container.innerHTML = result;
}







