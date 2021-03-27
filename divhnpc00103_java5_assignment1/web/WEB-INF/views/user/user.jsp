<%-- 
    Document   : Login
    Created on : Jan 17, 2020, 4:18:23 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fDi" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cDi"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <base href="${pageContext.servletContext.contextPath}/">
        <link href="css/user.css" rel="stylesheet" type="text/css"/>
        <style>
            body {font-family: Arial, Helvetica, sans-serif;
                  background-image: url("image/1.jpg");

                  background-size: 100%;
                  margin: 0;

            }*[id$=errors]{
                color:red;
                font-style: italic;
            }
        </style>
    </head>

    <body>
        <!--Form được hiển thị và xử lý nút thêm-->
        <fDi:form class="formDn" action="user/insert.htm" modelAttribute="User">

            <h3>User Page</h3>
            <div class="imgcontainer">
                <img src="image/avatar.png" alt="Avatar" class="avatar" >
            </div>
            ${message}
            <div class="container">
                <label> Username </label>
                <fDi:input path="username"/>
                <fDi:errors path="username"/><br><br>

                <label>Password</label>
                <fDi:input path="password" type="password"/>
                <fDi:errors path="password"/><br><br>

                <lable>Fullname</lable>
                    <fDi:input path="fullname"/>
                    <fDi:errors path="fullname"/><br><br>
                <button type="submit">Insert</button>

            </div>
            <!--Hiển thị thông tin lên bảng-->
            <hr>
            <table border="2">
                <tr>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Fullname</th>
                    <th>Action</th>
                </tr>
                <cDi:forEach var="s" items="${users }">
                    <tr>
                        <td>${s.username}</td>
                        <td>${s.password}</td>
                        <td>${s.fullname}</td>
                        <td><a href="user/edit/${s.username}.htm">Edit</a></td>
                    </tr>
                </cDi:forEach>

            </table>
        </fDi:form>

    </body>
</html>

