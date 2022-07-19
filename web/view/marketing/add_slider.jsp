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
                    <h1 style="margin-bottom: 30px;">Add slider</h1>
                    <form action="add" method="POST"enctype="multipart/form-data">
                        <table>
                            <tr>
                                <td><label for="title">Title:</label></td>
                                <td><input type="text" id="title" name="title" required></td>
                            </tr>
                            <tr>
                                <td><label for="backlink">Backlink:</label></td>
                                <td><input type="text" id="backlink" name="backlink" required></td>
                            </tr>
                            <tr>
                                <td><label for="status">Status:</label></td>
                                <td>
                                    <select name="status" id="status">
                                        <option value="inactive">Hide</option>
                                        <option value="active">Show</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><label for="note">Note:</label></td>
                                <td>
                                    <textarea name="note" id="note" ></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td><label for="imageURL">Slider Image:</label></td>
                                <td>
                                    <input type='file' id="imageURL" name="image" 
                                           onchange="readURL(this); checkValue('imageURL', 'previewImage');" required />
                                    <div class="slider__image">
                                        <img id="previewImage" src="#" style="display: none;"/>
                                    </div>
                                </td>
                            </tr>
                        </table>
                        <div class="slider__submit">
                            <input type="submit" value="Save">
                        </div>
                    </form>
                </div>

            </aside>
        </section>
        <jsp:include page="${pageContext.request.contextPath}../../view/footer.jsp"/>

        <jsp:include page="${pageContext.request.contextPath}../../view/user_popup.jsp"/>


        <script src="${pageContext.request.contextPath}/js/userPopup.js"></script>
        <script src="${pageContext.request.contextPath}/js/slider/uploadFile.js"></script>
    </body>

</html>