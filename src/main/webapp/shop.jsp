<%@ page import="java.util.List" %>y
<%@ page import="com.amt.shop.ShopServlet" %>
<%@ page import="com.DAO.Objects.Article" %>
<%@ page import="com.DAO.Objects.Category" %>
<%@ page import="com.DAO.Access.ArticleOps" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Article> articles = (List<Article>) request.getAttribute(ShopServlet.ARTICLES_ATTR);
    List<Category> categories = (List<Category>) request.getAttribute(ShopServlet.CATEGORY_ATTR);
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
                <form method="post" action="shop" id="categoryForm">
                    <%for (Category cat: categories) {%>
                    <input class="form-check-input" type="checkbox" value="<%out.print(cat.getId());%>" name="category"> <%out.print(cat.getName());%></input>
                    <%}%>
                    <button type="submit" class="button">Sort</button>
                </form>
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
            <%if (articles.isEmpty()){%>
            <h1>
                No articles to display
            </h1>
            <%}
                for (Article a: articles) {%>
            <div class="col-md-4">
                <div class=productbox>
                    <div class=fadeshop>
                        <div class="captionshop text-center" style="display: none">
                            <h3><a href="#"><%out.print(a.getName());%></a></h3>
                            <p><%out.print(a.getDescription());%></p>
                            <p>
                                <a href="#" class="learn-more detailslearn"><i class="fa fa-shopping-cart"></i> Purchase</a>
                                <a href="#" class="learn-more detailslearn"><i class="fa fa-link"></i> Details</a>
                            </p>
                        </div>
                        <span class="maxproduct"><a href="shop/<%out.print(a.getId());%>"><img src="<% out.print(a.getLink());%>" alt=""></a></span>
                    </div>
                    <div class="product-details">
                        <a href="shop/<%out.print(a.getId());%>">
                            <h1><%out.print(a.getName());%></h1>
                        </a>
                        <span class="price">
                            <span class="edd_price"><%out.print(ArticleOps.isSellable(a) ? "CHF " + a.getPrice() : "");%></span>
                        </span>
                        <%if (ArticleOps.isSellable(a)) {%>
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