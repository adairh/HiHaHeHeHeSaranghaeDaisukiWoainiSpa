<jsp:include page="/include/header/highHeader.jsp"/>
    <title>LuxurySpa a Spa Template</title>
<jsp:include page="/include/header/lowHeader.jsp"/>

<%
  String h1 = "Welcome To Our Luxury Spa";
  String p  = "Discover our world's #1 Luxury Room For VIP.<p><a href=\"booknow.jsp\" class=\"btn btn-primary\">Book Now</a></p>";
%>
<%@ include file="/include/header/firstSection.jsp"%>

    <section class="site-section">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-md-4">
            <div class="heading-wrap text-center element-animate">
              <h4 class="sub-heading">Stay with our luxury rooms</h4>
              <h2 class="heading">Stay and Enjoy</h2>
              <p class="mb-5">
                Welcome to Luxury Spa, where indulgence meets serenity, and every moment is an invitation to unwind. As a premier spa service company, we take pride in offering an unparalleled sanctuary for relaxation and rejuvenation.
              </p>
              <p><a href="about.jsp" class="btn btn-primary btn-sm">More About Us</a></p>
            </div>
          </div>
          <div class="col-md-1"></div>
          <div class="col-md-7">
            <img src="images/f_img_1.png" alt="Image placeholder" class="img-md-fluid">
          </div>
        </div>
      </div>
    </section>
    <!-- END section -->



    <!-- END section -->


<jsp:include page="/include/footer/highFooter.jsp"/>
<jsp:include page="/include/footer/lowFooter.jsp"/>