<%-- 
    Document   : sucessLogin
    Created on : 5 jan. 2022, 22:55:02
    Author     : porse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="examples.*" %>
<jsp:useBean class="examples.DbHandler" id="dbh" scope="session"></jsp:useBean>

    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
            <title>Welcome</title>
        </head>
        <body>
        <center>
            <div class="jumbotron">
                <h1 class="display-3">Successful login</h1>
                <hr class="my-4">
                <form action="home.jsp">
                    <p align="right">
                        <input type="submit" class="btn-sm btn-secondary" value="Log out" />
                    </p>
                </form>
                <p class="lead">Account information:</p>
            </div>
            <table class="table table-hover"><%
                Personal_information ssi = (Personal_information) application.getAttribute("personal_info");
            %>
            <tr class="table-primary">
                <td>First name: <%= ssi.getFirst_name()%> </td>
            </tr>
            <tr class="table-primary">
                <td>Last name: <%= ssi.getLast_name()%> </td>
            </tr>
            <tr class="table-primary">
                <td>Age: <%= ssi.getAge()%> </td>
            </tr>
            <tr class="table-primary">
                <td>SSN: <%= ssi.getSocial_security_number()%> </td>
            </tr>
        </table>
        <form action="/L3/Controller" method="post">
            <h2> My rentals: </h2>
            <%
                Rental[] rentals = (Rental[]) application.getAttribute("rentals");
                for (Rental r : rentals) {


            %>
            </table>
            <table class="table table-hover">
                <tr>
                    <td>Lended: <%= r.getStartDate()%></td>
                    <td>Return date: <%= r.getEndDate()%></td>
                    <td>Instrument: <%= r.getInstrumentName()%></td>
                    <td> <button type="submit" name ="terminateInstrument" value="<%= r.getInstrumentId()%>" class="btn-sm btn-danger"> Terminate </button> </td>
                </tr>
            </table>
            <% } %>
            <h2>My booked lessons</h2>  
            <%
                GroupLesson[] myLessons = (GroupLesson[]) application.getAttribute("myLessons");
                for (GroupLesson lesson : myLessons) {

            %>
            <table class="table table-hover">
                <tr>
                    <td><strong>Class information:</strong> <%= lesson.getLessonContent()%></td>
                    <td><strong>Date:</strong> <%= lesson.getDate()%></td>
                    <td><strong>Time:</strong> <%= lesson.getTime()%></td>
                </tr>
            </table>
            <%  } %>
            <div>
                <input type="submit" name="listInstruments" value="List all instruments" class="btn btn-primary">
                <input type="submit" name="listRentals" value="See all rentals" class="btn btn-primary">
                <input type="submit" name="findAppointment" value="Find appointment" class="btn btn-primary">
                </form>
                <%
                    if (application.getAttribute("admin").equals("1")) {


                %>
                <input type="button" name="appointment" onclick="onButtonPress()" value="Create lesson" class="btn btn-primary">
                <input type="button" name="newAdmin" onclick="setNewAdmin()" value="Set new admin" class="btn btn-primary">
                <% }%>
            </div>
            <div style="display: none;" id="myDIV" class="alert alert-success 
                 alert-dismissible fade show mx-5 mt-4 
                 border border-success" role="alert">
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                <b>Create an appointment</b>
                <form action="/L3/Controller" method="post">
                    Date <input type="date" name="date"><br>
                    Time <input type="time" name="time"><br>
                    Content <input type="text" name="content"><br>

                    <input type = "submit" name="makeAppointment" value = "Make appointment">
                </form>
            </div>
            <div style="display: none;" id="adminDiv" class="alert alert-success 
                 alert-dismissible fade show mx-5 mt-4 
                 border border-success" role="alert">
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                <b>Set new student to admin</b>
                <form action="/L3/Controller" method="post">
                    Student ID: <input type="text" name="newAdminId"><br>

                    <input type = "submit" name="makeAdmin" value = "Make admin">
                </form>
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
    <script>
        function setNewAdmin() {
            var x = document.getElementById("adminDiv");
            if (x.style.display === "none") {
                x.style.display = "block";
            } else {
                x.style.display = "none";
            }
        }
    </script>
</body>
</body>
</html>
