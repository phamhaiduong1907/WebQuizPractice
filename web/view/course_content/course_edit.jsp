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
        <link href="${pageContext.request.contextPath}/css/course_content/course_edit.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.14.0-beta2/dist/css/bootstrap-select.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.14.0-beta2/dist/js/bootstrap-select.min.js"></script>

    </head>
    <body>

        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>

      

        <ul class="breadcrumb nav">
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail?id=${requestScope.course.courseID}" class="addlink headnav currentnav">Overview</a></li>
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail/pricepackagedetail?id=${requestScope.course.courseID}" class="addlink headnav">Price Package</a></li>
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail/dimension?id=${requestScope.course.courseID}" class="addlink headnav">Dimension</a></li>
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail/topiclist?id=${requestScope.course.courseID}" class="addlink headnav ">Topic</a></li>

        </ul>  

        <form method="post" enctype="multipart/form-data" action="subjectedit">
            <input type="hidden" name="courseID" value="${requestScope.course.courseID}">
            <div class="content">
                <div class="upperpart row">
                    <div class="upperpart__left col-md-6" >
                        <div class="form-group">
                            <label for="">Subject name:</label>
                            <input   type="text" class="form-control" name="courseName" value="${requestScope.course.courseName}"/>
                        </div>
                        <div class="form-group">
                            <label for="">Category:</label>
                            <select class="form-control" id="select_category" name="categoryID">
                                <c:forEach items="${requestScope.categorys}" var="c">
                                    <option value="${c.categoryID}" ${c.categoryName == requestScope.category.categoryName?"selected":""}>${c.categoryName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="">Subcategory:</label>
                            <div id="subCategory_by_category">
                                <select class="form-control"  id='' name = 'subcategoryID'>
                                    <option selected="selected" value="${requestScope.course.subcategory.subcategoryID}">${requestScope.course.subcategory.subcategoryName} </option>
                                </select>
                            </div>
                        </div>
                        <div class="row">

                            <div class="col-md-5">
                                <input <c:if test="${requestScope.course.isFeatured}">checked</c:if> d-inline class="form-check-input"  type="checkbox" name="isFeatured" value="true"/>
                                    <label for="">Featured subject</label>
                                </div>

                            <c:if test="${sessionScope.account.role.roleID == 1}">
                                <div class="col-md-4">
                                    <label for="status">Status:</label>
                                    <select name="status"  class="form-control">
                                        <option ${!requestScope.isPublishable?"disabled":""} value="true" ${requestScope.course.status?"selected":""}>Published</option>
                                        <option ${!requestScope.isPublishable?"selected":""} value="false"${!requestScope.course.status?"selected":""}>UnPublished</option>
                                    </select>
                                </div>
                                <div class="form-group ">
                                    <p class="notification">${requestScope.notifymessage != null ? "Note":""}  ${requestScope.notifymessage} 
                                    </p>
                                </div>
                            </c:if>



                            <div class="form-group ">
                                <label for="">Tag line:</label>
                                <input  type="text" class="form-control" name="tagline" value="${requestScope.course.tagline}"/>
                            </div>
                            <div class="form-group ">
                                <label for="">Brief info:</label>
                                <textarea  value="${requestScope.course.briefInfo}" rows="3"  class="form-control" name="briefInfo"  >${requestScope.course.briefInfo}</textarea>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label for="">Description:</label>
                            <textarea  value="${requestScope.course.description}" rows="5"  class="form-control" name="description"  >${requestScope.course.description}</textarea>
                        </div>
                        <div class="form-group ">
                            <label for="">Owner:</label>

                            <select data-size="2"  class="selectpicker" data-live-search="true" name="owner">
                                <c:forEach items="${requestScope.accounts}" var="a">
                                    <option ${requestScope.course.owner eq a.username?"selected":""} value="${a.username}">${a.username}</option>
                                </c:forEach>
                            </select>
                        </div>

                    </div>
                    <div class="col-md-6">

                        <img src="${pageContext.request.contextPath}/images/thumbnails/${requestScope.course.thumbnailUrl}" id="output">
                        <input class="form-control" type="file" name="thumbnail" placeholder="link to a .png file" onchange="loadFile(event)">

                    </div>
                </div >
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">
                        Save
                    </button>
                    <button class="btn btn-default">
                        <a href="${pageContext.request.contextPath}/managesubject/subjectdetail?id=${requestScope.course.courseID}">Cancel</a>
                    </button>
                </div>
            </div>
        </form>

        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>
        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>


        <script>
            $('.btn dropdown-toggle btn-light').css("width", "300px");

            $(document).on('change', '#select_category', function (event) {
                var categoryID = this.value;
                $.ajax({
                    url: "${pageContext.request.contextPath}/getsubcategory",
                    type: 'POST',
                    dataType: 'html',
                    data: {ID: categoryID},
                })
                        .done(function (data) {
                            $('#subCategory_by_category').html(data);
                        })
                        .fail(function (error) {
                            $('#subCategory_by_category').html("<h1>error</h1>");
                        })
                        .always(function () {

                        });

            });
            var loadFile = function (event) {
                var output = document.getElementById('output');
                output.src = URL.createObjectURL(event.target.files[0]);
                output.onload = function () {
                    URL.revokeObjectURL(output.src) // free memory
                }
            };
            <c:if test="${param.errormessage != null}">
            alert("${param.errormessage}");
            </c:if>
        </script>



    </body>
</html>
