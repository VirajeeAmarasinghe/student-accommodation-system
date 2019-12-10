/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.International;
import business.Local;
import business.Student;
import business.Validator;
import dao.LoginDAO;
import dao.StudentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Virajee
 */
public class FindDisplayStudent extends HttpServlet {

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
        String message = "";
        String url = "/findStudent.jsp";

        Validator v = new Validator();
        StudentDAO sDAO = new StudentDAO();

        String stuID = request.getParameter("txtID");

        if (request.getParameter("btnFind") != null) {
            try {
                if (v.isPresent(stuID)) {
                    if (v.isValidNumber(stuID)) {
                        int id = Integer.parseInt(stuID);
                        Student s = (Student) sDAO.get(id);
                        if (s == null) {
                            message = "There is no account for this id";
                        } else {
                            request.setAttribute("stuObj", s);
                            if (s instanceof Local) {
                                Local l = (Local) s;
                                request.setAttribute("nic", l.getNic());
                                request.setAttribute("stuType", "Local");
                            }
                            if (s instanceof International) {
                                International l = (International) s;
                                request.setAttribute("passport", l.getPassport());
                                request.setAttribute("country", l.getCountry());
                                request.setAttribute("stuType", "International");
                            }
                        }
                    } else {
                        message = "Enter Student ID";
                    }
                } else {
                    message = "Enter Student ID";
                }
            } catch (NumberFormatException e) {
                url = "/error.jsp";
                message = "Error in Searching!!!!Contact System Administrator.";
            }
        } else if (request.getParameter("btnViewAll") != null) {
            url = "/DisplayAllStudents";
        } else if (request.getParameter("btnDelete") != null) {
            try {
                if (v.isPresent(stuID)) {
                    if (v.isValidNumber(stuID)) {
                        int id = Integer.parseInt(stuID);
                        Student s = (Student) sDAO.get(id);
                        if (s == null) {
                            message = "There is no account for this id";
                        } else {
                            String tableName = "";
                            if (s instanceof Local) {
                                tableName = "local";
                            }
                            if (s instanceof International) {
                                tableName = "international";
                            }
                            int result = sDAO.delete(id, tableName);
                            if (result > 0) {
                                int result_2 = new LoginDAO().delete(s.getEmail(), "studentlogindata");
                                if (result_2 > 0) {
                                    message = "Deleted Successfully";
                                } else {
                                    message = "Error in Deleting!!!!Contact System Administrator.";
                                    url = "/error.jsp";
                                }
                                message = "Deleted Successfully";
                            } else {
                                url = "/error.jsp";
                                message = "Error in Deleting!!!!Contact System Administrator.";
                            }
                        }
                    } else {
                        message = "Enter Student ID";
                    }
                } else {
                    message = "Enter Student ID";
                }
            } catch (NumberFormatException e) {
                url = "/error.jsp";
                message = "Error in Deleting!!!!Contact System Administrator.";
            } finally {
                request.setAttribute("message", message);
            }
        } else if (request.getParameter("btnCancel") != null) {
            url = "/adminHome.jsp";
        } else if (request.getParameter("btnReset") != null) {
            message = "";
            Student s = new Local();
            request.setAttribute("landlordObj", s);
            request.setAttribute("nic", "");
            request.setAttribute("passport", "");
            request.setAttribute("country", "");
            request.setAttribute("stuType", "");
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
