<%@ page import="com.amt.cart.CartServletModel" %>
<%@ page import="com.DAO.Objects.Cart" %>
<%@ page import="com.DAO.Objects.Article" %>
<%@ page import="com.DAO.Access.ArticleOps" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    CartServletModel cart = (CartServletModel) request.getAttribute("cart");
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
                    CART
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
                <h1 class="text-center latestitems">MY CART</h1>
            </div>
            <div class="wow-hr type_short">
			<span class="wow-hr-h">
			<i class="fa fa-star"></i>
			<i class="fa fa-star"></i>
			<i class="fa fa-star"></i>
			</span>
            </div>
        </div>
        <div id="edd_checkout_wrap" class="col-md-8 col-md-offset-2">
            <form id="edd_checkout_cart_form" method="post">
                <div id="edd_checkout_cart_wrap">
                    <table id="edd_checkout_cart" class="ajaxed">
                        <thead>
                        <tr class="edd_cart_header_row">
                            <th class="edd_cart_item_name">
                                Item Name
                            </th>
                            <th class="edd_cart_item_quantity">
                                Quantity
                            </th>
                            <th class="edd_cart_item_price">
                                Item Price
                            </th>
                            <th class="edd_cart_actions">
                                Actions
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for (Cart cartProduct : cart.getCartProductList()){
                                Article article = ArticleOps.fetchOne(cartProduct.getId().getIdArticle());
                        %>
                        <tr class="edd_cart_item" id="edd_cart_item_0_25" data-download-id="25">
                            <td class="edd_cart_item_name">
                                <a href="shop/<%out.print(String.valueOf(article.getId()));%>">
                                    <div class="edd_cart_item_image">
                                        <img width="25" height="25" src="./resources/images/scorilo2-70x70.jpg" alt="">
                                    </div>
                                    <span class="edd_checkout_cart_item_title"><%out.print(article.getName());%></span>
                                </a>
                            </td>
                            <td>
                                <span style="display: flex; justify-content: space-evenly; align-items: center">
                                    <form method="post">
                                        <input hidden name="id"
                                               value="<%out.print(String.valueOf(article.getId()));%>">
                                        <input hidden name="quantity"
                                               value="<%out.print(String.valueOf(cartProduct.getQuantity() - 1));%>">
                                        <input type="submit" class="edd_cart_remove_item_btn" value="&nbsp-&nbsp">
                                    </form>
                                    <%out.print(String.valueOf(cartProduct.getQuantity()));%>
                                    <form method="post">
                                        <input hidden name="id" value="<%out.print(String.valueOf(article.getId()));%>">
                                        <input hidden name="quantity"
                                               value="<%out.print(String.valueOf(cartProduct.getQuantity() + 1));%>">
                                        <input type="submit" class="edd_cart_remove_item_btn" value="&nbsp+&nbsp">
                                    </form>
                                </span>
                            </td>
                            <td class="edd_cart_item_price">
                                <%out.print(String.valueOf(article.getPrice()));%>
                            </td>
                            <td class="edd_cart_actions">
                                <form method="post">
                                    <input hidden name="id" value="<%out.print(String.valueOf(article.getId()));%>"/>
                                    <input hidden name="quantity" value="0"/> <!-- we set 0 to suppress object -->
                                    <input type="submit" class="edd_cart_remove_item_btn" value="Delete">
                                </form>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                        <tfoot>
                        <tr class="edd_cart_footer_row">
                            <th colspan="5">
                                <form method="post">
                                    <input hidden name="delete">
                                    <input type="submit" class="edd-cart-saving-button edd-submit button" id="edd-remove-cart-button" value="Remove cart">
                                </form>
                            </th>
                        </tr>
                        <tr class="edd_cart_footer_row edd_cart_discount_row" style="display:none;">
                            <th colspan="5" class="edd_cart_discount">
                            </th>
                        </tr>
                        <!-- <tr class="edd_cart_footer_row">
                            <th colspan="5" class="edd_cart_total">
                                Total: <span class="edd_cart_amount" data-subtotal="11.99" data-total="11.99">$11.99</span>
                            </th>
                        </tr> -->
                        </tfoot>
                    </table>
                </div>
            </form>
            </div>
        </div>
    </div>
</section>

<!-- FOOTER =============================-->
<%@include file="include/footer.html" %>

</body>
</html>