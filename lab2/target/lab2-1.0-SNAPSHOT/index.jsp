<%@page contentType="text/html" pageEncoding="UTF-8" import ="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="./styles/main.css" rel="stylesheet" type="text/css">
    <title>Main Page</title>
</head>
<body>

    <%
        Cookie [] cookies = request.getCookies();
        String username = null;
        String password = null;


        if (request.getAttribute("status") != null && request.getAttribute("status").equals("registration")) {
            username = (String) request.getAttribute("username");
            password = (String) request.getAttribute("password");
        } else {
            System.out.println("SET cokies");
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
                if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
        }
    %>

    <header>
        <nav class="ul-flex">
            <a href="registration">Registration Page</a>
            <a href="login">Login Page</a>
        </nav>
    </header>

    <div class="form-box">
        <div class="main-box">
            <p>Username: <span><%= username %></span></p>
            <p>Password: <span><%= password %></span></p>
        </div>
    </div>


</body>
</html>
