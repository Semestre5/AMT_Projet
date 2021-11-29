<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%@include file="include/head.html" %>
<body>
<!-- HEADER =============================-->
<%@include file="include/nav.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-12 text-center">
            <div class="text-pageheader">
                <div class="subtext-image" data-scrollreveal="enter bottom over 1.7s after 0.0s">
                    Login
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
                <h1 class="text-center latestitems">CONNECT TO THE WEBSITE</h1>
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
                <form method="post" action="logindev" id="contactform">
                    <div class="form">
                        <label for="idUser">ID</label>
                        <input type="number" name="idUser" id="idUser" placeholder="ID *" required>
                        <label for="selectRole">Role</label>
                        <select class="form-control" id="selectRole" name="selectRole">
                            <option value="user">user</option>
                            <option value="admin">admin</option>
                        </select>
                        <br>
                        <input type="submit" id="submit" class="clearfix btn" value="Login">
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