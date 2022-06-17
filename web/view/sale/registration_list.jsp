<%-- 
    Document   : newjsp
    Created on : Jun 8, 2022, 10:59:17 PM
    Author     : long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sale Index</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="${path}/css/admin/index.css">
    <link rel="stylesheet" href="${path}/css/popup.css">
    <link href="${path}/css/sale/regis_list.css" rel="stylesheet"/>
    <%
        int pageIndex = (Integer) request.getAttribute("pageIndex");
        int totalPage = (Integer) request.getAttribute("totalPage");
        String sortBy = (String) request.getAttribute("sortBy");
        String orderBy = (String) request.getAttribute("orderBy");
        String target = (String) request.getAttribute("target");
    %>
</head>

<body>
    <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>


    <section class="main">
        <!-- LEFT NAVIGATION BAR -->


        <!-- RIGHT CONTENT -->
        <aside class="right">
            <div class="right_content">
                <h1>Registration list</h1>

                <form autocomplete="off" action="registrationsearch" method="GET">
                    <label for="subject">Subject: </label>
                    <input id="subject" list="subjectName" type="text" name="subject" placeholder="Enter subject name..." value="${requestScope.subject}">
                    <datalist id="subjectName">
                        <c:forEach items="${requestScope.courses}" var="c">
                            <option value="${c.courseName}"></option>
                        </c:forEach>
                    </datalist>
                    <label for="from">Date: </label>
                    <input id="from" type="date" name="from" value="${requestScope.fromDate}"> - <input id="to" type="date"  name="to" value="${requestScope.toDate}">
                    &emsp;
                    <label for="raw_status">Status: </label>
                    <c:choose>
                        <c:when test="${requestScope.status == true}">
                            <input name="status" id="paid" type="radio" value="paid" checked> <label for="paid">Paid</label>
                            <input type="radio" id="unpaid" name="status" value="unpaid"> <label for="unpaid">Unpaid</label>
                        </c:when>
                        <c:when test="${requestScope.status == false}">
                            <input name="status" id="paid" type="radio" value="paid"> <label for="paid">Paid</label>
                            <input type="radio" id="unpaid" name="status" value="unpaid" checked> <label for="unpaid">Unpaid</label>
                        </c:when>
                        <c:otherwise>
                            <input name="status" id="paid" type="radio" value="paid"> <label for="paid">Paid</label>
                            <input type="radio" id="unpaid" name="status" value="unpaid"> <label for="unpaid">Unpaid</label>
                        </c:otherwise>
                    </c:choose>
                    &emsp;
                    <label for="email">Email: </label>
                    <input list="emailList"  type="email" name="email" value="${requestScope.email}" placeholder="Enter email...">
                    <datalist id="emailList">
                        <c:forEach items="${requestScope.users}" var="u">
                            <option value="${u.account.username}"></option>
                        </c:forEach>
                    </datalist>
                    <br>
                    <select name="sortBy" id="sortBy">
                        <option value="registrationID" checked>Sort by...</option>
                        <option value="registrationID">ID</option>
                        <option value="username">Username</option>
                        <option value="registrationTime">Registration Time</option>
                        <option value="courseID">Subject</option>
                        <option value="pricePackageID">Price Package</option>
                        <option value="totalCost">Total Cost</option>
                        <option value="status">Status</option>
                        <option value="validFrom">Valid From</option>
                        <option value="validTo">Valid To</option>
                        <option value="updatedBy">Last Updated By</option>
                    </select>
                    <input type="radio" id="asc" name="orderBy" value="asc"> <label for="asc">Ascending</label>
                    <input type="radio" id="desc" name="orderBy" value="desc"> <label for="desc">Descending</label>
                    <input type="submit" value="GO">
                </form>
                <br>
                <c:choose>
                    <c:when test="${requestScope.list.size() == 0}">
                        <h2>There are no matching result! Please try another keyword.</h2>
                    </c:when>
                    <c:otherwise>
                        <table class="main_table">
                            <thead>
                                <tr style="background-color: #68bce6">
                                    <th>ID</th>
                                    <th>Username</th>
                                    <th>Registration Time</th>
                                    <th>Subject Name</th>
                                    <th>Price Package</th>
                                    <th>Total Cost</th>
                                    <th>Status</th>
                                    <th>Valid From</th>
                                    <th>Valid To</th>
                                    <th>Upadted By</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.list}" var="r">
                                    <tr>
                                        <td style="background-color: #ffbd6cdf">${r.registrationID}</td>
                                        <td>${r.user.account.username}</td>
                                        <td>${r.registrationTime}</td>
                                        <td>${r.course.courseName}</td>
                                        <td>${r.pricePackage.priceName}</td>
                                        <td>${r.totalCost}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${r.status == true}">
                                                    Paid
                                                </c:when>
                                                <c:otherwise>
                                                    Unpaid
                                                </c:otherwise>
                                            </c:choose>                             
                                        </td>
                                        <td>${r.validFrom}</td>
                                        <td>${r.validTo}</td>
                                        <td>${r.updatedBy.account.username}</td>
                                        <td><a href="../sale/registrationdetail?id=${r.registrationID}">Edit</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
                <br>
                <a class="new_registration_link" href="../sale/registrationdetail">Add new</a>
                <div id="pagination" class="pagination"></div>
            </div>

            <footer>
                FOOTER
            </footer>
        </aside>
    </section>

    <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>

    <script src="js/userPopup.js"></script>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
    <script src="${pageContext.request.contextPath}/js/profile.js"></script>
    <script src="${pageContext.request.contextPath}/js/changepass.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/home.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
<!--    <script>registrationPagging("pagination", <%=pageIndex%>, <%=totalPage%>, 5, "<%=sortBy%>", "<%=orderBy%>", "<%=target%>");</script>-->
    <script>registrationPagger("pagination", <%=pageIndex%>, <%=totalPage%>, 5, "<%=sortBy%>", "<%=orderBy%>", "${requestScope.subject}", "${requestScope.fromDate}", "${requestScope.toDate}", "${requestScope.stringStatus}", "${requestScope.email}");</script>
</body>

</html>