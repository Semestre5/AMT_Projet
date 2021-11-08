<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%@include file="include/head.html" %>
<body>
<!-- HEADER =============================-->
<%@include file="include/nav.html" %>
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
                <input class="form-check-input" type="checkbox" value="" id="tagCheckbox">
                <label class="form-check-label" for="tagCheckbox">
                    category 1
                </label>
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
            <div class="col-md-4">
                <div class="productbox">
                    <div class="fadeshop">
                        <div class="captionshop text-center" style="display: none;">
                            <h3>Item Name</h3>
                            <p>
                                This is a short excerpt to generally describe what the item is about.
                            </p>
                            <p>
                                <a href="#" class="learn-more detailslearn"><i class="fa fa-shopping-cart"></i> Purchase</a>
                                <a href="#" class="learn-more detailslearn"><i class="fa fa-link"></i> Details</a>
                            </p>
                        </div>
                        <span class="maxproduct"><img src="./resources/images/product1-1.jpg" alt=""></span>
                    </div>
                    <div class="product-details">
                        <a href="#">
                            <h1>Calypso Theme</h1>
                        </a>
                        <span class="price">
					        <span class="edd_price">$49.00</span>
                        </span>
                        <a class="btn-buynow" href="#">Add to cart</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- FOOTER =============================-->
<%@include file="include/footer.html" %>

</body>
</html>