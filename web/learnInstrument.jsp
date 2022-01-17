<%-- 
    Document   : learnInstrument
    Created on : 10 jan. 2022, 19:57:42
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
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
            <title>Learn</title>
        </head>
        <body>
        <center>
            <div class="jumbotron">
                <h1 class="display-3">Learn to play instruments</h1>
                <hr class="my-4">
                <p class="lead">Instruments:</p>
            </div>
        </center>
        <%
            Links[] links = dbh.getLink();

            for (Links s : links) {
        %>
    <center>
        <div class="card small" style="width: 18rem;">
            <div class="card-image">
                <img src="<%=s.getGif()%>" alt="W3Schools.com">
                <span class="card-title"><%=s.getInstrument()%></span>
            </div>
            <div class="card-content">
                <p>Learn how to play the <%=s.getInstrument()%> by clicking the link below!</p>
            </div>
            <div class="card-action">
                <a href='<%=s.getLink()%>'>

                    Educational video for the <%=s.getInstrument()%>

                </a>
            </div>
        </div>
    </center>
    <br>

    <%
        }
    %>

</body>
</html>
