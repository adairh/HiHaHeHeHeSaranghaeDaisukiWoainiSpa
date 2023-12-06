<%@ page import="me.sjihh.spaservice.Authentication.Customer" %>
<%@ page import="me.sjihh.spaservice.Database.ServiceLoader" %>
<%@ page import="java.util.List" %>
<%@ page import="me.sjihh.spaservice.Booking.BookingDetail" %>
<%@ page import="me.sjihh.spaservice.Booking.Booking" %>
<%@ page import="me.sjihh.spaservice.Database.BookingDetailLoader" %>
<%@ page import="me.sjihh.spaservice.Database.RoomLoader" %>
<%@ page import="java.time.LocalDateTime" %>
<jsp:include page="/include/header/highHeader.jsp"/>
<link rel="stylesheet" href="css/profile.css">
<title>LuxurySpa a Spa Template</title>

<style>

	/* Style for the popup */
	.update {
		display: none;
		position: fixed;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
		width: 50%;
		height: 50%;
		background-color: #2f2618;
		padding: 20px;
		border-radius: 5px;
		box-shadow: 0 0 10px rgba(0, 0, 0, 0.3); /* Shadow effect */
		z-index: 100;
		overflow-y: scroll;
	}

	.passPop {
		 display: none;
		 position: fixed;
		 top: 50%;
		 left: 50%;
		 transform: translate(-50%, -50%);
		 width: 50%;
		 height: 50%;
		 background-color: #2f2618;
		 padding: 20px;
		 border-radius: 5px;
		 box-shadow: 0 0 10px rgba(0, 0, 0, 0.3); /* Shadow effect */
		 z-index: 100;
		 overflow-y: scroll;
	 }

	.overlay {
		display: none;
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent black overlay */
		justify-content: center;
		align-items: center;
		z-index: 1;
	}


</style>

