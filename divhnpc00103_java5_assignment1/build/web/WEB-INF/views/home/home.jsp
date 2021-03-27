<%-- 
    Document   : Home
    Created on : Jan 17, 2020, 5:39:06 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cDi"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fDi" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="${pageContext.servletContext.contextPath}/">
        <style>
            .box{
                width: 100%;
                height: 100%;
                background-color: darkorchid;
                background-size: 100% 100%;}
            #menu li {
                color: black;
                display: inline-block;
                width: 150px;
                height: 40px;
                line-height: 40px;
                margin-left: 5px;
            }
            #menu a {
                text-decoration: none;
                color: cornsilk;
                display: block;
            }
            #menu a:hover {
                background: mediumorchid;
                color: white;
            }
            .left{
                text-align: left;
                color: white;
                text-indent: 200px;
                font-family: Palatino, Tahoma, sans-serif;
            }
            .box1{
                width: 100%;
                height: 870px;

                background-color: cornflowerblue;
                background-size: 100% 100%;}

        </style>
    </head>
    <body>
        <div class="box"></br>
            <table style="width:110%">
                <td><h1 class="left">TRANG CHỦ</h1>
                <th><div id="menu" >
                        <ul>
                            <li><center><a href="staff/staff.htm">NHÂN VIÊN</a></center></li>
                            <li><center><a href="depart/depart.htm">PHÒNG BAN</a></center></li>
                            <li><center><a href="record/record.htm">GHI NHẬN</a></center></li>
                            <li><center><a href="user/user.htm">NGƯỜI DÙNG</a></center></li>
                            <li><center><a href="user/login.htm">ĐĂNG XUẤT</a></center></li>
                        </ul>
                    </div></th> 
            </table>
        </div>
        <div class="box1"><br><br><br><br><br>
            <h1><center>10 NHÂN VIÊN CÓ THÀNH TÍCH CAO NHẤT</center></h1>
            <hr  width="8%" size="5px" align="center" color="grey"/>
            <br>
            <table style="width:100%">

            </table>
        </div>

    </body>
</html>
