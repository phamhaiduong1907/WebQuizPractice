<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Add Question</title>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
                  integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
                  crossorigin="anonymous" referrerpolicy="no-referrer" />
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
                  integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
            <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css"/>
            <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css"/>
            <link href="${pageContext.request.contextPath}/css/test_content/question_detail.css" rel="stylesheet" type="text/css"/>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

        </head>

        <body>
            <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>

            <div class="content">
                <c:if test="${requestScope.message != null}">
                    <div>${requestScope.message}</div>
                </c:if>
                <form id="question__form" method="POST" action="addquestion" enctype="multipart/form-data">
                    <input type="hidden" value="${requestScope.courseID}" name="courseID">
                    <div class="upperpart row">
                        <div class="upperpart__left col-md-6" >
                            <div class="form-group">
                                <label>Question content: </label>
                                <textarea rows="3"  class="form-control" name="questioncontent" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="">Topic: </label>
                                <select required class="form-control" name="topicID" id="topicID">
                                    <c:forEach items="${requestScope.topics}" var="t">
                                        <option value="${t.topicID}">${t.topicName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="">Lesson: </label>
                                <div id="lesson"> 
                                    <select required class="form-control" name="lessonID">
                                    </select>
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="">Dimension: </label>
                                <select class="form-control" name="dimensionID">
                                    <c:forEach items="${requestScope.dimensions}" var="d">
                                        <option value="${d.dimensionID}">${d.dimensionName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="">Level: </label>
                                <select class="form-control" name="levelID">
                                    <option value="1">Easy</option>
                                    <option value="2">Medium</option>
                                    <option value="3">Hard</option>
                                </select>
                            </div>
                            <div class="row col-md-10 answer__detail">
                                <div class="col-lg-12 form-control">
                                    <div id="answer__details__1" class="row answer">
                                        <table>
                                            <tr>
                                                <td class="col-2">
                                                    <span class="question__answer">Answer: </span>
                                                </td>
                                                <td class="col-6">
                                                    <input required type="text" class="form-control" name="answer">
                                                </td>
                                                <td class="col-2 istrue__label">
                                                    <span class="istrue">Is True:</span>
                                                </td>
                                                <td class="col">
                                                    <input type="checkbox" name="istrue" value="1_istrue">
                                                </td>
                                                <td class="col-2">
                                                    <button style="display: none;" name="remove_item" class='remove' id="remove_item">
                                                        <i class="fa-solid fa-trash-can"></i>
                                                    </button>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div id="answer__details__2" class="row answer">
                                        <table>
                                            <tr>
                                                <td class="col-2">
                                                    <span class="question__answer">Answer: </span>
                                                </td>
                                                <td class="col-6">
                                                    <input required type="text" class="form-control" name="answer">
                                                </td>
                                                <td class="col-2 istrue__label">
                                                    <span class="istrue">Is True:</span>
                                                </td>
                                                <td class="col">
                                                    <input type="checkbox" name="istrue" value="2_istrue">
                                                </td>
                                                <td class="col-2">
                                                    <button name="remove_item" class='remove remove__answer' id="remove_item">
                                                        <i class="fa-solid fa-trash"></i>
                                                    </button>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div id="new_item_details" class="new_item_details">
                                    </div>
                                    <div class="form-group">
                                        <label>Answer explanation: </label>
                                        <textarea required rows="3"  class="form-control" name="explanation"></textarea>
                                    </div>
                                    <a class="addlink" href="javascript:{}">
                                        <button class="add__answer" type="button" href="javascript:void(0)" name="add_item" id="add_item">Add new answer</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="upperpart__right col-md-6">
                            <div class="media__file">
                                <label>Choose media type: </label>
                                <select class="file__select" id="file__select" onchange="InputFile()" name="mediaID">
                                    <option disabled selected="selected">Media type</option>
                                    <option value="1">Picture</option>
                                    <option value="3">Audio</option>
                                    <option value="2">Video</option>
                                    <option value="4">No media</option>
                                </select>
                            </div>
                            <div id="media__preview">
                                <img id="image__preview" class="image__preview col-md-12">
                                <audio id="audio__preview" class="audio__preview col-md-12" controls></audio>
                                <video id="video__preview" class="video__preview col-md-12" controls></video>
                            </div>
                            <input disabled id="file__input" type="file" name="mediafile">
                        </div>
                    </div >
                    <a class="addlink" href="javascript:{}"><button class="save__button" type="submit">Save</button></a>
                </form>
            </div>   
            <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>
            <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            <script src="../../js/userPopup.js"></script>
            <script src="${pageContext.request.contextPath}/js/test_content/addquestion.js" type="text/javascript"></script>
            <script>
                                    $(document).on('change', '#topicID', function (event) {
                                        var topicID = this.value;
                                        $.ajax({
                                            url: "getlessonbytopic",
                                            type: 'POST',
                                            dataType: 'html',
                                            data: {ID: topicID},
                                        })
                                                .done(function (data) {
                                                    $('#lesson').html(data);
                                                })
                                                .fail(function (error) {
                                                    $('#lesson').html("<h1>error</h1>");
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
            </script>
        </body>

    </html>