<jsp:include page="/include/header/lowHeader.jsp"/>
<%
	String h1 = "Your Profile";
	String p  = "Discover our journey in our Spa.";
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
					<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
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
									<h6 class="mb-0">Address</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<%=user.getAddress()%>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-4">
									<a class="btn btn-info " target="__blank" href="logout">Log out</a>
								</div>
								<div class="col-4">
									<button onclick="showPopup()" class="btn btn-info">Edit</button>
								</div>
								<div class="col-4">
									<button onclick="showPassPopup()" class="btn btn-info">Password</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="overlay" id="overlay"></div>
			<div class="update" id="update">

				<span onclick="hidePopup()" class="close">X</span>

				<form method="post" action="updateProfile">

					<!-- existing code -->

					<div class="form-group">
						<label for="username">Username</label>
						<input type="text" class="form-control" id="username" name="username" value="<%= user.getUsername() %>">
					</div>

					<div class="form-group">
						<label for="email">Email</label>
						<input type="email" class="form-control" id="email" name="email" value="<%= user.getEmail() %>">
					</div>

					<div class="form-group">
						<label for="phone">Phone</label>
						<input type="text" class="form-control" id="phone" name="phone" value="<%= user.getPhone() %>">
					</div>

					<div class="form-group">
						<label for="address">Address</label>
						<textarea class="form-control" id="address" name="address"><%= user.getAddress() %></textarea>
					</div>

					<button type="submit" class="btn btn-primary">Update</button>

				</form>
			</div>

			<div class="passPop" id="passPop">

				<span onclick="hidePassPopup()" class="close">X</span>
				<form action="changePassword" method="post">

					<div class="form-group">
						<label for="old_password">Old Password</label>
						<input id="old_password" type="password" class="form-control" name="old_password">
					</div>

					<div class="form-group">
						<label for="new_password">New Password</label>
						<input id="new_password" type="password" class="form-control" name="new_password">
					</div>

					<button type="submit">Change Password</button>

				</form>
			</div>

			<div class="booking-panel">
				<!-- reg serviceLoader -->

				<div class="booking-panel-title">
					<h3>
						Booked list
					</h3>
				</div>
				<%
					String room = "";
					for(Booking booking : Booking.getBookingsByCustomerID(user.getId())) {

						%>

				<a class="row btn" onclick="showPreview(<%=booking.getBooking_ID()%>)">
					Booking: #<%=booking.getBooking_ID()%>
				</a><br>
					Date: <%=booking.getBooking_date()==null?"":booking.getBooking_date().toString()%>
				<%--<div class="booking-preview" id="booking-<%=booking.getBooking_ID()%>" style="display: none">
					<%
						for (BookingDetailLoader bookingDetail : BookingDetailLoader.getBookingDetailsByBookingID(booking.getBooking_ID())) {
					%>
						<table>
							<tr>
								<th>Service</th>
								<th>Status</th>
								<th>Preview</th>
							</tr>
							<tr>
								<td>
									<%=ServiceLoader.loadServices().get(bookingDetail.getService_ID()-1).getService_name()%>
								</td>
								<td>
									<%
										String s = "";
										Object o = booking.getBooking_date();
										if (o != null){
											if (LocalDateTime.now().isAfter(LocalDateTime.parse(o.toString()))) {
												s = "DONE";
											} else {
												s = "PENDING";
											}
										} else {
											s = "UNKNOWN";
										}
									%>
									<%=s%>;
								</td>
								<td>

									<form id="myForm">
										<label for="inputField">Input:</label>
										<input type="text" id="inputField" name="inputField" placeholder="Type something...">
										<button type="button" onclick="handleButtonClick()">Submit</button>
									</form>
								</td>
							</tr>
						</table>
						<b><br>
						</b>

					<%
						}
					%>
					<br><a onclick="hidePreview(<%=booking.getBooking_ID()%>)">Close</a>
				</div>--%>
				<br>
				<br>
				<div class="row">
					<div class="col-4">
						<%
							for (BookingDetailLoader bookingDetail : BookingDetailLoader.getBookingDetailsByBookingID(booking.getBooking_ID())) {
						%>
						<tr>
							<%=ServiceLoader.loadServices().get(bookingDetail.getService_ID()-1).getService_name()%>
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
						<%=booking.getTotal()%> VND
					</div>

				</div>

				<br>
				<%
					}
				%>
			</div>

		</div>
	</div>
</section>

<jsp:include page="/include/footer/highFooter.jsp"/>
<script>
	function showPopup() {
		console.log("action")
		// Get the reference to the div based on the service ID
		var popupDiv = document.getElementById("update");

		// Toggle the visibility
		if (popupDiv.style.display === "none") {
			popupDiv.style.display = "block";
		} else {
			popupDiv.style.display = "none";
		}
	}

	function hidePopup() {
		var overlay = document.getElementById("overlay");
		var popupDiv = document.getElementById("update");

		overlay.style.display = "none";
		popupDiv.style.display = "none";
	}

	function showPreview(bookmarkId) {
		console.log("action")
		// Get the reference to the div based on the service ID
		var popupDiv = document.getElementById("booking-"+bookmarkId);

		// Toggle the visibility
		if (popupDiv.style.display === "none") {
			popupDiv.style.display = "block";
		} else {
			popupDiv.style.display = "none";
		}
	}

	function hidePreview(bookmarkId) {
		var overlay = document.getElementById("overlay");
		var popupDiv = document.getElementById("booking-"+bookmarkId);

		overlay.style.display = "none";
		popupDiv.style.display = "none";
	}

	function showPassPopup() {
		console.log("action")
		// Get the reference to the div based on the service ID
		var popupDiv = document.getElementById("passPop");

		// Toggle the visibility
		if (popupDiv.style.display === "none") {
			popupDiv.style.display = "block";
		} else {
			popupDiv.style.display = "none";
		}
	}

	function hidePassPopup() {
		var overlay = document.getElementById("overlay");
		var popupDiv = document.getElementById("passPop");

		overlay.style.display = "none";
		popupDiv.style.display = "none";
	}

</script>



<jsp:include page="/include/footer/lowFooter.jsp"/>