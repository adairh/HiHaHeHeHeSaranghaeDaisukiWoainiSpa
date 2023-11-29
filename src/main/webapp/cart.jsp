<%@ page import="me.sjihh.spaservice.Authentication.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="me.sjihh.spaservice.Booking.BookingDetail" %>
<%@ page import="me.sjihh.spaservice.Database.ServiceLoader" %>
<%@ page import="me.sjihh.spaservice.Booking.BookingHandle" %>
<%@ page import="me.sjihh.spaservice.Database.LevelLoader" %>
<%@ page import="me.sjihh.spaservice.Database.RoomLoader" %>
<%@ page import="me.sjihh.spaservice.Database.*" %>
<jsp:include page="/include/header/highHeader.jsp"/>
<link rel="stylesheet" href="scss/cart.scss">
<title>LuxurySpa a Spa Template</title>
<jsp:include page="/include/header/lowHeader.jsp"/>

<%
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

                              <div class="row">
                                  <div class="col-12">
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
                                  </div>
                              </div>

                              <%
                                  }
                              %>


                              <!--<li class="items even">Item 2</li>-->

                          </ul>


                          <div class="row"></div>

                          <div class="row">
                              <div class="col-6">
                                <label for="roomSelect">Select a Room:</label>
                              </div>
                              <div class="col-6">
                                  <select name="room" id="roomSelect" onchange="updatePrice()" >
                                      <option value="0">Select ROOM</option>
                                      <%
                                          for (RoomLoader roomLoader : RoomLoader.loadRooms()) {
                                      %>
                                            <option value="<%=roomLoader.getRoom_id()%>">
                                                <%=roomLoader.getRoom_type()%>
                                            </option>
                                      <%
                                          }
                                      %>
                                  </select>
                              </div>
                          </div>

                          <div class="promoCode">
                              <label for="promo">Have A Promo Code?</label>
                              <div class="row">
                                  <div class="col-6">
                                      <input type="text" id="promo" name="promo" placeholder="Enter Code" value="<%=session.getAttribute("promo")%>"/>
                                  </div>
                                  <div class="col-6">
                                      <button class="btnn" onclick="updatePrice()">Apply Promo</button>
                                  </div>
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
                                  <p><span id="priceDisplay">0</span></p>
                              </span></li>

                                  <li class="totalRow"><span class="label">Member Discount</span><span class="value">
                                  <%=LevelLoader.getLevelByID(user.getLevel_id()).getSale_percent()%>
                              </span></li>
                                  <li class="totalRow"><span class="label">Voucher Discount</span><span class="value">
                                  <%
                                      int discount = 0;
                                      List<SaleOffLoader> saleOffLoaders = SaleOffLoader.loadSaleOffs();

                                      for (SaleOffLoader saleOffLoader : saleOffLoaders) {
                                          if (session.getAttribute("promo").equals(saleOffLoader.getSaleOff_code())) {
                                              discount = saleOffLoader.getSaleOff_percent();
                                          }
                                      }
                                  %>
                                  <%=discount%>%
                              </span></li>
                                  <li class="totalRow final"><span class="label">Total</span><span class="value">
                                  <%
                                      session.setAttribute("servicePrice",
                                              BookingHandle.getTotalPrice((List<BookingDetail>)session.getAttribute("services")));
                                  %>
                                  <p><span id="finalPrice"><%=session.getAttribute("totalPrice")%></span></p>


                              </span></li>
                                  <li class="totalRow"><a href="checkout" class="btnn continue">Checkout</a></li>
                              </ul>
                          </div>

                      </div>



              </div>


          <%
              }
          %>s



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
        window.location.href = "UpdatePrice?" +
            "room=" + document.getElementById("roomSelect").value +
            "&promo=" + document.getElementById('promo').value;
    }
</script>
<jsp:include page="/include/footer/lowFooter.jsp"/>