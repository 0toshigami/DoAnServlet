/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mysql.SPDB;
import mysql.TraLoi;

/**
 *
 * @author NoName
 */
@WebServlet(name = "FinishServlet", urlPatterns = {"/finish"})
public class FinishServlet extends HttpServlet {

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
            ArrayList arr = new ArrayList();
            Map<String,String[]> paramMap = req.getParameterMap();
            //System.out.println(req.getSession().getAttribute("username"));
            //System.out.println(allMap.size());
            int i = 1;
            for(String key : paramMap.keySet()){
                if (!"stage".equals(key)) {
                    String[] strArr=(String[])paramMap.get(key);
                    
                    for(String val:strArr){
                        //System.out.println("Str Array= "+val);
                        TraLoi tl = new TraLoi();
                        tl._Username = (String)req.getSession().getAttribute("username");
                        tl._SttCauHoi = i;
                        tl._CauTL = val;
                        arr.add(tl);                        
                        System.out.println("('"+tl._Username+"',"+tl._SttCauHoi+",'"+tl._CauTL+"')");                        
                    }
                    i++;
                }                                   
            }
            int status = SPDB.InsertTraLoi(arr);
            if (status == 0) {
                //out.println("Failed to connect to database");
                res.sendRedirect(req.getContextPath()+"/hocsinh.jsp?error=0");
            }
            else if (status == -1){
                res.sendRedirect(req.getContextPath()+"/hocsinh.jsp?error=-1");
            }
            else {
                res.sendRedirect(req.getContextPath()+"/dangxuat");
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
