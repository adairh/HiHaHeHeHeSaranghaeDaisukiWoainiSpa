<%@ page import="me.sjihh.spaservice.Database.StaffLoader" %>
<%@ page import="me.sjihh.spaservice.Database.ServiceLoader" %>
<jsp:include page="/include/header/highHeader.jsp"/>

<title>LuxurySpa a Spa Template</title>
<jsp:include page="/include/header/lowHeader.jsp"/>
<link rel="stylesheet" href="css/about.css">
<%
  String h1 = "About Luxury Spa";
  String p  = "Discover our world's #1 Luxury Service.";
%>
<%@ include file="/include/header/firstSection.jsp"%>


    <!-- END section -->

    <section class="site-section">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-md-4">
            <div class="heading-wrap  element-animate">
              <h4 class="sub-heading">Stay with our luxury rooms</h4>
              <h2 class="heading">Our Story</h2>
              <p class="">
                In the heart of tranquility, Luxury Spa was born out of a vision to redefine wellness and elevate the spa experience. Established in 2003 by visionary wellness enthusiasts, the company aimed to create a sanctuary where individuals could escape the stresses of modern life and embrace the art of self-care.
              </p>
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



    <section class="site-section game-section">
      <div class="container">
        <h2 class="heading">Our Staff</h2>
        <div class="owl-carousel custom-carousel owl-theme">
          <%
          for (int i = 0; i < 6; i++) {
            StaffLoader staffLoader = StaffLoader.loadStaffs().get(i);
          %>
          <div class="item <%=staffLoader.getStaff_ID()==1?"active" : ""%>" style="background-image: url(images/person_<%=i+1%>.jpg);">
            <div class="item-desc">
              <h3 class="mb-3"><a href="#"><%=staffLoader.getStaff_name()%></a></h3>
              <p class="mb-4">Experience staff of our Spa, and the major is doing <%=ServiceLoader.loadServices().get(staffLoader.getService_ID()-1).getService_name()%>!</p>
            </div>
          </div>
          <%
          }
          %>
        </div>
      </div>
    </section>

    <!-- END section -->

    <!-- END section -->
<jsp:include page="/include/footer/highFooter.jsp"/>
<script>
  $(".custom-carousel").owlCarousel({
    autoWidth: true,
    loop: true
  });
  $(document).ready(function () {
    $(".custom-carousel .item").click(function () {
      $(".custom-carousel .item").not($(this)).removeClass("active");
      $(this).toggleClass("active");
    });
  });

</script>
<jsp:include page="/include/footer/lowFooter.jsp"/>