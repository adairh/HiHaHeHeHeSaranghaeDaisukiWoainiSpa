
<div class="overlay" id="overlay"></div>
<%
  for (ServiceLoader service : ServiceLoader.loadServices()) {
%>
<div class="pop-up" id="review-<%=service.getService_ID()%>">

  <span onclick="hidePopup(<%=service.getService_ID()%>)" class="close">X</span>
  <div class="container-fluid text-center previews">

    <h2>REVIEW</h2>
    <!-- -->
    <%
      for (PreviewLoader pl : PreviewLoader.getPreviewsByServiceID(service.getService_ID())) {
    %>
    <div class="row">
      <div class="col-4">
      </div>
      <div class="col-4">
        <div class="new-message-box">
          <div class="new-message-box-info">
            <div class="commenter">
              <%=Customer.getUserById(pl.getCustomer_ID()).getUsername()%>
            </div>
            <div class="tip-box-info">
              <p><%=pl.getComment()%></p>
            </div>
          </div>
        </div>
      </div>
      <div class="col-4">
      </div>
    </div>


    <br>
    <%
      }
    %>
    <p><a href="${pageContext.request.contextPath}/booknow.jsp" class="btn btn-primary btn-sm">Book now!</a></p>

    <!-- -->

  </div>


</div>
<%
  }
%>

<%@ page import="me.sjihh.spaservice.Database.ServiceLoader" %>
<%@ page import="me.sjihh.spaservice.Database.PreviewLoader" %>
<%@ page import="me.sjihh.spaservice.Authentication.Customer" %>
<jsp:include page="/include/header/highHeader.jsp"/>
<link rel="stylesheet" href="scss/comment.scss">
<title>LuxurySpa a Spa Template</title>
<jsp:include page="/include/header/lowHeader.jsp"/>
<%
  String h1 = "Our Services";
  String p  = "Discover our world's #1 Luxury Services.";
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
                <%--<div class="overlap-text">
                  <span>
                    Featured Ser
                    <span class="ion-ios-star"></span>
                    <span class="ion-ios-star"></span>
                    <span class="ion-ios-star"></span>
                  </span>
                </div>--%>
              </figure>
              <div class="media-body">
                <h4 class="mt-0"><a href="#"><%=service.getService_name()%></a></h4>
                <ul class="room-specs">
                  <li><span class="ion-ios-clock"></span> <%=service.getService_time()%> Minutes</li>
                  <li><span class="ion-ios-hashtag"></span> Service ID: #<%=service.getService_ID()%> </li>
                </ul>
                <div style="overflow-y: scroll" class="service-detail">
                  <p><%=service.getService_detail()%></p>
                </div>
                <br>
                <p><button onclick="showPopup(<%=service.getService_ID()%>)" class="btn btn-primary btn-sm">Click to view review</button></p>
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

<script>
  function showPopup(serviceID) {
    // Get the reference to the div based on the service ID
    var popupDiv = document.getElementById("review-" + serviceID);

    // Toggle the visibility
    if (popupDiv.style.display === "none") {
      popupDiv.style.display = "block";
    } else {
      popupDiv.style.display = "none";
    }
  }

  function hidePopup(serviceID) {
    var overlay = document.getElementById("overlay");
    var popupDiv = document.getElementById("review-" + serviceID);

    overlay.style.display = "none";
    popupDiv.style.display = "none";
  }

</script>
<jsp:include page="/include/footer/lowFooter.jsp"/>


