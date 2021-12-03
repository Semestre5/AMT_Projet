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
<body>
<!-- HEADER =============================-->
<%@include file="include/nav.jsp"%>
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

<!-- CONTENT =============================-->
<section class="item content">
    <div class="container toparea">
        <a href="shopManagement">
            <button class="clearfix button">Retour au Shop Management</button>
        </a>
        <div class="row">
            <!-- TODO MAKE THIS HTML CLEANER -->
            <div class="row">
                <%if (request.getAttribute("duplicatedName") != null){%>
                    <p class="text-primary danger"> The category you created already exists </p>
                <%}%>
            </div>
            <div class="row">
                <!-- Colonnes, à répartir sur 12 pour remplir la page, on peut mettre autant de colonnes qu'on veut tant qu'on
                     reste sur 12 (6 colonnes de 2 par exemple) -->
                <div class="col-lg-8">
                    <!-- Laisser id =contactform, c'est pour le visuel -->
                    <form id="contactform" method="POST" action="categoryAdd">
                        <div class="form">
                            <input type="text" name="name" placeholder="Name *" required>
                            <h3><input type="submit" value="Add category"/></h3>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="row">
            <ul class="list-group">
                <%if (categories == null || categories.isEmpty()){%>
                <li class="list-group-item">No categories to display</li>
                <%} else for (Category c : categories){%>
                <li class="list-group-item">
                    <p><%out.print(c.getName());%></p>
                    <% if(request.getAttribute(CategoryAddServlet.NEED_CONFIRMATION) != null &&
                            c.getId() == request.getAttribute(CategoryAddServlet.NEED_CONFIRMATION)){%>
                        <p> The category you want to delete contains the following articles :</p>
                        <ul>
                        <% if (articlesConcerned != null){
                            for (Article categoryArticles: articlesConcerned){ %>
                            <li><%out.print(categoryArticles.getName());%></li>
                            <% }
                            } %>
                        </ul>
                        <form method="post">
                            <input hidden name="delete_anyways" value="true"/>
                            <input hidden name="idCategorywithArticles" value="<%out.print(String.valueOf(c.getId()));%>"/>
                            <input type="submit" class="edd_cart_remove_item_btn" value="Delete Anyways">
                        </form>
                    <%}%>
                    <form method="post">
                        <input hidden name="id" value="<%out.print(String.valueOf(c.getId()));%>"/>
                        <input type="submit" class="edd_cart_remove_item_btn" value="Delete">
                    </form>
                </li>
                <%}%>
            </ul>
        </div>
    </div>
</section>

<!-- FOOTER =============================-->
<%@include file="include/footer.html"%>

</body>
</html>