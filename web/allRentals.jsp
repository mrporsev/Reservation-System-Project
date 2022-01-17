<%-- 
    Document   : allRentals
    Created on : 11 jan. 2022, 18:24:09
    Author     : porse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="examples.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <title>All rentals</title>
    </head>
    <body>
        <form action="/L3/Controller" method="post">
            <h2> Instruments rented out by others: </h2>
            <%
                Rental[] rentals = (Rental[]) application.getAttribute("allRentals");
                for (Rental r : rentals) {


            %>
            <table class="table table-hover">
                <tr>
                    <td>Lended: <%= r.getStartDate()%></td>
                    <td>Return date: <%= r.getEndDate()%></td>
                    <td>Instrument: <%= r.getInstrumentName()%></td>
                    <td> <button type="submit" name ="reserve" value="<%= r.getInstrumentId()%>" class="btn-sm btn-danger"> Reserve </button> </td>
                </tr>
            </table>
            <% }%>
            <form action="/L3/Controller" method="post">
            <input type="submit" name="goBack" value="Go back" class="btn btn-primary">
            </form>
            </body>
            </html>
