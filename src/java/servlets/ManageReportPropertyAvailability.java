/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Property;
import dao.PropertyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Virajee
 */
public class ManageReportPropertyAvailability extends HttpServlet {

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
        String url = "/propertyAvailabilityReport.jsp";

        PropertyDAO pDAO = new PropertyDAO();
        ArrayList<Object> pList = new ArrayList<>();
        ArrayList<Property> pNewList = new ArrayList<>();

        if (request.getParameter("btnAvailable") != null) {
            try {
                pList = pDAO.getAll();                
                if (pList.isEmpty()) {
                    message = "No Data";                    
                } else {
                    for (Object ob : pList) {
                        Property p = (Property) ob;
                        pNewList.add(p);
                    }
                    request.setAttribute("propertyList", pNewList);              
                          
                }
            } catch (Exception e) {
                url = "/error.jsp";
                message = "Error Occurred.Contact System Administrator";
            }
        } else if (request.getParameter("btnNotAvailable") != null) {
           try {
                pList = pDAO.getAllNonAvailableProperties();                
                if (pList.isEmpty()) {
                    message = "No Data";                    
                } else {
                    for (Object ob : pList) {
                        Property p = (Property) ob;
                        pNewList.add(p);
                    }
                    request.setAttribute("propertyList", pNewList);              
                          
                }
            } catch (Exception e) {
                url = "/error.jsp";
                message = "Error Occurred.Contact System Administrator";
            }
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
