<%@ page import="com.DAO.Objects.Article" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                    Ajouter un nouvel article
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
            <%if (request.getAttribute("duplicatedName") != null && request.getAttribute("duplicatedID") != null){%>
            <div class="col-lg-8 col-lg-offset-2">
                <div class="alert alert-danger content">
                 <p class="text-danger text-center">
                     The object (<%out.print(request.getAttribute("duplicatedName"));%>) you wanted to create already exists<br>
                 </p>
                </div>
            </div>
            <%}%>
        </div>
        <div class="row">
            <!-- Colonnes, à répartir sur 12 pour remplir la page, on peut mettre autant de colonnes qu'on veut tant qu'on
                 reste sur 12 (6 colonnes de 2 par exemple) -->
            <div class="col-lg-8 col-lg-offset-2">
                <!-- Laisser id =contactform, c'est pour le visuel -->
                <form id="contactform" method="POST" action="articleAdd" enctype="multipart/form-data">
                    <div class="form">
                        <label for="name">Name *</label>
                        <input type="text" name="name" placeholder="Name *" required>
                        <label for="quantity">Quantity *</label>
                        <input type="number" name="quantity" placeholder="Quantity *" required>
                        <label for="image">Product Image</label>
                        <input type="file" name="image">
                        <label for="description">Description</label>
                        <textarea rows="5" name="description" placeholder="Description"></textarea>
                        <label for="price">Price</label>
                        <input type="number" name="price" placeholder="Price" step="0.01"> <br>
                        <input type="submit" class="clearfix btn" value="Add article"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<!-- FOOTER =============================-->
<%@include file="include/footer.html"%>

</body>
</html>