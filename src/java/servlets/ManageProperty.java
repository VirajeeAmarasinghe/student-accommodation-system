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
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author Virajee
 */


public class ManageProperty extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
      
        String saveFile = "";      
        String message = "";
        String url = "/property.jsp";
        LandlordDAO lDAO = new LandlordDAO();
        Landlord l = null;
        Validator v = new Validator();
        Property p = null;
        PropertyDAO propDAO = new PropertyDAO();

        String no = "";
        String street = "";
        String city = "";
        String type = "";
        String tenants = "";
        String bedRooms = "";
        String bathRooms = "";
        String chkKitchen = "";
        String chkElectriity = "";
        String chkWater = "";
        String rent = "";
        String addInfo = "";
        String btnSub = null;
        String btnDel =null;
        String btnUp = null;
        String btnView = null;
        String btnCancel = null;
        String btnUpload=null;
        String btnAvailability=null;
      
        
        int pID=0;

        try {
                        DiskFileUpload fu = new DiskFileUpload();
            List fileItems = fu.parseRequest(request);
            Iterator it = fileItems.iterator();

            while (it.hasNext()) {
                FileItem item = (FileItem) it.next();
                if (item.isFormField()) {                    
                    String name = item.getFieldName();
                    
                    String value = item.getString();
                   
                    switch (name) {
                        case "txtNo":
                            no = value;
                            break;
                        case "txtStreet":
                            street = value;
                            break;
                        case "txtCity":
                            city = value;
                            break;
                        case "typeProperty":
                            type = value;
                            break;
                        case "txtTenant":
                            tenants = value;
                            break;
                        case "txtBedrooms":
                            bedRooms = value;
                            break;
                        case "txtBathrooms":
                            bathRooms = value;
                            break;
                        case "chkKitchen":
                            chkKitchen = value;
                            break;
                        case "chkElectricity":
                            chkElectriity = value;
                            break;
                        case "chkWater":
                            chkWater = value;
                            break;
                        case "txtRent":
                            rent = value;
                            break;
                        case "txtAddInfo":
                            addInfo = value;
                            break;
                        case "btnSubmit":
                            btnSub = value;
                            break;
                        case "btnDelete":
                            btnDel = value;
                            break;
                        case "btnUpdate":
                            btnUp = value;
                            break;
                        case "btnViewAll":
                            btnView = value;
                            break;
                        case "btnCancel":
                            btnCancel = value;
                            break;
                        case "btnUploadImage":
                            btnUpload=value;
                            break;  
                        case "btnEditAvilability":
                            btnAvailability=value;
                            break;
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        if (btnSub != null) {          
            try {
                pID = propDAO.getNxtId();
                int typeID = Integer.parseInt(type);
                String hasKitchen = "No";
                String hasElectricity = "No";
                String hasWater = "No";
                if (chkKitchen.equals("on")) {
                    hasKitchen = "Yes";
                }
                if (chkElectriity.equals("on")) {
                    hasElectricity = "Yes";
                }
                if (chkWater.equals("on")) {
                    hasWater = "Yes";
                }
          
                String email = (String) request.getSession(false).getAttribute("user");
                l = (Landlord) lDAO.get(email);
                int lID = l.getL_id();
                String availability = "No";

                if (v.isPresent(no) && v.isPresent(street) && v.isPresent(city) && v.isPresent(tenants) && v.isPresent(bedRooms) && v.isPresent(bathRooms) && v.isPresent(rent)) {
                    int noOfTenants = Integer.parseInt(tenants);
                    int noOfBedRooms = Integer.parseInt(bedRooms);
                    int noOfBathRooms = Integer.parseInt(bathRooms);
                    double rentOfProperty = Double.parseDouble(rent);

                    //----------------------------------------------------------------------------------------------------------------------              
                    p = new Property(pID, no, street, city, noOfTenants, noOfBedRooms, noOfBathRooms, hasKitchen, hasElectricity, hasWater, rentOfProperty, addInfo, saveFile, lID, typeID, availability);
                    try {
                        int result = propDAO.insert(p);
                        if (result > 0) {
                            message = "Inserted Successfully";
                            HttpSession session = request.getSession(true);
                            String propID=String.valueOf(pID);                           
                            session.setAttribute("propID",propID);   
                        } else {
                            message = "Error in Inserting!!!!Contact System Administrator.";
                            url = "/error.jsp";
                        }
                    } catch (Exception e) {
                        message = "Error in Inserting!!!!Contact System Administrator.";
                        url = "/error.jsp";
                    }
                } else {
                    message = "Please fill out all the text boxes with * mark";
                }
            } catch (NumberFormatException e) {
                message = "Error in Inserting!!!!Contact System Administrator.";
                url = "/error.jsp";
            } finally {
                request.setAttribute("message", message);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        } else if (btnDel != null || btnUp != null) {
            url = "/propertyDeleteUpdateFind.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if (btnView != null) {
            url = "/DisplayAllProperties";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if (btnCancel != null) {
            url = "/landlord.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }else if(btnUpload!=null){           
            url="/uploadImage.jsp";                 
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }else if(btnAvailability!=null){
            url="/updateAvailability.jsp";
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
