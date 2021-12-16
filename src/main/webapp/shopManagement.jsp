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

    .wrapper {
         position: relative;
         width: 100%;
         height: 100%;
         margin-top:25px;
         margin-bottom:80px;
    }

    button {
        font-family: 'Ubuntu', sans-serif;
        position: absolute;
        top: 50%;
        left: 50%;

        transform: translate(-50%, -50%);

        width: 170px;
        height: 40px;
        line-height: 1;
        font-size: 18px;
        font-weight: bold;
        letter-spacing: 1px;
        border: 3px solid black;
        background: #fff;
        color: black;
        border-radius: 40px;
        cursor: pointer;
        overflow: hidden;
        transition: all .35s;
    }

    button:hover {
        background: black;
        color: #fff;
    }

    button span {
        opacity: 1;
        visibility: visible;
        transition: all .35s;
    }

    .success {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: #fff;
        border-radius: 50%;
        z-index: 1;
        opacity: 0;
        visibility: hidden;
        transition: all .35s;
    }

    .success svg {
        width: 20px;
        height: 20px;
        fill: yellowgreen;
        transform-origin: 50% 50%;
        transform: translateY(-50%) rotate(0deg) scale(0);
        transition: all .35s;
    }

    button.is_active {
        width: 40px;
        height: 40px;
    }

    button.is_active .success {
        opacity: 1;
        visibility: visible;
    }

    button.is_active .success svg {
        margin-top: 50%;
        transform: translateY(-50%) rotate(720deg) scale(1);
    }

    button.is_active span {
        opacity: 0;
        visibility: hidden;
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
            <div class="wrapper">
                <div class=productbox>
                    <div class=fadeshop>
                        <span class="maxproduct article_image"><img src="<% out.print(a.getLink());%>" alt=""></span>
                    </div>
                    <div class="product-details">
                        <h1><%out.print(a.getName());%></h1>
                        <div class="wrapper">
                            <button class ="wrapper"> Price : <%out.print(a.getPrice());%></button>
                            <label>Categories</label>
                            <div>
                                <%for (Category c : a.getCategories()){ %>
                                   <button class="btn btn-admin"><%out.print(c.getName());%></button>
                                <%}%>
                            </div>
                        </div>
                        <%if (!a.isSellable()) {%>
                            <h4>Unavailable</h4>
                        <%} else { %>
                            <h4>Available</h4>
                        <%}%>
                        <p>Quantity : <%out.print(a.getQuantity());%></p>
                        <form method="POST" href="editquantity" action="editquantity">
                            <input hidden name="id" value="<%out.print(String.valueOf(a.getId()));%>"/>
                            <input type="number" name="newQuantity">
                            <input class="wrapper" type="submit" value="Change quantity">
                        </form>



                        <form method="POST">
                            <input hidden name="articleId" value="<%out.print(String.valueOf(a.getId()));%>"/>
                            <label for="category">Choose a category:</label>
                            <select id="category" name="categoryId">
                                <%for  (Category c : categories){
                                    if (!a.hasCategory(c)){%>
                                <option value="<%out.print(String.valueOf(c.getId()));%>"><%out.print(c.getName());%></option>
                                <%}}%>
                            </select>
                            <button class="wrapper" type="submit" value="Add Category to Current Article">Add Category to article
                            </button>

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