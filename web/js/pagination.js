/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function pagination(id, url, pageindex, queryString, totalpage, gap) {
    var pagination = document.getElementById(id);
    var result = '<ul>';
    // process pagination UI
    if (totalpage <= 4) {
        for (var i = 1; i <= totalpage; i++) {
            if (i === pageindex) {
                result += '<li><a class="active" href="' + url + '?page=' + i + queryString + '">' + i + '</a></li>';
            } else {
                result += '<li><a href="' + url + '?page=' + i + queryString + '">' + i + '</a></li>';
            }
        }
    } else {
        if (pageindex - gap >= 1) {
            result += '<li><a href="' + url + '?page=1' + queryString + '">&laquo;</a></li>';
        }
        if (pageindex - gap <= 0) {
            for (var i = 1; i < pageindex; i++) {
                result += '<li><a href="' + url + '?page=' + i + queryString + '">' + i + '</a></li>';
            }
        } else if (pageindex + gap > totalpage) {
            for (var i = totalpage - gap; i < pageindex; i++) {
                result += '<li><a href="' + url + '?page=' + i + queryString + '">' + i + '</a></li>';
            }
        } else {
            for (var i = pageindex - gap + 1; i < pageindex; i++)
                result += '<li><a href="' + url + '?page=' + i + queryString + '">' + i + '</a></li>';
        }
        result += '<li><a class="active" href="' + url + '?page=' + pageindex + queryString + '">' + pageindex + '</a></li>';
        if (pageindex - gap <= 0) {
            for (var i = pageindex + 1; i <= (gap + 1); i++) {
                result += '<li><a href="' + url + '?page=' + i + queryString + '">' + i + '</a></li>';
            }
        } else if (pageindex + gap > totalpage) {
            for (var i = pageindex + 1; i <= totalpage; i++) {
                result += '<li><a href="' + url + '?page=' + i + queryString + '">' + i + '</a></li>';
            }
        } else {
            for (var i = pageindex + 1; i < (pageindex + gap); i++) {
                result += '<li><a href="' + url + '?page=' + i + queryString + '">' + i + '</a></li>';
            }
        }
        if (pageindex + gap <= totalpage)
            result += '<li><a href="' + url + '?page=' + totalpage + queryString + '">&raquo;</a></li>';

    }
    result += '</ul>';
    pagination.innerHTML = result;
}

function submitForm(id) {
    document.getElementById(id).submit();
}


