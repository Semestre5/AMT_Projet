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
<style>
    .article_image{
        width:331px;
        height:216px;
        object-fit:contain;
    }
    .categories-display{
        border: groove;
        margin: 2px;
    }
    .btn-admin{
        width: 450px;
        position: inherit;
    }
    .container-btn-admin{
        display: flex;
        justify-content: center;
    }
</style>
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
            <%if (articles.isEmpty()){%>
            <h1>
                No articles to display
            </h1>
            <%}
                for (Article a: articles) {%>
            <div class="col-md-4">
                <div class=productbox>
                    <div class=fadeshop>
                        <span class="maxproduct article_image"><img src="<% out.print(a.getLink());%>" alt=""></span>
                    </div>
                    <div class="product-details">
                        <h1><%out.print(a.getName());%></h1>
                        <span class="price">
                            <span class="edd_price">Price : <%out.print(a.getPrice());%></span>
                        </span>
                        <%if (!a.isSellable()) {%>
                            <h4>Article unavailable</h4>
                        <%} else { %>
                            <h4>Article available</h4>
                        <%}%>
                        <p>Quantity : <%out.print(a.getQuantity());%></p>
                        <form method="POST" href="editquantity" action="editquantity">
                            <input hidden name="id" value="<%out.print(String.valueOf(a.getId()));%>"/>
                            <input type="number" name="newQuantity">
                            <input class="btn-success" type="submit" value="Change quantity">
                        </form>
                        <div class="categories-display" style="height: 75px; overflow: auto;">
                            <h4>Categories :</h4>
                            <ul class="list-group">
                                <%for (Category c : a.getCategories()){ %>
                                <li class="list-group-item" style="padding: 2px; border: none;">
                                    <%out.print(c.getName());%>
                                </li>
                                <%}%>
                            </ul>
                        </div>
                        <form method="POST">
                            <input hidden name="articleId" value="<%out.print(String.valueOf(a.getId()));%>"/>
                            <label for="category">Choose a category:</label>
                            <select id="category" name="categoryId">
                                <%for  (Category c : categories){
                                    if (!a.hasCategory(c)){%>
                                <option value="<%out.print(String.valueOf(c.getId()));%>"><%out.print(c.getName());%></option>
                                <%}}%>
                            </select>
                            <input class="btn-success" type="submit" value="Add Category to Current Article">
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