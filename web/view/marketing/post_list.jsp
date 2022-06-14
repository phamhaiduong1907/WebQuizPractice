<%-- 
    Document   : post_list
    Created on : Jun 12, 2022, 1:18:35 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Post</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/marketing/default_marketing.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/marketing/add_post.css">





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
                        <li><a href="dashboard.html">Dashboard</a></li>
                        <li><a href="post.html">Posts</a></li>
                        <li><a href="#">Sliders</a></li>
                    </ul>
                </nav>
            </aside>

            <!-- RIGHT CONTENT -->
            <aside class="right">
                <div class="right_content">
                    <ul class="breadcrumb">
                        <li><a href="#">Home</a></li>
                        <li><a href="#">Post</a></li>
                        <li><a href="#">Add a post</a></li>

                    </ul>
                </div>
                <!--Starting of the filter-->
                <section class="option__box">
                    <div class="option__filter">
                        <div class="option__searchbar">
                            <form action="bloglist" method="GET">
                                <input type="text" name="search"  placeholder="Type something to search...">
                                </div>
                                <div class="option__checkbox">
                                    <h3>Category: </h3>
                                    <div class="option__options-value">
                                        <div class="accordion accordion-flush" id="accordionFlushExample">
                                            <c:forEach items="${requestScope.categories}" var="cate">
                                                <div class="accordion-item">
                                                    <h2 class="accordion-header" id="flush-headingOne">
                                                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse${cate.categoryID}" aria-expanded="false" aria-controls="flush-collapseOne">
                                                            ${cate.categoryName}
                                                        </button>
                                                    </h2>
                                                    <div id="flush-collapse${cate.categoryID}" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                                                        <div class="accordion-body">
                                                            <c:forEach items="${cate.subcategories}" var="sc">
                                                                <div class="subcategory">
                                                                    <input type="checkbox" name="subcategory" value="${sc.subcategoryID}"> <span>${sc.subcategoryName}</span>
                                                                </div>
                                                            </c:forEach></div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="option__sort">
                                    <select name="sort">
                                        <option selected disabled>Sort by:</option>
                                        <option value="DESC">Date added(newest)</option>
                                        <option value="ASC">Date added(oldest)</option>
                                    </select>
                                </div>
                                <div class="search__button">
                                    <button type="submit">Search</button>
                                </div>
                                <div class="contact__link">
                                    <a href="#">Contact Information</a>
                                </div>
                            </form>
                        </div>
                </section>

                <div class="table__data">
                    <table>
                        <tr>
                            <th>Post ID</th>
                            <th>Title</th>
                            <th>Category</th>
                            <th>Feature</th>
                            <th>Status</th>
                            <th>Author</th>
                            <th>Updated date</th>

                        </tr>
                        <c:forEach items="${requestScope.posts}" var="p">
                            <tr>
                                <td>${p.postID}</td>
                                <td>${p.title}</td>
                                <td>${p.subcategory.subcategoryName}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${p.isFeatured}">
                                            Yes
                                        </c:when>
                                        <c:otherwise>
                                            No
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${p.status}">
                                            On
                                        </c:when>
                                        <c:otherwise>
                                            Off
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${p.author.username}</td>
                                <td>${p.updatedDate}</td>
                                <td>
                                    <a disabled="disabled" href="changeblogstatus?postID=${p.postID}&status=true">Show</a>
                                    <a href="changeblogstatus?postID=${p.postID}&status=false">Hide</a>
                                </td>
                                <td><a href="view?postID=${p.postID}">View</a></td>
                                
                            </tr>

                        </c:forEach>
                    </table>

                </div>
                 <div id="pagination" class="pagination"></div>



                <footer>
                    FOOTER
                </footer>
            </aside>
        </section>

        <section class="popup">
            <div class="popup__content">
                <img src="../../images/close.png" alt="" class="close">

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

        </section>

        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/script.js"></script>
        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/profile.js"></script>
        <script src="${pageContext.request.contextPath}/js/changepass.js"></script>
        <script src="${pageContext.request.contextPath}/js/common/home.js"></script>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
                <script>pagger("pagination", ${requestScope.pageindex}, ${requestScope.totalpage}, 3, "${requestScope.url}", "${requestScope.querystring}");</script>

    </body>
</html>
