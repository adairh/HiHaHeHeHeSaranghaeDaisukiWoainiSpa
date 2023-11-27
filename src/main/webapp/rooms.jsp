<%@ page import="me.sjihh.spaservice.Database.ServiceLoader" %>
<jsp:include page="/include/header/highHeader.jsp"/>
<title>LuxurySpa a Spa Template</title>
<jsp:include page="/include/header/lowHeader.jsp"/>
<%
  String h1 = "Our Rooms";
  String p  = "Discover our world's #1 Luxury Room For VIP.";
%>
<%@ include file="/include/header/firstSection.jsp"%>
    <section class="site-section">
      <div class="container">
        <div class="row">


          <%
            for (ServiceLoader service : ServiceLoader.loadServices()) {
          %>

          <div class="col-md-4 mb-4">
            <div class="media d-block room mb-0">
              <figure>
                <img src="images/img_3.jpg" alt="Generic placeholder image" class="img-fluid">
                <div class="overlap-text">
                  <span>
                    Featured Room
                    <span class="ion-ios-star"></span>
                    <span class="ion-ios-star"></span>
                    <span class="ion-ios-star"></span>
                  </span>
                </div>
              </figure>
              <div class="media-body">
                <h4 class="mt-0"><a href="#"><%=service.getService_name()%></a></h4>
                <ul class="room-specs">
                  <li><span class="ion-ios-clock"></span> <%=service.getService_time()%> Minutes</li>
                  <li><span class="ion-ios-clock"></span> Service ID: #<%=service.getService_ID()%> </li>
                </ul>
                <p>Nulla vel metus scelerisque ante sollicitudin. Fusce condimentum nunc ac nisi vulputate fringilla. </p>
                <p><a href="#" class="btn btn-primary btn-sm">Click to view review</a></p>
              </div>
            </div>
          </div>

          <%
            }
          %>



        </div>
      </div>
    </section>

   
   

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
<jsp:include page="/include/footer/lowFooter.jsp"/>