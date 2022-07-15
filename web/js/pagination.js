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

function paggerLesson(id, pageindex, totalpage, gap, url, queryString, cid)
{
    queryString = "&" +queryString;
    var container = document.getElementById(id);
    var result = '';
    if (pageindex - gap > 1)
        result += '<a href="' + url + '?page=1' + queryString + '&courseID='+cid+'">' + '&laquo;' + '</a>';

    for (var i = pageindex - gap; i < pageindex; i++)
        if (i > 0)
            result += '<a href="' + url + '?page=' + i + queryString + '&courseID='+cid+'">' + i + '</a>';

    result += '<span>' + pageindex + '</span>';

    for (var i = pageindex + 1; i <= pageindex + gap; i++)
        if (i <= totalpage)
            result += '<a href="' + url + '?page=' + i + queryString + '&courseID='+cid+'">' + i + '</a>';

    if (pageindex + gap < totalpage)
        result += '<a href="' + url + '?page=' + totalpage + queryString + '&courseID='+cid+'">' + '&raquo;' + '</a>';

    container.innerHTML = result;
}

function registrationPagger(id, pageindex, totalpage, gap, sortBy, orderBy, subject, fromDate, toDate, status, email)
{
    var container = document.getElementById(id);
    var result = '';
    if (pageindex - gap > 1) {
        result += '<a href="registrationsearch?' + 'subject=' + subject + '&from=' + fromDate + '&to=' + toDate + '&status=' + status + '&email=' + email + '&sortBy=' + sortBy + '&orderBy=' + orderBy + '&page=' + '1' + '">' + 'First' + '</a>';
    }
    for (var i = pageindex - gap; i < pageindex; i++)
        if (i > 0)
            result += '<a href="registrationsearch?' + 'subject=' + subject + '&from=' + fromDate + '&to=' + toDate + '&status=' + status + '&email=' + email + '&sortBy=' + sortBy + '&orderBy=' + orderBy + '&page=' + i + '">' + i + '</a>';

    result += '<span>' + pageindex + '</span>';

    for (var i = pageindex + 1; i <= pageindex + gap; i++)
        if (i <= totalpage)
            result += '<a href="registrationsearch?' + 'subject=' + subject + '&from=' + fromDate + '&to=' + toDate + '&status=' + status + '&email=' + email + '&sortBy=' + sortBy + '&orderBy=' + orderBy + '&page=' + i + '">' + i + '</a>';
    if (pageindex + gap < totalpage) {
        result += '<a href="registrationsearch?' + 'subject=' + subject + '&from=' + fromDate + '&to=' + toDate + '&status=' + status + '&email=' + email + '&sortBy=' + sortBy + '&orderBy=' + orderBy + '&page=' + totalpage + '">' + 'Last' + '</a>';
    }
    container.innerHTML = result;
}
