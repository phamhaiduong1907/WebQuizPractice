<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/marketing/default_marketing.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/marketing/dashboard.css">
    </head>

    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>

        <section class="page__main">
            <div class="page__title">
                <h3>Dashboard</h3>
            </div>

            <div class="page__container">
                <div class="page__subject">
                    <div class="subject__item subject__table">
                        <div class="subject__item__heading">
                            <h3>Subject Registers</h3>
                        </div>
                        <table class="subject__list__table">
                            <thead>
                                <tr>
                                    <th>Subject Name</th>
                                    <th>Category</th>
                                    <th>Learners</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.courses}" var="c">
                                    <tr>
                                        <td>${c.courseName}</td>
                                        <td>${c.subcategory.subcategoryName}</td>
                                        <td>${c.learners}</td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${requestScope.courses.size() % 3 == 2}">
                                    <tr style="color: #fff">
                                        <td style="color: #fff">Lorem ipsum dolor sit amet.</td>
                                        <td style="color: #fff">Lorem ipsum</td>
                                        <td style="color: #fff">Lorem ipsum</td>
                                    </tr>
                                </c:if>
                                <c:if test="${requestScope.courses.size() % 3 == 1}">
                                    <tr style="color: #fff">
                                        <td style="color: #fff">Lorem ipsum dolor sit amet.</td>
                                        <td style="color: #fff">Lorem ipsum</td>
                                        <td style="color: #fff">Lorem ipsum</td>
                                    </tr>
                                    <tr style="color: #fff">
                                        <td style="color: #fff">Lorem ipsum dolor sit amet.</td>
                                        <td style="color: #fff">Lorem ipsum</td>
                                        <td style="color: #fff">Lorem ipsum</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>

                    <div class="subject__item subject__trending">
                        <div class="subject__item__heading">
                            <h3>Most Popular Subjects</h3>
                        </div>
                        <div class="subject__new__list">
                            <c:forEach items="${requestScope.popularCourses}" var="c">
                                <div class="subject__new__item">
                                    <div class="subject__new__thumbnail">
                                        <img src="${pageContext.request.contextPath}/images/thumbnails/${c.thumbnailUrl}">
                                    </div>
                                    <div class="subject__new__detail">
                                        <div class="subject__new__name">
                                            <h5>${c.courseName}</h5>
                                        </div>
                                        <div class="subject__new__category">
                                            <p><i class="fa fa-list"></i> ${c.subcategory.subcategoryName}</p>
                                        </div>
                                    </div>
                                    <div class="subject__new__info">
                                        <span>${c.learners} learners</span>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div class="page__half">
                    <div class="page__report">
                        <div class="report__heading">
                            <h3>Revenue</h3>
                        </div>
                        <div class="report__chart">
                            <div id="piechart"></div>
                        </div>
                    </div>

                    <div class="page__registration">
                        <div class="report__heading">
                            <h3>Registration Trend</h3>
                        </div>
                        <div class="registration__search">
                            <form id="registrationSearch" action="#" method="GET">
                                <div class="search__item">
                                    <label for="startDate">Start Date:</label>
                                    <input type="date" name="startDate" id="startDate">
                                </div>
                                <div class="search__item">
                                    <label for="endDate">End Date:</label>
                                    <input type="date" name="endDate" id="endDate">
                                </div>
                                <div class="form__button">
                                    <button type="button" onclick="checkDate()">
                                        Find trend
                                    </button>
                                </div>
                            </form>
                        </div>
                        <div class="registration__chart">
                            <div id="curve_chart"></div>
                        </div>
                    </div>
                </div>
            </div>

        </section>

        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>

        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery.fancytable/dist/fancyTable.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js@3.8.0/dist/chart.min.js"></script>
        <script src="https://www.gstatic.com/charts/loader.js"></script>
        <script>
                                        //check submitted form data


                                        // pie chart
                                        google.charts.load('current', {'packages': ['corechart']});
                                        google.charts.setOnLoadCallback(drawPieChart);
                                        google.charts.setOnLoadCallback(drawLineChart);

                                        function drawLineChart() {
                                            var data = google.visualization.arrayToDataTable([
                                                ['Day', 'Success', 'All'],
            <c:forEach items="${requestScope.orderDatas}" var="od">
                                                ['${od.date}', ${od.success}, ${od.all}],
            </c:forEach>
                                            ]);

                                            var options = {
                                                // title: 'Total number in the last 7 days',
                                                // curveType: 'function',
                                                fontSize: 15,
                                                legend: {position: 'bottom'}
                                            };

                                            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

                                            chart.draw(data, options);
                                        }

                                        function drawPieChart() {

                                            var data = google.visualization.arrayToDataTable([
                                                ['Category', 'dollars'],
            <c:forEach items="${requestScope.categories}" var="c">
                                                ['${c.categoryName}', ${c.revenue}],
            </c:forEach>
                                            ]);

                                            var options = {
                                                title: 'Total Revenues($): ${requestScope.totalRevenues}',
                                                fontSize: 18,
                                                pieStartAngle: 100
                                            };

                                            var chart = new google.visualization.PieChart(document.getElementById('piechart'));

                                            chart.draw(data, options);
                                        }

                                        //table
                                        $(document).ready(function () {
                                            $(".subject__list__table").fancyTable({
                                                pagination: true,
                                                perPage: 3,
                                                searchable: false,
                                                sortable: false
                                            });
                                        });

                                        var form = document.getElementById('registrationSearch');

                                        function checkDate() {
                                            let startDate = document.getElementById('startDate').value.trim();
                                            let endDate = document.getElementById('endDate').value.trim();
                                            if ((startDate === '' && endDate !== '') || 
                                                    (startDate !== '' && endDate === '')) {
                                                alert('Start date or end date can\'t be empty!');
                                            }
                                            const start = new Date(startDate);
                                            const end = new Date(endDate);
                                            const current = new Date();
                                            if (end > current) {
                                                alert('End date can\'t be greater than current date');
                                            } else if (start >= end) {
                                                alert('Start date can\'t be greater than end date');
                                            } else {
                                                loadChart();
                                            }
                                        }
                                        ;

                                        function loadChart() {
                                            let startDate = document.getElementById('startDate').value.trim();
                                            let endDate = document.getElementById('endDate').value.trim();
                                            $.ajax({
                                                url: "/SWP391-SE1617-NET_Group06-QuizWebsite/marketing/chart",
                                                type: 'GET',
                                                data: {
                                                    startdate: startDate,
                                                    enddate: endDate
                                                },
                                                success: function (data) {
                                                    google.charts.setOnLoadCallback(function () {
                                                        drawChart(data);
                                                    });
                                                }
                                            });
                                        }

                                        function drawChart(result) {
                                            var data = new google.visualization.DataTable();
                                            data.addColumn('string', 'Day');
                                            data.addColumn('number', 'Success');
                                            data.addColumn('number', 'All');
                                            var dataArray = [];
                                            $.each(result, function (i, obj) {
                                                dataArray.push([obj.date, obj.success, obj.all]);
                                            });

                                            data.addRows(dataArray);

                                            var options = {
                                                fontSize: 15,
                                                legend: {position: 'bottom'}
                                            };

                                            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
                                            chart.draw(data, options);
                                        }
        </script>
    </body>

</html>