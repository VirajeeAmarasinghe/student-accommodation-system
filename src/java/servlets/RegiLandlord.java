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
public class RegiLandlord extends HttpServlet {

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
        if (request.getParameter("btnSubmit") != null) {
            String url = "";  //this variable is for storing the web page name
            String message = "";  //this variable is for storing the message

            Landlord l = null;
            Login log = null;
            LandlordDAO lDAO = new LandlordDAO();
            LoginDAO logDAO = new LoginDAO();
            Validator v = new Validator();

            try {
                int lId = lDAO.getNxtId();

                String fName = request.getParameter("txtFName");
                String lName = request.getParameter("txtLName");
                String Add = request.getParameter("txtCurrentAdd");
                String tele_land = request.getParameter("txtTele");
                String tele_mob = request.getParameter("txtTeleMob");               
                String email = request.getParameter("txtEmail");
                String pass = request.getParameter("txtPass");
                String confirmPass = request.getParameter("txtConfirmPass");

                if (v.isPresent(fName) && v.isPresent(lName)
                        && v.isPresent(Add) && v.isPresent(tele_land) && v.isPresent(tele_mob) && v.isPresent(email)
                        && v.isPresent(pass) && v.isPresent(confirmPass)) {
                    if (v.isValidTele(tele_land) && v.isValidTele(tele_mob)) {
                        if (v.isValidEmail(email)) {
                            if (logDAO.alreadyHasEmail(email, "landlordlogindata")) {
                                message = "There is already an account for the email";
                                url = "/landlordReg.jsp";
                            } else {
                                if (pass.equals(confirmPass)) {
                                    if (v.isValidWithPasswordPolicy(pass)) {
                                        if (request.getParameter("chkAgree") != null) {
                                            //save data
                                            l = new Landlord(lId, fName, lName, Add, tele_land, tele_mob, email);
                                            log = new Login(email, pass);
                                            int result_1 = logDAO.insert(log, "landlordlogindata");
                                            if (result_1 > 0) {
                                                int result_2 = lDAO.insert(l);

                                                if (result_2 > 0) {
                                                    message = "Congratulations.Your Account is created successfully.";
                                                    url = "/landlordLog.jsp";
                                                    HttpSession session = request.getSession(true);
                                                    session.setAttribute("userType", "Landlord");
                                                } else {
                                                    message = "Error in Inserting!!!!Contact System Administrator.";
                                                    url = "/error.jsp";
                                                }
                                            } else {
                                                message = "Error in Inserting!!!!Contact System Administrator.";
                                                url = "/error.jsp";
                                            }
                                        } else {
                                            message = "You must be agree to terms and conditions of Student Accommodation";
                                            url = "/landlordReg.jsp";
                                        }
                                    } else {
                                        message = "Password must contain atleast one uppercase letter, one lowercase letter, one digit, one special character and length must be within 8 to 20 characters";
                                        url = "/landlordReg.jsp";
                                    }
                                } else {
                                    message = "Password and Confirm Password not match";
                                    url = "/landlordReg.jsp";
                                }
                            }
                        } else {
                            message = "Please enter valid email address";
                            url = "/landlordReg.jsp";
                        }
                    } else {
                        message = "Please enter valid Contact Number";
                        url = "/landlordReg.jsp";
                    }
                } else {
                    message = "Please fill out all the text boxes";
                    url = "/landlordReg.jsp";
                }

            } catch (Exception e) {
                message = "Error in Inserting!!!!Contact System Administrator.";
                url = "/error.jsp";
            } finally {
                request.setAttribute("message", message);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        } else if (request.getParameter("btnCancel") != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/homePage.jsp");
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
