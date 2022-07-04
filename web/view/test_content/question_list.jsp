<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Question List</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer"
              />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/system.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/test_content/popup_import.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/test_content/question_list.css">

    </head>

    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>
        <section class="main">
            <aside class="right">
                <div class="right_content">
                    <h1>QUESTION LIST</h1>
                    <div class="setting_tool">
                        <div class="search_form">
                            <form action="#" id="search">
                                <div class="search__item">
                                    <input type="text" name="content" id="contentSearch" placeholder="Type content to search">
                                </div>
                                <div class="search__item">
                                    <label for="subjectSearch">Subject</label>
                                    <select name="subject" id="subjectSearch">
                                        <option value="-1">All subject</option>
                                        <c:forEach items="${requestScope.courses}" var="course">
                                            <option value="${course.courseID}" ${requestScope.subjectID == course.courseID ? "selected" : ""}>
                                                ${course.courseName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="search__item__ajax" id="ajaxArea">
                                    <div class="search__item">
                                        <label for="lessonSearch">Lesson</label>
                                        <select name="lesson" id="lessonSearch" ${requestScope.subjectID == -1?"disabled":""}>
                                            <c:if test="${requestScope.subjectID != -1}">
                                                <option value="-1">All Lessons</option>
                                                <c:forEach items="${requestScope.lessons}" var="l">
                                                    <option value="${l.lessonID}" ${requestScope.lessonID == l.lessonID?"selected":""}>
                                                        ${l.lessonName}
                                                    </option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                    <div class="search__item">
                                        <label for="dimensionSearch">Dimension</label>
                                        <select name="dimension" id="dimensionSearch" ${requestScope.subjectID == -1?"disabled":""}>
                                            <c:if test="${requestScope.subjectID != -1}">
                                                <option value="-1">All Dimensions</option>
                                                <c:forEach items="${requestScope.dimensions}" var="d">
                                                    <option value="${d.dimensionID}" ${requestScope.dimensionID == d.dimensionID?"selected":""}>
                                                        ${d.dimensionName}
                                                    </option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>
                                <div class="search__item">
                                    <label for="levelSearch">Level</label>
                                    <select name="level" id="levelSearch">
                                        <option value="-1">All levels</option>
                                        <c:forEach items="${requestScope.levels}" var="l">
                                            <option value="${l.levelID}" ${l.levelID == requestScope.levelID?"selected":""}>
                                                ${l.levelName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="search__item">
                                    <label for="statusSearch">Status</label>
                                    <select name="status" id="statusSearch">
                                        <option value="all">All statuses</option>
                                        <option value="active" ${requestScope.status == "active"?"selected":""}>Active</option>
                                        <option value="inactive"${requestScope.status == "inactive"?"selected":""}>Inactive</option>
                                    </select>
                                </div>
                                <button type="submit">Search</button>
                            </form>
                        </div>
                        <div class="add_setting">
                            <a href="#" id="openImport">Import Question</a>
                        </div>
                    </div>
                    <table class="setting_list">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Subject</th>
                                <th>Dimension</th>
                                <th>Lesson</th>
                                <th>Level</th>
                                <th>Content</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.questions}" var="q">
                                <tr>
                                    <td>${q.questionID}</td>
                                    <td>${q.course.courseName}</td>
                                    <td>${q.dimension.dimensionName}</td>
                                    <td>${q.lesson.lessonName}</td>
                                    <td>${q.level.levelName}</td>
                                    <td class="question__content">
                                        <p>${q.questionContent}</p>
                                    </td>
                                    <td>
                                        <span class="${q.status?"status__active":"status__inactive"}" onclick="changeStatus(this, '${q.questionID}')">
                                            ${q.status?"Active":"Inactive"}
                                        </span>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/editquestion?questionID=${q.questionID}" class="action__edit">Edit</a>
                                        <a href="${pageContext.request.contextPath}/viewquestion?questionID=${q.questionID}" class="action__view">View</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>
            </aside>
        </section>

        <section class="popup__import" id="popupImport">
            <div class="import__content">
                <div class="import__heading">
                    <h2>Import Questions</h2>
                    <a href="${pageContext.request.contextPath}/template/Template.xlsx" download>
                        <i class="fa fa-cloud-download-alt"></i>Download Template
                    </a>
                </div>
                <form action="import" enctype="multipart/form-data" method="post" onsubmit="return checkFileType();">
                    <input type="file" hidden name="uploadedQuestion" id="uploadedQuestion">
                    <div class="uploaded__area">
                        <div class="uploaded__label">
                            <i class="fa fa-cloud-upload-alt"></i>
                            <p>Browse File To Upload</p>
                            <p>(.xlsx, .xls file only)</p>
                        </div>
                    </div>
                    <div class="uploaded__modify" style="display: none;">
                        <div class="uploaded__file">
                            <div class="uploaded__detail">
                                <div class="uploaded__icon"><i class="fa fa-file-alt"></i></div>
                                <div class="uploaded__filename"><span class="uploaded__name"></span></div>
                            </div>
                            <div class="uploaded__check">
                                <i class="fa fa-check icon__check"></i>
                                <i class="fa fa-times icon__close" style="display: none;"></i>
                            </div>
                        </div>
                        <button type="submit">Upload</button>
                    </div>
                </form>
            </div>
        </section>

        <div id="toast" style="display:${sessionScope.countWrong == null && sessionScope.countRight == null && sessionScope.errorMessage == null ?"none":"block"}">
            <div class="toast__wrapper">
                <div class="toast">
                    <div class="success" style="display: ${sessionScope.countRight > 0?"flex":"none"}">
                        <i class="fa fa-check-circle"></i>
                        <div class="notify">
                            <span class="message">Success</span>
                            <span>${sessionScope.countRight} questions successfully imported</span>
                        </div>  
                    </div>

                    <div class="fail" style="display: ${(sessionScope.countWrong > 0 || sessionScope.errorMessage != null)?"flex":"none"}">
                        <!--<i class="fa fa-exclamation-circle"></i>-->
                        <i class="fa fa-exclamation-circle"></i>
                        <div class="notify">
                            <span class="message">Fail</span>
                            <span class="errorMessage">${sessionScope.errorMessage}</span>
                            <c:if test="${sessionScope.countWrong > 0}">
                                <span>${sessionScope.countWrong} questions imported fail</span>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <c:remove var="errorMessage" scope="session"/>
            <c:remove var="countRight" scope="session"/>
            <c:remove var="countWrong" scope="session"/>
        </div>

        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery.fancytable/dist/fancyTable.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script>
                    setTimeout(function () {
                        document.querySelector('.toast').style.animation = 'slide_hide 2s ease forwards';
                    }, 4000);

                    $(document).ready(function () {
                        $(".setting_list").fancyTable({
                            pagination: true,
                            paginationClass: 'btn btn-primary',
                            perPage: 3,
                            searchable: false
                        });
                    });

                    const openImport = document.getElementById('openImport');
                    const popupImport = document.getElementById('popupImport');
                    const uploadInfoTab = document.querySelector('.uploaded__file');
                    const iconCheck = uploadInfoTab.querySelector('.icon__check');
                    const iconClose = uploadInfoTab.querySelector('.icon__close');
                    const uploadedArea = document.querySelector('.uploaded__area');
                    const uploadedQuestion = document.getElementById('uploadedQuestion');

                    openImport.addEventListener("click", () => {
                        setTimeout(() => {
                            popupImport.classList.add('import__active');
                        }, 100);
                    })

                    document.addEventListener("click", (e) => {
                        let isClosest = e.target.closest('.import__content');
                        if (!isClosest && popupImport.classList.contains('import__active')) {
                            popupImport.classList.remove('import__active');
                            let uploadedModify = document.querySelector('.uploaded__modify');
                            uploadedModify.style.display = 'none';
                            uploadedQuestion.value = "";
                        }
                    });

                    function checkFileType() {
                        let file = uploadedQuestion.files[0];
                        if (file.name.split(".").pop() === "xlsx" || file.name.split(".").pop() === "xls") {
                            return true;
                        } else {
                            alert("Check type of uploaded file (accept .xlsx/.xls file only)!");
                            return false;
                        }

                    }

                    uploadedArea.addEventListener("click", () => {
                        uploadedQuestion.click();
                    });

                    uploadedQuestion.onchange = ({ target }) => {
                        let file = target.files[0];
                        if (file.name !== '' || file !== null) {
                            console.log(file.name.split(".").pop());
                            let uploadedModify = document.querySelector('.uploaded__modify');
                            let uploadedName = document.querySelector('.uploaded__name');
                            uploadedModify.style.display = 'block';
                            uploadedName.innerHTML = file.name;
                    }
                    };

                    uploadInfoTab.onmouseenter = function () {
                        iconCheck.style.display = "none";
                        iconClose.style.display = "block";
                    };

                    uploadInfoTab.onmouseleave = function () {
                        iconCheck.style.display = "block";
                        iconClose.style.display = "none";
                    };

                    iconClose.addEventListener("click", function () {
                        let uploadedModify = document.querySelector('.uploaded__modify');
                        uploadedModify.style.display = 'none';
                        uploadedQuestion.value = "";
                    });

                    var subjectSearch = document.getElementById("subjectSearch");
                    var dimensionSearch = document.getElementById("dimensionSearch");
                    var lessonSearch = document.getElementById("lessonSearch");

                    subjectSearch.onchange = function () {
                        var subjectID = subjectSearch.value.trim();
                        if (subjectSearch.value.trim() === '-1') {
                            lessonSearch.innerHTML = '';
                            dimensionSearch.innerHTML = '';
                            $('#lessonSearch').prop('disabled', true);
                            $('#dimensionSearch').prop('disabled', true);

                        } else {
                            $('#lessonSearch').prop('disabled', false);
                            $('#dimensionSearch').prop('disabled', false);

                            $.ajax({
                                url: "/SWP391-SE1617-NET_Group06-QuizWebsite/test/load",
                                type: "GET",
                                data: {
                                    subjectID: subjectID
                                },
                                success: function (data) {
                                    var content = document.getElementById('ajaxArea');
                                    content.innerHTML = data;
                                }
                            });
                        }
                    };


                    function changeStatus(btn, questionID) {
                        var text = btn.innerHTML;
                        if (text.trim() === 'Inactive') {
                            btn.classList.remove('status__inactive');
                            btn.classList.add('status__active');
                            btn.textContent = "Active";
                        } else {
                            btn.classList.add('status__inactive');
                            btn.classList.remove('status__active');
                            btn.textContent = "Inactive";
                        }

                        $.ajax({
                            url: "/SWP391-SE1617-NET_Group06-QuizWebsite/test/changeStatus",
                            type: "GET",
                            data: {
                                questionID: questionID
                            }
                        });
                    }


        </script>

    </body>

</html>