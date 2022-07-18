<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

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
                    <form class="row" action="registrationedit" method="POST">
                        <div class="form-group col-md-6" style="padding: 0 20px;">
                            <div class="row">
                                <h3>User Information</h3>
                            </div>
                            <div class="form-group">
                                <label>Username</label>
                                <input class="form-control" <c:if test="${requestScope.registration != null}">disabled</c:if>  required type="email" name="username" value="${requestScope.user.account.username}" placeholder="Enter username...">
                                </div>
                                <div class="form-group">
                                    <label>First Name</label>
                                    <input class="form-control" required type="text" name="firstName" id="firstName" value="${requestScope.user.firstName}">
                            </div>
                            <div class="form-group">
                                <label>Last Name</label>
                                <input class="form-control" required type="text" name="lastName" id="lastName" value="${requestScope.user.lastName}">
                            </div>
                            <div class="form-group">
                                <label>Gender</label>
                                <div class="form-group" style="padding-bottom: 0;">
                                    <c:choose>
                                        <c:when test="${requestScope.user.gender == true}">
                                            <input required name="gender" id="male" type="radio" value="male" checked> <label for="male">Male</label>
                                            &emsp;&emsp;&emsp;&emsp;
                                            <input required type="radio" id="female" name="gender" value="female"> <label for="female">Female</label>
                                        </c:when>
                                        <c:when test="${requestScope.user.gender == false}">
                                            <input name="gender" id="male" type="radio" value="male"> <label for="male">Male</label>
                                            &emsp;&emsp;&emsp;&emsp;
                                            <input type="radio" id="female" name="gender" value="female" checked> <label for="female">Female</label>
                                        </c:when>
                                        <c:otherwise>
                                            <input required name="gender" id="male" type="radio" value="male"> <label for="male">Male</label>
                                            &emsp;&emsp;&emsp;&emsp;
                                            <input required type="radio" id="female" name="gender" value="female"> <label for="female">Female</label>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <c:choose>
                                    <c:when test="${requestScope.user == null}">
                                        <input class="form-control" required type="email" name="email" placeholder="Enter email...">
                                        <input type="hidden" name="user_exist" value="no">
                                    </c:when>
                                    <c:otherwise>
                                        <input class="form-control" readonly type="text" name="email" value="${requestScope.user.account.username}">
                                        <input type="hidden" name="email" value="${requestScope.user.account.username}">
                                        <input type="hidden" name="user_exist" value="yes">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="form-group">
                                <label>Phone Number</label>
                                <input class="form-control" type="tel" name="phone" id="phone" value="${requestScope.user.phoneNumber}">
                            </div>
                        </div>
                        <div class="form-group col-md-6" style="padding: 0 20px;">
                            <div class="form-group">
                                <h3>Registration Information</h3>
                            </div>
                            <c:if test="${requestScope.registration != null}">
                                <div class="form-group">
                                    <label>Registration ID</label>
                                    <input class="form-control" readonly type="text" name="registrationID" value="${requestScope.registration.registrationID}">
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label>Category</label>
                                <select class="form-control" id="select_category" >
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
                            </div>
                            <div class="form-group">
                                <label>SubCategory</label>
                                <select class="form-control" id="subCategory_by_category">
                                    <option value="${requestScope.subcategory.subcategoryID}">${requestScope.subcategory.subcategoryName}</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Subject</label>
                                <select class="form-control" id="course_by_subcategory" name="courseID" onchange="submitForm();">
                                    <option value="${requestScope.course.courseID}">${requestScope.course.courseName}</option>
                                </select>
                            </div>
                            <c:if test="${requestScope.registration != null}">
                                <div class="form-group">
                                    <label>Registration Time</label>
                                    <input class="form-control" type="datetime" name="registrationTime" value="${requestScope.registration.registrationTime}" readonly>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <th>Price Package</th>
                                <td>
                                    <select class="form-control" id="pricePackage" name="pricePackage">
                                        <c:forEach items="${requestScope.prices}" var="p">
                                            <option value="${p.pricePackageID}" <c:if test="${requestScope.registration.pricePackage.pricePackageID == requestScope.pkg.pricePackageID}">selected</c:if>>
                                                ${p.priceName}, list price: ${p.listPrice}, <c:if test="${p.isOnSale == true}">is on</c:if> sale at ${p.salePrice}
                                                </option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </div>
                            <div class="form-group">
                                <label>Status</label>
                                <div class="form-group" style="padding-bottom: 0;">
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
                                </div>
                            </div>
                            <c:choose>
                                <c:when test="${requestScope.registration != null}">
                                    <div class="form-group">
                                        <label>Valid Date</label>
                                        <p class="form-control">From &emsp;${requestScope.registration.validFrom}&emsp;to&emsp;${requestScope.registration.validTo}</p>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
                        </div>
                                <div class="btngrps" style="width: 100%; text-align: center; margin-top: 20px;">
                            <input type="submit" value="SUBMIT">
                        </div>
                    </form>
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

                                    $(document).on('change', '#course_by_subcategory', function () {
                                        var courseID = this.value;
                                        $.ajax({
                                            url: "${pageContext.request.contextPath}/renderprice",
                                            type: 'POST',
                                            dataType: 'html',
                                            data: {ID: courseID}
                                        })
                                                .done(function (data) {
                                                    $('#pricePackage').html(data);
                                                })
                                                .fail(function (error) {
                                                    $('#pricePackage').html("<h1>error</h1>");
                                                })
                                                .always(function () {

                                                });

                                    });
    </script>

</body>

</html>
