<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.DAO.Objects.Article" %>
<%@ page import="com.DAO.Access.ArticleOps" %>
<%
    Article article = (Article) request.getAttribute("articleDetails");
%>
<!DOCTYPE html>
<html>
<%@include file="include/head.html" %>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<link href="../resources/css/style.css" rel="stylesheet">
<script>
    function incrementValue()
    {
        var value = parseInt(document.getElementById('number').value, 10);
        value = isNaN(value) ? 0 : value;
        value++;
        document.getElementById('number').value = value;
    }
    function decrementValue()
    {
        var value = parseInt(document.getElementById('number').value, 10);
        value = isNaN(value) ? 0 : value;
        if (value > 1) {
            value--;
        }
        document.getElementById('number').value = value;
    }
    function validNumber(){
        var value = parseInt(document.getElementById('number').value, 10);
        value = isNaN(value) ? 0 : value;
        if (value <= 0) {
            document.getElementById("message").innerHTML = "Quantity must be positive";
            return false;
        }
        return true;
    }
</script>

<body>
<!-- HEADER =============================-->
<%@include file="include/deepNav.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12 text-center">
                    <div class="text-pageheader">
                        <div class="subtext-image" data-scrollreveal="enter bottom over 1.7s after 0.0s">
                            <%out.print(article.getName());%>
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
                    <img src="../<%out.print(article.getLink());%>" alt="">
                    <div class="clearfix">
                    </div>
                    <br/>
                    <div class="product-details text-left">
                        <p>
                            <%out.print(article.getDescription());%>
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <%if (article.isSellable()) {%>
                <form method="post" action="../cart" onsubmit="return validNumber()">
                    <span style="display: flex;justify-content: space-evenly">
                        <input hidden name="id" value="<%out.print(String.valueOf(article.getId()));%>"/>
                        <input hidden name="incremental"/>
                        <input type="button" onclick="decrementValue()" class="edd_cart_remove_item_btn" value="&nbsp-&nbsp">
                        <input type="number" name="quantity" id="number" value="1"/>
                        <input type="button" onclick="incrementValue()" class="edd_cart_remove_item_btn" value="&nbsp+&nbsp">
                    </span>
                    <span id = "message" style="color:red"> </span>
                    <br>
                    <span style="display: flex; justify-content: center">
                        <input style="width: 100%" type="submit" class="btn-buynow" value="Send to cart"/>
                    </span>
                </form>
                <%} else {%>
                <span><h1>Article unavailable</h1></span>
                <%}%>
                    <!-- <div class="properties-box">
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
                    </div> -->
                </div>
            </div>
        </div>
    </section>

    <!-- FOOTER =============================-->
<%@include file="include/footer.html" %>

</body>
</html>