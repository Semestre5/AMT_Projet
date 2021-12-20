<%@ page import="com.DAO.Objects.Article" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%@include file="include/head.html" %>
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
    <%@include file="include/nav.jsp" %>
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

</header>
<body>
<!-- CONTENT =============================-->
<section class="item content">
    <div class="container toparea">
        <div class="row">
            <%if ( request.getAttribute( "duplicatedName" ) != null && request.getAttribute( "duplicatedID" ) != null ) {%>
            <div class="col-lg-8 col-lg-offset-2">
                <div class="alert alert-danger content">
                    <p class="text-danger text-center">
                        The object (<%out.print( request.getAttribute( "duplicatedName" ) );%>) you wanted to create
                        already exists<br>
                    </p>
                </div>
            </div>
            <%}%>
        </div>
        <div class="row">
            <!-- Colonnes, à répartir sur 12 pour remplir la page, on peut mettre autant de colonnes qu'on veut tant qu'on
                 reste sur 12 (6 colonnes de 2 par exemple) -->
            <div class="col-lg-8 col-lg-offset-2">
                <div class="form">
                <!-- Laisser id =contactform, c'est pour le visuel -->
                <form id="contactform" method="POST" action="articleAdd" enctype="multipart/form-data">

                        <Label>Nom (*):</Label>
                        <input type="text" name="name"  required>
                        <Label>Quantité (*):</Label>
                        <input type="number" name="quantity" required>
                        <Label>Image(s) :</Label>
                        <input type="file" name="image">
                        <Label>Description :</Label>
                        <textarea rows="5" name="description" ></textarea>
                        <Label>Prix:</Label>
                        <input type="number" name="price" step="0.01"> <br>
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
</section>

<!-- FOOTER =============================-->
<%@include file="include/footer.html" %>

</body>
</html>