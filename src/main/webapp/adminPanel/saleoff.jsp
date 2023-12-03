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
                                <i class="fas fa-chart-bar"></i>Bookings</a>
                        </li>
                        <li class="active">
                            <a href="services.jsp">
                                <i class="fas fa-chart-bar"></i>Services</a>
                        </li>
                        <li class="active">
                            <a href="customer.jsp">
                                <i class="fas fa-chart-bar"></i>Customer</a>
                        </li>
                        <li class="active">
                            <a href="rooms.jsp">
                                <i class="fas fa-chart-bar"></i>Rooms</a>
                        </li>
                        <li class="active">
                            <a href="staff.jsp">
                                <i class="fas fa-chart-bar"></i>Staff</a>
                        </li>
                        <li class="active">
                            <a href="preview.jsp">
                                <i class="fas fa-chart-bar"></i>Preview</a>
                        </li>
                        <li class="active">
                            <a href="saleoff.jsp">
                                <i class="fas fa-chart-bar"></i>SaleOff</a>
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

                        <!-- Popup panel -->
                        <div id="addSaleOffPanel" style="display:none;">
                            <h3>Add New SaleOff</h3>

                            <div class="card">
                                <div class="card-body card-block">
                                    <form>
                                        <label>Start Date:</label>
                                        <input class="form-control" type="datetime-local" id="saleOffStartDate">

                                        <label>End Date:</label>
                                        <input class="form-control" type="datetime-local" id="saleOffEndDate">

                                        <label>Discount Percent:</label>
                                        <input class="form-control" type="number" id="discountPercent">

                                        <label>Code:</label>
                                        <input class="form-control" type="text" id="discountCode">

                                        <br>

                                        <button class="btn" type="button" onclick="addSaleOff()">Save</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <!-- Hidden edit popup -->
                        <div id="editPopup" style="display:none;">
                            <h3>Edit Saleoff</h3>
                            <div class="card">
                                <div class="card-body card-block">
                                    <form>
                                        <input type="hidden" id="editId">
                                        <label>Start Date:</label>
                                        <input type="datetime-local" id="editStart">
                                        <label>Finish Date:</label>
                                        <input type="datetime-local" id="editFinish">
                                        <label>Percent:</label>
                                        <input id="editPercent">
                                        <label>Code:</label>
                                        <input id="editCode">
                                        <!-- other fields -->
                                    </form>
                                    <button onclick="saveEdit()">Save</button>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-6">
                                <h2 class="title-1 m-b-25">SaleOff</h2>
                            </div>
                            <div class="col-lg-6">
                                <button class="btn" id="addSaleOffBtn">Add New SaleOff</button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="table-responsive table--no-card m-b-40">
                                    <table class="table table-borderless table-striped table-earning">
                                        <thead>
                                        <tr>
                                            <th>SaleOff ID</th>
                                            <th>Discount Percentage</th>
                                            <th>Discount Code</th>
                                            <th>Start</th>
                                            <th>End</th>
                                            <th>Modify</th>
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
                                            <td>
                                                <button onclick="showEditPopup(<%= saleOff.getSaleOff_ID() %>)">Edit</button>
                                                |||
                                                <button onclick="deleteSaleoff(<%= saleOff.getSaleOff_ID() %>)">Delete</button>
                                            </td>
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
    <script>
        function addSaleOff() {

            // Get input values
            let start = document.getElementById("saleOffStartDate").value;
            let end = document.getElementById("saleOffEndDate").value;
            let percent = document.getElementById("discountPercent").value;
            let code = document.getElementById("discountCode").value;

            fetch("/AddSaleOff?start=" + start + "&end=" + end + "&percent=" + percent + "&code=" + code);

            // Reload page
            window.location.reload();
        }

        let addSaleOffBtn = document.getElementById("addSaleOffBtn");
        let panel = document.getElementById("addSaleOffPanel");

        addSaleOffBtn.onclick = function() {
            if (panel.style.display === "block") {
                panel.style.display = "none";
            }
            else {
                panel.style.display = "block";
            }
        }

        // Global variables
        let selectedSaleoff;

        function showEditPopup(saleoffId) {

            // Get saleoff data from API
            fetch("/getSaleoff?id=" + saleoffId)
                .then(res => res.json())
                .then(saleoff => {
                    selectedSaleoff = saleoff;

                    // Populate values
                    document.getElementById("editId").value = selectedSaleoff.id;
                    document.getElementById("editStart").value = selectedSaleoff.start;
                    document.getElementById("editEnd").value = selectedSaleoff.end;
                    document.getElementById("editPercent").value = selectedSaleoff.percent;
                    document.getElementById("editCode").value = selectedSaleoff.code;

                    // Show popup
                    document.getElementById("editPopup").style.display = "block";

                })
                .catch(err => {
                    console.log(err);
                });

        }

        function saveEdit() {

            // Get updated value
            let updatedSaleoff = {
                id: document.getElementById("editId").value,
                start: document.getElementById("editStart").value,
                end: document.getElementById("editEnd").value,
                percent: document.getElementById("editPercent").value,
                code: document.getElementById("editCode").value
            };

            // Validate input

            // Call API to update
            fetch("/EditSaleOff", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedSaleoff)
            })
                .then(res => {
                    // Close popup
                    document.getElementById("editPopup").style.display = "none";

                    // Reload page
                    window.location.reload();
                })
                .catch(err => {
                    console.log(err);
                });

        }

        function deleteSaleoff(saleoffId) {

            if (confirm("Are you sure?")) {

                fetch("/DeleteSaleOff?id=" + saleoffId, {
                    method: "DELETE"
                })
                    .then(res => {
                        window.location.reload();
                    })
                    .catch(err => {
                        console.log(err);
                    })

            }

        }


    </script>

</body>

</html>
<!-- end document-->
