<%@ page import="me.sjihh.spaservice.Authentication.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="me.sjihh.spaservice.Booking.BookingDetail" %>
<%@ page import="me.sjihh.spaservice.Database.ServiceLoader" %>
<%@ page import="me.sjihh.spaservice.Booking.BookingHandle" %>
<%@ page import="me.sjihh.spaservice.Database.LevelLoader" %>
<%@ page import="me.sjihh.spaservice.Database.RoomLoader" %>
<%@ page import="me.sjihh.spaservice.Database.*" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<jsp:include page="/include/header/highHeader.jsp"/>
<link rel="stylesheet" href="scss/cart.scss">


<title>LuxurySpa a Spa Template</title>
<jsp:include page="/include/header/lowHeader.jsp"/>

<%
  String h1 = "Your cart";
  String p  = "Booking SPA room. #1 VIP Spa service!";
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


          <%
              if (session.getAttribute("services") != null) {
          %>
          <script>
              function checkReferrer() {
                  var referrer = document.referrer;
                  var expectedPath = "/UpdatePrice";

                  if (!referrer.includes(expectedPath)) {
                      window.location.href = "/UpdatePrice";
                  }
              }
              window.onload = checkReferrer;
          </script>
          <div>
              <div class="wrap cf">
                  <h1 class="projTitle">Service Cart</h1>
                  <div class="heading cf">
                      <h1>My Cart</h1>
                      <a href="booknow.jsp" class="continue">Continue Booking</a>
                  </div>


                  <div class="cart">

                          <ul class="cartWrap">

                              <%
                                  for (BookingDetail bookingDetail : (List<BookingDetail>)session.getAttribute("services")) {
                              %>

                              <div class="row">
                                  <div class="col-4">
                                      <img style="width: 150px;" src="${pageContext.request.contextPath}/images/big_image_1.jpeg" alt="" class="itemImg" />
                                  </div>

                                  <div class="col-1">
                                      <p class="itemNumber">#<%=bookingDetail.getService_ID()%></p>
                                  </div>

                                  <div class="col-3">
                                      <h3><%=ServiceLoader.loadServices().get(bookingDetail.getService_ID()-1).getService_name()%></h3>
                                  </div>

                                  <div class="col-3">
                                      <div class="prodTotal cartSection">
                                          <p> <%=ServiceLoader.loadServices().get(bookingDetail.getService_ID()-1).getService_price()%>,000 VND</p>
                                      </div>
                                  </div>

                                  <div class="col-1">
                                      <div class="cartSection removeWrap">
                                          <a href="RemoveBooking?service=<%=bookingDetail.getService_ID()%>" class="remove">x</a>
                                      </div>
                                  </div>

                              </div>

                              <%
                                  }
                              %>


                              <!--<li class="items even">Item 2</li>-->

                          </ul>


                          <br>
                          <br>

                          <div class="row">
                              <div class="col-3">
                                <h4 for="roomSelect">Select a Room:</h4>
                              </div>
                              <div class="col-3">
                                  <select name="room" id="roomSelect" onchange="updatePrice()" >
                                      <option value="0">Select ROOM</option>
                                      <%
                                          int selected = 0;
                                          if (session.getAttribute("room") != null) {
                                              selected = (int) session.getAttribute("room");
                                          }
                                          selected += 1;
                                          for (RoomLoader roomLoader : RoomLoader.loadRooms()) {
                                      %>
                                            <option <%=
                                                roomLoader.getRoom_id()==selected?" selected ":""
                                            %> value="<%=roomLoader.getRoom_id()%>">
                                                <%=roomLoader.getRoom_type()%>
                                            </option>
                                      <%
                                          }
                                      %>
                                  </select>
                              </div>
                              <div class="col-3">
                                  <h4>Date of service</h4>
                              </div>

                              <div class="col-3">
                                  <!-- Date and Time Picker Input -->




                                  <input type="datetime-local" id="dateTimePicker" min="" max="">

                              </div>

                          </div>

                      <br>
                      <br>

                      </div>


                  <div class="promoCode">
                      <div class="row">
                          <%String promo = String.valueOf(session.getAttribute("promo"));%>
                          <span>
                            <input type="text" id="promo" name="promo" placeholder="Enter Code"
                                   value="<%=
                                     promo!=null?
                                     session.getAttribute("promo"):
                                     ""
                                     %>"/>
                              <label for="promo">Promo Code?</label>
                          </span>

                      </div>
                      <div class="row">
                          <button class="btn" onclick="updatePrice()">Apply Promo</button>
                      </div>
                      <div id="discountResult"></div>
                  </div>

                  <div class="subtotal cf">
                      <ul>
                          <li class="totalRow">
                              <span class="label">Subtotal</span>
                              <span class="value">
                                  <%=BookingHandle.getTotalPrice((List<BookingDetail>)session.getAttribute("services"))%> VND
                                      </span>
                          </li>

                          <li class="totalRow"><span class="label">Room</span><span class="value">
                                  <p><span id="priceDisplay">
                                      <%=
                                      (session.getAttribute("room") != null && (Integer)session.getAttribute("room") >= 0) ?
                                                RoomLoader.loadRooms().get((Integer) session.getAttribute("room")).getRoom_price()
                                                : 0
                                      %>
                                  </span></p>
                              </span></li>

                          <li class="totalRow"><span class="label">Member Discount</span><span class="value">
                                  <%=LevelLoader.getLevelByID(user.getLevel_id()).getSale_percent()%>%
                              </span></li>
                          <li class="totalRow"><span class="label">Voucher Discount</span><span class="value">
                                  <%
                                      int discount = 0;
                                      if (session.getAttribute("promo") != null) {
                                          List<SaleOffLoader> saleOffLoaders = SaleOffLoader.loadSaleOffs();

                                          for (SaleOffLoader saleOffLoader : saleOffLoaders) {
                                              if (session.getAttribute("promo").equals(saleOffLoader.getSaleOff_code())) {
                                                  discount = saleOffLoader.getSaleOff_percent();
                                              }
                                          }
                                      }
                                  %>
                                  <%=discount%>%
                              </span></li>
                          <li class="totalRow final"><span class="label">Total</span><span class="value">
                                  <%
                                      session.setAttribute("servicePrice",
                                              BookingHandle.getTotalPrice((List<BookingDetail>)session.getAttribute("services")));
                                      int price = (int) session.getAttribute("finalPrice");
                                      if (session.getAttribute("finalPrice") == null) {
                                          price = 0;
                                      }
                                  %>
                                  <p><span id="finalPrice"><%=price%></span></p>

                              </span></li>
                          <li class="totalRow"><a href="checkout" class="btnn continue">Checkout</a></li>
                      </ul>
                  </div>

              </div>
          <%
              }
              else {
          %>
              Nothing in your cart! <a href="booknow.jsp">BOOKING</a> now!
              <%
                  }
              %>

      </div>
    </section>
    <!-- END section -->


   
   

    <section class="section-cover" data-stellar-background-ratio="0.5" style="background-image: url(images/img_5.jpg);">
      <div class="container">
        <div class="row justify-content-center align-items-center intro">
          <div class="col-md-9 text-center element-animate">
            <h2>Relax and Enjoy your Holiday</h2>
            <p class="lead mb-5">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Architecto quidem tempore expedita facere facilis, dolores!</p>
            <div class="btn-play-wrap"><a href="https://vimeo.com/channels/staffpicks/93951774" class="btn-play popup-vimeo "><span class="ion-ios-play"></span></a></div>
          </div>
        </div>
      </div>
    </section>
    <!-- END section -->
