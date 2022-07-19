<%-- 
    Document   : post_list
    Created on : Jun 12, 2022, 1:18:35 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add post</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/marketing/default_marketing.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/marketing/add_post.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/profile.css">






    </head>

    <body>
        <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>


        <section class="main">

            <!-- RIGHT CONTENT -->
            <aside class="right">
                <div class="container">
                    <div class="row">

                        <div class="col-md-8 col-md-offset-2">

                            <h1>Create post</h1>

                            <form action="addblog" method="POST" enctype="multipart/form-data">

                                <div class="form-group">
                                    <label for="title">Title <span class="require">*</span> </label>
                                    <input type="text" class="form-control" name="title" required/>
                                </div>

                                <div class="form-group">
                                    <label for="title">Brief info <span class="require">*</span></label>
                                    <input type="text" class="form-control" name="briefInfo" required/>
                                </div>

                                <div class="form-group">
                                    <label for="title">Category<span class="require">*</span></label>
                                    <select class="form-control" id="select_category">
                                        <c:forEach items="${requestScope.categories}" var="c">
                                            <option value="${c.categoryID}">${c.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="title">Sub Category<span class="require">*</span></label>
                                    <div id="subCategory_by_category">
                                        <select class="form-control"  id='' name = 'subcategoryID'> </select>
                                    </div>

                                </div>

                                <div class="form-group">
                                    <label for="isStatus">Display status</label>
                                    <select class="form-control" name="isStatus" id="isStatus">
                                        <option value="true">On</option>
                                        <option value="false">Off</option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="isFeatured">Featured</label>
                                    <select class="form-control" name="isFeatured" id="isStatus">
                                        <option value="true">Supported</option>
                                        <option value="false">No supported</option>
                                    </select>
                                </div>


                                <div class="form-group">
                                    <label for="description">Description</label>
                                    <textarea rows="5" class="form-control" name="description" required></textarea>
                                </div>



                                <div class="form-group"> 
                                    <label for="thumbnail">Thumbnail</label>
                                    <input class="form-control" type="file" name="thumbnail" placeholder="link to a .png file" onchange="loadFile(event)">

                                    <img  id="output" src="${pageContext.request.contextPath}/images/default_user_avatar.png">
                                </div>


                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary">
                                        Create
                                    </button>
                                    <button class="btn btn-default">
                                        Cancel
                                    </button>
                                </div>
                            </form>
                        </div>
                        <div class="error__message">
                            <p>${requestScope.errorMessage}</p>
                        </div>

                    </div>
                </div>




            </aside>

        </section>

        <footer>
            FOOTER
        </footer>

        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>

        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/script.js"></script>
        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/profile.js"></script>
        <script src="${pageContext.request.contextPath}/js/changepass.js"></script>
        <script src="${pageContext.request.contextPath}/js/common/home.js"></script>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>pagger("pagination", ${requestScope.pageindex}, ${requestScope.totalpage}, 3, "${requestScope.url}", "${requestScope.querystring}");</script>
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
