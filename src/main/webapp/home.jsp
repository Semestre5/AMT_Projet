<%@ page import="com.DAO.Objects.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="com.amt.home.HomeServlet" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    List<Article> articles = (List<Article>) request.getAttribute(HomeServlet.ARTICLES_ATTR);

%>
<!DOCTYPE html>
<html>
<%@include file="include/head.html"%>
<style>
    .article_image{
        display: block;
        margin-left: 100px;
        margin-right: auto;
    }
</style>
<body>
<!-- HEADER =============================-->
<%@include file="include/navIndex.jsp"%>


<!-- STEPS =============================-->



<!-- LATEST ITEMS =============================-->
<section class="item content">
    <div class="container">
        <div class="underlined-title">
            <div class="editContent">
                <h1 class="text-center latestitems">LATEST ARTICLES</h1>
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
            <div class="col-md-4">
                <h1>
                    No articles to display
                </h1>
                <%} else for (Article a: articles) {%>
                <div class="col-md-4">
                    <div class=productbox>
                        <div class=fadeshop>
                                <span class="maxproduct article_image">
                                    <a href="shop/<%out.print(a.getId());%>">
                                        <img src="<% out.print(a.getLink());%>" alt="">
                                    </a>
                                </span>
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
                                <input hidden name="incremental">
                                <input hidden name="id" value="<%out.print(String.valueOf(a.getId()));%>"/>
                                <input hidden name="quantity" value="1"/>
                                <button type="submit" class="btn btn-success" value="Add to Cart"><span>Add to Cart</span><span style="text-align:right;" class="glyphicon glyphicon-shopping-cart"></span></button>
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
    </div>
</section>
<div class="row">
    <div class="row" style="text-align: center;">
        <a href="shop" class="btn btn-primary">Browse All Products
            <div>
                <i class="fa fa-star fa-spin"></i>
            </div>
        </a>
    </div>
</div>
<br><br>


<div class="item content">
    <div class="container toparea">
        <div class="row text-center">
            <div class="col-md-4">
                <div class="col editContent">
                    <span class="numberstep"><i class="fa fa-shopping-cart"></i></span>
                    <h3 class="numbertext">Choose our Products</h3>
                    <p>
                        Bob is a skillful worker, and Bob likes his clients to have a large choice of products. Our lovely Bobby invites you to browse through our catalog to find what makes you happy.
                    </p>
                </div>
                <!-- /.col-md-4 -->
            </div>
            <!-- /.col-md-4 col -->
            <div class="col-md-4 editContent">
                <div class="col">
                    <span class="numberstep"><i class="fa fa-gift"></i></span>
                    <h3 class="numbertext">Pay with PayPal or Card</h3>
                    <p>
                        Bob is a modern Bob, and Bob likes his clients to be able to pay the way they want. Our lovely Bobby allows you to pay however you want (but still money, he does not accept cows as a payment).
                    </p>
                </div>
                <!-- /.col -->
            </div>
            <!-- /.col-md-4 col -->
            <div class="col-md-4 editContent">
                <div class="col">
                    <span class="numberstep"><i class="fa fa-download"></i></span>
                    <h3 class="numbertext">Get Instant Confirmation</h3>
                    <p>
                        Bob is a pro, and Bob likes to reassure his clients. Thus, our lovely Bobby provides an instant confirmation and support on your order.
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- BUTTON =============================-->

<!-- FOOTER =============================-->
<%@include file="include/footer.html"%>

<script>
    //----HOVER CAPTION---//
    jQuery(document).ready(function ($) {
        $('.fadeshop').hover(
            function(){
                $(this).find('.captionshop').fadeIn(150);
            },
            function(){
                $(this).find('.captionshop').fadeOut(150);
            }
        );
    });
</script>

</body>
</html>