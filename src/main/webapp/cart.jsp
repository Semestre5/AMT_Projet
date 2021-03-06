<%@ page import="com.amt.cart.CartModel" %>
<%@ page import="com.DAO.Objects.Cart" %>
<%@ page import="com.DAO.Objects.Article" %>
<%@ page import="com.DAO.Access.ArticleOps" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    CartModel cart = (CartModel) request.getAttribute("cart");
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

        <div id="edd_checkout_wrap" class="col-md-8 col-md-offset-2">
            <% if (!cart.getCartProductList().isEmpty()) { %>
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
                                        <img width="25" height="25" src="<%out.print(String.valueOf(article.getLink()));%>" alt="">
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
                                        <button type="submit" class="btn btn-light" value="&nbsp-&nbsp"><span class="glyphicon glyphicon-triangle-bottom"> </span></button>
                                    </form>
                                    <%out.print(String.valueOf(cartProduct.getQuantity()));%>
                                    <form method="post">
                                        <input hidden name="id" value="<%out.print(String.valueOf(article.getId()));%>">
                                        <input hidden name="quantity"
                                               value="<%out.print(String.valueOf(cartProduct.getQuantity() + 1));%>">
                                        <button type="submit" class="btn btn-light" value="&nbsp+&nbsp"><span class="glyphicon glyphicon-triangle-top"> </span></button>
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
                                    <input type="submit" class="btn btn-danger" value="Delete">
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
                                    <button type="submit" class="btn btn-danger" style="float:left;" id="edd-remove-cart-button">Remove Cart<span class="glyphicon glyphicon-remove-circle" style="margin-left:5px"></span> </button>
                                </form>
                                <form method="post">
                                    <button class="btn btn-warning" style="float:right;">Checkout <span class="glyphicon glyphicon-euro" style="margin-left:5px"></span></button>
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
                    <button class="btn btn-light" style="float:right;"><a href="shop"/>Back to shop <span class="glyphicon glyphicon-back"></span></button>
                </div>
            </form>
            <%
            }
            else { %>
                <h2 style="align-content: center">Your cart is empty</h2>
                <h4 style="align-content: center">Go to our <a href="shop">shop</a></h4>
            <br><br><br><br>
            <%
            }
            %>
            </div>
        </div>
    </div>
</section>

<!-- FOOTER =============================-->
<%@include file="include/footer.html" %>

</body>
</html>