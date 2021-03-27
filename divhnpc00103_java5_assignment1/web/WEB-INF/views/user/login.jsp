<%-- 
    Document   : login
    Created on : Feb 26, 2020, 10:03:56 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fDi" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cDi"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <base href="${pageContext.servletContext.contextPath}/">
        <link href="css/user.css" rel="stylesheet" type="text/css"/>
        <style>
            body {font-family: Arial, Helvetica, sans-serif;
                  background-image: url("image/1.jpg");

                  background-size: 100%;
                  margin: 0;

            }
        </style>
    </head>
    <body>
        <form class="formDn" action="user/login.htm" method="POST">
            <h3>Login</h3>
            <div class="imgcontainer">
                <img src="image/avatar.png" alt="Avatar" class="avatar" >
            </div>

            <div class="container">
                <lable> Username </lable>
                <input type="text" name="username" value=""/><br><br>
                <lable> Password </lable>
                <input type="password" name="password" value=""/>
                <button type="submit">Login</button>
                <button type="reset" >Reset</button>
            </div>

        </form

    </body>
</html>
