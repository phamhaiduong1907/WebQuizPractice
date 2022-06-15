<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Slider List</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/slider/slider_list.css">
        <%
            int pageindex = (Integer) request.getAttribute("pageindex");
            int totalpage = (Integer) request.getAttribute("totalpage");
            String url = (String) request.getAttribute("url");
            String queryString = (String) request.getAttribute("queryString");
        %>
    </head>

    <body>
        <header>
            <div class="logo">
                <p>LOGO</p>
            </div>

            <div class="user_bar">
                <div class="user_log">
                    <i class="fa fa-user-circle"></i>
                    <span class="user_name">Marketing</span>
                    <div class="submenu">
                        <ul>
                            <li><a href="#" id="openProfile">User Profile</a></li>
                            <li><a href="#" id="openChangePassword">Change Password</a></li>
                            <li><a href="#">Log out</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>

        <section class="main">
            <!-- LEFT NAVIGATION BAR -->
            <aside class="left">
                <nav>
                    <ul>
                        <li><a href="#">Dashboard</a></li>
                        <li><a href="#">Posts</a></li>
                        <li><a href="${requestScope.url}">Sliders</a></li>
                    </ul>
                </nav>
            </aside>

            <!-- RIGHT CONTENT -->
            <aside class="right">
                <div class="right_content">
                    <div class="tool__stripe">
                        <form action="list" method="GET" id="statusFilter">
                            <label for="status">Choose status to filter: </label>
                            <select name="status" id="status" onchange="submitForm('statusFilter')">
                                <option value="all" ${requestScope.status == "all" ?"selected":""}>All</option>
                                <option value="active" ${requestScope.status == "active" ?"selected":""}>Active</option>
                                <option value="inactive" ${requestScope.status == "inactive" ?"selected":""}>Inactive</option>
                            </select>
                        </form>
                        <form action="list" method="GET" id="formSearch">
                            <div class="search__input">
                                <input type="text" id="searchByTitle" name="title"
                                       placeholder="Enter title or backlink to search"
                                       value="${requestScope.title}">
                            </div>
                            <button type="submit">Search</button>
                        </form>
                    </div>
                    <div class="add__hyperlink">
                        <a href="add">Add a slider</a>
                    </div>
                    <c:if test="${requestScope.sliders.size() != 0}">
                        <div class="slider__list">
                            <table>
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Title</th>
                                        <th>Image</th>
                                        <th>Backlink</th>
                                        <th>Status</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.sliders}" var="s">
                                        <tr>
                                            <td>${s.sliderID}</td>
                                            <td>${s.title}</td>
                                            <td>
                                                <img src="${pageContext.request.contextPath}/${s.imageUrl}" alt="">
                                            </td>
                                            <td class="slider__backlink">
                                                <p>${s.backlink}</p> 
                                            </td>
                                            <td class="slider__status" id="status${s.sliderID}">
                                                <span class="status ${s.status?"status__active":"status__inactive"}">
                                                    ${s.status?"Active":"Inactive"}
                                                </span>
                                            </td>
                                            <td class="slider__action">
                                                <button href="#" class="slider__hide" onclick="changeStatus(this,'status${s.sliderID}','${s.sliderID}','${s.status?"inactive":"active"}')">${s.status?"Hide":"Show"}</button>
                                                <a href="detail?sliderID=${s.sliderID}" class="slider__detail">Detail</a>
                                            </td>
                                        </tr>    
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="pagination" id="pagination"></div>
                    </c:if>
                    <c:if test="${requestScope.sliders.size() == 0}">
                        <p style="font-size: 2rem; text-align: center; font-weight: bold;">There are no records found</p>
                    </c:if>
                </div>
                <footer>
                    FOOTER
                </footer>
            </aside>
        </section>

        <section class="popup">
            <div class="popup__content">
                <img src="${pageContext.request.contextPath}/images/close.png" alt="" class="close">

                <div class="form_user-profile">
                    <h2>User Profile</h2>
                    <form action="#">
                        <div class="user__avatar">
                            <!-- <input type="file" name="" id=""> -->
                        </div>
                        <input type="text" name="email" id="email" disabled placeholder="Your email">
                        <input type="text" name="firstName" id="firstName" placeholder="Enter your first name">
                        <input type="text" name="lastName" id="lastName" placeholder="Enter your last name">
                        <input type="text" name="phone" id="phone" placeholder="Enter your phone">
                        <div class="profile__gender signup__gender">
                            <h5>Gender</h5>
                            <input type="radio" name="" id="">
                            <p>Male</p>
                            <input type="radio" name="" id="">
                            <p>Female</p>
                        </div>
                        <input type="text" name="address" id="address" placeholder="Enter your address">
                        <div class="form__button">
                            <button type="submit">Save</button>
                        </div>
                    </form>
                </div>

                <div class="form__change-password" style="display: none;">
                    <h2>Change Password</h2>
                    <form action="#">
                        <input type="password" placeholder="Enter your current password">
                        <input type="password" placeholder="Enter new password">
                        <input type="password" placeholder="Reenter your new password">
                        <div class="form__button">
                            <button type="submit">Save</button>
                        </div>
                    </form>
                </div>
            </div>
            <!--<a href="list?page=2&status=false"></a>-->

        </section>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
        <script>
                                                    function changeStatus(link, id, sliderID, statusParam) {
                                                        var text = link.textContent;
                                                        var status = document.getElementById(id);
                                                        if(text === "Hide"){
                                                            link.innerHTML = "Show";
                                                            status.innerHTML = '<span class="status status__inactive">Inactive</span>';
                                                        } else if(text === "Show"){
                                                            link.innerHTML = "Hide";
                                                            status.innerHTML = '<span class="status status__active">Active</span>';
                                                        }
                                                        $.ajax({
                                                            url: "/SWP391-SE1617-NET_Group06-QuizWebsite/slider/changestatus",
                                                            type: "GET",
                                                            data: {
                                                                sliderID: sliderID,
                                                                statusParam: statusParam
                                                            },
                                                            success: function (data) {
//                                                                
                                                            }
                                                        });

                                                    }
                                                    function submitForm(id) {
                                                        document.getElementById(id).submit();
                                                    }
                                                    function checkAction(sliderId) {
                                                        if (${requestScope.deactiveLink}) {
                                                            window.alert("There is at least 1 active slider!");
                                                            return false;
                                                        } else {
                                                            window.location.href = "changestatus?id=" + sliderId;
                                                            return true;
                                                        }
                                                    }

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

                                                    pagination('pagination', '<%=(url)%>', <%=(pageindex)%>, '<%=(queryString)%>',<%=(totalpage)%>, 2);
        </script>
    </body>

</html>