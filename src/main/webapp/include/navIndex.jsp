
<header class="item header margin-top-0">
  <div class="wrapper">
    <nav role="navigation" class="navbar navbar-white navbar-embossed navbar-lg navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button data-target="#navbar-collapse-02" data-toggle="collapse" class="navbar-toggle" type="button">
            <i class="fa fa-bars"></i>
            <span class="sr-only">Toggle navigation</span>
          </button>
          <%
          String userRole = (String) session.getAttribute("roleUser");
          if (userRole != null && userRole.equals("admin")) {
          %>
          <a href="" class="navbar-brand brand"> WORK IN PROGRESS </a>
          <%
          } else {
          %>
          <a href="." class="navbar-brand brand"> WORK IN PROGRESS </a>
          <%
          }
          %>
        </div>
        <div id="navbar-collapse-02" class="collapse navbar-collapse">
          <ul class="nav navbar-nav navbar-right">
            <%
              if (userRole != null && userRole.equals("admin")) {
            %>
            <li class="propClone"><a href="shopManagement">Management</a></li>
            <%
            } else {
            %>
            <li class="propClone"><a href="home">Home</a></li>
            <li class="propClone"><a href="shop">Shop</a></li>
            <li class="propClone"><a href="cart">Cart</a></li>
            <%
              } // end else role


              if (session.getAttribute("idUser") == null) {
            %>
            <li class="propClone"><a href="login">Login</a></li>
            <%
            } else {
            %>
            <li class="propClone"><a href="logout">Logout</a></li>
            <%
              } // end if id
            %>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container">
      <div class="row">
        <div class="col-md-12 text-center">
          <div class="text-homeimage">
            <div class="maintext-image" data-scrollreveal="enter top over 1.5s after 0.1s">
              Build it like nobody is watching
            </div>
            <div class="subtext-image" data-scrollreveal="enter bottom over 1.7s after 0.3s">
              Be the new Bob
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</header>