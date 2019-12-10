/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Landlord;
import business.Property;
import business.Validator;
import dao.LandlordDAO;
import dao.PropertyDAO;
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
public class MngDelteUpdateFindProperty extends HttpServlet {

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
        String url = "/propertyDeleteUpdateFind.jsp";
        Validator v = new Validator();
        PropertyDAO pDAO = new PropertyDAO();
        Property p = null;

        String propID = request.getParameter("txtID");

        String email = (String) request.getSession(false).getAttribute("user");
        LandlordDAO lDAO = new LandlordDAO();
        Landlord l = (Landlord) lDAO.get(email);
        int lID = l.getL_id();

        if (request.getParameter("btnUpdate") != null) {
            try {
                String addNo = request.getParameter("txtNo");
                String addStreet = request.getParameter("txtStreet");
                String addCity = request.getParameter("txtCity");
                String type = request.getParameter("typeProperty");
                String noOfTenants = request.getParameter("txtTenant");
                String noOfBedRooms = request.getParameter("txtBedrooms");
                String noOfBathRooms = request.getParameter("txtBathrooms");
                String kitchen = request.getParameter("chkKitchen");
                String electricity = request.getParameter("chkElectricity");
                String water = request.getParameter("chkWater");
                String rent = request.getParameter("txtRent");
                String addInfo = request.getParameter("txtAddInfo");

                if (v.isPresent(propID) && v.isPresent(addNo) && v.isPresent(addStreet) && v.isPresent(addCity) && v.isPresent(noOfTenants) && v.isPresent(noOfBedRooms) && v.isPresent(noOfBathRooms) && v.isPresent(rent)) {
                    int pID = Integer.parseInt(propID);
                    int tenants = Integer.parseInt(noOfTenants);
                    int bedRooms = Integer.parseInt(noOfBedRooms);
                    int bathRooms = Integer.parseInt(noOfBathRooms);
                    int tID = Integer.parseInt(type);
                    double propRent = Double.parseDouble(rent);
                    String hasKitchen = "No";
                    String hasElectricity = "No";
                    String hasWater = "No";
                    String additionalInfo = "";

                    if (kitchen != null) {
                        hasKitchen = "Yes";
                    }
                    if (electricity != null) {
                        hasElectricity = "Yes";
                    }
                    if (water != null) {
                        hasWater = "Yes";
                    }
                    if (v.isPresent(addInfo)) {
                        additionalInfo = addInfo;
                    }

                    p = (Property) pDAO.get(pID);
                    if (p != null) {
                        //update
                        p = new Property(pID, addNo, addStreet, addCity, tenants, bedRooms, bathRooms, hasKitchen, hasElectricity, hasWater, propRent, additionalInfo, lID, tID);

                        int result = pDAO.update(p);
                        if (result > 0) {
                            message = "Updated Successfully";
                            HttpSession session = request.getSession(true);
                            String propertyID = String.valueOf(pID);
                            session.setAttribute("propID", propID);
                        } else {
                            message = "Error in Updating!!!!Contact System Administrator.+1";
                            url = "/error.jsp";
                        }
                    } else {
                        message = "Sorry.You don't have a property under this id";
                    }
                } else {
                    message = "Please fill out all the text boxes with * mark";
                }
            } catch (NumberFormatException e) {
                message = "Error in Updating!!!!Contact System Administrator.";
                url = "/error.jsp";
            } finally {
                request.setAttribute("message", message);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        } else if (request.getParameter("btnDelete") != null) {
            try {
                if (v.isPresent(propID)) {
                    int pID = Integer.parseInt(propID);
                    p = (Property) pDAO.get(pID);
                    if (p != null) {
                        //delete                        
                        int result = pDAO.delete(pID, lID);
                        if (result > 0) {
                            message = "Deleted Successfully";
                        } else {
                            message = "Error in Updating!!!!Contact System Administrator.";
                            url = "/error.jsp";
                        }
                    } else {
                        message = "Sorry.You don't have a property under this id";
                    }
                } else {
                    message = "Please enter the property ID";
                }
            } catch (NumberFormatException e) {
                message = "Error in Deleting!!!!Contact System Administrator.+2";
                url = "/error.jsp";
            } finally {
                request.setAttribute("message", message);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        } else if (request.getParameter("btnUploadImage") != null) {            
            url = "/uploadImage.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if (request.getParameter("btnCancel") != null) {
            url = "/landlord.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }else if (request.getParameter("btnFind") != null) {
            url = "/findProperty.jsp";
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
