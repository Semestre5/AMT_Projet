<%@ page import="com.amt.object.Product" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Product product = (Product) request.getAttribute("productDetails");
%>
<!DOCTYPE html>
<html>
<%@include file="include/head.html" %>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<link href="../resources/css/style.css" rel="stylesheet">
<body>
<!-- HEADER =============================-->
<%@include file="include/deepNav.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12 text-center">
                    <div class="text-pageheader">
                        <div class="subtext-image" data-scrollreveal="enter bottom over 1.7s after 0.0s">
                            <%out.print(product.getName());%>
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
                <h1 class="text-center latestitems">Awesome products</h1>
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
            <div class="col-md-8">
                <div class="productbox">
                    <img src="../<%out.print(product.getUrlToImage());%>" alt="">
                    <div class="clearfix">
                    </div>
                    <br/>
                    <div class="product-details text-left">
                        <p>
                            <%out.print(product.getDescription());%>
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <a href="#" class="btn btn-buynow"><%out.print(product.getPrice());%> - Add to cart</a>
                <div class="properties-box">
                    <ul class="unstyle">
                        <li><b class="propertyname">Version:</b> 1.0</li>
                        <li><b class="propertyname">Image Size:</b> 2340x1200</li>
                        <li><b class="propertyname">Files Included:</b> mp3, audio, jpeg, png</li>
                        <li><b class="propertyname">Documentation:</b> Well Documented</li>
                        <li><b class="propertyname">License:</b> GNU</li>
                        <li><b class="propertyname">Requires:</b> Easy Digital Downloads</li>
                        <li><b class="propertyname">Environment:</b> Wordpress</li>
                        <li><b class="propertyname">Any Field Etc:</b> Any Detail</li>
                        <li><b class="propertyname">Number:</b> Up to 20 specifications in this box</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- FOOTER =============================-->
<%@include file="include/footer.html" %>

</body>
</html>