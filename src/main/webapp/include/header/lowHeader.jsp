<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/22/2023
  Time: 11:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text.jsp;charset=UTF-8" language="java" %>
</head>
<body>

<header role="banner">

    <nav class="navbar navbar-expand-md navbar-dark bg-light">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">LuxurySpa</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample05" aria-controls="navbarsExample05" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse navbar-light" id="navbarsExample05">
                <ul class="navbar-nav ml-auto pl-lg-5 pl-0">
                    <li class="nav-item">
                        <a class="nav-link active" href="index.jsp">Home</a>
                    </li>
                    <%--<li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="rooms.jsp" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Services</a>
                        <div class="dropdown-menu" aria-labelledby="dropdown04">
                            <a class="dropdown-item" href="rooms.jsp">Classic Service</a>
                            <a class="dropdown-item" href="rooms.jsp">Presidential Service</a>
                            <a class="dropdown-item" href="rooms.jsp">Luxury Service</a>
                            <a class="dropdown-item" href="rooms.jsp">Deluxe Service</a>
                        </div>

                    </li>--%>
                    <li class="nav-item">
                        <a class="nav-link" href="rooms.jsp">Service</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="about.jsp">About</a>
                    </li>
                    <%
                        if (session==null || session.getAttribute("user") == null) {
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="sign.jsp">Account</a>
                    </li>
                    <%
                        } else {
                            if (session.getAttribute("admin") == null || !(boolean)request.getSession().getAttribute("admin")) {
                    %>
                            <li class="nav-item">
                                <a class="nav-link" href="profile.jsp">Profile</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" href="cart.jsp">Cart</a>
                            </li>

                            <li class="nav-item cta">
                                <a class="nav-link" href="booknow.jsp"><span>Book Now</span></a>
                            </li>
                    <%
                            } else {
                                %>
                                <li class="nav-item">
                                    <a class="nav-link" href="admin/index.jsp">Admin</a>
                                </li>
                    <%
                            }
                        }
                    %>
                </ul>

            </div>
        </div>
    </nav>
</header>
<!-- END header -->