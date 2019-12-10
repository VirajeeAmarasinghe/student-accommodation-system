/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Landlord;
import business.Login;
import business.Validator;
import dao.LandlordDAO;
import dao.LoginDAO;
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
public class ManageLandlord extends HttpServlet {

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
        String url = "/landlord.jsp";
        Validator v = new Validator();
        LandlordDAO lDAO = new LandlordDAO();
        Landlord l = null;
        Login log = null;
        LoginDAO logDAO=new LoginDAO();
        
        try {
            String lID=request.getParameter("txtID");            
            int id = Integer.parseInt(lID);
            String fName = request.getParameter("txtFName");
            String lName = request.getParameter("txtLName");
            String Add = request.getParameter("txtCurrentAdd");
            String tele_land = request.getParameter("txtTele");
            String tele_mob = request.getParameter("txtTeleMob");
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPass");

            log = new Login(email, password);

            if (request.getParameter("btnUpdate") != null) {
                if (v.isPresent(request.getParameter("txtID")) && v.isPresent(fName) && v.isPresent(lName) && v.isPresent(Add) && v.isPresent(tele_land) && v.isPresent(tele_mob)) {
                    if (v.isValidTele(tele_land) && v.isValidTele(tele_mob)) {
                        l = new Landlord(id, fName, lName, Add, tele_land, tele_mob, email);
                        try {
                            int result = lDAO.update(l);
                            if (result > 0) {
                                message = "Updated Successfully";
                                request.setAttribute("landlordObj", l);
                                request.setAttribute("logObj", log);
                            } else {
                                message = "Error in Updating!!!!Contact System Administrator.";
                                url = "/error.jsp";
                            }
                        } catch (Exception e) {
                            message = "Error in Updating!!!!Contact System Administrator.";
                            url = "/error.jsp";
                        }

                    } else {
                        message = "Please enter valid Contact Number";
                    }
                } else {
                    message = "Please fill out all the text boxes with * mark";
                }
            } else if (request.getParameter("btnDelete") != null) {
                if (v.isPresent(request.getParameter("txtID"))) {
                    try {
                        int result = lDAO.delete(id);
                        if (result > 0) {
                            int result_2=logDAO.delete(email,"landlordlogindata");
                            if(result_2>0){
                               message = "Deleted Successfully.Your information is no longer in the DB.";
                            }else{
                               message = "Error in Deleting!!!!Contact System Administrator.";
                               url = "/error.jsp";
                            }                            
                        } else {
                            message = "Error in Deleting!!!!Contact System Administrator.";
                            url = "/error.jsp";
                        }
                    } catch (Exception e) {
                        message = "Error in Deleting!!!!Contact System Administrator.";
                        url = "/error.jsp";
                    }
                } else {
                    message = "Please enter landlord id";
                }
            } else if (request.getParameter("btnCancel") != null) {
                url = "/homePage.jsp";
            } else if (request.getParameter("btnManageProp") != null) {
                url = "/property.jsp";
            } else if (request.getParameter("btnResetPass") != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("userResetType", "Landlord");
                url = "/landlordResetPass.jsp";
            }else if (request.getParameter("btnViewMessages") != null){
                url="/DisplayMessages";
                request.setAttribute("msgUserType","Landlord");
            }else if(request.getParameter("btnSearchPayment")!=null){
                url="/findPayment.jsp";               
            }else if(request.getParameter("btnViewPayment")!=null){
               url="/DisplayPayments";
            }
        } catch (NumberFormatException e) {
            message = "Error!!!!Contact System Administrator."+e.toString();
            url = "/error.jsp";
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
