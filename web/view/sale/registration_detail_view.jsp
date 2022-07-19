<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sale Index</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.1.3/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
    <link href="${context}/css/test_content/quiz_view.css" rel="stylesheet"/>
</head>

<body>
    <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>


    <section class="main">
        <aside class="right" style="padding: 3% 2%;">
            <div class="right_content" style="box-shadow: 0 0 10px 5px #e1e5ee;">
                <h1>Registration Details</h1>
                <div style="margin: 0 auto;" class="upperpart row col-md-11">
                    <div class="form-group col-md-6" style="padding: 0 20px;">
                        <div class="row">
                            <h3>User Information</h3>
                        </div>
                        <div class="form-group">
                            <label>Username</label>
                            <input readonly  type="text" class="form-control" name="" value="${requestScope.user.account.username}"/>
                        </div>
                        <div class="form-group">
                            <label>First Name</label>
                            <input readonly  type="text" class="form-control" name="" value="${requestScope.user.firstName}"/>
                        </div>
                        <div class="form-group">
                            <label>Last Name</label>
                            <input readonly  type="text" class="form-control" name="" value="${requestScope.user.lastName}"/>
                        </div>
                        <div class="form-group">
                            <label>Gender</label>
                            <c:choose>
                                <c:when test="${requestScope.user.gender == true}">
                                    <input readonly  type="text" class="form-control" name="" value="Male"/>
                                </c:when>
                                <c:when test="${requestScope.user.gender == false}">
                                    <input readonly  type="text" class="form-control" name="" value="Female"/> 
                                </c:when>
                            </c:choose>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input readonly  type="text" class="form-control" name="" value="${requestScope.user.account.username}"/>
                        </div>
                        <div class="form-group">
                            <label>Phone Number</label>
                            <input readonly  type="text" class="form-control" name="" value="${requestScope.user.phoneNumber}"/>
                        </div>
                    </div>
                    <div class="form-group col-md-6" style="padding: 0 20px;">
                        <div class="form-group">
                            <h3>Registration Information</h3>
                        </div>
                        <div class="form-group">
                            <label>Registration ID</label>
                            <input readonly  type="text" class="form-control" name="" value="${requestScope.registration.registrationID}"/>
                        </div>
                        <div class="form-group">
                            <label>Subject</label>
                            <input readonly  type="text" class="form-control" name="" value="${requestScope.course.courseName}"/>
                        </div>
                        <div class="form-group">
                            <label>Registration Time</label>
                            <input readonly  type="text" class="form-control" name="" value="${requestScope.registration.registrationTime}"/>
                        </div>
                        <div class="form-group">
                            <label>Price Package</label>
                            <input readonly  type="text" class="form-control" name="" value="${requestScope.registration.pricePackage.priceName}"/>
                        </div>
                        <div class="form-group">
                            <label>Status</label>
                            <c:choose>
                                <c:when test="${requestScope.registration.status == true}">
                                    <input readonly  type="text" class="form-control" name="" value="Paid"/>
                                </c:when>
                                <c:when test="${requestScope.registration.status == false}">
                                    <input readonly  type="text" class="form-control" name="" value="Unpaid"/>
                                </c:when>
                            </c:choose>
                        </div>
                        <div class="form-group">
                            <label>Valid Date</label>
                            <input readonly  type="text" class="form-control" name="" value="${requestScope.registration.validFrom} - ${requestScope.registration.validTo}"/>
                        </div>
                    </div>
                </div>
                <div class="btngrps" style="width: 100%; text-align: center; margin-top: 20px;">
                    <a href="../sale/registrationlist" style="border-radius: 5px; padding: 7px;">Back</a>
                    <a style="background-color: white; color: #000;" href="../sale/registrationedit?id=${requestScope.registration.registrationID}">Edit</a>  
                </div>
            </div>
        </aside>
    </section>
    <footer>
        FOOTER
    </footer>
    <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>


    <script src="js/userPopup.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <script>
        $(document).on('change', '#select_category', function (event) {
            var categoryID = this.value;
            $.ajax({
                url: "../getsubcategory",
                type: 'POST',
                dataType: 'html',
                data: {ID: categoryID}
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

        $(document).on('change', '#subCategory_by_category', function (event) {
            var categoryID = this.value;
            $.ajax({
                url: "${pageContext.request.contextPath}/getcoursebysubcategoryid",
                type: 'POST',
                dataType: 'html',
                data: {ID: categoryID}
            })
                    .done(function (data) {
                        $('#course_by_subcategory').html(data);
                    })
                    .fail(function (error) {
                        $('#course_by_subcategory').html("<h1>error</h1>");
                    })
                    .always(function () {

                    });
        });
    </script>

</body>

</html>