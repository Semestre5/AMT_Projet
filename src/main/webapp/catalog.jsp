<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.DAO.Objects.Article" %>

<%
    List<Article> articles = (List<Article>) request.getAttribute("articles");
%>
<!DOCTYPE html>
<html>
<%@include file="include/head.html"%>
<body>
<!-- HEADER =============================-->
<%@include file="include/nav.jsp"%>
<div class="container">
    <div class="row">
        <div class="col-md-12 text-center">
            <div class="text-pageheader">
                <div class="subtext-image" data-scrollreveal="enter bottom over 1.7s after 0.0s">
                    Gestion du catalogue
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
        <div class="row">
            <!-- Colonnes, à répartir sur 12 pour remplir la page, on peut mettre autant de colonnes qu'on veut tant qu'on
                 reste sur 12 (6 colonnes de 2 par exemple) -->
            <div class="col-md-6">
                <a href="product.add">
                    <button>Ajouter un nouvel article</button>
                </a>
            </div>
            <div class="col-md-6">
                <a href="category.add">
                    <button>Ajouter une nouvelle catégorie</button>
                </a>
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
                        <div class="product-name text-center">
                            <%out.print(a.getName());%>
                        </div>
                        <span class="maxproduct"><a href="shop/<%out.print(a.getId());%>"><img src="<% out.print(a.getLink());%>" alt=""></a></span>
                    </div>
                    <div class="product-details">
                        <span class="price">
                            <span class="edd_price"><%out.print(a.isSellable() ? "CHF " + a.getPrice() : "");%></span>
                        </span>
                        <%if (!a.isSellable()) {%>
                            <h4>Article unavailable</h4>
                        <%}%>
                        <p>Quantity : <%out.print(a.getQuantity());%></p>
                        <form method="POST" href="editquantity" action="editquantity">
                            <input hidden name="id" value="<%out.print(String.valueOf(a.getId()));%>"/>
                            <input type="number" name="newQuantity">
                            <input type="submit" value="Change quantity">
                        </form>
                    </div>
                </div>
            </div>
            <%}%>
        </div>
    </div>
</section>

<!-- FOOTER =============================-->
<%@include file="include/footer.html"%>

</body>
</html>