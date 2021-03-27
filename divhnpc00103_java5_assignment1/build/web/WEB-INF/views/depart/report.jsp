<%-- 
    Document   : Departs_achievements
    Created on : Jan 17, 2020, 7:07:35 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body, html {
                height: 100%;
                font-family: Arial, Helvetica, sans-serif;
                margin: 0;
                padding: 0;

            }

            * {
                box-sizing: border-box;
            }

            .bg-img {
                /* The image used */
                background-color: aqua;
                max-width: 100%;
                min-height: 100%;
                height: 120%;   
                margin: 0;
                /* Center and scale the image nicely */
                background-position: center;
                background-repeat: no-repeat;
                background-size: cover;
                position: relative;
            }

            /* Style Form Đăng nhập */
            .formDn1 {
                position: absolute;
                margin: 20px;
                top: 1%;
                left: 17%;
                max-width: 1500px;
                padding: 16px;
                background-color: white;
                width: 1200px;
                height: auto;
                box-shadow: 7px 10px 10px;

                /*background-image: none;*/

            }


            /* Full-width input fields */
            input[type=text], input[type=password] {
                width: 100%;
                padding: 15px;
                margin: 5px 0 22px 0;
                border: none;
                background: #f1f1f1;
            }

            input[type=text]:focus, input[type=password]:focus {
                background-color: #ddd;
                outline: none;
            }

            table, th, td{
                border-top:1px solid #ccc;
                border-bottom:1px solid #ccc;
            }
            table{
                border-collapse:collapse;
                width:100%;
            }
            .table2{
                border-top:0px solid white;
                border-bottom:0px solid white;

            }
            th, td{
                text-align:center;
                padding:10px;
            }
        </style>
    </head>
    <body>
        <div class="bg-img">

            <form class="formDn1" action="ControllerSP" method="POST">
                <h1>Tổng hợp thành tích cá nhân</h1>

                <table border="1">
                    <tr>
                        <th>Phòng Ban</th>
                        <th>Tổng Thành Tích</th>
                        <th>Tổng Kỹ Luật</th>
                        <th>Điểm Thưởng</th>
                    </tr>

                </table>          
            </form>
        </div>
    </body>
</html>
