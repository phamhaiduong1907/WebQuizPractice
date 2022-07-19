<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Blogs</title>
        <!-- FontAwesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- Bootstrap's CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog/list.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/profile.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/home.css">

    </head>

    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>
        <div class="heading">
            <h1>LASTEST BLOG</h1>
        </div>

        <div class="main">
            <c:choose>
                <c:when test="${requestScope.count != 0}">
                    <section class="post__list">
                        <c:forEach items="${requestScope.posts}" var="p">
                            <form action="blogdetail" method="GET">
                                <input type="hidden" name="postID" value="${p.postID}">
                                <div class="post__item">
                                    <div class="post__thumbnail"><img src="images/blog/${p.thumbnailUrl}" alt="alt"/></div>
                                    <div class="post__content">
                                        <h3>${p.title}</h3>
                                        <div class="post__info">
                                            <p><i class="fa fa-align-justify"></i> Category: ${p.subcategory.subcategoryName}</p>
                                            <p><i class="fa fa-calendar-alt"></i> Post on: ${p.updatedDate}</p>
                                        </div>
                                        <p class="post__review">${p.briefInfo}</p>
                                        <div class="post__detail">                                  
                                            <button type="submit">More Detail</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </c:forEach>
                        <div id="pagination" class="pagination"></div>
                    </section>
                </c:when>
                <c:otherwise>
                    <section class="post__list">
                        <h1>No matching result for keyword: ${requestScope.search}</h1>
                    </section>
                </c:otherwise>
            </c:choose>
            <!-- RIGHT start of filter/option box -->
            <jsp:include page="${pageContext.request.contextPath}../../view/searchsider.jsp"/>
        </div>
        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>
        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>
        <script src="${pageContext.request.contextPath}/js/script.js"></script>
        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/profile.js"></script>
        <script src="${pageContext.request.contextPath}/js/changepass.js"></script>
        <script src="${pageContext.request.contextPath}/js/common/home.js"></script>
        <script src="${pageContext.request.contextPath}/js/pagination.js"></script>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
        <script>pagger("pagination", ${requestScope.pageindex}, ${requestScope.totalpage}, 3, "${requestScope.url}", "${requestScope.querystring}");</script>
    </body>


</html>