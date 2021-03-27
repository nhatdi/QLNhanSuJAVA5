<%-- 
    Document   : Insert
    Created on : Jan 17, 2020, 6:48:50 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fDi" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cDi"%>
<!DOCTYPE html>
<html>
    <head>
        <base href="${pageContext.servletContext.contextPath}/">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/record.css" rel="stylesheet" type="text/css"/>
    </head>
    <style>
        *[id$=errors]{
            color:red;
            font-style: italic;
        }
    </style>
    <body>
        <div class="bg-img">

            <fDi:form class="formDn1" action="record/insert.htm" modelAttribute="Record">
                <h1>Thông Tin Thành Tích Kỹ Luật</h1>

                <div>
                    <label>Nhân viên</label>
                    <fDi:select path="staff.id"
                                items="${staffs}" itemValue="id" itemLabel="name"/>
                </div>
                <div>
                    <label>Loại:</label>
                    <fDi:radiobutton path="type" value="1" label="Thành tích"/>
                    <fDi:radiobutton path="type" value="0" label="Kỷ luật"/><br><br>
                    <fDi:errors path="type"/>
                </div>
                <div>
                    <label>Lý do:</label>
                    <br>
                    <fDi:textarea path="Reason" rows="5"/>
                </div>


                <br/>
                <br/>
                <input class="btn2" type="submit" name="action" value="Insert"/>
                <a <input class="btn2" type="submit" href="staff/report.htm"/>Thành tích cá Nhân</a>
                <a <input class="btn2" type="submit" href="depart/report.htm"/>Thành tích phòng ban</a>
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
                            <td>${u.type == '1'?'':'Kỷ luật'}
                                ${u.type == '0'?'':'Thành tích'}</td>
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
