<%@ page import="com.DAO.Objects.Article" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    boolean duplicatedError = false;
    if (request.getAttribute("errorDuplicate") != null){
        duplicatedError = true;
    }
    Article duplicate = (Article) request.getAttribute("duplicate");
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
            <%if (duplicatedError){%>
            <p class="text-primary danger"> The object you created already exists you can find it here : </p>
            <a href="shop/<%out.print(duplicate.getId());%>" class="text-primary danger"><%out.print(duplicate.getName());%></a>
            <%}%>
        </div>
        <div class="row">
            <!-- Colonnes, à répartir sur 12 pour remplir la page, on peut mettre autant de colonnes qu'on veut tant qu'on
                 reste sur 12 (6 colonnes de 2 par exemple) -->
            <div class="col-lg-8 col-lg-offset-2">
                <!-- Laisser id =contactform, c'est pour le visuel -->
                <form id="contactform" method="POST" action="articleAdd" enctype="multipart/form-data">
                    <div class="form">
                        <input type="text" name="name" placeholder="Name" required>
                        <input type="number" name="quantity" placeholder="Quantity" required>
                        <input type="file" name="image">
                        <textarea rows="5" name="description" placeholder="Description"></textarea>
                        <input type="number" name="price" placeholder="Price"> <br>
                        <h3><input type="submit" value="Add article"/></h3>
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