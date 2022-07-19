<%-- 
    Document   : pricepackage_detail
    Created on : Jun 25, 2022, 11:05:21 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pricepackage detail</title>
        <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/course_content/course_detail.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/course_content/pricepackage_view.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/course_content/pricepackage_edit.css" rel="stylesheet" type="text/css"/>

        <link href="${pageContext.request.contextPath}/css/table.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    </head>


    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>
      

        <ul class="breadcrumb nav ">
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail/pricepackagedetail?id=${requestScope.course.courseID}" class="addlink headnav currentnav">Price Package</a></li>
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail?id=${requestScope.course.courseID}" class="addlink headnav">Overview</a></li>
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail/dimension?id=${requestScope.course.courseID}" class="addlink headnav">Dimension</a></li>
            <li><a href="${pageContext.request.contextPath}/managesubject/subjectdetail/topiclist?id=${requestScope.course.courseID}" class="addlink headnav ">Topic</a></li>

        </ul>  
        <form action="pricepackageedit" method="post">
            <input type="hidden" name="courseID" value="${requestScope.course.courseID}">
            <div class="row ">
                <div class="col-md-5 content">
                    <div class="form-group">
                        <label for="">Pricepackage ID:</label>
                        <input readonly type="text" class="form-control" name="pricePackageID" value="${requestScope.pricePackage.pricePackageID}"/>
                    </div>
                    <div class="form-group">
                        <label for="">Pricepackage name:</label>
                        <input   type="text" class="form-control" name="priceName" value="${requestScope.pricePackage.priceName}"/>
                    </div>
                    <div class="form-group">

                        <label for="">Description</label>
                        <textarea  value="${requestScope.pricePackage.description}" rows="3"  class="form-control" name="description"  >${requestScope.pricePackage.description}</textarea>

                    </div>
                    <div class="form-group" id="nav">
                        <button type="submit" class="btn btn-primary">
                            Save
                        </button>
                        <button class="btn btn-default">
                            <a href="${pageContext.request.contextPath}/managesubject/subjectdetail/pricepackageview?cid=${requestScope.course.courseID}&pid=${requestScope.pricePackage.pricePackageID}">Cancel</a>
                        </button>
                    </div>
                </div>
                <div class="col-md-2 ">
                    <div class="form-group">
                        <label for="">Duration (month):</label>
                        <input  id="duration" required pattern="^[1-9]\d*$"  type="text" class="form-control" name="duration" value="${requestScope.pricePackage.duration}"/>
                        <input   type="checkbox" id="indefinite" name="indefinite" value="true">
                        <label>Indefinite package</label>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-6">
                            <label  for="">List Price </label>
                            <input  pattern="^(?:[1-9]\d*|0)?(?:\.\d+)?$"  required title="Wrong format" name="listPrice" type="text" class="form-control"  value=${requestScope.pricePackage.listPrice}>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="">Sale Price </label>
                            <input  pattern="^(?:[1-9]\d*|0)?(?:\.\d+)?$"  required name="salePrice" type="text" class="form-control"  value=${requestScope.pricePackage.salePrice}>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="status">Status: </label>
                        <select name="status" class="form-control">
                            <option ${!requestScope.isTurnOnable?"disabled":""} value="true" ${requestScope.pricePackage.status?"checked":""}>On</option>
                            <option ${!requestScope.isTurnOnable?"selected":""} value="false" ${!requestScope.pricePackage.status?"checked":""}>Off</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="Is on sale:">Is on sale:</label>
                        <input ${!requestScope.isTurnOnable?"  onclick='return false;'  ":""} name="isOnSale" value="true" class="form-check-input" type="checkbox" ${requestScope.pricePackage.isOnSale?"checked":""}>
                    </div>

                    <div class="form-group">
                        <p class="notify">${requestScope.notifyMessage}</p>
                    </div>
                </div> 

            </div>

        </form>


        <script>
            $("#indefinite").click(function () {
                if ($("#indefinite").prop('checked') == true) {
                    $("#duration").val('');
                    $("#duration").prop('readonly', true);

                } else {
                    $("#duration").prop('readonly', false);
                    $("#duration").val('');

                }
            });
        </script>


        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>
        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>


    </body>
</html>
