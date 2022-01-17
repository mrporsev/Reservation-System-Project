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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <title>List instruments</title>
    </head>
    <body>
    <center><h1>All available instruments</h1> </center>
    <table class="table table-hover">
        <% Instrument[] instruments = (Instrument[]) application.getAttribute("instruments");
            for (Instrument instrument : instruments) {
        %>

        <form method="post" action="/L3/Controller">

            <tr class="table-dark">
                <td>Type: <%= instrument.getType()%></td>
                <td>Cost: <%= instrument.getRental()%></td>
                <td>Is rented: <%= instrument.getRentalStatus()%></td>
                <td> <button type="submit" name ="rentInstrument" value="<%= instrument.getInstrumentId()%>" class="btn btn-success"> Rent </button> </td>
            </tr>


        </form>

        <%
            }
        %>
    </table>
    <form action="/L3/Controller" method="post">
        <input type="submit" name="goBack" value="Go back" class="btn btn-primary">
    </form>
</body>
</html>
