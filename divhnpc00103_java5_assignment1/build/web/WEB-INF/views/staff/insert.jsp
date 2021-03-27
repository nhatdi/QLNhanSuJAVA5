<%-- 
    Document   : insert
    Created on : Feb 28, 2020, 12:44:58 PM
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
        <link href="css/staff.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="bg-img">
            <!--            <form class="anh"> 
                            <center>Ảnh Nhân Viên</center><br>
                            <img src="image/avatar.png" alt="" style="width:250px;"/>
                        </form>-->

            <fDi:form class="formDn1" action="staff/insert.htm" modelAttribute="Staff">
                <h1>THÔNG TIN NHÂN VIÊN</h1>

                <label>Mã nhân viên:</label>
                <fDi:input path="id"/>
                <label>Họ tên:</label>
                <fDi:input path="name"/><br>
                <label>Giới tính:</label>
                <fDi:radiobutton path="gender" label="Nam"/>
                <fDi:radiobutton path="gender" label="Nữ"/><br><br>
                <label>Ngày sinh:</label>
                <fDi:input path="birthday"/><br>
                <label>Lương:</label>
                <fDi:input path="salary"/><br>
                <label>Email:</label>
                <fDi:input path="email"/><br>
                <label>Điện thoại:</label>
                <fDi:input path="phone"/><br>
                <lable>Ghi chú:</lable>
                <fDi:input path="notes"/><br>
                <lable>Phòng ban:</lable>
                 <fDi:select path="depart.id"
                                items="${departs}" itemValue="id" itemLabel="name"/>
                <br/>
                <br/>
                {message}
                <input class="btn1" type="submit" name="btAction" value="Insert"/>
                <br/>
                <br/>
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
                            <td>${t.notes}</td>
                            <td>${t.depart.name}</td>
                        </tr>
                    </cDi:forEach>
                </table>
            </fDi:form>
        </div>
    </body>
</html>
