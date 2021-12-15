<%@ page import="com.DAO.Objects.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="com.amt.shopManagement.CategoryAddServlet" %>
<%@ page import="com.DAO.Objects.Article" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% List<Category> categories = (List<Category>) request.getAttribute(CategoryAddServlet.CATEGORIES);
   Set<Article> articlesConcerned = (Set<Article>) request.getAttribute(CategoryAddServlet.ARTICLES_CONCERNED);
%>
<!DOCTYPE html>
<html>
<%@include file="include/head.html"%>
<%@include file="include/nav.jsp"%>
<style>
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
                    Ajouter une nouvelle catégorie
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</header>
<body>
<!-- CONTENT =============================-->
<section class="item content">
    <div class="container toparea">
        <div class="row">
            <!-- TODO MAKE THIS HTML CLEANER -->
            <div class="row">
                <%if (request.getAttribute("duplicatedName") != null){%>
                <div class="col-lg-8 col-lg-offset-2">
                    <div class="alert alert-danger content">
                        <p class="text-danger text-center"> The category you wanted to create already exists </p>
                    </div>
                </div>
                <%}%>
            </div>
            <div class="row">
                <!-- Colonnes, à répartir sur 12 pour remplir la page, on peut mettre autant de colonnes qu'on veut tant qu'on
                     reste sur 12 (6 colonnes de 2 par exemple) -->
                <div class="col-lg-8 col-lg-offset-2">
                    <!-- Laisser id =contactform, c'est pour le visuel -->
                    <form id="contactform" method="POST" action="categoryAdd">
                        <div class="form">
                            <Label>Nom du catégorie</Label>
                            <input type="text" name="name" placeholder="Name *" required>
                            <div class="wrapper">
                                <button class="">
                                    <span>Ajouter</span>
                                    <div class="success">
                                        <svg xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1"
                                             viewBox="0 0 29.756 29.756" style="enable-background:new 0 0 29.756 29.756;"
                                             xml:space="preserve">

	<path d="M29.049,5.009L28.19,4.151c-0.943-0.945-2.488-0.945-3.434,0L10.172,18.737l-5.175-5.173   c-0.943-0.944-2.489-0.944-3.432,0.001l-0.858,0.857c-0.943,0.944-0.943,2.489,0,3.433l7.744,7.752   c0.944,0.943,2.489,0.943,3.433,0L29.049,8.442C29.991,7.498,29.991,5.953,29.049,5.009z"/>
 </svg>
                                    </div>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <div class="text-center">
                <ul class="list-group">
                    <%if (categories == null || categories.isEmpty()){%>
                    <li class="list-group-item">No categories to display</li>
                    <%} else for (Category c : categories){%>
                    <li class="list-group-item">
                        <p><%out.print(c.getName());%></p>
                        <% if(request.getAttribute(CategoryAddServlet.NEED_CONFIRMATION) != null &&
                            c.getId() == request.getAttribute(CategoryAddServlet.NEED_CONFIRMATION)){%>
                            <div class="alert-warning alert">
                            <p> The category you want to delete contains the following articles :</p>
                            <ul class="list-group">
                            <% if (articlesConcerned != null){
                                for (Article categoryArticles: articlesConcerned){ %>
                                <!--TODO on peut faire ça sans le inline qui est pas terrible ? -->
                                <li class="list-group-item alert-warning alert" style="padding: 5px; border: none;background-color: #fcf8e3">
                                    <%out.print(categoryArticles.getName());%>
                                </li>
                                <% }
                                } %>
                            </ul>
                            <form method="post">
                                <input hidden name="delete_anyways" value="true"/>
                                <input hidden name="idCategorywithArticles" value="<%out.print(String.valueOf(c.getId()));%>"/>
                                <input type="submit" class="edd_cart_remove_item_btn" value="Delete Anyways">
                            </form>
                            </div>
                            <%} else {%>
                            <form method="post">
                                <input hidden name="id" value="<%out.print(String.valueOf(c.getId()));%>"/>
                                <input type="submit" class="edd_cart_remove_item_btn" value="Delete">
                            </form>
                            <%}%>
                    </li>
                    <%}%>
                </ul>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- FOOTER =============================-->
<%@include file="include/footer.html"%>

</body>
</html>