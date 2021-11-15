<%@ page import="com.amt.object.Product" %>
<%@ page import="java.util.List" %>y
<%@ page import="com.amt.object.Product" %>
<%@ page import="com.amt.shop.ShopServlet" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Product> products = (List<Product>) request.getAttribute(ShopServlet.PRODUCTS_ATTR);
    List<String> categories = (List<String>) request.getAttribute(ShopServlet.CATEGORY_ATTR);
%>
<!DOCTYPE html>
<html>
<%@include file="include/head.html" %>
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
    <div class="container toparea">
        <div class="underlined-title">
            <div class="editContent">
                <h1 class="text-center latestitems">OUR PRODUCTS</h1>
            </div>
            <div class="form-check">
                <%
                    for (String cat: categories) {
                %>
                <input class="form-check-input" type="checkbox" value="" id="tagCheckbox">
                <label class="form-check-label" for="tagCheckbox">
                    <%out.print(cat);%>
                </label>
                <%}%>

            </div>
            <div class="wow-hr type_short">
			<span class="wow-hr-h">
			<i class="fa fa-star"></i>
			<i class="fa fa-star"></i>
			<i class="fa fa-star"></i>
			</span>
            </div>
        </div>
        <div class="row">
            <%for (Product p: products) {%>
            <div class="col-md-4">
                <div class=productbox>
                    <div class=fadeshop>
                        <div class="captionshop text-center" style="display: none">
                            <h3><a href="#"><%out.print(p.getName());%></a></h3>
                            <p><%out.print(p.getDescription());%></p>
                            <p>
                                <a href="#" class="learn-more detailslearn"><i class="fa fa-shopping-cart"></i> Purchase</a>
                                <a href="#" class="learn-more detailslearn"><i class="fa fa-link"></i> Details</a>
                            </p>
                        </div>
                        <span class="maxproduct"><a href="shop/<%out.print(p.getId());%>"><img src="<% out.print(p.getUrlToImage());%>" alt=""></a></span>
                    </div>
                    <div class="product-details">
                        <a href="shop/<%out.print(p.getId());%>">
                            <h1><%out.print(p.getName());%></h1>
                        </a>
                        <span class="price">
                            <span class="edd_price"> <%out.print(p.getPrice());%></span>
                        </span>
                        <a class="btn-buynow" href="#">Add to cart</a>
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