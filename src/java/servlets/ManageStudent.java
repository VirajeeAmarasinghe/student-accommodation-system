/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.International;
import business.Local;
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
public class ManageStudent extends HttpServlet {

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
        String url = "/student.jsp";
        Validator v = new Validator();
        StudentDAO lDAO = new StudentDAO();
        LoginDAO logDAO=new LoginDAO();
        Student s = null;
        Login log = null;
        StudentFactory stFac = new StudentFactory();
        Local l = null;
        International i = null;
        String studentType = "";

        try {
            String sID = request.getParameter("txtID");
            int id = Integer.parseInt(sID);
            String fName = request.getParameter("txtFName");
            String lName = request.getParameter("txtLName");
            String Add = request.getParameter("txtCurrentAdd");
            String tele = request.getParameter("txtTele");
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPass");
            String stuType = request.getParameter("rbtnType");
            String nic = request.getParameter("txtNic");
            String passport = request.getParameter("txtPassId");
            String country = request.getParameter("txtCountry");

            log = new Login(email, password);

            String email_2 = (String) request.getSession(false).getAttribute("user");

            studentType = lDAO.getStudentType(email_2);

            if (request.getParameter("btnUpdate") != null) {
                if (studentType.equals("Local")) {
                    if (v.isPresent(sID) && v.isPresent(fName) && v.isPresent(lName) && v.isPresent(Add) && v.isPresent(tele) && v.isPresent(nic) && v.isPresent(email)) {
                        if (v.isValidTele(tele)) {                           
                            s = stFac.getStudent(id, fName, lName, Add, tele, email, nic, passport, country, studentType);
                            l = (Local) s;
                            try {
                                int result = lDAO.update(s);
                                if (result > 0) {
                                    message = "Updated Successfully";
                                    request.setAttribute("checked_local", "checked");
                                    request.setAttribute("readOnlyPassport", "readonly='readonly'");
                                    request.setAttribute("readOnlyCountry", "readonly='readonly'");
                                    request.setAttribute("stuID", l.getStu_id());
                                    request.setAttribute("stuFName", l.getStu_fName());
                                    request.setAttribute("stuLName", l.getStu_lName());
                                    request.setAttribute("stuAdd", l.getStu_currentAdd());
                                    request.setAttribute("stuTele", l.getStu_tele());
                                    request.setAttribute("stuNic", l.getNic());
                                    request.setAttribute("stuEmail", l.getEmail());
                                    request.setAttribute("stuPassport", " ");
                                    request.setAttribute("stuCountry", " ");
                                    request.setAttribute("password", log.getPass());
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
                } else if (studentType.equals("International")) {
                    if (v.isPresent(sID) && v.isPresent(fName) && v.isPresent(lName) && v.isPresent(Add) && v.isPresent(tele) && v.isPresent(passport) && v.isPresent(email) && v.isPresent(country)) {
                        if (v.isValidTele(tele)) {
                            s = stFac.getStudent(id, fName, lName, Add, tele, email, nic, passport, country, studentType);
                            i = (International) s;
                            try {
                                int result = lDAO.update(s);
                                if (result > 0) {
                                    message = "Updated Successfully";
                                    request.setAttribute("checked_inter", "checked");
                                    request.setAttribute("readOnlyNic", "readonly='readonly'");
                                    request.setAttribute("stuID", i.getStu_id());
                                    request.setAttribute("stuFName", i.getStu_fName());
                                    request.setAttribute("stuLName", i.getStu_lName());
                                    request.setAttribute("stuAdd", i.getStu_currentAdd());
                                    request.setAttribute("stuTele", i.getStu_tele());
                                    request.setAttribute("stuNic", " ");
                                    request.setAttribute("stuEmail", i.getEmail());
                                    request.setAttribute("stuPassport", i.getPassport());
                                    request.setAttribute("stuCountry", i.getCountry());
                                    request.setAttribute("password", log.getPass());
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
                }

            } else if (request.getParameter("btnDelete") != null) {
                if (v.isPresent(request.getParameter("txtID"))) {
                    String table = "";
                    if (studentType.equals("Local")) {
                        table = "local";
                    } else if (studentType.equals("International")) {
                        table = "international";
                    }
                    try {
                        int result = lDAO.delete(id, table);
                        if (result > 0) {
                            int result_2=logDAO.delete(email,"studentlogindata");
                            if(result_2>0){
                               message = "Deleted Successfully.Your Information is no longer in the DB.";
                            }else{
                               message = "Error in Deleting!!!!Contact System Administrator.";
                               url = "/error.jsp";
                            }
                            message = "Deleted Successfully";
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
            } else if (request.getParameter("btnResetPass") != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("userResetType", "Student");
                url = "/studentResetPass.jsp";
            }else if (request.getParameter("btnViewMessages") != null){
                url="/DisplayMessages";
                request.setAttribute("msgUserType","Student");
            }
        } catch (NumberFormatException e) {
            message = "Error!!!!Contact System Administrator." + e.toString();
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
