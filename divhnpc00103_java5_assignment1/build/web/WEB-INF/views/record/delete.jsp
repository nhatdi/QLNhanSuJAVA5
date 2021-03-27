<%-- 
    Document   : delete
    Created on : Feb 26, 2020, 5:43:56 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fDi" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cDi"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="${pageContext.servletContext.contextPath}/">
        <link href="css/record.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="bg-img">

            <fDi:form class="formDn1" action="record/insert.htm" modelAttribute="record">
                <h1>Thông Tin Thành Tích Kỹ Luật</h1>

                <div>
                    <label>Nhân viên</label>
                    <fDi:select path="staff.id"
                                items="${staffs}" itemValue="id" itemLabel="name"/>
                </div>
                <div>
                    <label>Loại:</label>
                    <fDi:radiobutton path="type" value="1" label="Thành tích"/>
                    <fDi:radiobutton path="type" value="0" label="Kỷ luật"/>
                </div>
                <div>
                    <label>Lý do:</label>
                    <br>
                    <fDi:textarea path="Reason" rows="5"/>
                </div>
                <br/>
                <br/>
                <input class="btn2" type="submit" name="action" value="Insert"/>
                <input class="btn2" type="submit" name="action" value="Thành tích cá Nhân"/>
                <input class="btn2" type="submit" name="action" value="Thành tích phòng Ban"/>
                <br/>
                <br/>

                <hr>
                <table class="table table-hover" border="1">
                    ${message}
                    <tr>
                        <th>Nhân Viên</th>
                        <th>Loại</th>
                        <th>Lý do</th>
                        <th>Ngày ghi nhân</th>
                        <th>Action</th>
                    </tr>
                    <cDi:forEach var="u" items="${records }">
                        <tr>
                            <td>${u.staff.name}</td>
                            <td>${u.type}</td>
                            <td>${u.reason}</td>
                            <td>${u.date}</td>
                            <td><a href="record/edit/${u.id}.htm">Edit</a>
                            <a href="record/delete/${u.id}.htm">Delete</a></td>
                        </tr>
                    </cDi:forEach>
                </table>
            </fDi:form>


        </div>
    </body>
</html>
