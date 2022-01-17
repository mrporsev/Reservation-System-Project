<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="examples.*" %>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
        <script type = "text/javascript">
            function myfunction() {
            alert("New appointment has been made!");
         }
        </script>
    </head>
      <body>
        <form method="post" action="/L3/Controller">
            Date <input type="date" name="date"><br>
            Time <input type="time" name="time"><br>
            Content <input type="text" name="content"><br>
            <input type="hidden" name="makeAppointment" value="makeAppointment">
            <input type = "submit" onclick = "myfunction()" value = "Make appointment">
        </form>
    </body>
</html>