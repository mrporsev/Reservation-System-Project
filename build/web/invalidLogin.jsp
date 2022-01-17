<%-- 
    Document   : sucessLogin
    Created on : 5 jan. 2022, 22:55:02
    Author     : porse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="examples.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WRONG STUDENT ID</title>
    </head>
    <body>
                <h1>Invalid student-id</h1>
        <table><tr><th>Username</th></tr>
        <%
            // pre defined variables are request, response, out, session, application
            Person[] users = (Person[])application.getAttribute("students");
            for(Person u : users){
        %>
    <tr>
        <td><%= u.getStudentID() %></td>
    </tr>

        <%
            }
        %>
        </table>

    </body>
    </body>
</html>
