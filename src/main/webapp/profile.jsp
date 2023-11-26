<%@ page import="me.sjihh.spaservice.Authentication.Customer" %>
<%@ page import="me.sjihh.spaservice.Database.ServiceLoader" %>
<%@ page import="java.util.List" %>
<%@ page import="me.sjihh.spaservice.Booking.BookingDetail" %>
<%@ page import="me.sjihh.spaservice.Database.BookingLoader" %>
<%@ page import="me.sjihh.spaservice.Booking.Booking" %>
<%@ page import="me.sjihh.spaservice.Database.BookingDetailLoader" %>
<%@ page import="me.sjihh.spaservice.Database.RoomLoader" %>
<jsp:include page="/include/header/highHeader.jsp"/>
<link rel="stylesheet" href="css/profile.css">
<title>LuxurySpa a Spa Template</title>
<jsp:include page="/include/header/lowHeader.jsp"/>
<%
	String h1 = "Our Rooms";
	String p  = "Discover our world's #1 Luxury Room For VIP.";
%>
<%@ include file="/include/header/firstSection.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	if (session==null || session.getAttribute("user") == null) {
%>
<c:redirect url="sign.jsp" />
<%
	}
	assert session != null;
	Customer user = ((Customer)session.getAttribute("user"));

%>
<section class="site-section">
	<div class="container">
		<div class="main-body">

			<!-- Breadcrumb -->
			<%--<nav aria-label="breadcrumb" class="main-breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="index.html">Home</a></li>
					<li class="breadcrumb-item"><a href="javascript:void(0)">User</a></li>
					<li class="breadcrumb-item active" aria-current="page">User Profile</li>
				</ol>
			</nav>--%>
			<!-- /Breadcrumb -->

			<div class="row gutters-sm">
				<div class="col-md-4 mb-3">
					<div class="card">
						<div class="card-body">
							<div class="d-flex flex-column align-items-center text-center">
								<img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" class="rounded-circle" width="150">
								<div class="mt-3">
									<h4><%=user.getUsername()%></h4>
									<p class="text-secondary mb-1">Level: <%=user.getLevel_id()%></p>
									<p class="text-muted font-size-sm"><%=user.getAddress()%></p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-8">
					<div class="card mb-3">
						<div class="card-body">
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">User Name</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<%=user.getUsername()%>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Email</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<%=user.getEmail()%>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Phone</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<%=user.getPhone()%>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Mobile</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<%=user.getPhone()%>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<h6 class="mb-0">Address</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<%=user.getAddress()%>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-12">
									<a class="btn btn-info " target="__blank" href="https://www.bootdey.com/snippets/view/profile-edit-data-and-skills">Edit</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div>
				<!-- reg serviceLoader -->


				<%
					String room = "";
					for(BookingLoader booking : BookingLoader.getBookingsByCustomerID(user.getId())) {

						%>

				<div class="row">
					Booking: #<%=booking.getBooking_ID()%>
				</div>
				<br>
				<div class="row">
					<div class="col-4">
						<%
							for (BookingDetailLoader bookingDetail : BookingDetailLoader.getBookingDetailsByBookingID(booking.getBooking_ID())) {
						%>

						<tr>
							<%=ServiceLoader.loadServices().get(bookingDetail.getService_ID()).getService_name()%>
							<br>
							<% room = RoomLoader.loadRooms().get(bookingDetail.getRoom_ID()).getRoom_type(); %>
						</tr>
						<%
							}
						%>
					</div>
					<div class="col-4">
						<%=room%>
					</div>
					<div class="col-4">
						<%=booking.getTotal()%>
					</div>

				</div>
				<%

					}
				%>
			</div>

		</div>
	</div>
</section>

<jsp:include page="/include/footer/highFooter.jsp"/>
<jsp:include page="/include/footer/lowFooter.jsp"/>