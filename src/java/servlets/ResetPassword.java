/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Login;
import business.Validator;
import dao.LoginDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Virajee
 */
public class ResetPassword extends HttpServlet {

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
        String url = "";

        Validator v = new Validator();
        Login l = null;
        LoginDAO lDAO = new LoginDAO();

        try {
            String email = request.getParameter("username");
            String pass = request.getParameter("password");
            String confirmPass = request.getParameter("confirmPass");

            String userResetType = (String) request.getSession(false).getAttribute("userResetType");
            String tableName = "";

            if (userResetType.equals("Student")) {
                tableName = "studentlogindata";
                url = "/studentResetPass.jsp";
            } else if (userResetType.equals("Landlord")) {
                tableName = "landlordlogindata";
                url = "/landlordResetPass.jsp";
            } else if (userResetType.equals("Admin")) {
                tableName = "adminlogindata";
                url = "/adminResetPass.jsp";                
            }
            

            if (v.isPresent(email) && v.isPresent(pass) && v.isPresent(confirmPass)) {
                if (v.isValidEmail(email)) {
                    if (pass.equals(confirmPass)) {
                        if (v.isValidWithPasswordPolicy(pass)) {
                            if (lDAO.alreadyHasEmail(email, tableName)) {
                                //update
                                l = new Login(email, pass);
                                int result = lDAO.update(l, tableName);
                                if (result > 0) {
                                    message = "Successfully Updated";                                    
                                } else {
                                    message = "Error in Updating!!!!Contact System Administrator.";
                                    url = "error.jsp";
                                }
                            } else {
                                message = "There is no account for the email";
                            }
                        } else {
                            message = "Password must contain atleast one uppercase letter, one lowercase letter, one digit, one special character and length must be within 6 to 20 characters";
                        }
                    } else {
                        message = "Password and Confirm Password not match";
                    }
                } else {
                    message = "Please enter valid email address";
                }
            } else {
                message = "Please fill out all the text boxes";
            }
            if (userResetType.equals("Admin")) {                
                if(request.getParameter("btn_cancel")!=null){
                    message="";
                    url="/adminLog.jsp";
                }
            }
        } catch (Exception e) {
            message = "Error in Updating!!!!Contact System Administrator.";
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
