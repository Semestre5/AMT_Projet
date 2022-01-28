<%--
  Created by IntelliJ IDEA.
  User: outca
  Date: 16/12/2021
  Time: 08:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.DAO.Objects.Article" %>
<%@ page import="com.DAO.Access.ArticleOps" %>
<%@ page import="com.DAO.Objects.Category" %>
<%@ page import="com.amt.shopManagement.ShopManagementServlet" %>

<%
  List<Article> articles = (List<Article>) request.getAttribute(ShopManagementServlet.ARTICLES);
  List<Category> categories = (List<Category>) request.getAttribute(ShopManagementServlet.CATEGORIES);

%>
<html>
<style>
  @import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');


  *{
    font-family: 'Poppins', sans-serif;
  }

  body{
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background: #131313;
  }

  .container{
    position: relative;
  }

  .container .card{
    display: inline-block;
    position: relative;
    width: 320px;
    height: 450px;
    background: #232323;
    border-radius: 20px;
    overflow: hidden;
  }

  .container .card:before{
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: #9bdc28;
    clip-path: circle(150px at 80% 20%);
    transition: 0.5s ease-in-out;
  }

  .container .card:hover:before{
    clip-path: circle(300px at 80% -20%);
  }

  .container .card:after{
    content: 'Nike';
    position: absolute;
    top: 30%;
    left: -20%;
    font-size: 12em;
    font-weight: 800;
    font-style: italic;
    color: rgba(255,255,25,0.05)
  }

  .container .card .imgBx{
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    z-index: 10000;
    width: 100%;
    height: 220px;
    transition: 0.5s;
  }

  .container .card:hover .imgBx{
    top: 0%;
    transform: translateY(0%);

  }

  .container .card .imgBx img{
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%) rotate(-25deg);
    width: 270px;
  }

  .container .card .contentBx{
    position: absolute;
    bottom: 0;
    width: 100%;
    height: 100px;
    text-align: center;
    transition: 1s;
    z-index: 10;
  }

  .container .card:hover .contentBx{
    height: 210px;
  }

  .container .card .contentBx h2{
    position: relative;
    font-weight: 600;
    letter-spacing: 1px;
    color: #fff;
    margin: 0;
  }

  .container .card .contentBx .size, .container .card .contentBx .color {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 8px 20px;
    transition: 0.5s;opacity: 0;
    visibility: hidden;
    padding-top: 0;
    padding-bottom: 0;
  }

  .container .card:hover .contentBx .size{
    opacity: 1;
    visibility: visible;
    transition-delay: 0.5s;
  }

  .container .card:hover .contentBx .color{
    opacity: 1;
    visibility: visible;
    transition-delay: 0.6s;
  }

  .container .card .contentBx .size h3, .container .card .contentBx .color h3{
    color: #fff;
    font-weight: 300;
    font-size: 14px;
    text-transform: uppercase;
    letter-spacing: 2px;
    margin-right: 10px;
  }

  .container .card .contentBx .size span{
    width: 50px;
    height: 35px;
    text-align: center;
    line-height: 32px;
    font-size: 12px;
    display: inline-flex;
    color: #111;
    background: #fff;
    margin: 0 5px;
    transition: 0.5s;
    color: #111;
    border-radius: 4px;
    cursor: pointer;
  }

  .container .card .contentBx .size span:hover{
    background: #9bdc28;
  }

  .container .card .contentBx .color span{
    width: 20px;
    height: 20px;
    background: #ff0;
    border-radius: 50%;
    margin: 0 5px;
    cursor: pointer;
  }

  .container .card .contentBx .color span:nth-child(2){
    background: #9bdc28;
  }

  .container .card .contentBx .color span:nth-child(3){
    background: #03a9f4;
  }

  .container .card .contentBx .color span:nth-child(4){
    background: #e91e63;
  }

  .container .card .contentBx a{
    display: inline-block;
    padding: 10px 20px;
    background: #fff;
    border-radius: 4px;
    margin-top: 10px;
    text-decoration: none;
    font-weight: 600;
    color: #111;
    opacity: 0;
    transform: translateY(50px);
    transition: 0.5s;
    margin-top: 0;
  }

  .container .card:hover .contentBx a{
    opacity: 1;
    transform: translateY(0px);
    transition-delay: 0.75s;

  }

</style>
  <head>

    <title>Title</title>
  </head>
  <body>
  <div class="container">
  <% List<Article> items = (List<Article>) ArticleOps.fetchAll();%>
  <% for (Article a: items) {%>



    <div class="card">
      <div class="imgBx">
        <img src="https://assets.codepen.io/4164355/shoes.png">
      </div>
      <div>


      </div>
      <div class="contentBx">
        <h2><%a.getName();%></h2>
        <div class="size">
            <h3>Categories :</h3>
          <%for (Category c : a.getCategories()){ %>
          <span><%out.print(c.getName());%></span>
          <br>
          <%}%>
        </div>

        <div class="color">
          <h3>Color :</h3>
          <span></span>
          <span></span>
          <span></span>
        </div>
        <a href="#">Buy Now</a>
      </div>
    </div>
    <%}%>
  </div>

  </body>
</html>
