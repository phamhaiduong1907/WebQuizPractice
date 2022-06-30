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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sale/regis_detail.css">
</head>

<body>
    <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>


    <section class="main">
        <!-- LEFT NAVIGATION BAR -->


        <!-- RIGHT CONTENT -->
        <aside class="right">
            <div class="right_content">
                <h1>Registration Details</h1>
                <form action="registrationdetail" method="POST">
                    <table>
                        <tr>
                            <td colspan="2">
                                <h3>User Information</h3>
                            </td>
                        </tr>
                        <tr>
                            <th>Username</th>

                            <td>
                                ${requestScope.user.account.username}
                            </td>

                        </tr>
                        <tr>
                            <th>First Name</th>
                            <td>${requestScope.user.firstName}</td>
                        </tr>
                        <tr>
                            <th>Last Name</th>
                            <td>${requestScope.user.lastName}</td>
                        </tr>
                        <tr>
                            <th>Gender</th>
                            <td>
                                <c:choose>
                                    <c:when test="${requestScope.user.gender == true}">
                                        Male
                                    </c:when>
                                    <c:when test="${requestScope.user.gender == false}">
                                        Female 
                                    </c:when>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>Email</th>
                            <td>
                                ${requestScope.user.account.username}
                            </td>
                        </tr>
                        <tr>
                            <th>Phone Number</th>
                            <td>${requestScope.user.phoneNumber}</td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <h3>Registration Information</h3>
                            </td>
                        </tr>
                        <tr>
                            <th>Registration ID</th>
                            <td>${requestScope.registration.registrationID}
                            </td>
                        </tr>
                        <tr>
                            <th>Subject</th>
                            <td>
                                ${requestScope.course.courseName}
                            </td>
                        </tr>
                        <tr>
                            <th>Registration Time</th>
                            <td>
                                ${requestScope.registration.registrationTime}</tr>
                        <tr>
                            <th>Price Package</th>
                            <td>
                                ${requestScope.registration.pricePackage.priceName} 
                            </td>
                        </tr>
                        <tr>
                            <th>Status</th>  
                            <td>
                                <c:choose>
                                    <c:when test="${requestScope.registration.status == true}">
                                        Paid
                                    </c:when>
                                    <c:when test="${requestScope.registration.status == false}">
                                        Unpaid
                                    </c:when>
                                </c:choose>
                            </td>
                        </tr>

                        <tr>
                            <th>Valid Date</th>
                            <td>From &emsp;${requestScope.registration.validFrom}&emsp;to&emsp;${requestScope.registration.validTo}
                            </td>
                        </tr>
                    </table>
                    <div class="abtn" style="width: 100%; text-align: center; margin-top: 20px;">
                        <a class="back"  href="../sale/registrationlist">Back</a>
                        <a class="save" href="../sale/registrationedit?id=${requestScope.registration.registrationID}">Edit</a>  
                    </div>
                </form>
            </div>

            <footer>
                FOOTER
            </footer>
        </aside>
    </section>

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

        $(document).on('change', '#subCategory_by_category', function (event) {
            var categoryID = this.value;
            $.ajax({
                url: "${pageContext.request.contextPath}/getcoursebysubcategoryid",
                type: 'POST',
                dataType: 'html',
                data: {ID: categoryID},
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