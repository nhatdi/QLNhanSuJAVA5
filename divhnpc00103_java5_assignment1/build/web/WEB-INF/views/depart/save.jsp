<%-- 
    Document   : save
    Created on : Feb 27, 2020, 11:22:22 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fDi" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cDi"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <base href="${pageContext.servletContext.contextPath}/">
        <link href="css/depart.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="bg-img">

            <fDi:form class="formDn1" action="depart/insert.htm" modelAttribute="Depart">
                <h1>Quản LÝ Phòng Ban</h1>

                <label> Mã Phòng Ban </label>
                <fDi:input path="id"/>
                <fDi:errors path="id"/><br><br>
                <label>Tên Phòng Ban</label>
                <fDi:input path="name"/>
                <fDi:errors path="name"/><br><br>

                <input class="btn1" type="submit" name="btAction" value="Save"/>


                <!--Hiển thị thông tin lên bảng-->
                ${message}
                <hr>
                <table border="1">
                    <tr>
                        <th>Mã Phòng Ban</th>
                        <th>Tên Phòng Ban</th>
                        <th>Action</th>
                    </tr>
                    <cDi:forEach var="d" items="${departs }">
                        <tr>
                            <td>${d.id}</td>
                            <td>${d.name}</td>
                            <td><a href="depart/edit/${d.id}.htm">Edit</a>
                            <a href="depart/delete/${d.id}.htm">Delete</a></td>
                        </tr>
                    </cDi:forEach>
                </table>
            </fDi:form>
        </div>
    </body>
</html>
