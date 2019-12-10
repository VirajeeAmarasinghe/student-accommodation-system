/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Landlord;
import business.Payment;
import business.Validator;
import dao.LandlordDAO;
import dao.PaymentDAO;
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
public class FindPayment extends HttpServlet {

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
        Validator v = new Validator();
        Payment p = new Payment();
        PaymentDAO pDAO = new PaymentDAO();
        String message = "";
        String url = "/findPayment.jsp";

        //get landlord id
        String email = (String) request.getSession(false).getAttribute("user");
        LandlordDAO lDAO = new LandlordDAO();
        Landlord l = (Landlord) lDAO.get(email);
        int lID = l.getL_id();

        String propID = request.getParameter("txtPID");

        if (request.getParameter("btnFind") != null) {
            try {
                if (v.isPresent(propID)) {
                    if (v.isValidNumber(propID)) {
                        int propertyID = Integer.parseInt(propID);
                        p = pDAO.get(propertyID, lID);
                        if (p == null) {
                            message = "Sorry.You aren't the owner of this property or You haven't done payment yet.";
                        }
                    } else {
                        message = "Enter valid property id";
                    }
                } else {
                    message = "Enter property ID";
                }
            } catch (Exception e) {
                message = "Error in Searching!!!!Contact System Administrator.+2";
                url = "/error.jsp";
            } finally {
                request.setAttribute("message", message);
                request.setAttribute("pObj", p);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }

        } else if (request.getParameter("btnReset") != null) {
            p = null;
            request.setAttribute("message", message);
            request.setAttribute("pObj", p);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if ((request.getParameter("btnCancel") != null)) {
            url = "/landlord.jsp";
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
