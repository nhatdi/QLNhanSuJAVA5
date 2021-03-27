<%-- 
    Document   : Staff
    Created on : Jan 17, 2020, 6:39:02 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fDi" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cDi"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="${pageContext.servletContext.contextPath}/">
        <link href="css/staff.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="bg-img">
            <fDi:form class="formDn1" action="staff/insert.htm" modelAttribute="Staff">
                <h1>THÔNG TIN NHÂN VIÊN</h1>

                <label>Mã nhân viên:</label>
                <fDi:input path="id"/>
                <label>Họ tên:</label>
                <fDi:input path="name"/><br>
                <label>Giới tính:</label>
                <fDi:radiobutton path="gender" value="true" label="Nam"/>
                <fDi:radiobutton path="gender" value="false" label="Nữ"/><br><br>
                <label>Ngày sinh:</label>
                <fDi:input path="birthday"/><br>
                <label>Lương:</label>
                <fDi:input path="salary"/><br>
                <label>Email:</label>
                <fDi:input path="email"/><br>
                <label>Điện thoại:</label>
                <fDi:input path="phone"/><br>
                <label>Hình:</label>
                <fDi:input path="photo"/><br>
                <lable>Ghi chú:</lable>
                <fDi:input path="notes"/><br>
                <lable>Phòng ban:</lable>
                    <fDi:select path="depart.id"
                                items="${departs}" itemValue="id" itemLabel="name"/>
                <br/>
                <br/>
                <input class="btn1" type="submit" name="btnAction" value="Insert"/>
                <input class="btn1" type="reset" name="btnAction" value="Reset"/>
                <br/>
                <br/>
                ${message}
                <hr>
                <table border="1">
                    <tr>
                        <th>Mã nhân viên</th>
                        <th>Họ tên</th>
                        <th>Giới tính</th>
                        <th>Hình ảnh</th>
                        <th>Ngày sinh</th>
                        <th>Lương</th>
                        <th>Email</th>
                        <th>Điện thoại</th>
                        <th>Hình</th>
                        <th>Ghi chú</th>
                        <th>Phòng ban</th>
                        <th>Action</th>
                    </tr>
                    <cDi:forEach var="t" items="${staffs }">
                        <tr>
                            <td>${t.id}</td>
                            <td>${t.name}</td>
                            <td>${t.gender?'Nam':'Nữ'}</td>
                            <td>${t.photo}</td>
                            <td>${t.birthday}</td>
                            <td>${t.salary}</td>
                            <td>${t.email}</td>
                            <td>${t.phone}</td>
                            <td>${t.photo}</td>
                            <td>${t.notes}</td>
                            <td>${t.depart.name}</td>
                            <td><a href="staff/edit/${t.id}.htm">Edit</a>
                                <a href="staff/delete/${t.id}.htm">Delete</a></td>
                        </tr>
                    </cDi:forEach>
                </table>
            </fDi:form>
        </div>
    </body>
</html>
