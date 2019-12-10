/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Payment;
import business.Property;
import business.Validator;
import dao.PaymentDAO;
import dao.PropertyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Virajee
 */
public class ManagePaymentAdmin extends HttpServlet {

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
        String url = "/payment.jsp";

        Validator v = new Validator();
        Payment p = null;
        PaymentDAO pDAO = new PaymentDAO();
        Property prop = null;
        PropertyDAO propDAO = new PropertyDAO();

        int nextID = pDAO.getNxtId();

        if (request.getParameter("btnSubmit") != null) {
            try {
                String amount = request.getParameter("txtAmount");
                String date = request.getParameter("txtDate");
                String propID = request.getParameter("txtProperty");

                if (v.isPresent(amount) && v.isPresent(date) && v.isPresent(propID)) {
                    if (v.isValidDate(date)) {
                        //check whether the property is in the db or not
                        int propertyID = Integer.parseInt(propID);
                        prop = (Property) propDAO.get(propertyID);
                        if (prop == null) {
                            message = "Property isn't in the database";
                        } else {
                            //check already paid or not
                            p = (Payment) pDAO.getByProperty(propertyID);
                            if (prop == null) {
                                //save
                                Date payDate = Date.valueOf(date);
                                double paidAmount = Double.parseDouble(amount);
                                p = new Payment(nextID, propertyID, payDate, paidAmount);
                                int result = pDAO.insert(p);
                                if (result > 0) {
                                    //update the availability of property
                                    int result2 = propDAO.updateAvailability(propertyID, "Yes");
                                    if (result2 > 0) {
                                        message = "Saved successfully";
                                    } else {
                                        message = "Error in Inserting!!!!Contact System Administrator.";
                                        url = "/error.jsp";
                                    }
                                } else {
                                    message = "Error in Inserting!!!!Contact System Administrator.";
                                    url = "/error.jsp";
                                }
                            } else {
                                message = "Already payment has done";
                            }
                        }
                    } else {
                        message = "Fill all the textboxes";
                    }
                } else {
                    message = "Fill all the textboxes";
                }
            } catch (NumberFormatException e) {
                message = "Error in Inserting!!!!Contact System Administrator.";
                url = "/error.jsp";
            }
        } else if (request.getParameter("btnDelete") != null) {
            try {
                String payID = request.getParameter("txtID");
                if (v.isPresent(payID)) {
                    int paymentID = Integer.parseInt(payID);
                    int result = pDAO.delete(paymentID);
                    if (result > 0) {
                        message = "Deleted Successfully";
                    } else {
                        message = "Error in Deleting!!!!Contact System Administrator.";
                        url = "/error.jsp";
                    }
                } else {
                    message = "Enter Payment ID";
                }
            } catch (NumberFormatException e) {
                message = "Error in Deleting!!!!Contact System Administrator.";
                url = "/error.jsp";
            }

        } else if (request.getParameter("btnUpdate") != null) {
            try {
                String amount = request.getParameter("txtAmount");
                String date = request.getParameter("txtDate");
                String propID = request.getParameter("txtProperty");
                String payID = request.getParameter("txtID");

                if (v.isPresent(amount) && v.isPresent(date) && v.isPresent(propID)) {
                    if (v.isValidDate(date)) {
                        int propertyID = Integer.parseInt(propID);
                        int paymentID = Integer.parseInt(payID);
                        //check already paid or not
                        p = (Payment) pDAO.getByProperty(propertyID);
                        if (p != null) {
                            //update
                            Date payDate = Date.valueOf(date);
                            double paidAmount = Double.parseDouble(amount);
                            p = new Payment(paymentID, propertyID, payDate, paidAmount);
                            int result = pDAO.update(p);
                            if (result > 0) {
                                message = "Updated successfully";
                            } else {
                                message = "Error in Updating!!!!Contact System Administrator.";
                                url = "/error.jsp";
                            }
                        } else {
                            message = "No payment Record for this id";
                        }
                    } else {
                        message = "Fill all the textboxes";
                    }
                } else {
                    message = "Fill all the textboxes";
                }
            } catch (NumberFormatException e) {
                message = "Error in Updating!!!!Contact System Administrator.";
                url = "/error.jsp";
            }
        } else if (request.getParameter("btnFind") != null) {
            String payID = request.getParameter("txtID");
            if (v.isPresent(payID)) {
                int paymentID = Integer.parseInt(payID);
                p = (Payment) pDAO.get(paymentID);
                if (p == null) {
                    message = "No Payment Record for this id";
                }
                request.setAttribute("payObj", p);
                request.setAttribute("pID", p.getPayID());
            } else {
                message = "Enter Payment ID";
            }
        } else if (request.getParameter("btnViewAll") != null) {
               url="/ViewAllPaymentsAdmin";
        } else if (request.getParameter("btnReset") != null) {
              p=null;
              request.setAttribute("payObj", p);
        } else if (request.getParameter("btnCancel") != null) {
              url="/adminHome.jsp";
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
