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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${path}/css/admin/index.css">
    <link rel="stylesheet" href="${path}/css/popup.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.1.3/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css">
    <link href="${path}/css/sale/regis_list.css" rel="stylesheet"/>
<!--    <link href="${path}/css/table.css" rel="stylesheet"/>-->
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
                    &emsp;
                    <label for="from">Date: </label>
                    <input id="from" type="date" name="from" > - <input onchange="dateCheck()" id="to" type="date" name="to">
                    &emsp;
                    <script>
                        function dateCheck() {
                            var from = new Date(document.getElementById('from').value);
                            console.log(from);
                            var to = new Date(document.getElementById('to').value);
                            console.log(to);
                            if (to.getTime() < from.getTime()) {
                                alert('Invalid Range! Please re-enter.');
                                document.getElementById('to').value = '';
                            }
                        }
                    </script>
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
                    &emsp;
                    <input type="submit" value="Search">
                </form>
                <br>
                <a class="newlink" href="../sale/registrationedit">Add new <i class="fa-solid fa-plus"></i></a>
                    <c:choose>
                        <c:when test="${requestScope.list.size() == 0}">
                        <h2>There are no matching result! Please try another keyword.</h2>
                    </c:when>
                    <c:otherwise>
                        <table class="main_table" id="table">
                            <thead>
                                <tr>
                                    <th data-sortas="numeric">ID</th>
                                    <th>Username</th>
                                    <th data-sortas="numeric">Registration Time</th>
                                    <th>Subject Name</th>
                                    <th data-sortas="numeric">Price Package</th>
                                    <th data-sortas="numeric">Total Cost</th>
                                    <th>Status</th>
                                    <th data-sortas="numeric">Valid From</th>
                                    <th data-sortas="numeric">Valid To</th>
                                    <th>Updated By</th>
                                    <th colspan="2">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.list}" var="r">
                                    <tr>
                                        <td>${r.registrationID}</td>
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
                                        <td><a href="../sale/registrationview?id=${r.registrationID}">View</a></td>
                                        <td><a href="../sale/registrationedit?id=${r.registrationID}"class="action__edit">Edit</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
                <br>
            </div>


        </aside>
    </section>
    <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>

    <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.fancytable/dist/fancyTable.min.js"></script>
    <script type="text/javascript">
                        $(document).ready(function () {
                            $("#table").fancyTable({
                                /* Column number for initial sorting*/
                                //undefined
                                /* Setting pagination or enabling */
                                pagination: true,
                                paginationClass: 'lol',
                                /* Rows per page kept for display */
                                perPage: 5,
                                searchable: false
                            });
                        });
    </script>
    <script src="js/userPopup.js"></script>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
    <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
    <script src="${pageContext.request.contextPath}/js/profile.js"></script>
    <script src="${pageContext.request.contextPath}/js/changepass.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/home.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>