<%-- 
    Document   : question_view
    Created on : Jul 1, 2022, 6:00:00 AM
    Author     : Hai Tran
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Question</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/test_content/question_view.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    </head>
    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>

        <div class="content">
            <form method="POST" action="editquestion" enctype="multipart/form-data">
                <input type="hidden" value="${requestScope.question.questionID}" name="questionID">
                <input type="hidden" value="${requestScope.question.mediaURL}" name="mediaURl">
                <div class="upperpart row">
                    <div class="upperpart__left col-md-6" >
                        <div class="form-group">
                            <label>Question content: </label>
                            <textarea readonly required rows="3"  class="form-control" name="questioncontent">${requestScope.question.questionContent}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="">Topic: </label>
                            <select disabled class="form-control" name="topicID">
                                <c:forEach items="${requestScope.topics}" var="t">
                                    <c:choose >
                                        <c:when test="${requestScope.question.lesson.topicID == t.topicID}">
                                            <option selected value="${t.topicID}">${t.topicName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option  value="${t.topicID}">${t.topicName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="">Lesson: </label>
                            <input readonly class="form-control" value="${requestScope.question.lesson.lessonName}"/>
                        </div>
                        <div class="form-group">
                            <label for="">Dimension: </label>
                            <select disabled class="form-control" id="select_dimension" name="dimensionID">
                                <c:forEach items="${requestScope.dimensions}" var="d">
                                    <c:choose >
                                        <c:when test="${requestScope.question.dimension.dimensionID == d.dimensionID}">
                                            <option selected value="${d.dimensionID}">${d.dimensionName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option  value="${d.dimensionID}">${d.dimensionName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="">Level: </label>
                            <select disabled class="form-control" id="select_level" name="levelID">
                                <c:forEach items="${requestScope.levels}" var="l">
                                    <c:choose >
                                        <c:when test="${requestScope.question.level.levelID == l.levelID}">
                                            <option selected value="${l.levelID}">${l.levelName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option  value="${l.levelID}">${l.levelName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="row col-md-10 answer__detail">
                            <div class="col-lg-12 form-control">
                                <c:forEach items="${requestScope.question.answers}" var="a" end="0">
                                    <div id="answer__details__1" class="row answer">
                                        <table>
                                            <tr>
                                                <td class="col-2">
                                                    <span class="question__answer">Answer: </span>
                                                </td>
                                                <td>
                                                    <input disabled type="text" class="form-control" name="answer" value="${a.answerContent}">
                                                </td>
                                                <td class="col-2 istrue__label">
                                                    <span class="istrue">Is True:</span>
                                                </td>
                                                <td class="col">
                                                    <c:choose>
                                                        <c:when test="${a.isTrue}">
                                                            <input disabled checked type="checkbox" name="istrue" value="1_istrue">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input disabled type="checkbox" name="istrue" value="1_istrue">
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td class="col-2">
                                                    <button style="display: none;" name="remove_item" class='remove remove__answer' id="remove_item">
                                                        <i class="fa-solid fa-trash"></i>
                                                    </button>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </c:forEach>
                                <c:forEach items="${requestScope.question.answers}" var="a" begin="1" end="1">
                                    <div id="answer__details__2" class="row answer">
                                        <table>
                                            <tr>
                                                <td class="col-2">
                                                    <span class="question__answer">Answer: </span>
                                                </td>
                                                <td>
                                                    <input disabled type="text" class="form-control" name="answer" value="${a.answerContent}">
                                                </td>
                                                <td class="col-2 istrue__label">
                                                    <span class="istrue">Is True:</span>
                                                </td>
                                                <td class="col">
                                                    <c:choose>
                                                        <c:when test="${a.isTrue}">
                                                            <input disabled checked type="checkbox" name="istrue" value="2_istrue">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input disabled type="checkbox" name="istrue" value="2_istrue">
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td class="col-2">
                                                    <button style="display: none;" name="remove_item" class='remove remove__answer' id="remove_item">
                                                        <i class="fa-solid fa-trash"></i>
                                                    </button>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </c:forEach>
                                <% int i = 2;%>
                                <c:forEach items="${requestScope.question.answers}" var="a" begin="2">
                                    <% i++;%>
                                    <div id="answer__details__<%=i%>" class="row answer">
                                        <table>
                                            <tr>
                                                <td class="col-2">
                                                    <span class="question__answer">Answer: </span>
                                                </td>
                                                <td>
                                                    <input disabled type="text" class="form-control" name="answer" value="${a.answerContent}">
                                                </td>
                                                <td class="col-2 istrue__label">
                                                    <span class="istrue">Is True:</span>
                                                </td>
                                                <td class="col">
                                                    <c:choose>
                                                        <c:when test="${a.isTrue}">
                                                            <input disabled  checked type="checkbox" name="istrue" value="<%=i%>_istrue">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input disabled type="checkbox" name="istrue" value="<%=i%>_istrue">
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td class="col-2">
                                                    <button disabled onclick="this.parentElement.parentElement.parentElement.parentElement.remove()" style="display: block;" name="remove_item" class='remove remove__answer' id="remove_item" data-id="<%=i%>">
                                                        <i class="fa-solid fa-trash"></i>
                                                    </button>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </c:forEach>
                                <div id="new_item_details" class="new_item_details">
                                </div>
                                <div class="form-group">
                                    <label>Answer explanation: </label>
                                    <textarea readonly required rows="3"  class="form-control" name="explanation">${requestScope.question.explanation}</textarea>
                                </div>
                                <a class="addlink" href="javascript:{}">
                                    <button disabled class="add__answer"  type="button" href="javascript:void(0)" name="add_item" id="add_item">Add new answer</button>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="upperpart__right col-md-6">
                        <div class="media__file">
                            <label>Choose media type: </label>
                            <select disabled class="file__select" id="file__select" onchange="InputFile()" name="mediaID">
                                <c:choose>
                                    <c:when test="${requestScope.question.mediaType.mediaID == 1}">
                                        <option selected="selected" value="1">Picture</option>
                                        <option value="3">Audio</option>
                                        <option value="2">Video</option>
                                        <option value="4">No media</option>
                                    </c:when>
                                    <c:when test="${requestScope.question.mediaType.mediaID == 2}"> 
                                        <option value="1">Picture</option>
                                        <option value="3">Audio</option>
                                        <option selected="selected" value="2">Video</option>
                                        <option value="4">No media</option>
                                    </c:when>
                                    <c:when test="${requestScope.question.mediaType.mediaID == 3}">
                                        <option value="1">Picture</option>
                                        <option selected="selected" value="3">Audio</option>
                                        <option value="2">Video</option>
                                        <option value="4">No media</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="1">Picture</option>
                                        <option value="3">Audio</option>
                                        <option value="2">Video</option>
                                        <option selected="selected" value="4">No media</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                        <div class="media__preview">
                            <c:choose>
                                <c:when test="${requestScope.question.mediaType.mediaID == 1}">
                                    <img id="image__preview__data" class="image__preview__data col-md-12" src="${pageContext.request.contextPath}/media/image/${requestScope.question.mediaURL}">
                                </c:when>
                                <c:when test="${requestScope.question.mediaType.mediaID == 2}"> 
                                    <video id="video__preview__data" class="video__preview__data col-md-12" controls src="${pageContext.request.contextPath}/media/video/${requestScope.question.mediaURL}">

                                    </video>
                                </c:when>
                                <c:when test="${requestScope.question.mediaType.mediaID == 3}">
                                    <audio id="audio__preview__data" class="audio__preview__data col-md-12" controls src="${pageContext.request.contextPath}/media/audio/${requestScope.question.mediaURL}">

                                    </audio>
                                </c:when>
                            </c:choose>
                        </div>
                        <input disabled id="file__input" type="file" name="mediafile" value="${requestScope.question.mediaURL}">
                    </div>
                </div > 
                <a href="editquestion?questionID=${requestScope.question.questionID}" class="addlink">Edit</a>
        </div>   
        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>
        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>

        <script src="../../js/userPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/test_content/addquestion.js" type="text/javascript"></script>
    </body>
</html>
