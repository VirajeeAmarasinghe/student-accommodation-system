/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Validator;
import dao.ViewDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Virajee
 */
public class ManageMessages extends HttpServlet {

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
        String url = "/displayMessages.jsp";
        String message = "";

        ViewDAO vDAO = new ViewDAO();
        Validator v = new Validator();

        try {
            String userType = (String) request.getSession(false).getAttribute("msgUserType");
            System.out.println(userType);

            if (request.getParameter("btnConfirm") != null) {
                int id = Integer.parseInt(request.getParameter("btnConfirm"));
                int result = vDAO.updateConfirm(id, userType);

                if (result > 0) {
                    message = "Confirmed";
                    if (userType.equals("Landlord")) {
                        request.setAttribute("msgUserType", "Landlord");
                        url = "/DisplayMessages";
                    } else if (userType.equals("Student")) {
                        request.setAttribute("msgUserType", "Student");
                        url = "/DisplayMessages";
                    }
                } else {
                    url = "/error.jsp";
                    message = "Error Occurred.Contact System Administrator";
                }
            } else if ((request.getParameter("btnAdjsut") != null)) {
                int id = Integer.parseInt(request.getParameter("btnAdjsut"));
                //get row id                
                int row=Integer.parseInt(request.getParameter("rowNo"));             
                String date = request.getParameterValues("txtDate")[row];
                String time = request.getParameterValues("txtTime")[row];
                if (v.isPresent(date) && v.isPresent(time)) {
                    if (v.isValidDate(date) && v.isValidTime(time)) {               
                                                Date vDate = Date.valueOf(date);
                        Time vTime = Time.valueOf(time);               
                      
                        int result = vDAO.updateConfirmDateTime(id, userType, vDate, vTime);

                        if (result > 0) {
                            message = "Adjusted";
                            if (userType.equals("Landlord")) {
                                request.setAttribute("msgUserType", "Landlord");
                                url = "/DisplayMessages";
                            } else if (userType.equals("Student")) {
                                request.setAttribute("msgUserType", "Student");
                                url = "/DisplayMessages";
                            }
                        } else {
                            url = "/error.jsp";
                            message = "Error Occurred.Contact System Administrator";
                        }
                    } else {
                        message = "Enter Valid date and time";
                    }
                } else {
                    message = "Enter date and time";
                }
            }else if ((request.getParameter("btnCancel") != null)) {
                 url="/landlord.jsp";
            }
        } catch (NumberFormatException e) {
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
