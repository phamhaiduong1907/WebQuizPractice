/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function pagger(id, pageindex, totalpage, gap, url, queryString)
{
    queryString = "&" +queryString;
    var container = document.getElementById(id);
    var result = '';
    if (pageindex - gap > 1)
        result += '<a href="' + url + '?page=1' + queryString + '">' + '&laquo;' + '</a>';

    for (var i = pageindex - gap; i < pageindex; i++)
        if (i > 0)
            result += '<a href="' + url + '?page=' + i + queryString + '">' + i + '</a>';

    result += '<span>' + pageindex + '</span>';

    for (var i = pageindex + 1; i <= pageindex + gap; i++)
        if (i <= totalpage)
            result += '<a href="' + url + '?page=' + i + queryString + '">' + i + '</a>';

    if (pageindex + gap < totalpage)
        result += '<a href="' + url + '?page=' + totalpage + queryString + '">' + '&raquo;' + '</a>';

    container.innerHTML = result;
}

function cancelRegistration(str)
{
    var result = confirm("Do you wish to cancel this registration?");
    if (result)
    {
        window.location.href = str;
    }
}

