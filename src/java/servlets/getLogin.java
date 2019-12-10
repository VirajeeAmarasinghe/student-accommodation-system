/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Login;
import dao.LoginDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Virajee
 */
public class getLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        String message = "";
        String userType = "";

        if (request.getParameter("btn_log") != null) {
            try {
                if (request.getParameter("username").length() == 0 || request.getParameter("password").length() == 0) {
                    message = "Please fill out all the text boxes";
                } else {
                    Login l = new Login();
                    LoginDAO d = new LoginDAO();

                    if (request.getSession(false).getAttribute("userType") != null) {
                        userType = (String) request.getSession(false).getAttribute("userType");
                    } else {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("userType", "Admin");
                        userType = "Admin";
                    }

                    String tableName = "";

                    if (userType.equals("Student")) {
                        tableName = "studentlogindata";
                        url = "/studentLog.jsp";
                    } else if (userType.equals("Landlord")) {
                        tableName = "landlordlogindata";
                        url = "/landlordLog.jsp";
                    } else if (userType.equals("Admin")) {
                        tableName = "adminlogindata";
                        url = "/adminLog.jsp";
                    }
                    System.out.println(tableName + " " + request.getParameter("username"));
                    l = (Login) d.get(request.getParameter("username"), tableName);
                    if (l == null) {
                        message = "Email or Password is incorrect";
                    } else if (request.getParameter("password").equals(l.getPass())) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("user", request.getParameter("username"));
                        if (userType.equals("Student")) {
                            url = "/student.jsp";
                        } else if (userType.equals("Landlord")) {
                            url = "/landlord.jsp";
                        } else if (userType.equals("Admin")) {
                            url = "/adminHome.jsp";
                        }
                    } else {
                        message = "Email or Password is incorrect";
                    }
                }
            } catch (Exception e) {
                message = "Error in accessing login data";
                url = "/error.jsp";
            }
        } else if (request.getParameter("btn_cancel") != null) {
            url = "/homePage.jsp";
        }

        request.setAttribute("message", message);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

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
