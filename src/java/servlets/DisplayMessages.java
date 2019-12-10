/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.International;
import business.Landlord;
import business.Local;
import business.Student;
import business.View;
import dao.LandlordDAO;
import dao.StudentDAO;
import dao.ViewDAO;
import java.io.IOException;
import java.util.ArrayList;
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
public class DisplayMessages extends HttpServlet {

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
        String email = (String) request.getSession(false).getAttribute("user");
        int id = 0;
        String url = "/displayMessages.jsp";
        String message = "";
        try {
            if (request.getAttribute("msgUserType").equals("Landlord")) {
                LandlordDAO lDAO = new LandlordDAO();
                Landlord l = (Landlord) lDAO.get(email);
                id = l.getL_id();

                ViewDAO vDAO = new ViewDAO();
                ArrayList<View> vList = vDAO.getByLandlordAndConfirmation(id);
                if (vList.isEmpty()) {
                    message = "No Messages";
                    request.setAttribute("visibility", "no");
                }
                request.setAttribute("messageList", vList);
                request.setAttribute("msgUserType", "Landlord");
                if (request.getSession(false).getAttribute("msgUserType") == null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("msgUserType", "Landlord");
                } else {
                    request.getSession(false).setAttribute("msgUserType", "Landlord");
                }
            } else if (request.getAttribute("msgUserType").equals("Student")) {
                StudentDAO sDAO = new StudentDAO();
                Student s = (Student) sDAO.get(email);
                id = s.getStu_id();

                ViewDAO vDAO = new ViewDAO();

                int stu_Local = 4;
                int stu_Intenational = 5;

                if (s instanceof Local) {
                    stu_Local = id;
                } else if (s instanceof International) {
                    stu_Intenational = id;
                }

                ArrayList<View> vList = vDAO.getByStudentAndConfirmation(stu_Local, stu_Intenational);

                if (vList.isEmpty()) {
                    message = "No Messages";
                    request.setAttribute("visibility", "no");
                }
                request.setAttribute("messageList", vList);
                request.setAttribute("msgUserType", "Student");
                if (request.getSession(false).getAttribute("msgUserType") == null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("msgUserType", "Student");
                } else {
                    request.getSession(false).setAttribute("msgUserType", "Student");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            url = "/error.jsp";
            message = "Error Occurred.Contact System Administrator";
        } finally {
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
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
