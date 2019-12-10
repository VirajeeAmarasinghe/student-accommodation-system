/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import static java.lang.System.out;
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
public class manageHome extends HttpServlet {

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
        if ("logoutLink".equals(request.getParameter("link"))) {
            String userType=(String) request.getSession(false).getAttribute("userType");
            String url="";
            request.getSession().removeAttribute("user");
            request.getSession().removeAttribute("userType");
            if(userType.equals("Admin")){
              url="/adminLog.jsp";
            }else{
              url="/homePage.jsp";
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if ("studentLink".equals(request.getParameter("link"))) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userType", "Student");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/studentLog.jsp");
            dispatcher.forward(request, response);
        } else if ("landlordLink".equals(request.getParameter("link"))) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userType", "Landlord");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/landlordLog.jsp");
            dispatcher.forward(request, response);
        } else if ("landlordResetLink".equals(request.getParameter("link"))) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userResetType", "Landlord");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/landlordResetPass.jsp");
            dispatcher.forward(request, response);
        } else if ("studentResetLink".equals(request.getParameter("link"))) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userResetType", "Student");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/studentResetPass.jsp");
            dispatcher.forward(request, response);
        } else if ("adminResetLink".equals(request.getParameter("link"))) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userResetType", "Admin");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminResetPass.jsp");
            dispatcher.forward(request, response);
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
