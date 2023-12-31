<%@ page import="me.sjihh.spaservice.Authentication.Customer" %>
<%@ page import="me.sjihh.spaservice.Database.ServiceLoader" %>
<%@ page import="java.util.List" %>
<%@ page import="me.sjihh.spaservice.Database.ServiceLoader" %>
<jsp:include page="/include/header/highHeader.jsp"/>
<link rel="stylesheet" href="css/booking.css">
<title>LuxurySpa a Spa Template</title>
<jsp:include page="/include/header/lowHeader.jsp"/>

<%
  String h1 = "Booking serviceLoaders";
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
            <div class="row align-items-stretch">
                <%
                    int i = 0;
                    for (ServiceLoader serviceLoader : ServiceLoader.loadServices()) {
                        i++;
                %>
            <div class="col-6 product" data-aos="fade-up" data-aos-delay="100">
                <div class="product-card">
                    <h2 class="name"><%=serviceLoader.getService_name()%></h2>
                    <span class="price"><%=serviceLoader.getService_price()%> VND</span>
                    <a class="popup-btn">Quick View</a>
                    <img src="${pageContext.request.contextPath}/images/background.png" class="product-img" alt="">
                </div>
                <div class="popup-view">
                    <div class="popup-card">
                        <a><i class="fas fa-times close-btn"></i></a>
                        <div class="product-img">
                            <img src="${pageContext.request.contextPath}/images/background.png" alt="">
                        </div>
                        <div class="info">
                            <h3><%=serviceLoader.getService_name()%></h3>
                            <p><%=serviceLoader.getService_detail()%></p>
                            <span class="price"><%=serviceLoader.getService_price()%> VND</span>
                            <a href="./booking?service=<%=i%>" class="add-cart-btn">Add to Cart</a>
                        </div>
                    </div>
                </div>
            </div>
                <%
                    }
                %>


            </div>
        </div>


        <%--<div class="container">
        <div class="row">

            <div class="row align-items-stretch">
                <%
                    int i = -1;
                    for (Service serviceLoader : Service.loadServices()) {
                        i++;
                %>
                <div class="col-6" data-aos="fade-up" data-aos-delay="100">
                    <a target="_blank" href="./booking?serviceLoader=<%=i%>" class="d-block photo-item">

                        <img src="${pageContext.request.contextPath}/images/big_image_1.jpeg" alt="Image" class="img-fluid mb-0">
                        <div class="photo-text-more">
                      <span class="icon icon-eye">
                        <h3 class="card-title"><%=serviceLoader.getService_name()%></h3>
                      </span>
                        </div>
                    </a>
                </div>

        </div>



      </div>--%>


    </section>
    <!-- END section -->


   
   


    <!-- END section -->
<jsp:include page="/include/footer/highFooter.jsp"/>
<script src="js/aos.js"></script>
<script type="text/javascript">
    var popupViews = document.querySelectorAll('.popup-view');
    var popupBtns = document.querySelectorAll('.popup-btn');
    var closeBtns = document.querySelectorAll('.close-btn');
    //javascript for quick view button
    var popup = function(popupClick) {
        popupViews[popupClick].classList.add('active');
    }
    popupBtns.forEach((popupBtn, i) => {
        popupBtn.addEventListener("click", () => {
            popup(i);
        });
    });
    //javascript for close button
    closeBtns.forEach((closeBtn) => {
        closeBtn.addEventListener("click", () => {
            popupViews.forEach((popupView) => {
                popupView.classList.remove('active');
            });
        });
    });
</script>


<jsp:include page="/include/footer/lowFooter.jsp"/>