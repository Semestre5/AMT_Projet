<header class="item header margin-top-0">
    <div class="wrapper">
        <nav role="navigation" class="navbar navbar-white navbar-embossed navbar-lg navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button data-target="#navbar-collapse-02" data-toggle="collapse" class="navbar-toggle" type="button">
                        <i class="fa fa-bars"></i>
                        <span class="sr-only">Toggle navigation</span>
                    </button>
                    <a href=".." class="navbar-brand brand"> SCORILO </a>
                </div>
                <div id="navbar-collapse-02" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="propClone"><a href="..">Home</a></li>
                        <li class="propClone"><a href="../shop">Shop</a></li>
                        <li class="propClone"><a href="../cart">Cart</a></li>
                        <%
                            if (session.getAttribute("idUser") == null) {
                        %>
                        <li class="propClone"><a href="../login">Login</a></li>
                        <li class="propClone"><a href="../logindev">Login_Dev</a></li>
                        <%
                        } else {
                                String userRole = (String) session.getAttribute("roleUser");
                                if (userRole.equals("admin")) {
                        %>
                        <li class="propClone"><a href="../shopManagement">Management</a></li>
                        <%
                                } // end if role
                        %>
                        <li class="propClone"><a href="../logout">Logout</a></li>
                        <%
                            } // end if id
                        %>
                    </ul>
                </div>
            </div>
        </nav>