/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Property;
import business.Validator;
import dao.PropertyDAO;
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
public class FindDisplayProperty extends HttpServlet {

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
        String url = "/findPropertyByAdmin.jsp";

        PropertyDAO pDAO = new PropertyDAO();
        Validator v = new Validator();
        Property p = null;

        String pID = request.getParameter("txtID");

        if (request.getParameter("btnFind") != null) {
            try {
                if (v.isPresent(pID)) {
                    if (v.isValidNumber(pID)) {
                        int propID = Integer.parseInt(pID);
                        p = (Property) pDAO.get(propID);
                        if (p == null) {
                            message = "No property under this id";
                        } else {
                            if (p.getKitchen().equals("Yes")) {
                                request.setAttribute("checked_kitchen", "checked");
                            }
                            if (p.getElectricity().equals("Yes")) {
                                request.setAttribute("checked_electricity", "checked");
                            }
                            if (p.getWater().equals("Yes")) {
                                request.setAttribute("checked_water", "checked");
                            }
                        }
                        request.setAttribute("propObj", p);

                    } else {
                        message = "Enter Valid ID";
                    }
                } else {
                    message = "Enter Property ID";
                }
            } catch (NumberFormatException e) {
                message = "Error in Searching!!!!Contact System Administrator.";
                url = "/error.jsp";
            }
        } else if (request.getParameter("btnViewAll") != null) {
            url = "/ViewAllProperties";
        } else if (request.getParameter("btnCancel") != null) {
            url = "/adminHome.jsp";
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
