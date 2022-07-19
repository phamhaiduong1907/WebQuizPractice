<%-- 
    Document   : lesson_list
    Created on : Jun 23, 2022, 8:06:55 PM
    Author     : Zuys
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lesson list</title>      
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/course_content/lesson_list.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>

        <section class="main">



            <h1>Subject lessons</h1>

            <h4>Subject name: ${requestScope.course.courseName}</h4>
            <div class="search__bar">
                <form class="form" action="searchlesson" method="GET">
                    <input type="text" name="courseID" hidden value="${requestScope.course.courseID}">
                    <div class="search__bar__item">
                        <input name="lesson_name" id="lessonName" type="text" placeholder="Type lesson name to search">
                    </div>
                    <div class="search__bar__item">
                        <label for="price_package">Price Package</label>
                        <select name="price_package" id="price_package">
                            <option value="0">All price packages</option>
                            <c:forEach items="${requestScope.prices}" var="p">
                                <option value="${p.pricePackageID}">${p.pricePackageID}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="search__bar__item">
                        <label for="lesson_type">Lesson Type</label>
                        <select name="lesson_type" id="lesson_type">
                            <option value="0">All lesson types</option>
                            <option value="1">Subject topic</option>
                            <option value="2">Lesson</option>
                            <option value="3">Quiz</option>
                        </select>
                    </div>
                    <div class="search__bar__item">
                        <label for="lesson_status">Status</label>
                        <select name="lesson_status" id="lesson_status">
                            <option value="All">All lesson status</option>
                            <option value="1">Active</option>
                            <option value="0">Inactive</option>
                        </select>
                    </div>
                    <input type="submit" value="Search">
                </form>
                <div class="add__button">
                    <a class="addlink" href="addlesson?">Add lesson</a>
                </div>
            </div>

            <div class="lesson_table">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Lesson</th>
                            <th>Order</th>
                            <th>Type</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.lessons}" var="l">
                            <tr>
                                <td>${l.lessonID}</td>
                                <td>${l.lessonName}</td>
                                <td>${l.lessonOrder}</td>
                                <td>${l.lessonType.lessonTypeName}</td>
                                <td>
                                    <a class="${l.status?"status__active":"status__inactive"}" href=lessonstatus?lessonID=${l.lessonID}&courseID=${requestScope.course.courseID}&page=${requestScope.page}>${l.status?"Active":"Inactive"}</a>
                                </td>
                                <td>
                                    <a class="edit_alink" href="editlesson?lessonID=${l.lessonID}&courseID=${requestScope.course.courseID}">Edit</a>
                                    <a class="view__alink" href="viewlesson?lessonID=${l.lessonID}&courseID=${requestScope.course.courseID}">View</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>


            <div id="pagination" class="pagination"></div>
        </section>

        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>
        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>

        <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
        <script>paggerLesson("pagination", ${requestScope.pageindex}, ${requestScope.totalpage}, 3, "${requestScope.url}", "${requestScope.querystring}", ${requestScope.course.courseID});</script>
        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>
        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp" />
    </body>

</html>
