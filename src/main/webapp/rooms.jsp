
<div class="overlay" id="overlay"></div>
<%
  for (ServiceLoader service : ServiceLoader.loadServices()) {
%>
<div class="pop-up" id="review-<%=service.getService_ID()%>">

  <span onclick="hidePopup(<%=service.getService_ID()%>)" class="close">X</span>


  detail
  asdasdasd

  <h2>REVIEW</h2>

  <%
    for (PreviewLoader pl : PreviewLoader.getPreviewsByServiceID(service.getService_ID())) {
  %>
    <%=pl.getComment()%>
  <br>
  <%
    }
  %>

</div>
<%
  }
%>

<%@ page import="me.sjihh.spaservice.Database.ServiceLoader" %>
<%@ page import="me.sjihh.spaservice.Database.PreviewLoader" %>
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
                  <li><span class="ion-ios-hashtag"></span> Service ID: #<%=service.getService_ID()%> </li>
                </ul>
                <p>Nulla vel metus scelerisque ante sollicitudin. Fusce condimentum nunc ac nisi vulputate fringilla. </p>
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
<style>
  /* Style for the overlay */
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

  /* Style for the popup */
  .pop-up {
    display: none;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 50%;
    height: 50%;
    background-color: #fff; /* White background */
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.3); /* Shadow effect */
    z-index: 2;
    overflow-y: scroll;
  }

</style>
<jsp:include page="/include/footer/lowFooter.jsp"/>


