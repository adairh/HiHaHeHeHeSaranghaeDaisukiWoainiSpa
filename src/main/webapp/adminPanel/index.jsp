<%@ page import="me.sjihh.spaservice.Authentication.Customer" %>
<%@ page import="me.sjihh.spaservice.Booking.Booking" %>
<%@ page import="java.util.List" %>
<%@ page import="me.sjihh.spaservice.Booking.BookingDetail" %>
<%@ page import="me.sjihh.spaservice.Database.*" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.DecimalFormatSymbols" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">

    <!-- Title Page-->
    <title>Dashboard</title>

    <!-- Fontfaces CSS-->
    <link href="css/font-face.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
    <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

    <!-- Bootstrap CSS-->
    <link href="vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

    <!-- Vendor CSS-->
    <link href="vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
    <link href="vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
    <link href="vendor/wow/animate.css" rel="stylesheet" media="all">
    <link href="vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
    <link href="vendor/slick/slick.css" rel="stylesheet" media="all">
    <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="css/theme.css" rel="stylesheet" media="all">

</head>

<body class="animsition">
    <div class="page-wrapper">
        <!-- HEADER MOBILE-->

        <!-- MENU SIDEBAR-->
        <aside class="menu-sidebar d-none d-lg-block">
            <div class="logo">
                <a href="#">
                    <img src="images/icon/logo.png" alt="Spa Admin" />
                </a>
            </div>
            <div class="menu-sidebar__content js-scrollbar1">
                <nav class="navbar-sidebar">
                    <ul class="list-unstyled navbar__list">
                        <li class="active">
                            <a href="index.jsp">
                                <i class="fas fa-chart-bar"></i>Dash board</a>
                        </li>
                        <li class="active">
                            <a href="/logout">
                                <i class="fas fa-door-open"></i>Log out</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </aside>
        <!-- END MENU SIDEBAR-->

        <!-- PAGE CONTAINER-->
        <div class="page-container">

            <!-- HEADER DESKTOP-->

            <!-- MAIN CONTENT-->
            <div class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="row m-t-25">
                            <div class="col-sm-6 col-lg-3">
                                <div class="overview-item overview-item--c1">
                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="zmdi zmdi-account-o"></i>
                                            </div>
                                            <div class="text">
                                                <h2><%=Customer.getAllCustomers().size()%></h2>
                                                <span>members</span>
                                            </div>
                                        </div>
                                        <div class="overview-chart">
                                            <canvas id="widgetChart1"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-lg-3">
                                <div class="overview-item overview-item--c2">
                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="zmdi zmdi-shopping-cart"></i>
                                            </div>
                                            <div class="text">
                                                <h2><%=PreviewLoader.loadPreviews().size()%></h2>
                                                <span>reviews</span>
                                            </div>
                                        </div>
                                        <div class="overview-chart">
                                            <canvas id="widgetChart2"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-lg-3">
                                <div class="overview-item overview-item--c3">
                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="zmdi zmdi-calendar-note"></i>
                                            </div>
                                            <div class="text">
                                                <h2><%=Booking.loadBookings().size()%></h2>
                                                <span>bookings</span>
                                            </div>
                                        </div>
                                        <div class="overview-chart">
                                            <canvas id="widgetChart3"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-lg-3">
                                <div class="overview-item overview-item--c4">
                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="zmdi zmdi-money"></i>
                                            </div>
                                            <div class="text">
                                                <%
                                                    double total = 0;
                                                    DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
                                                    df.setMaximumFractionDigits(340);
                                                    for (Booking booking : Booking.loadBookings()) {
                                                        total += booking.getTotal();
                                                    }
                                                %>
                                                <h2><%=df.format(total*1000)%></h2>
                                                <span>total earnings</span>
                                            </div>
                                        </div>
                                        <div class="overview-chart">
                                            <canvas id="widgetChart4"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-12">
                                <h2 class="title-1 m-b-25">Booking Details</h2>
                                <div class="table-responsive table--no-card m-b-40">
                                    <table class="table table-borderless table-striped table-earning">
                                        <thead>
                                        <tr>
                                            <th>Booking ID</th>
                                            <th>Customer ID</th>
                                            <th>Booking Date</th>
                                            <th>Total</th>
                                            <th>Service Name</th>
                                            <th>Room Type</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            List<Booking> bookings = Booking.loadBookings();
                                            for (Booking booking : bookings) {
                                                List<BookingDetailLoader> bookingDetails = BookingDetailLoader.getBookingDetailsByBookingID(booking.getBooking_ID());
                                                for (BookingDetailLoader bookingDetail : bookingDetails) {
                                        %>
                                        <tr>
                                            <td><%= booking.getBooking_ID() %></td>
                                            <td><%= Customer.getUserById(booking.getCustomer_ID()).getUsername() %></td>
                                            <td><%= booking.getBooking_date() %></td>
                                            <td><%= booking.getTotal()*1000 %></td>
                                            <td><%= ServiceLoader.loadServices().get(bookingDetail.getService_ID()-1).getService_name() %></td>
                                            <td><%= RoomLoader.loadRooms().get(bookingDetail.getRoom_ID()-1).getRoom_type() %></td>
                                        </tr>
                                        <%
                                                }
                                            }
                                        %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <h2 class="title-1 m-b-25">Previews</h2>
                                <div class="table-responsive table--no-card m-b-40">
                                    <table class="table table-borderless table-striped table-earning">
                                        <thead>
                                        <tr>
                                            <th>Preview ID</th>
                                            <th>Admin ID</th>
                                            <th>Customer ID</th>
                                            <th>Service ID</th>
                                            <th>Comment</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            List<PreviewLoader> previews = PreviewLoader.loadPreviews();
                                            for (PreviewLoader preview : previews) {
                                        %>
                                        <tr>
                                            <td><%= preview.getPreview_ID() %></td>
                                            <td><%= preview.getAdmin_ID() %></td>
                                            <td><%= preview.getCustomer_ID() %></td>
                                            <td><%= preview.getService_ID() %></td>
                                            <td><%= preview.getComment() %></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                        </tbody>
                                    </table>
                                </div>
                                <h2 class="title-1 m-b-25">Customers</h2>
                                <div class="table-responsive table--no-card m-b-40">
                                    <table class="table table-borderless table-striped table-earning">
                                        <thead>
                                        <tr>
                                            <th>Customer ID</th>
                                            <th>Username</th>
                                            <th>Address</th>
                                            <th>Email</th>
                                            <th>Phone</th>
                                            <th>Level ID</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            List<Customer> customers = Customer.getAllCustomers();
                                            for (Customer customer : customers) {
                                        %>
                                        <tr>
                                            <td><%= customer.getId() %></td>
                                            <td><%= customer.getUsername() %></td>
                                            <td><%= customer.getAddress() %></td>
                                            <td><%= customer.getEmail() %></td>
                                            <td><%= customer.getPhone() %></td>
                                            <td><%= customer.getLevel_id() %></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-12">
                                <h2 class="title-1 m-b-25">Services</h2>
                                <div class="table-responsive table--no-card m-b-40">
                                    <table class="table table-borderless table-striped table-earning">
                                        <thead>
                                        <tr>
                                            <th>Service ID</th>
                                            <th>Admin ID</th>
                                            <th>Service Name</th>
                                            <th>Service Price</th>
                                            <th>Service Time</th>
                                            <th>Service Detail</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            List<ServiceLoader> services = ServiceLoader.loadServices();
                                            for (ServiceLoader service : services) {
                                        %>
                                        <tr>
                                            <td><%= service.getService_ID() %></td>
                                            <td><%= service.getAdmin_ID() %></td>
                                            <td><%= service.getService_name() %></td>
                                            <td><%= service.getService_price() %></td>
                                            <td><%= service.getService_time() %></td>
                                            <td><%= service.getService_detail() %></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <h2 class="title-1 m-b-25">Rooms</h2>
                                <div class="table-responsive table--no-card m-b-40">
                                    <table class="table table-borderless table-striped table-earning">
                                        <thead>
                                        <tr>
                                            <th>Room ID</th>
                                            <th>Room Type</th>
                                            <th>Room Price</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            List<RoomLoader> rooms = RoomLoader.loadRooms();
                                            for (RoomLoader room : rooms) {
                                        %>
                                        <tr>
                                            <td><%= room.getRoom_id() %></td>
                                            <td><%= room.getRoom_type() %></td>
                                            <td><%= room.getRoom_price() %></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                        </tbody>
                                    </table>
                                </div>

                                <!-- Staff Table -->
                                <h2 class="title-1 m-b-25">Staff</h2>
                                <div class="table-responsive table--no-card m-b-40">
                                    <table class="table table-borderless table-striped table-earning">
                                        <thead>
                                        <tr>
                                            <th>Staff ID</th>
                                            <th>Service ID</th>
                                            <th>Staff Name</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            List<StaffLoader> staffs = StaffLoader.loadStaffs();
                                            for (StaffLoader staff : staffs) {
                                        %>
                                        <tr>
                                            <td><%= staff.getStaff_ID() %></td>
                                            <td><%= staff.getService_ID() %></td>
                                            <td><%= staff.getStaff_name() %></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-12">
                                <h2 class="title-1 m-b-25">SaleOff</h2>
                                <div class="table-responsive table--no-card m-b-40">
                                    <table class="table table-borderless table-striped table-earning">
                                        <thead>
                                        <tr>
                                            <th>SaleOff ID</th>
                                            <th>Discount Percentage</th>
                                            <th>Discount Code</th>
                                            <th>Start</th>
                                            <th>End</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            List<SaleOffLoader> saleOffs = SaleOffLoader.loadSaleOffs();
                                            for (SaleOffLoader saleOff : saleOffs) {
                                        %>
                                        <tr>
                                            <td><%= saleOff.getSaleOff_ID() %></td>
                                            <td><%= saleOff.getSaleOff_percent() %></td>
                                            <td><%= saleOff.getSaleOff_code() %></td>
                                            <td><%= saleOff.getSaleOff_start() %></td>
                                            <td><%= saleOff.getSaleOff_finish() %></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- END MAIN CONTENT-->
            <!-- END PAGE CONTAINER-->
        </div>

    </div>

    <!-- Jquery JS-->
    <script src="vendor/jquery-3.2.1.min.js"></script>
    <!-- Bootstrap JS-->
    <script src="vendor/bootstrap-4.1/popper.min.js"></script>
    <script src="vendor/bootstrap-4.1/bootstrap.min.js"></script>
    <!-- Vendor JS       -->
    <script src="vendor/slick/slick.min.js">
    </script>
    <script src="vendor/wow/wow.min.js"></script>
    <script src="vendor/animsition/animsition.min.js"></script>
    <script src="vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
    </script>
    <script src="vendor/counter-up/jquery.waypoints.min.js"></script>
    <script src="vendor/counter-up/jquery.counterup.min.js">
    </script>
    <script src="vendor/circle-progress/circle-progress.min.js"></script>
    <script src="vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
    <script src="vendor/chartjs/Chart.bundle.min.js"></script>
    <script src="vendor/select2/select2.min.js">
    </script>

    <!-- Main JS-->
    <script src="js/main.js"></script>

</body>

</html>
<!-- end document-->
