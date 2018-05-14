<%-- 
    Document   : index
    Created on : May 9, 2018, 4:21:05 PM
    Author     : NoName
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thi Tự luận - Đăng nhập</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="css/custom.css">
        <script src="bootstrap/jquery.min.js"></script>
        <script src="bootstrap/popper.min.js"></script>
        <script src="bootstrap/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            String name = (String)session.getAttribute("username");
            if (name == null) {
        %>
        <div class="container">
            
            <div class="row" id="content">
                <div class="header">
                    <span id="title">Thi tự luận</span>
                    <form id="login-form" action="<%=request.getContextPath()%>/login" method="post">
                        <input type="text" id="username" name="username" placeholder="username"><br>
                        <input type="password" id="password" name="password" placeholder="password"><br>
                        <input type="submit" id="login-btn" value="Đăng nhập">
                    </form>
                    <%
                        if(request.getParameter("error") != null){
                            out.print("<span style='color:#fff;'>Có lỗi xảy ra hoặc Tài khoản không tồn tại</span>");
                        }                        
                    %>
                </div>                
            </div>            
                                                          
        </div>    
        <script src="./js/custom.js"></script>
        <%} else {
        out.print("<h1>Bạn đã đăng nhập</h1>");
        }%>
    </body>
</html>