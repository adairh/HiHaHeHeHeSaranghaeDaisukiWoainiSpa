<%@ page import="me.sjihh.spaservice.Authentication.Customer" %>
<%@ page import="me.sjihh.spaservice.Database.ServiceLoader" %>
<%@ page import="java.util.List" %>
<%@ page import="me.sjihh.spaservice.Booking.BookingDetail" %>
<%@ page import="me.sjihh.spaservice.Database.ServiceLoader" %>
<jsp:include page="/include/header/highHeader.jsp"/>
<link rel="stylesheet" href="css/cart.scss">
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

          <div class="row">
              <div class="row align-items-stretch">

                  <div class="wrap cf">
                      <h1 class="projTitle">Shopping Cart</h1>
                      <div class="heading cf">
                          <h1>My Cart</h1>
                          <a href="booknow.jsp" class="continue">Continue Shopping</a>
                      </div>
                      <div class="cart">

                          <div class="basket">
                              <div class="basket-module">
                                  <label for="promo-code">Enter a promotional code</label>
                                  <input id="promo-code" type="text" name="promo-code" maxlength="5" class="promo-code-field">
                                  <button class="promo-code-cta">Apply</button>
                              </div>
                              <div class="basket-labels">
                                  <ul>
                                      <li class="item item-heading">Item</li>
                                      <li class="price">Price</li>
                                  </ul>
                              </div>

                          <%
                              for (BookingDetail bookingDetail : (List<BookingDetail>)session.getAttribute("services")) {
                          %>
                          <div class="col-6" data-aos="fade-up" data-aos-delay="100">


                              <li class="items odd">

                                  <div class="infoWrap">
                                      <div class="cartSection">
                                          <img src="http://lorempixel.com/output/technics-q-c-300-300-4.jpg" alt="" class="itemImg" />
                                          <p class="itemNumber"><%=bookingDetail.getService_ID()%></p>
                                          <h3><%=ServiceLoader.loadServices().get(bookingDetail.getService_ID()).getService_name()%></h3>


                                      </div>


                                      <div class="prodTotal cartSection">
                                          <p>
                                              <%=ServiceLoader.loadServices().get(bookingDetail.getService_ID()).getService_price()%>,000 đ
                                          </p>
                                      </div>
                                      <div class="cartSection removeWrap">
                                          <a href="#" class="remove">x</a>
                                      </div>
                                  </div>
                              </li>

                              <div class="basket-product">
                                  <div class="item">
                                      <div class="product-image">
                                          <img src="http://placehold.it/120x166" alt="Placholder Image 2" class="product-frame">
                                      </div>
                                      <div class="product-details">
                                          <h1><strong>
                                              <%=ServiceLoader.loadServices().get(bookingDetail.getService_ID()).getService_name()%>
                                          </strong></h1>

                                          <p>Product Code - <%=bookingDetail.getService_ID()%></p>
                                      </div>
                                  </div>
                                  <div class="price">
                                      <%=ServiceLoader.loadServices().get(bookingDetail.getService_ID()).getService_price()%>
                                  </div>
                                  <div class="remove">
                                      <button>Remove</button>
                                  </div>
                              </div>

                          </div>
                          <%
                              }
                          %>

                  <!--<li class="items even">Item 2</li>-->

                      </div>


                      <aside>
                          <div class="summary">
                              <div class="summary-total-items"><span class="total-items"></span> Items in your Bag</div>
                              <div class="summary-subtotal">
                                  <div class="subtotal-title">Subtotal</div>
                                  <div class="subtotal-value final-value" id="basket-subtotal">130.00</div>
                                  <div class="summary-promo hide">
                                      <div class="promo-title">Promotion</div>
                                      <div class="promo-value final-value" id="basket-promo"></div>
                                  </div>
                              </div>
                              <div class="summary-delivery">
                                  <select name="delivery-collection" class="summary-delivery-selection">
                                      <option value="0" selected="selected">Select Collection or Delivery</option>
                                      <option value="collection">Collection</option>
                                      <option value="first-class">Royal Mail 1st Class</option>
                                      <option value="second-class">Royal Mail 2nd Class</option>
                                      <option value="signed-for">Royal Mail Special Delivery</option>
                                  </select>
                              </div>
                              <div class="summary-total">
                                  <div class="total-title">Total</div>
                                  <div class="total-value final-value" id="basket-total">130.00</div>
                              </div>
                              <div class="summary-checkout">
                                  <button class="checkout-cta">Go to Secure Checkout</button>
                              </div>
                          </div>
                      </aside>
                  </div>

              </div>
          </div>


              <div class="wrap cf">
                  <h1 class="projTitle">Responsive Table<span>-Less</span> Shopping Cart</h1>
                  <div class="heading cf">
                      <h1>My Cart</h1>
                      <a href="#" class="continue">Continue Shopping</a>
                  </div>
                  <div class="cart">

                      <ul class="cartWrap">
                          <li class="items odd">

                              <div class="infoWrap">
                                  <div class="cartSection">
                                      <img src="http://lorempixel.com/output/technics-q-c-300-300-4.jpg" alt="" class="itemImg" />
                                      <p class="itemNumber">#QUE-007544-002</p>
                                      <h3>Item Name 1</h3>

                                      <p> <input type="text"  class="qty" placeholder="3"/> x $5.00</p>

                                      <p class="stockStatus"> In Stock</p>
                                  </div>


                                  <div class="prodTotal cartSection">
                                      <p>$15.00</p>
                                  </div>
                                  <div class="cartSection removeWrap">
                                      <a href="#" class="remove">x</a>
                                  </div>
                              </div>
                          </li>



                          <!--<li class="items even">Item 2</li>-->

                      </ul>
                  </div>

                  <div class="promoCode"><label for="promo">Have A Promo Code?</label><input type="text" name="promo" placholder="Enter Code" />
                      <a href="#" class="btn"></a></div>

                  <div class="subtotal cf">
                      <ul>
                          <li class="totalRow"><span class="label">Subtotal</span><span class="value">$35.00</span></li>

                          <li class="totalRow"><span class="label">Shipping</span><span class="value">$5.00</span></li>

                          <li class="totalRow"><span class="label">Tax</span><span class="value">$4.00</span></li>
                          <li class="totalRow final"><span class="label">Total</span><span class="value">$44.00</span></li>
                          <li class="totalRow"><a href="#" class="btn continue">Checkout</a></li>
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
<jsp:include page="/include/footer/lowFooter.jsp"/>