<header class="item header margin-top-0">
  <div class="wrapper">
    <nav role="navigation" class="navbar navbar-white navbar-embossed navbar-lg navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button data-target="#navbar-collapse-02" data-toggle="collapse" class="navbar-toggle" type="button">
            <i class="fa fa-bars"></i>
            <span class="sr-only">Toggle navigation</span>
          </button>
          <a href="." class="navbar-brand brand"> WORK IN PROGRESS </a>
        </div>
        <div id="navbar-collapse-02" class="collapse navbar-collapse">
          <ul class="nav navbar-nav navbar-right">
            <li class="propClone"><a href=".">Home</a></li>
            <li class="propClone"><a href="shop">Shop</a></li>
            <li class="propClone"><a href="cart">Cart</a></li>
            <%
              if (session.getAttribute("idUser") == null) {
            %>
            <li class="propClone"><a href="login">Login</a></li>
            <li class="propClone"><a href="logindev">Login_Dev</a></li>
            <%
            } else {
            %>
            <li class="propClone"><a href="logout">Logout</a></li>
            <%
              }
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
              Increase Digital Sales
            </div>
            <div class="subtext-image" data-scrollreveal="enter bottom over 1.7s after 0.3s">
              Boost revenue with Scorilo
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</header>