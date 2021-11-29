<%@ page import="java.util.List" %>y
<%@ page import="com.amt.shop.ShopServlet" %>
<%@ page import="com.DAO.Objects.Article" %>
<%@ page import="com.DAO.Objects.Category" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Article> articles = (List<Article>) request.getAttribute(ShopServlet.ARTICLES_ATTR);
    List<Category> categories = (List<Category>) request.getAttribute(ShopServlet.CATEGORY_ATTR);
%>
<!DOCTYPE html>
<html>
<%@include file="include/head.html" %>
<style>
    .category_fl_box {
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
    }

    .category_item {
        checked: false;
        border: 1px solid black;
        flex-basis: 150px;
        margin: 6px;
        background-color: white;
        color: #939393;
        text-align: center;
        align-content: center;
        border-radius: 15px;
    }

    .form_button {
        border: 1px solid black;
        flex-basis: 150px;
        margin: 6px;
        background-color: #006d49;
        color: white;

    }

</style>
<script>
    function setClickedColor(checkboxId){
        checkboxId.classes.
        if(checkboxId.style.backgroundColor === "transparent"){
            checkboxId.style.backgroundColor = "#1b857c";
            checkboxId.style.color = "white";
            checkboxId.style.checked = "true";
        } else {
            checkboxId.style.backgroundColor = "transparent";
            checkboxId.style.color = '#939393';
            checkboxId.style.checked = "false";
        }
    }
</script>
<body>
<!-- HEADER =============================-->
<%@include file="include/nav.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-12 text-center">
            <div class="text-pageheader">
                <div class="subtext-image" data-scrollreveal="enter bottom over 1.7s after 0.0s">
                    Shop
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</header>

<!-- CONTENT =============================-->
<section class="item content">
    <div class="container">
        <div class="underlined-title">
            <div class="editContent">
                <h1 class="text-center latestitems">OUR PRODUCTS</h1>
            </div>
            <div class="wow-hr type_short">
                <span class="wow-hr-h">
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                </span>
            </div>
        </div>
        <div class="form-check">
            <form method="post" class="category_fl_box" action="shop" id="categoryForm">
                <button type="submit" class="button form_button">Sort</button>
                <%for (Category cat: categories) {%>
                <span class="category_item" onclick="setClickedColor(this, this.checked)" checked="false">
                    <input class="form-check-input category_item" type="checkbox" style="display: none" id="cat<%out.print(cat.getId());%>" value="<%out.print(cat.getId());%>" name="category" >
                    <label for="cat<%out.print(cat.getId());%>" style="display: block"><%out.print(cat.getName());%></label>
                </span>
                <%}%>
            </form>
        </div>
        <div class="row">
            <%if (articles.isEmpty()){%>
            <h1>
                No articles to display
            </h1>
            <%} else for (Article a: articles) {%>
            <div class="col-md-4">
                <div class=productbox>
                    <div class=fadeshop>
                        <div class="captionshop text-center" style="display: none;">
                            <h3><%out.print(a.getName());%></h3>
                            <p>
                                <%out.print(a.getDescription());%>
                            </p>
                            <p>
                                <a href="#" class="learn-more detailslearn"><i class="fa fa-shopping-cart"></i> Purchase</a>
                                <a href="#" class="learn-more detailslearn"><i class="fa fa-link"></i> Details</a>
                            </p>
                        </div>
                        <span class="maxproduct"><img src="<% out.print(a.getLink());%>" alt=""></span>
                    </div>
                    <div class="product-details">
                        <a href="shop/<%out.print(a.getId());%>">
                            <h1><%out.print(a.getName());%></h1>
                        </a>
                        <span class="price">
                            <span class="edd_price"><%out.print(a.isSellable() ? "CHF " + a.getPrice() : "");%></span>
                        </span>
                        <%if (a.isSellable()) {%>
                        <form method="post" action="cart">
                            <input hidden name="id" value="<%out.print(String.valueOf(a.getId()));%>"/>
                            <input hidden name="quantity" value="1"/>
                            <span style="display: flex; justify-content: center">
                                <input type="submit" class="btn-buynow" value="Add to Cart"/>
                            </span>
                        </form>
                        <%} else {%>
                        <h4>Article unavailable</h4>
                        <%}%>
                    </div>
                </div>
            </div>
            <%}%>
        </div>
    </div>
</section>

<!-- FOOTER =============================-->
<%@include file="include/footer.html" %>

</body>
</html>