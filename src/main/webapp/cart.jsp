<%@ page import="me.sjihh.spaservice.Authentication.Customer" %>
<%@ page import="me.sjihh.spaservice.Database.ServiceLoader" %>
<%@ page import="java.util.List" %>
<%@ page import="me.sjihh.spaservice.Booking.BookingDetail" %>
<%@ page import="me.sjihh.spaservice.Database.ServiceLoader" %>
<%@ page import="me.sjihh.spaservice.Booking.BookingHandle" %>
<%@ page import="me.sjihh.spaservice.Database.LevelLoader" %>
<%@ page import="me.sjihh.spaservice.Database.RoomLoader" %>
<jsp:include page="/include/header/highHeader.jsp"/>
<link rel="stylesheet" href="scss/cart.scss">
<title>LuxurySpa a Spa Template</title>
<jsp:include page="/include/header/lowHeader.jsp"/>

<%
    double finalPrice = 0;
  String h1 = "Your cart";
  String p  = "Booking SPA room. #1 VIP Spa serviceLoader!";
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

                          <li class="items odd">
                              <table class="cart-item">
                                  <div class="infoWrap">
                                      <tr>
                                          <div class="cartSection">
                                              <td>
                                                  <img style="width: 150px;" src="${pageContext.request.contextPath}/images/big_image_1.jpeg" alt="" class="itemImg" />
                                              </td>
                                              <td>
                                                  <p class="itemNumber">#<%=bookingDetail.getService_ID()%></p>
                                              </td>
                                              <td>
                                                  <h3><%=ServiceLoader.loadServices().get(bookingDetail.getService_ID()).getService_name()%></h3>
                                              </td>


                                          </div>


                                          <td>
                                              <div class="prodTotal cartSection">
                                                  <p> <%=ServiceLoader.loadServices().get(bookingDetail.getService_ID()).getService_price()%>,000 VND</p>
                                              </div>
                                          </td>
                                          <td>
                                              <div class="cartSection removeWrap">
                                                  <a href="RemoveBooking?service=<%=bookingDetail.getService_ID()%>" class="remove">x</a>
                                              </div>
                                          </td>
                                      </tr>

                                  </div>
                              </table>

                          </li>

                          <%
                              }
                          %>


                          <!--<li class="items even">Item 2</li>-->

                      </ul>

                      <label for="roomSelect">Select a Room:</label>
                      <select id="roomSelect" onchange="updatePrice()">
                          <option value="0">Select ROOM</option>
                          <%
                              for (RoomLoader roomLoader : RoomLoader.loadRooms()) {
                          %>
                                <option value="<%=roomLoader.getRoom_id()%>"><%=roomLoader.getRoom_type()%></option>
                          <%

                              }
                          %>

                      </select>


                  </div>

                  <div class="promoCode">
                      <label for="promo">Have A Promo Code?</label><input type="text" name="promo" placholder="Enter Code" />
                      <a href="#" class="btnn"></a></div>

                  <div class="subtotal cf">
                      <ul>
                          <li class="totalRow"><span class="label">Subtotal</span><span class="value">
                              <%=BookingHandle.getTotalPrice((List<BookingDetail>)session.getAttribute("services"))%> VND
                          </span></li>

                          <li class="totalRow"><span class="label">Room</span><span class="value">
                              <p><span id="priceDisplay">0</span></p>
                          </span></li>

                          <li class="totalRow"><span class="label">Member Discount</span><span class="value">
                              <%=LevelLoader.getLevelByID(user.getLevel_id()).getSale_percent()%>
                          </span></li>
                          <li class="totalRow"><span class="label">Voucher Discount</span><span class="value">
                              <%=LevelLoader.getLevelByID(user.getLevel_id()).getSale_percent()%>
                          </span></li>
                          <li class="totalRow final"><span class="label">Total</span><span class="value">
                              <%
                                finalPrice =
                                        BookingHandle.getTotalPrice((List<BookingDetail>)session.getAttribute("services"))
                              *
                                        (100-LevelLoader.getLevelByID(user.getLevel_id()).getSale_percent())
                              /100;
                              %>
                              <p><span id="finalPrice"><%=finalPrice%></span></p>


                          </span></li>
                          <li class="totalRow"><a href="checkout" class="btnn continue">Checkout</a></li>
                      </ul>
                  </div>
              </div>


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
<script>
    function updatePrice() {
        var roomId = document.getElementById("roomSelect").value;

        // Make an AJAX request to the server to get the price based on the selected room ID
        // This is where you'd use JSP or Java to interact with your backend and retrieve the price

        // For now, let's assume you have a JavaScript function getPriceFromServer(roomId)
        // that makes an AJAX request to the server and returns the price

        getPriceFromServer(roomId).then(price => {
            document.getElementById("priceDisplay").textContent = price;
            document.getElementById("finalPrice").textContent = price + <%=finalPrice%>;
        });
    }

    // Simulating an AJAX request with a Promise
    function getPriceFromServer(roomId) {
        return new Promise((resolve) => {
            // Assuming you have a JSP endpoint that returns the price for the given room ID
            // This is where you'd interact with your JSP or Java backend
            // Replace this with the actual endpoint in your application
            fetch('<%= request.getContextPath() %>/getPrice?roomId='+roomId)
                .then(response => response.json())
                .then(data => resolve(data.price))
                .catch(error => console.error('Error:', error));
        });
    }
</script>
<jsp:include page="/include/footer/lowFooter.jsp"/>