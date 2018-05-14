<%-- 
    Document   : giaovien
    Created on : May 11, 2018, 10:07:35 PM
    Author     : NoName
--%>

<%@page import="mysql.TraLoi"%>
<%@page import="java.util.ArrayList"%>
<%
    String name = (String)session.getAttribute("username");
    int type = (int)session.getAttribute("type");
    String[] num = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
    if (name != null && type != 2) {
        ArrayList dstl = (ArrayList)request.getAttribute("dstl");
        //ArrayList info = (ArrayList)request.getAttribute("info");        
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang Giáo Viên</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/custom.css">
        <link rel="stylesheet" href="css/giaovien.css">
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">        
        <style type="text/css">
            .demo {
                display: inline-block;
                border: 1px solid #fff;
                width: auto;
                padding: 5px;
            }    
        </style>
        <script src="bootstrap/jquery.min.js"></script>
        <script src="bootstrap/popper.min.js"></script>
        <script src="bootstrap/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            
            <div class="row" id="content">
                
                <div class="header">
                
                    <div id="title">
                        <h2 style="color: #fff;">Xin chào <%=name%></h2>
                        <a href="<%=request.getContextPath()%>/dangxuat" style="color: #fff;">Đăng xuất</a>
                    </div>
                     
                    <div id="hs-list" style="color: #fff;">
                        <span>Danh sách các sinh viên đã tham gia trả lời</span>
                        <div>
                        <%
                        if (dstl.size() <= 0) {
                            out.println("<span>Chưa có ai làm bài</span>");
                        } else {
                            for (int i = 0; i < dstl.size(); i++) {
                                TraLoi tl = (TraLoi)dstl.get(i);
                                out.println("<a class='demo' href='javascript:void(0);' data-toggle='modal' data-target='#myModal' style='color: #fff;'>"+tl._Username+"</a>");
                            }
                        }                                                
                        %>
                        </div>
                    </div>
                    
                </div>
                    
            </div>
                    
            <div id="showModal"></div>
                        
        </div>
        
        <script>
            $(document).ready(function() {
                $('.demo').on('click', function(e) {
                    e.preventDefault();
                    var user = $(this);
                    $.ajax({
                        url: '<%=request.getContextPath()%>/getinfo?user='+user.text(),
                        method: 'GET',
                        success: function(result) {
                            $('#showModal').append(result);
                            $('#myModal').modal("show");                            
                        }
                    });
                    $('#showModal').empty();
                });
                
                $('.form .stages label').on('click', function() {
                    
                    var radioButtons = $('.form input:radio');
                    var selectedIndex = radioButtons.index(radioButtons.filter(':checked'));
                    selectedIndex = selectedIndex + 1;
                });
                
                $(document).on('click', '#btnNext', function() {
                    
                    var radioButtons = $('.form input:radio');
                    var selectedIndex = radioButtons.index(radioButtons.filter(':checked'));

                    selectedIndex = selectedIndex + 2;

                    $('.form input[type="radio"]:nth-of-type(' + selectedIndex + ')').prop('checked', true);
                });
                
                $(document).on('click','#btnBack', function() {
                    
                    var radioButtons = $('.form input:radio');
                    var selectedIndex = radioButtons.index(radioButtons.filter(':checked'));

                    //selectedIndex = selectedIndex + 2;

                    $('.form input[type="radio"]:nth-of-type(' + selectedIndex + ')').prop('checked', true);
                });
            });            
        </script>            
    </body>
</html>
<%} else {
    out.print("<h1>Bạn không có quyền truy cập</h1>");
}%>