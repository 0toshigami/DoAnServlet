<%-- 
    Document   : hocsinh
    Created on : May 10, 2018, 8:43:15 PM
    Author     : NoName
--%>

<%@page import="mysql.CauHoi"%>
<%@page import="java.util.ArrayList"%>
<%
    String[] num = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
    String name = (String)session.getAttribute("username");
    int type = (int)session.getAttribute("type");
    if (name != null && type != 1) {
        ArrayList dsch = (ArrayList)request.getAttribute("dsch");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang học sinh</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
<!--        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">-->
        <link rel="stylesheet" href="css/hocsinh.css"> 
        <style type="text/css">
            <%
            for (int i = 0; i < dsch.size(); i++) {
                if (i == dsch.size()-1) {
                    out.println("#"+num[i+1]+":checked ~ .stages label[for='"+num[i+1]+"'] {border-color: #0cc39f;}");
                }
                else {
                    out.println("#"+num[i+1]+":checked ~ .stages label[for='"+num[i+1]+"'],");
                }
            }
            //out.println("border-color: #0cc39f;}");
            %>
            <%
            for (int i = 0; i < dsch.size(); i++) {
                if (i == 0) {
                   out.println("#"+num[i+1]+":checked ~ .stages label,");
                }
                else if (i == dsch.size()-1) {
                    out.println("#"+num[i+1]+":checked ~ .stages label[for='"+num[i]+"'] ~ label {font-size: 1rem;}");
                } else {
                    out.println("#"+num[i+1]+":checked ~ .stages label[for='"+num[i]+"'] ~ label,");
                }
            }            
            %>
            <%
            for (int i = 0; i < dsch.size(); i++) {
                if (i == 0) {
                   out.println("#"+num[i+1]+":checked ~ .stages label:after,");
                }
                else if (i == dsch.size()-1) {
                    out.println("#"+num[i+1]+":checked ~ .stages label[for='"+num[i]+"'] ~ label:after {display: none;}");
                } else {
                    out.println("#"+num[i+1]+":checked ~ .stages label[for='"+num[i]+"'] ~ label:after,");
                }
            }            
            %>
            <%
            for (int i = 1; i < dsch.size(); i++) {
                out.println("#"+num[i+1]+":checked ~ .progress span {width: calc(100% / 5 * "+(i+1)+");}");
            }
            %>
            <%
            for (int i = 0; i < dsch.size(); i++) {
                if (i == dsch.size()-1) {
                    out.println("#"+num[i+1]+":checked ~ .panels [data-panel='"+num[i+1]+"'] {display: block;}");
                }
                else {
                    out.println("#"+num[i+1]+":checked ~ .panels [data-panel='"+num[i+1]+"'],");
                }
            }
            %>
        </style>
        <script src="bootstrap/jquery.min.js"></script>
<!--        <script src="bootstrap/popper.min.js"></script>
        <script src="bootstrap/bootstrap.min.js"></script>-->        
        
    </head>
    <body>                
        <div class="form" style="text-align: center;">
            <form id="formTest" action="<%=request.getContextPath()%>/finish" method="post">
            <h2>Xin chào <%=name%></h2>
            <a href="<%=request.getContextPath()%>/dangxuat">Đăng xuất</a>
            <%
            for (int i = 0; i < dsch.size(); i++) {
                if (i == 0) {
                    out.println("<input id='"+num[i+1]+"' type='radio' name='stage' checked='checked' />");
                }
                else {
                    out.println("<input id='"+num[i+1]+"' type='radio' name='stage' />");
                }
            }

            out.println("<div class='stages'>");
            for (int i = 0; i < dsch.size(); i++) {
                out.println("<label for='"+num[i+1]+"'>"+(i+1)+"</label>");
            }
            out.println("</div>");
            %>
    <!--            <span class="progress"><span></span></span>-->
                <div class="panels">
                <%
                for (int i = 0; i < dsch.size(); i++) {
                    CauHoi ch = (CauHoi)dsch.get(i);
                    out.println("<div data-panel='"+num[i+1]+"'>");
                        out.println("<h4>Câu "+(i+1)+": "+ch._NoiDung+"</h4>");
                        out.println("<textarea name='cau"+(i+1)+"' cols='80' rows='5'></textarea>");
                    out.println("</div>");
                }
                %>
                </div>
                <input id="btnSubmit" type="submit" value="Kết thúc bài làm" style="display: none;">
            </form>
            <%
                if(request.getParameter("error") != null){
                        out.print("<span style='color:#fff;'>Không thể kết nối Database</span>");
                }
            %>
            <button id="btnBack">Back</button>    
            <button id="btnNext">Next</button>
        </div>
        
        <script type="text/javascript">
        $(document).ready(function() {
            $('.form .stages label').click(function() {
                var radioButtons = $('.form input:radio');
                var selectedIndex = radioButtons.index(radioButtons.filter(':checked'));
                selectedIndex = selectedIndex + 1;
            });

            $('#btnNext').click(function() {
                var radioButtons = $('.form input:radio');
                var selectedIndex = radioButtons.index(radioButtons.filter(':checked'));

                selectedIndex = selectedIndex + 2;

                $('.form input[type="radio"]:nth-of-type(' + selectedIndex + ')').prop('checked', true);

                if (selectedIndex == <%=dsch.size()%>) {
                    $('#btnSubmit').css('display','inline-block');
                }
            });
            
            $('#btnBack').click(function() {
                var radioButtons = $('.form input:radio');
                var selectedIndex = radioButtons.index(radioButtons.filter(':checked'));
                
                //selectedIndex = selectedIndex - 0;
                
                $('.form input[type="radio"]:nth-of-type(' + selectedIndex + ')').prop('checked', true);
                
                if (selectedIndex < <%=dsch.size()%>) {
                    $('#btnSubmit').css('display','none');
                }
            });
        });        
        </script>
    </body>
</html>
<%}else {
    out.print("<h1>Bạn không có quyền truy cập</h1>");
}%>
