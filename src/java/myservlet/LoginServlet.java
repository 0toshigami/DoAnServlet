/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mysql.*;

/**
 *
 * @author NoName
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = res.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String name = req.getParameter("username");
            String pass = req.getParameter("password");
            
            ArrayList validAccount = SPDB.checkAccount(name, pass);                                                
            
            if (validAccount == null) {
                //out.print("There is no one like that");
                res.sendRedirect(req.getContextPath()+"/index.jsp?error=0");            
            } else {
                //out.print("Found one!");
                TaiKhoan tk = (TaiKhoan)validAccount.get(0);
                int type = tk._Type;
                
                HttpSession session = req.getSession();
                session.setAttribute("username", name);
                session.setAttribute("type", type);
                                                
                
                // Xử lý ở chỗ này~~~!!!!!!!!!!!!!
                if (type == 1) {
                    res.sendRedirect(req.getContextPath()+"/GiaoVien");
                    //RequestDispatcher dispatcher = req.getRequestDispatcher("giaovien.jsp");
                    //dispatcher.forward(req, res);
                } else if (type == 2){
                    res.sendRedirect(req.getContextPath()+"/HocSinh");
                    //RequestDispatcher dispatcher = req.getRequestDispatcher("hocsinh.jsp");
                    //dispatcher.forward(req, res);
                }                
                
            }
        }
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

    
}
