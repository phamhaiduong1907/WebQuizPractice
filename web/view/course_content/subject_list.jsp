<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/course_content/template.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- Bootstrap's CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">


    </head>

    <body>
        <header>
            <div class="heading_logo">
                <img src="images/logo.png" alt="alt"/>
            </div>
            <nav>
                <ul class="nav_links">
                    <li><a href="home">Home</a></li>
                    <li><a href="subjectList">Subject</a></li>
                    <li><a href="bloglist">Blog</a></li>
                    <!--                    <li><a href="#">My Registration</a></li>
                                        <li><a href="#">Practice</a></li>-->
                    <li>
                        <a href="#" class="login" id="loginButton"><i class="fa fa-user-alt"></i>
                            <c:out value="${sessionScope.account.username}"/>
                        </a>
                        <div class="submenu">
                            <ul>
                                <li><a href="#" id="openProfile">User Profile</a></li>
                                <li><a href="#" id="openChangePassword">Change Password</a></li>
                                <li><a href="logout">Log out</a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </nav>
        </header>
        <a href="newsubject">Create a new subject</a>

        <section class="main">
            <section class="left__block">
                <table class="subject__table">
                    <thead>
                        <tr class="table__row">
                            <th>ID</th>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Description</th>
                            <th>NoL</th>
                            <th>Owner</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="c" items="${requestScope.courses}">
                            <tr class="table__row">
                                <td>${c.courseID}</td>
                                <td>${c.courseName}</td>
                                <td>${c.subcategory.subcategoryName}</td>
                                <td>${c.description}</td>
                                <td>0</td>
                                <td>${c.owner}</td>
                                <td>${c.status?"Published":"Unpublished"}</td>
                                <td><a href="#">Edit</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div id="pagination" class="pagination"></div>
            </section>

            <section class="right__block">
                <div class="option__filter">
                    <div class="option__searchbar">
                        <form action="managesearch" method="GET">
                            <input type="text" name="search" placeholder="Type something to search...">
                            </div>
                            <div class="option__checkbox">
                                <h3>Category: </h3>
                                <div class="option__options-value">
                                    <div class="accordion accordion-flush" id="accordionFlushExample">
                                        <c:forEach items="${requestScope.categories}" var="cate">
                                            <div class="accordion-item">
                                                <h2 class="accordion-header" id="flush-headingOne">
                                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse${cate.categoryID}" aria-expanded="false" aria-controls="flush-collapseOne">
                                                        <input type="checkbox" onclick="checkAllBox(this, ${cate.categoryID})">&emsp;<span>${cate.categoryName}</span>
                                                    </button>
                                                </h2>
                                                <div id="flush-collapse${cate.categoryID}" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                                                    <div class="accordion-body">
                                                        <c:forEach items="${cate.subcategories}" var="sc">
                                                            <div class="subcategory">
                                                                <input type="checkbox" class="${cate.categoryID}" name="subcategory" value="${sc.subcategoryID}"> <span>${sc.subcategoryName}</span>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                            <div class="option__sort">
                                <h3>Status: </h3>
                                <select name="sort">
                                    <option value="1">Published</option>
                                    <option value="0">Unpublished</option>
                                </select>
                            </div>
                            <div class="search__button">
                                <button type="submit">Search</button>
                            </div>
                            <div class="contact__link">
                                <a href="newsubject">Add new subject</a>
                            </div>
                        </form>
                    </div>
            </section>
        </section>

        <script src="${pageContext.request.contextPath}/js/script.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
        <script>pagger("pagination", ${requestScope.pageindex}, ${requestScope.totalpage}, 3, "${requestScope.url}", "${requestScope.querystring}");</script>
    </body>

</html>