<jsp:include page="/include/footer/highFooter.jsp"/>
<script src="js/aos.js"></script>
<%
    String formattedDateTime = "2023-12-5T13:15:34";
    if (request.getSession().getAttribute("time") != null) {
        // Parse the stored time string to LocalDateTime
        LocalDateTime date = LocalDateTime.parse((String) request.getSession().getAttribute("time"));
        formattedDateTime = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }

    // Format LocalDateTime to a string in the format 'yyyy-MM-ddTHH:mm'

%>
<script>


    function updatePrice() {
        window.location.href = "UpdatePrice?" +
            "room=" + (document.getElementById("roomSelect").value-1) +
            "&promo=" + document.getElementById('promo').value +
            "&time=" + document.getElementById('dateTimePicker').value;
    }

    // Get current date and time
    var currentDate = new Date();

    // Set the current date and time as the min attribute for the date-time picker
    document.getElementById('dateTimePicker').min = getCurrentDateTime();


    // Calculate the same day of the next month
    var nextMonth = new Date(currentDate);
    nextMonth.setMonth(currentDate.getMonth() + 1);

    // Set the max attribute for the date-time picker
    document.getElementById('dateTimePicker').max = nextMonth.getFullYear() + '-'
        + ('0' + (nextMonth.getMonth() + 1)).slice(-2) + '-'
        + ('0' + nextMonth.getDate()).slice(-2) + 'T21:00';

    // Set the initial value for the time picker


    <%--<%
        if (formattedDateTime.equals("")) {
    %>--%>
        document.getElementById('dateTimePicker').value = getCurrentDateTimeWithDefaultTime();
    <%--<%
        } else {
    %>
        document.getElementById('dateTimePicker').value = <%=formattedDateTime%>;
    <%
        }
    %>
--%>
    // Add an event listener to restrict the time to be between 9 am and 9 pm
    document.getElementById('dateTimePicker').addEventListener('input', function () {
        var selectedTime = this.value.split('T')[1];
        if (selectedTime < '09:00' || selectedTime > '21:00') {
            alert('Please select a time between 9 am and 9 pm.');
            this.value = getCurrentDateTimeWithDefaultTime();
        }
    });

    // Function to get the current date and time in YYYY-MM-DDTHH:mm format
    function getCurrentDateTime() {
        var year = currentDate.getFullYear();
        var month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
        var day = ('0' + currentDate.getDate()).slice(-2);
        var hours = ('0' + currentDate.getHours()).slice(-2);
        var minutes = ('0' + currentDate.getMinutes()).slice(-2);

        return year + '-' + month + '-' + day + 'T' + hours + ':' + minutes;
    }

    // Function to get the current date and default time (09:00)
    function getCurrentDateTimeWithDefaultTime() {
        var year = currentDate.getFullYear();
        var month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
        var day = ('0' + currentDate.getDate()).slice(-2);

        return year + '-' + month + '-' + day + 'T09:00';

    }

</script>
<jsp:include page="/include/footer/lowFooter.jsp"/>