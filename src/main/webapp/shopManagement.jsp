<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.DAO.Objects.Article" %>
<%@ page import="com.DAO.Access.ArticleOps" %>
<%@ page import="com.DAO.Objects.Category" %>
<%@ page import="com.amt.shopManagement.ShopManagementServlet" %>
<%@ page import="com.DAO.Access.CategoryOps" %>

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
        display: block;
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
    /* Dropdown Button */
    .dropbtn {
        background-color: #2f343f;
        color: white;
        padding: 16px;
        font-size: 16px;
        border: none;
    }

    /* The container <div> - needed to position the dropdown content */
    .dropdown {
        position: relative;
        display: inline-block;
    }

    /* Dropdown Content (Hidden by Default) */
    .dropdown-content {
        display: none;
        position: absolute;
        background-color: #f1f1f1;
        min-width: 160px;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        z-index: 1;
    }

    /* Links inside the dropdown */
    .dropdown-content a {
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
    }

    /* Change color of dropdown links on hover */
    .dropdown-content a:hover {background-color: #ddd;}

    /* Show the dropdown menu on hover */
    .dropdown:hover .dropdown-content {display: block;}

    /* Change the background color of the dropdown button when the dropdown content is shown */
    .dropdown:hover .dropbtn {background-color: #3A038BFF;}
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
        <div class="row" style="margin-bottom: 10px">
            <div class="dropdown">
                    <button class="dropbtn btn-success btn-lg">Ajouter</button>
                    <div class="dropdown-content">
                    <a href="articleAdd">
                        <button class="btn btn-light">Ajouter un nouvel article</button>
                    </a>

                    <a href="categoryAdd">
                        <button class="btn btn-light" >Gestion des catégories</button>
                    </a>
                </div>



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
                        <span class="maxproduct article_image"><img src="<% out.print(a.getLink());%>" alt=""></span>
                    </div>
                    <div class="product-details">
                        <div>
                        <h1><%out.print(a.getName());%></h1>
                        <%if (!a.isSellable()) {%>
                        <p>Unavailable</p>
                        <%} else { %>
                        <p>Available</p>
                        <%}%>
                        <h4> Price </h4>
                        <span class="edd_price"><%out.print(a.getPrice());%></span>

                        <h4>Quantity</h4>
                        <form method="POST" href="editquantity" action="editquantity">
                            <input hidden name="id" value="<%out.print(String.valueOf(a.getId()));%>"/>
                            <input style="width: 60px; text-align: center;" type="number" name="newQuantity" placeholder="<%out.print(a.getQuantity());%>">
                            <input class="btn btn-success" type="submit" value="Change quantity">
                        </form>
                        </div>

                        <div class="categories-display" style="height: 75px; overflow: auto;">
                                <%for (Category c : a.getCategories()){ %>
                            <button class="btn btn-outline-success btn-sm disabled"><%out.print(c.getName());%></button>
                                <%}%>
                        </div>
                        <h4>Choose a category</h4>
                        <form method="POST">
                            <input hidden name="articleId" value="<%out.print(String.valueOf(a.getId()));%>"/>
                            <select id="category" name="categoryId">
                                <%for  (Category c : categories){
                                    if (!a.hasCategory(c)){%>
                                <option style="text-align: center;" value="<%out.print(String.valueOf(c.getId()));%>"><%out.print(c.getName());%></option>
                                <%}}%>
                            </select>
                            <button class="btn btn-success" type="submit" value="">Add Category</button>
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