<%-- 
    Document   : course_detail
    Created on : Jun 24, 2022, 7:27:55 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subject Detail</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/course_content/course_detail.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>

        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>



        <ul class="breadcrumb nav">
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail?id=${requestScope.course.courseID}" class="addlink headnav currentnav">Overview</a></li>
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail/pricepackagedetail?id=${requestScope.course.courseID}" class="addlink headnav">Price Package</a></li>
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail/dimension?id=${requestScope.course.courseID}" class="addlink headnav">Dimension</a></li>
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail/topiclist?id=${requestScope.course.courseID}" class="addlink headnav ">Topic</a></li>
        </ul>  

        <div class="addquestion">
            <a class="addlink_ver2" href="${pageContext.request.contextPath}/addquestion?courseID=${requestScope.course.courseID}">Add question</a>
            <a class="addlink_ver3" href="${pageContext.request.contextPath}/lessonlist?courseID=${requestScope.course.courseID}">View lesson</a>

        </div>


        <div class="content">
            <div class="upperpart row">
                <div class="upperpart__left col-md-6" >
                    <div class="form-group">
                        <label for="">Subject name:</label>
                        <input readonly  type="text" class="form-control" name="" value="${requestScope.course.courseName}"/>
                    </div>
                    <div class="form-group">
                        <label for="">Category:</label>
                        <input readonly type="text" class="form-control" name="" value="${requestScope.category.categoryName}"/>
                    </div>
                    <div class="form-group">
                        <label for="">Subcategory:</label>
                        <input readonly type="text" class="form-control" name="" value="${requestScope.course.subcategory.subcategoryName}"/>
                    </div>
                    <div class="row">

                        <div class="col-md-5">
                            <input <c:if test="${requestScope.course.isFeatured}">checked</c:if> d-inline readonly type="checkbox" name="" value="" />
                                <label for="">Featured subject</label>

                            </div>
                            <div class="col-md-4">
                                <label for="">Status:</label>
                                <input d-inline readonly type="text" class="form-control" name="" value="<c:if test="${requestScope.course.status}">Published</c:if><c:if test="${!requestScope.course.status}">Unpublished</c:if>"/>
                            </div>
                            <div class="form-group ">
                                <label for="">Tag line:</label>
                                    <input readonly type="text" class="form-control" name="briefInfo" value="${requestScope.course.tagline}"/>

                        </div>
                        <div class="form-group ">
                            <label for="">Brief info:</label>
                            <textarea readonly value="${requestScope.course.briefInfo}" rows="3"  class="form-control" name="briefInfo"  >${requestScope.course.briefInfo}</textarea>

                        </div>
                    </div>

                    <div class="form-group ">
                        <label for="">Description:</label>
                        <textarea readonly value="${requestScope.course.description}" rows="5"  class="form-control" name="description"  >${requestScope.course.description}</textarea>
                    </div>
                    <div class="form-group ">
                        <label for="">Owner:</label>
                        <input readonly type="text" class="form-control" name="owner" value="${requestScope.course.owner}"/>

                    </div>


                </div>
                <div class="col-md-6">
                    <img src="${pageContext.request.contextPath}/images/thumbnails/${requestScope.course.thumbnailUrl}">
                </div>
            </div > 


            <a class="addlink" href="${pageContext.request.contextPath}/managesubject/subjectedit?id=${requestScope.course.courseID}">Edit</a>


        </div>   

        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>
        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>




    </body>
</html>
