<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Slider Details</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/slider/slider_detail.css">
    </head>

    <body>
         <jsp:include page="${pageContext.request.contextPath}../../view/header_for_staff.jsp"/>

        <section class="main">
            <!-- LEFT NAVIGATION BAR -->
         

            <!-- RIGHT CONTENT -->
            <aside class="right">
                <div class="right_content">
                    <form action="detail" method="POST" enctype="multipart/form-data">
                        <table>
                            <tr>
                                <td><label for="sliderID">ID:</label></td>
                                <td>
                                    <input type="text" id="sliderID" name="sliderID" value="${requestScope.sliderByID.sliderID}" readonly>
                                </td>
                            </tr>
                            <tr>
                                <td><label for="title">Title:</label></td>
                                <td>
                                    <input type="text" id="title" name="title" value="${requestScope.sliderByID.title}">
                                </td>
                            </tr>
                            <tr>
                                <td><label for="backlink">Backlink:</label></td>
                                <td>
                                    <input type="text" id="backlink" name="backlink" value="${requestScope.sliderByID.backlink}">
                                </td>
                            </tr>
                            <tr>
                                <td><label for="status">Status:</label></td>
                                <td>
                                    <select name="status" id="status">
                                        <option value="inactive" ${requestScope.sliderByID.status?"":"selected"}>Hide</option>
                                        <option value="active" ${requestScope.sliderByID.status?"selected":""}>Show</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><label for="note">Note:</label></td>
                                <td>
                                    <textarea name="note" id="note">${requestScope.sliderByID.note}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td><label for="imageURL">Slider Image:</label></td>
                                <td>
                                    <input type="hidden" name="imageURLDefault" value="${requestScope.sliderByID.imageUrl}">
                                    <input type='file' id="imageURL" name="imageURLUpdate" onchange="readURL(this); checkValueUpdate('imageURL', 'previewImage','${pageContext.request.contextPath}/${requestScope.sliderByID.imageUrl}');"/>
                                    <div class="slider__image">
                                        <img id="previewImage" src="${pageContext.request.contextPath}/${requestScope.sliderByID.imageUrl}"
                                             style="width: 90%; height: 200px;">
                                    </div>
                                </td>
                            </tr>
                        </table>
                        <div class="slider__submit">
                            <input type="submit" value="Save">
                        </div>
                    </form>
                </div>
                <footer>
                    FOOTER
                </footer>
            </aside>
        </section>

        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>

        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/slider/uploadFile.js"></script>
        <script>
                                        function checkValueUpdate(id, targetId, src) {
                                            var input = document.getElementById(id).value;
                                            var image = document.getElementById(targetId);
                                            if (input === "" || input === undefined) {
                                                image.src = src;
                                                image.style.width = '90%';
                                                image.style.height = '200px';
                                            }
                                        }
        </script>
    </body>

</html>