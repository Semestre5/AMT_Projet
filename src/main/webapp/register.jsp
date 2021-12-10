<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="include/head.html"%>
<script>
  // DPE - Dans une certaine mesure vous avez pas besoin d'utiliser du JS.
  function verifyPassword() {
    var pwd = document.getElementById("password").value;
    // DPE - Aie aie aie, ce message est déjà présent dans la réponse que le service d'authentification
    // Donc votre vue n'aurait pas besoin de connaitre le code juste savoir si il y a un message à afficher
    if(!pwd.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[#$^+=!*()@%&.])[A-Za-z\d#$^+=!*()@%&.]{8,}$/)){
      document.getElementById("message").innerHTML = "Password must be at least 8 char long, should contain at " +
              "least one uppercase char, one lowercase char, one digit and one special character";
      return false;
    }
    return true;
  }

  function verifyStatus() {
    <%
        Integer statusCode = (Integer) request.getAttribute("statusCode");
        if(statusCode != null && statusCode == 409){
    %>
      document.getElementById("message").innerHTML = "This username is already used, please choose another one";
    <%
        }
    %>
  }

  window.onload = function() {
      verifyStatus();
  }
</script>
<body>
<!-- HEADER =============================-->
<%@include file="include/nav.jsp"%>
<div class="container">
  <div class="row">
    <div class="col-md-12 text-center">
      <div class="text-pageheader">
        <div class="subtext-image" data-scrollreveal="enter bottom over 1.7s after 0.0s">
          Register
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
    <div class="underlined-title">
      <div class="editContent">
        <h1 class="text-center latestitems">CREATE A NEW ACCOUNT</h1>
      </div>
      <div class="wow-hr type_short">
			<span class="wow-hr-h">
			<i class="fa fa-star"></i>
			<i class="fa fa-star"></i>
			<i class="fa fa-star"></i>
			</span>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-8 col-lg-offset-2">
        <form method="post" action="register" id="contactform" onsubmit="return verifyPassword()">
          <div class="form">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" placeholder="Username *" required>
            <label for="password">Password</label>
            <input type="password" name="password" id="password" placeholder="Password *" required>
            <span id = "message" style="color:red"></span>
            <input type="submit" id="submit" class="clearfix btn" value="Register">
          </div>
        </form>
        <h4 class="text-center">Already registered ? <a href="login">Log in to your account</a></h4>
      </div>
    </div>
  </div>
</section>

<!-- FOOTER =============================-->
<%@include file="include/footer.html"%>

</body>
</html>