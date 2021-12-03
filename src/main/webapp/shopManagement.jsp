<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.DAO.Objects.Article" %>
<%@ page import="com.DAO.Access.ArticleOps" %>
<%@ page import="com.DAO.Objects.Category" %>
<%@ page import="com.amt.shopManagement.ShopManagementServlet" %>

<%
    List<Article> articles = (List<Article>) request.getAttribute(ShopManagementServlet.ARTICLES);
    List<Category> categories = (List<Category>) request.getAttribute(ShopManagementServlet.CATEGORIES);

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
                    Shop management
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
            <!-- TODO travailler le visuel des boutons -->
            <div class="col-md-6">
                <a href="articleAdd">
                    <button class="clearfix button">Ajouter un nouvel article</button>
                </a>
            </div>
            <div class="col-md-6">
                <a href="categoryAdd">
                    <button class="clearfix button">Gestion des catégories</button>
                </a>
            </div>
        </div>
        <div class="row">
            <%if (articles.isEmpty()){%>
            <h1>
                No articles to display
            </h1>
            <!-- TODO ajouter la modif de plusieurs choses des articles, en particuliers leur catégorie , limiter la taille des images ?-->
            <%}
                for (Article a: articles) {%>
            <div class="col-md-4">
                <div class=productbox>
                    <div class=fadeshop>
                        <div class="product-name text-center">
                            <a href="shop/<%out.print(a.getId());%>">
                                <%out.print(a.getName());%>
                            </a>
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
                        <p>Categories :</p>
                        <ul>
                            <%for (Category c : a.getCategories()){ %>
                            <li> <%out.print(c.getName());%> </li>
                            <%}%>
                        </ul>
                        <form method="POST">
                            <input hidden name="articleId" value="<%out.print(String.valueOf(a.getId()));%>"/>
                            <label for="category">Choose a category:</label>
                            <select id="category" name="categoryId">
                                <%for  (Category c : categories){%>
                                <option value="<%out.print(String.valueOf(c.getId()));%>"><%out.print(c.getName());%></option>
                                <%}%>
                            </select>
                            <input type="submit" value="Add Category to Current Article">
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