/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Landlord;
import business.Validator;
import dao.LandlordDAO;
import dao.LoginDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Virajee
 */
public class FindDisplayLandlord extends HttpServlet {

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
        String url = "/findLandlord.jsp";

        Validator v = new Validator();
        LandlordDAO lDAO = new LandlordDAO();

        String landordID = request.getParameter("txtID");

        if (request.getParameter("btnFind") != null) {
            try {
                if (v.isPresent(landordID)) {
                    if (v.isValidNumber(landordID)) {
                        int id = Integer.parseInt(landordID);
                        Landlord l = (Landlord) lDAO.get(id);
                        if (l == null) {
                            message = "There is no account for this id";
                        } else {
                            request.setAttribute("landlordObj", l);
                        }
                    } else {
                        message = "Enter Landlord ID";
                    }
                } else {
                    message = "Enter Landlord ID";
                }
            } catch (NumberFormatException e) {
                url = "/error.jsp";
                message = "Error in Searching!!!!Contact System Administrator.";
            }
        } else if (request.getParameter("btnViewAll") != null) {
            url = "/DisplayAllLandlords";
        } else if (request.getParameter("btnDelete") != null) {
            try {
                if (v.isPresent(landordID)) {
                    if (v.isValidNumber(landordID)) {
                        int id = Integer.parseInt(landordID);
                        Landlord l = (Landlord) lDAO.get(id);
                        if (l == null) {
                            message = "There is no account for this id";
                        } else {
                            int result = lDAO.delete(id);
                            if (result > 0) {
                                int result_2 = new LoginDAO().delete(l.getEmail(), "landlordlogindata");
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
                        message = "Enter Landlord ID";
                    }
                } else {
                    message = "Enter Landlord ID";
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
            Landlord l = new Landlord();
            request.setAttribute("landlordObj", l);
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
