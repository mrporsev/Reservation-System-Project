<%-- 
    Document   : listInstruments
    Created on : 8 jan. 2022, 14:09:54
    Author     : porse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="examples.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href=
              "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.slim.js">
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

        <title>Forgot ID</title>
    </head>
    <body>
    <center>
        <h1>Forgot Student Id Page</h1>
        <form method="post" action="/L3/Controller">
            KTH Email <input type="text" name="emailValue">
            KTH Password <input type="password" name="passwordValue">
            <input type="submit" name="email" onclick="onButtonPress()" class="btn bg-warning ml-4" value="sendEmail">
        </form>
        <div style="display: none;" id="myDIV" class="alert alert-success 
             alert-dismissible fade show mx-5 mt-4 
             border border-success" role="alert">
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            <b>Check your inbox for your studentID!</b>
        </div>
    </center>
    <script>
           function onButtonPress() {
               var x = document.getElementById("myDIV");
               if (x.style.display === "none") {
                   x.style.display = "block";
               } else {
                   x.style.display = "none";
               }
           }
    </script>
</body>
</html>
