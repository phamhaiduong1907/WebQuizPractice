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
                                <c:choose>
                                    <c:when test="${requestScope.user == null}">
                                    <td><input type="email" name="username" placeholder="Enter username..."></td>
                                    </c:when>
                                    <c:otherwise>
                                    <td>
                                        <input disabled type="text" name="username" value="${requestScope.user.account.username}">
                                        <input type="hidden" name="username" value="${requestScope.user.account.username}">
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                        <tr>
                            <th>First Name</th>
                            <td><input type="text" name="firstName" id="firstName" value="${requestScope.user.firstName}"></td>
                        </tr>
                        <tr>
                            <th>Last Name</th>
                            <td><input type="text" name="lastName" id="lastName" value="${requestScope.user.lastName}"></td>
                        </tr>
                        <tr>
                            <th>Gender</th>
                            <td>
                                <c:choose>
                                    <c:when test="${requestScope.user.gender == true}">
                                        <input name="gender" id="male" type="radio" value="male" checked> <label for="male">Male</label>
                                        &emsp;&emsp;&emsp;&emsp;
                                        <input type="radio" id="female" name="gender" value="female"> <label for="female">Female</label>
                                    </c:when>
                                    <c:when test="${requestScope.user.gender == false}">
                                        <input name="gender" id="male" type="radio" value="male"> <label for="male">Male</label>
                                        &emsp;&emsp;&emsp;&emsp;
                                        <input type="radio" id="female" name="gender" value="female" checked> <label for="female">Female</label>
                                    </c:when>
                                    <c:otherwise>
                                        <input name="gender" id="male" type="radio" value="male"> <label for="male">Male</label>
                                        &emsp;&emsp;&emsp;&emsp;
                                        <input type="radio" id="female" name="gender" value="female"> <label for="female">Female</label>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>Email</th>
                                <c:choose>
                                    <c:when test="${requestScope.user == null}">
                                    <td><input type="email" name="email" placeholder="Enter email...">
                                        <input type="hidden" name="user_exist" value="no">
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <input disabled type="text" name="email" value="${requestScope.user.account.username}">
                                        <input type="hidden" name="email" value="${requestScope.user.account.username}">
                                        <input type="hidden" name="user_exist" value="yes">
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                        <tr>
                            <th>Phone Number</th>
                            <td><input type="tel" name="phone" id="phone" value="${requestScope.user.phoneNumber}"></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <h3>Registration Information</h3>
                            </td>
                        </tr>
                        <c:if test="${requestScope.registration != null}">
                            <tr>
                                <th>Registration ID</th>
                                <td>${requestScope.registration.registrationID}
                                    <input type="hidden" name="registrationID" value="${requestScope.registration.registrationID}">
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <th>Category</th>
                            <td>
                                <select id="select_category" >
                                    <c:forEach items="${requestScope.categories}" var="ca">
                                        <c:choose>
                                            <c:when test="${ca.categoryID == requestScope.registration.course.subcategory.categoryID}">
                                                <option selected value="${ca.categoryID}">${ca.categoryName}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option  value="${ca.categoryID}">${ca.categoryName}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>SubCategory</th>
                            <td>
                                <select id="subCategory_by_category">
                                    <option value="${requestScope.subcategory.subcategoryID}">${requestScope.subcategory.subcategoryName}</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>Subject</th>
                            <td>
                                <select id="course_by_subcategory" name="courseID" onchange="submitForm();">
                                    <option value="${requestScope.course.courseID}">${requestScope.course.courseName}</option>
                                </select>
                            </td>
                        </tr>
                        <c:if test="${requestScope.registration != null}">
                            <tr>
                                <th>Registration Time</th>
                                <td>
                                    <input type="hidden" name="registrationTime" value="${requestScope.registration.registrationTime}">
                                    <input type="datetime" name="registrationTime" value="${requestScope.registration.registrationTime}" disabled></td>
                            </tr>
                        </c:if>
                        <tr>
                            <th>Price Package</th>
                            <td>
                                <select name="pricePackage">
                                    <c:forEach items="${requestScope.prices}" var="p">
                                        <option value="${p.pricePackageID}" <c:if test="${requestScope.registration.pricePackage.pricePackageID == requestScope.pkg.pricePackageID}">selected</c:if>>
                                            ${p.priceName}, list price: ${p.salePrice}, <c:if test="${p.isOnSale == true}">is on</c:if> sale at ${p.listPrice}
                                            </option>
                                    </c:forEach>
                                    <c:if test="${requestScope.registration == null}">
                                        <option value="3">
                                            3 monthc extension, list price: 90.0, is on sale at 100.0
                                        </option>
                                        <option value="4">
                                            6 monthc extension, list price: 180.0, is on sale at 120.0
                                        </option>
                                        <option value="5">
                                            9 monthc extension, list price: 200.0, is on sale at 180.0
                                        </option>
                                    </c:if>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>Status</th>  
                            <td>
                                <c:choose>
                                    <c:when test="${requestScope.registration.status == true}">
                                        <input name="status" id="paid" type="radio" value="paid" checked=""> <label for="paid">Paid</label>
                                        &emsp;&emsp;&emsp;&emsp;
                                        <input type="radio" id="unpaid" name="status" value="unpaid"> <label for="unpaid">Unpaid</label>
                                    </c:when>
                                    <c:when test="${requestScope.registration.status == false}">
                                        <input name="status" id="paid" type="radio" value="paid"> <label for="paid">Paid</label>
                                        &emsp;&emsp;&emsp;&emsp;
                                        <input type="radio" id="unpaid" name="status" value="unpaid" checked> <label for="unpaid">Unpaid</label>
                                    </c:when>
                                    <c:otherwise>
                                        <input name="status" id="paid" type="radio" value="paid"> <label for="paid">Paid</label>
                                        &emsp;&emsp;&emsp;&emsp;
                                        <input type="radio" id="unpaid" name="status" value="unpaid"> <label for="unpaid">Unpaid</label>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>

                        <c:choose>
                            <c:when test="${requestScope.registration != null}">
                                <tr>
                                    <th>Valid Date</th>
                                    <td>From &emsp;<input disabled type="date" name="fromDate"  value="${requestScope.registration.validFrom}">&emsp; to &emsp;<input disabled type="date" name="toDate" value="${requestScope.registration.validTo}">
                                        <input type="hidden" name="fromDate"  value="${requestScope.registration.validFrom}"><input  type="hidden" name="toDate" value="${requestScope.registration.validTo}">
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>

                    </table>
                    <div style="width: 100%; text-align: center; margin-top: 20px;">
                        <input type="submit" value="SAVE">
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