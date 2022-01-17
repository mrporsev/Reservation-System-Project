<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
    <body>
    <center>
        <h1>Rent an instrument</h1>
        <br>
        <br>
        <br>
        <div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
            <div class="card-header">Enter your student ID:</div>
            <div class="card-body">
                <form method="post" action="/L3/Controller">
                    StudentID <input type="text" name="username">
                    <br> 
                    <br>
                    <input type="submit" name="studentIDCheck" class="btn btn-primary btn-sm" value="login">
                    <input type="submit" class="btn btn-primary btn-sm" name="forgotID" value="Forgot my studentID">
                    </div>
                    <br>


                </form>
                <p>Don't have an account?</p>
                <form action="register.jsp">
                    <input type="submit" value="register" class="btn btn-lg btn-success" />
                </form>
                <form action="learnInstrument.jsp">
                    <input type="submit" value="learn" class="btn btn-info" />
                </form>
                </center>
                </body>
                </html>
