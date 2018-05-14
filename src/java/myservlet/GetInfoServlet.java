/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mysql.Info;
import mysql.SPDB;

/**
 *
 * @author NoName
 */
@WebServlet(name = "GetInfoServlet", urlPatterns = {"/getinfo"})
public class GetInfoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = res.getWriter()) {
            /* TODO output your page here. You may use following sample code. */            
            String user = req.getParameter("user");
            String[] num = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
            if (user != null) {
                ArrayList info = SPDB.getInfoTraLoi(user);
                out.print("<style>");
                for (int i = 0; i < info.size(); i++) {
                    if (i == info.size()-1) {
                        out.println("#"+num[i+1]+":checked ~ .stages label[for='"+num[i+1]+"'] {border-color: #0cc39f;}");
                    }
                    else {
                        out.println("#"+num[i+1]+":checked ~ .stages label[for='"+num[i+1]+"'],");
                    }
                }
                for (int i = 0; i < info.size(); i++) {
                    if (i == 0) {
                       out.println("#"+num[i+1]+":checked ~ .stages label,");
                    }
                    else if (i == info.size()-1) {
                        out.println("#"+num[i+1]+":checked ~ .stages label[for='"+num[i]+"'] ~ label {font-size: 1rem;}");
                    } else {
                        out.println("#"+num[i+1]+":checked ~ .stages label[for='"+num[i]+"'] ~ label,");
                    }
                }
                for (int i = 0; i < info.size(); i++) {
                    if (i == 0) {
                       out.println("#"+num[i+1]+":checked ~ .stages label:after,");
                    }
                    else if (i == info.size()-1) {
                        out.println("#"+num[i+1]+":checked ~ .stages label[for='"+num[i]+"'] ~ label:after {display: none;}");
                    } else {
                        out.println("#"+num[i+1]+":checked ~ .stages label[for='"+num[i]+"'] ~ label:after,");
                    }
                }
                for (int i = 0; i < info.size(); i++) {
                    if (i == info.size()-1) {
                        out.println("#"+num[i+1]+":checked ~ .panels [data-panel='"+num[i+1]+"'] {display: block;}");
                    }
                    else {
                        out.println("#"+num[i+1]+":checked ~ .panels [data-panel='"+num[i+1]+"'],");
                    }
                }
                out.print("</style>");
                out.print("<div class='modal fade' id='myModal'>");
                out.print("     <div class='modal-dialog modal-dialog-centered'>");
                out.print("         <div class='modal-content'>");
                out.print("             <div class='modal-header'>");
                out.print("                 <h4 class='modal-title'>Bài làm của "+user+"</h4>");
                out.print("                 <button type='button' class='close' data-dismiss='modal'>&times;</button>");
                out.print("             </div>");
                out.print("             <div class='modal-body form'>");
                //out.print("                 <div class='form'>");
                for (int i = 0; i < info.size(); i++) {
                    if (i == 0) {
                        out.println("           <input id='"+num[i+1]+"' type='radio' name='stage' checked='checked' />");
                    }
                    else {
                        out.println("           <input id='"+num[i+1]+"' type='radio' name='stage' />");
                    }
                }
                out.println("                   <div class='stages'>");
                for (int i = 0; i < info.size(); i++) {
                    out.println("                   <label for='"+num[i+1]+"'>"+(i+1)+"</label>");
                }
                out.println("                   </div>");
                out.print("                     <div class='panels'>");
                for (int i = 0; i < info.size(); i++) {
                    Info ch = (Info)info.get(i);
                    out.print("                     <div data-panel='"+num[i+1]+"'>");
                    out.print("                         <h4>Câu "+(i+1)+": "+ch._NoiDung+"</h4>");
                    out.print("                         <h3>Câu trả lời</h3>");                        
                    out.print("                         <textarea name='cau"+(i+1)+"' cols='50' rows='3'>"+ch._CauTL+"</textarea>");
                    out.print("                         <h3>Câu trả lời đúng</h3>");                        
                    out.print("                         <textarea name='cau"+(i+1)+"' cols='50' rows='3'>"+ch._CauTLDung+"</textarea>");
                    out.print("                     </div>");
                }
                out.print("                     </div>"); // đóng div .panels
                out.print("                     <button id='btnBack'>Back</button>");
                out.print("                     <button id='btnNext'>Next</button>");
                //out.print("                 </div>"); // đóng div .form
                out.print("             </div>"); // đóng div .modal-body
                out.print("             <div class='modal-footer'>");
                out.print("                 <button type='button' class='btn btn-secondary' data-dismiss='modal'>Close</button>");
                out.print("             </div>");                                
                out.print("         </div>");
                out.print("     </div>");
                out.print("</div>");
                //req.setAttribute("info", info);
            }
        }        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
