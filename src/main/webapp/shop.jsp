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
    .category_span {
        flex-basis: 150px;
    }
    .category_label_base{
        width: 100%;
        border: 1px solid black;
        flex-basis: 150px;
        margin: 6px;
        text-align: center;
        align-content: center;
        border-radius: 15px;
        user-select: none;
    }
    .unchecked_category_label {
        background-color: white;
        color: #939393;
    }
    .checked_category_label{
        background-color: #1b857c;
        color: white;
    }
    .form_button {
        border: 1px solid black;
        flex-basis: 150px;
        margin: 6px;
        background-color: #006d49;
        color: white;
    }
    .article_image{
        width:100%;
        height:100%;
        object-fit:cover;
        align-content: center;
    }
</style>
<script>
    function setClickedColor(checkbox){
        if(checkbox.classList.contains("checked_category_label")){
            checkbox.classList.remove("checked_category_label")
            checkbox.classList.add("unchecked_category_label")
        } else {
            checkbox.classList.add("checked_category_label")
            checkbox.classList.remove("unchecked_category_label")
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
                <span class="category_span">
                    <input class="form-check-input" type="checkbox" style="display: none;" id="cat<%out.print(cat.getId());%>" value="<%out.print(cat.getId());%>" name="category" >
                    <label class="category_label_base unchecked_category_label" onclick="setClickedColor(this)" for="cat<%out.print(cat.getId());%>"><%out.print(cat.getName());%></label>
                </span>
                <%}%>
            </form>
        </div>
        <div class="row">
            <%if (articles.isEmpty()){%>
            <div class="col-md-4">
                <h1>
                    No articles to display
                </h1>
                <%} else for (Article a: articles) {%>
                <div class="col-md-4">
                    <div class=productbox>
                        <div class=fadeshop>
                            <span class="maxproduct article_image"><img src="<% out.print(a.getLink());%>" alt=""></span>
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
                                <input hidden name="incremental"/>
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