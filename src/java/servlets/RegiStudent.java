/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Login;
import business.Student;
import business.StudentFactory;
import business.Validator;
import dao.LoginDAO;
import dao.StudentDAO;
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
public class RegiStudent extends HttpServlet {

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
            String checked_local = "";
            String checked_international = "";

            Student stu = null;
            Login log = null;
            StudentDAO s = new StudentDAO();
            LoginDAO logDAO = new LoginDAO();
            Validator v = new Validator();
            StudentFactory stuFac = new StudentFactory();

            try {
                int stuId = s.getNxtId();

                String fName = request.getParameter("txtFName");
                String lName = request.getParameter("txtLName");
                String curAdd = request.getParameter("txtCurrentAdd");
                String tele = request.getParameter("txtTele");
                String email = request.getParameter("txtEmail");
                String pass = request.getParameter("txtPass");
                String confirmPass = request.getParameter("txtConfirmPass");

                String stuType = request.getParameter("rbtnType");

                String nic = request.getParameter("txtNic");
                String passport = request.getParameter("txtPassId");
                String country = request.getParameter("txtCountry");

                if (stuType != null) {
                    if (stuType.equals("Local")) {
                        if (v.isPresent(fName) && v.isPresent(lName)
                                && v.isPresent(curAdd) && v.isPresent(tele) && v.isPresent(email)
                                && v.isPresent(pass) && v.isPresent(confirmPass) && v.isPresent(nic)) {
                            if (v.isValidTele(tele)) {
                                if (v.isValidEmail(email)) {
                                    if (logDAO.alreadyHasEmail(email, "studentlogindata")) {
                                        message = "There is already an account for the email";
                                        url = "/studentReg.jsp";
                                        checked_local = "checked";
                                    } else {
                                        if (pass.equals(confirmPass)) {
                                            if (v.isValidWithPasswordPolicy(pass)) {
                                                if (request.getParameter("chkAgree") != null) {
                                                    //save data
                                                    stu = stuFac.getStudent(stuId, fName, lName, curAdd, tele, email, nic, passport, country, stuType);
                                                    log = new Login(email, pass);
                                                    int result_1 = logDAO.insert(log, "studentlogindata");
                                                    if (result_1 > 0) {
                                                        int result_2 = s.insert(stu);

                                                        if (result_2 > 0) {
                                                            message = "Congratulations.Your Account is created successfully.";
                                                            url = "/studentLog.jsp";
                                                            HttpSession session = request.getSession(true);
                                                            session.setAttribute("userType", "Student");
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
                                                    url = "/studentReg.jsp";
                                                    checked_local = "checked";
                                                }
                                            } else {
                                                message = "Password must contain atleast one uppercase letter, one lowercase letter, one digit, one special character and length must be within 8 to 20 characters";
                                                url = "/studentReg.jsp";
                                                checked_local = "checked";
                                            }
                                        } else {
                                            message = "Password and Confirm Password not match";
                                            url = "/studentReg.jsp";
                                            checked_local = "checked";
                                        }
                                    }
                                } else {
                                    message = "Please enter valid email address";
                                    url = "/studentReg.jsp";
                                    checked_local = "checked";
                                }
                            } else {
                                message = "Please enter valid Contact Number";
                                url = "/studentReg.jsp";
                                checked_local = "checked";
                            }
                        } else {
                            message = "Please fill out all the text boxes";
                            url = "/studentReg.jsp";
                            checked_local = "checked";
                        }
                    } else {
                        if (v.isPresent(fName) && v.isPresent(lName)
                                && v.isPresent(curAdd) && v.isPresent(tele) && v.isPresent(email)
                                && v.isPresent(pass) && v.isPresent(confirmPass) && v.isPresent(passport) && v.isPresent(country)) {
                            if (v.isValidTele(tele)) {
                                if (v.isValidEmail(email)) {
                                    //save
                                    if (logDAO.alreadyHasEmail(email, "studentlogindata")) {
                                        message = "There is already an account for the email";
                                        url = "/studentReg.jsp";
                                        checked_international = "checked";
                                    } else {
                                        if (pass.equals(confirmPass)) {
                                            if (v.isValidWithPasswordPolicy(pass)) {
                                                if (request.getParameter("chkAgree") != null) {
                                                    //save data
                                                    stu = stuFac.getStudent(stuId, fName, lName, curAdd, tele, email, nic, passport, country, stuType);
                                                    log = new Login(email, pass);
                                                    int result_1 = logDAO.insert(log, "studentlogindata");
                                                    if (result_1 > 0) {
                                                        int result_2 = s.insert(stu);

                                                        if (result_2 > 0) {
                                                            message = "Congratulations.Your Account is created successfully.";
                                                            url = "/studentLog.jsp";
                                                            HttpSession session = request.getSession(true);
                                                            session.setAttribute("userType", "Student");
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
                                                    url = "/studentReg.jsp";
                                                    checked_international = "checked";
                                                }
                                            } else {
                                                message = "Password must contain atleast one uppercase letter, one lowercase letter, one digit, one special character and minimum length must be 8 characters.No whitespaces allowed";
                                                url = "/studentReg.jsp";
                                                checked_international = "checked";
                                            }
                                        } else {
                                            message = "Password and Confirm Password not match";
                                            url = "/studentReg.jsp";
                                            checked_international = "checked";
                                        }
                                    }
                                } else {
                                    message = "Please enter valid email address";
                                    url = "/studentReg.jsp";
                                    checked_international = "checked";
                                }
                            } else {
                                message = "Please enter valid Contact Number";
                                url = "/studentReg.jsp";
                                checked_international = "checked";
                            }
                        } else {
                            message = "Please fill out all the text boxes";
                            url = "/studentReg.jsp";
                            checked_international = "checked";
                        }
                    }
                } else {
                    message = "Please select the student type";
                    url = "/studentReg.jsp";
                }
            } catch (Exception e) {
                message = "Error in Inserting!!!!Contact System Administrator.";
                url = "/error.jsp";
            } finally {
                if (checked_local.equals("checked")) {
                    request.setAttribute("checked_local", "checked");                  
                } else if (checked_international.equals("checked")) {
                    request.setAttribute("checked_inter", "checked");
                }
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
