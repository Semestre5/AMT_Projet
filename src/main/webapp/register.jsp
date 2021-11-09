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
        <form method="post" action="register" id="contactform">
          <div class="form">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" placeholder="Username *" required>
            <label for="password">Password</label>
            <input type="password" name="password" id="password" placeholder="Password *" required>
            <input type="submit" id="submit" class="clearfix btn" value="Register">
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