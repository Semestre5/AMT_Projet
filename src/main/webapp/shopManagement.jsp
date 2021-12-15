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
        width: 200px;
        color:forestgreen;
        position: inherit;

    }
    .container-btn-admin{
        display: flex;
        justify-content: center;
    }
    <!--Details style here -->
    :root {
        --primColor: #dcdcdc;
        --secoColor: #555555;
        --cornerRad: 4px;
    }
    body {
        background-color: var(--primColor);
        font-family: Arial, Helvetica, sans-serif;
        font-size: 20px;
    }
    details {
        margin: 40px;
    }
    summary {
        writing-mode: vertical-lr;
        text-align: center;
        padding: 12px 10px;
        width: 23px;
        height: 17px;
        background-color: var(--primColor);
        border: 2px solid var(--secoColor);
        border-radius: var(--cornerRad);
        color: var(--secoColor);
        cursor: pointer;
        user-select: none;
        outline: none;
        transition: transform 200ms ease-in-out 0s;
    }
    summary::before,
    summary::after {
        position: static;
        top: 0;
        left: 0;
    }
    summary::before {
        content: "";
    }
    summary::after {
        content: "III";
        letter-spacing: -1px;
    }
    summary:hover {
        transform: scale(1.1);
    }
    summary::marker {
        font-size: 0;
    }
    summary::-webkit-details-marker {
        display: none;
    }
    details[open] .menu {
        animation-name: menuAnim;
    }
    details[open] summary::before {
        content: "X";
    }
    details[open] summary::after {
        content: "";
    }
    .menu {
        height: 0;
        width: fit-content;
        border-radius: var(--cornerRad);
        background-color: var(--primColor);
        box-shadow: 0 4px 12px 0 rgba(0, 0, 0, 0.2);
        margin-top: 8px;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        overflow: hidden;
        animation: closeMenu 300ms ease-in-out forwards;
    }
    .menu a {
        padding: 12px 24px;
        margin: 0 16px;
        color: var(--secoColor);
        border-bottom: 2px solid rgba(0, 0, 0, 0.1);
        text-decoration: none;
        text-align: center;
        transition: filter 200ms linear 0s;
    }
    .menu a:nth-of-type(1) {
        padding-top: 24px;
    }
    .menu a:nth-last-of-type(1) {
        border-bottom: none;
    }
    .menu a:hover {
        filter: brightness(200%);
    }
    details::before {
        content: "← Gestion d'article et catégories par là !";
        color: var(--secoColor);
        position: absolute;
        margin-left: 80px;
        padding: 10px 10px;
        opacity: 0.4;
    }
    details[open]::before {
        animation: fadeMe 300ms linear forwards;
    }
    @keyframes menuAnim {
        0% {
            height: 0;
        }
        100% {
            height: 312px;
        }
    }
    @keyframes fadeMe {
        0% {
            opacity: 0.4;
        }
        100% {
            opacity: 0;
        }
    }

</style>
<header>
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
</header>
<body>


<%@include file="include/nav.jsp"%>


<!-- CONTENT =============================-->
<section class="item content">
    <div class="container toparea">
        <div>
            <details>
                <summary></summary>
                <nav class="menu">
                    <a href="shopManagement">Assets</a>
                    <a href="articleAdd">Add Article</a>
                    <a href="categoryAdd">Add Category</a>
                    <a href="logout">Log out</a>
                </nav>
            </details>
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