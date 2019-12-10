/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Landlord;
import business.Property;
import business.PropertyType;
import business.Student;
import business.Validator;
import dao.LandlordDAO;
import dao.PropertyDAO;
import dao.PropertyTypeDAO;
import dao.RentDAO;
import dao.StudentDAO;
import dao.ViewDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Virajee
 */
public class DisplayProperty extends HttpServlet {

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
        String url = "/error.jsp";
        ArrayList<Property> propertyList = null;
        PropertyDAO propDAO = new PropertyDAO();
        PropertyTypeDAO pTypeDAO = new PropertyTypeDAO();
        Validator v = new Validator();
        try {
            if (request.getParameter("btnSearch") != null) {
                if (request.getParameter("txtMin").equals("") && request.getParameter("txtMax").equals("")) {
                    String street = request.getParameter("streets");
                    int typeID = Integer.parseInt(request.getParameter("typeProperty"));
                    PropertyType type = (PropertyType) pTypeDAO.get(typeID);
                    propertyList = propDAO.getPropertiesByStreetAndType(street, typeID);
                    if (propertyList.isEmpty()) {
                        message = "No Records";
                        url = "/searchAccommodation.jsp";
                    } else {
                        request.setAttribute("propList", propertyList);
                        request.setAttribute("propertyType", type.getTypeName());
                        url = "/searchAccommodation.jsp";
                    }

                } else if (v.isPresent(request.getParameter("txtMin")) && v.isPresent(request.getParameter("txtMax"))) {
                    String street = request.getParameter("streets");
                    int typeID = Integer.parseInt(request.getParameter("typeProperty"));
                    double minRent = Double.parseDouble(request.getParameter("txtMin"));
                    double maxRent = Double.parseDouble(request.getParameter("txtMax"));
                    PropertyType type = (PropertyType) pTypeDAO.get(typeID);
                    propertyList = propDAO.getPropertiesByStreetAndTypeAndMinMaxRent(street, typeID, minRent, maxRent);
                    if (propertyList.isEmpty()) {
                        message = "No Records";
                        url = "/searchAccommodation.jsp";
                    } else {
                        request.setAttribute("propList", propertyList);
                        request.setAttribute("propertyType", type.getTypeName());
                        url = "/searchAccommodation.jsp";
                    }

                }
            } else if (request.getParameter("viewMore") != null) {
                int propID = Integer.parseInt(request.getParameter("viewMore"));
                Object ob = propDAO.get(propID);
                Property p = (Property) ob;
                PropertyType type = (PropertyType) pTypeDAO.get(p.gettID());
                LandlordDAO lDAO = new LandlordDAO();
                Landlord l = (Landlord) lDAO.get(p.getlID());
                request.setAttribute("propertyType", type.getTypeName());
                request.setAttribute("propOb", p);
                request.setAttribute("landlordOb", l);
                url = "/viewProperties.jsp";
            } else if (request.getParameter("rent") != null) {
                if (request.getSession(false) != null) {
                    if (request.getSession(false).getAttribute("user") != null) {
                        String email = (String) request.getSession(false).getAttribute("user");
                        StudentDAO sDAO = new StudentDAO();
                        Student s = (Student) sDAO.get(email);
                        if (s != null) {
                            //display rent form
                            int propID = Integer.parseInt(request.getParameter("rent"));
                            RentDAO rDAO = new RentDAO();
                            int rID = rDAO.getNxtId();
                            request.setAttribute("pID", propID);
                            request.setAttribute("sID", s.getStu_id());
                            request.setAttribute("rID", rID);
                            url = "/rentForm.jsp";
                        } else {
                            url = "/error.jsp";
                            message = "Only Students are allowed to rent an accommodation";
                        }
                    } else {
                        url = "/MangeError";
                        request.setAttribute("error", "First Log In to the profile");
                        request.setAttribute("type", "student");
                    }
                }
            } else if (request.getParameter("view") != null) {
                if (request.getSession(false) != null) {
                    if (request.getSession(false).getAttribute("user") != null) {
                        String email = (String) request.getSession(false).getAttribute("user");
                        StudentDAO sDAO = new StudentDAO();
                        Student s = (Student) sDAO.get(email);
                        if (s != null) {
                            //display view form
                            int propID = Integer.parseInt(request.getParameter("view"));
                            ViewDAO vDAO = new ViewDAO();
                            int vID = vDAO.getNxtId();
                            request.setAttribute("pID", propID);
                            request.setAttribute("sID", s.getStu_id());
                            request.setAttribute("vID", vID);
                            url = "/viewForm.jsp";
                        } else {
                            url = "/error.jsp";
                            message = "Only Students are allowed to request view";
                        }
                    } else {
                        url = "/MangeError";
                        request.setAttribute("error", "First Log In to the profile");
                        request.setAttribute("type", "student");
                    }
                }
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
