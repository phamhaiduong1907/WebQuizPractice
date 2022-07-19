<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : searchsider
    Created on : Jul 18, 2022, 5:09:04 PM
    Author     : Hai Tran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- RIGHT -->
<section class="option__box">
    <div class="option__filter">
        <div class="option__searchbar">
            <form action="coursesearch" method="GET">
                <input type="text" name="search" placeholder="Enter name to search...">
                </div>
                <div class="option__checkbox">
                    <h3>Category: </h3>
                    <div class="option__options-value">
                        <div class="accordion accordion-flush" id="accordionFlushExample">
                            <c:forEach items="${requestScope.categories}" var="cate">
                                <div class="accordion-item">
                                    <h2 class="accordion-header" id="flush-headingOne">
                                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse${cate.categoryID}" aria-expanded="false" aria-controls="flush-collapseOne">
                                            <input type="checkbox" onclick="checkAllBox(this, ${cate.categoryID})">&emsp;<span>${cate.categoryName}</span>
                                        </button>
                                    </h2>
                                    <div id="flush-collapse${cate.categoryID}" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                                        <div class="accordion-body">
                                            <c:forEach items="${cate.subcategories}" var="sc">
                                                <div class="subcategory">
                                                    <input type="checkbox" class="${cate.categoryID}" name="subcategory" value="${sc.subcategoryID}"> <span>${sc.subcategoryName}</span>
                                                </div>
                                            </c:forEach></div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="option__sort">
                    <select name="sort">
                        <option selected disabled>Sort by:</option>
                        <option value="DESC">Date added(newest)</option>
                        <option value="ASC">Date added(oldest)</option>
                    </select>
                </div>
                <div class="search__button">
                    <button type="submit">Search</button>
                </div>
                <div class="contact__link">
                    <a href="mailto: yourquizwebsite@gmail.com">Contact Information</a>
                </div>
            </form>
        </div>
</section>
<script>
    function checkAllBox(source, cateID) {
        checkboxes = document.getElementsByClassName(cateID);
        for (var i = 0, n = checkboxes.length; i < n; i++) {
            checkboxes[i].checked = source.checked;
        }
    }
</script>

