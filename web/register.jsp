<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="examples.*" %>
<!DOCTYPE html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Users</title>
    <script type = "text/javascript">
        function myfunction() {
            alert("New user has been created");
        }
    </script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
<center>
    <h1>Register</h1>
    <div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
        <div class="card-header">Enter your information:</div>
        <div class="card-body">
            <form method="post" action="/L3/Controller">
                KTH email:<input type="text" name="registerMail"><br>
                Age:<input type="text" name="registerbirth"><br>
                First name:<input type="text" name="registerFirstName"><br>
                Last name:<input type="text" name="registerLastName"><br>
                SSN:<input type="text" name="registerSSN"><br>
                Phone:<input type="text" name="registerPhone"><br>
                Zip code:<input type="text" name="registerZip"><br>
                Street:<input type="text" name="registerStreet"><br>
                City:<input type="text" name="registerCity"><br>

                <input type = "submit" onclick = "myfunction()" name = "register" value = "registerVal">
                </div>
            </form>
            </center>
            </body>
            </html>