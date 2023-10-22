<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/12/2023
  Time: 11:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="floating-button">
  <button href="javascript:void(0);" onclick="goToHomepage()">Back to Homepage</button>
</div>
<style>
  .floating-button {
    position: fixed;
    bottom: 20px;
    right: 20px;
    z-index: 1000; /* Adjust the z-index to ensure it appears above other content */
  }

  .floating-button button {
    background-color: #007bff;
    color: #fff;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
  }
</style>
<script>
  function goToHomepage() {
    var currentLocation = window.location;
    window.location.href = currentLocation.protocol + '//' + currentLocation.host;
  }
</script>
