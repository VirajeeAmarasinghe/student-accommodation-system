/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.International;
import business.Local;
import business.Validator;
import business.View;
import dao.StudentDAO;
import dao.ViewDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ManageView extends HttpServlet {

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
        String url = "/viewForm.jsp";

        Validator v = new Validator();
        ViewDAO vDAO = new ViewDAO();

        String vID = request.getParameter("txtVID");
        String pID = request.getParameter("txtPID");
        String sID = request.getParameter("txtSID");
        String date = request.getParameter("txtDate");
        String time = request.getParameter("txtTime");

        try {
            if (request.getParameter("btnConfirm") != null) {
                if (v.isPresent(vID) && v.isPresent(pID) && v.isPresent(sID) && v.isPresent(date) && v.isPresent(time)) {
                    if (v.isValidDate(date) && v.isValidTime(time)) {
                        //save data
                        int viewID = Integer.parseInt(vID);
                        int propID = Integer.parseInt(pID);
                        int stuID = Integer.parseInt(sID);

                        Date viewDate = Date.valueOf(date);
                        Time viewTime = Time.valueOf(time);

                        String confirmStu = "Yes";
                        String confirmLandlord = "No";

                        String email = (String) request.getSession(false).getAttribute("user");
                        StudentDAO sDAO = new StudentDAO();
                        Object ob = sDAO.get(email);

                        int stu_Local = 4;
                        int stu_Intenational = 5;

                        if (ob instanceof Local) {
                            stu_Local = stuID;
                        } else if (ob instanceof International) {
                            stu_Intenational = stuID;
                        }
                        View view=new View(viewID, viewDate, viewTime, confirmStu, confirmLandlord, propID, stu_Local, stu_Intenational);
                        int result=vDAO.insert(view);
                        if(result>0){
                           message="Requested Successfully";
                        }else{
                          message="Error Occurred.Contact System Administrator";
                          url="/error.jsp";
                        }
                    } else {
                        message = "Enter Valid Date and Time";
                        request.setAttribute("vID",request.getParameter("txtVID") );
                        request.setAttribute("pID",request.getParameter("txtPID") );
                        request.setAttribute("sID",request.getParameter("txtSID") );                        
                    }
                } else {
                    message = "Fill out all the text boxes";
                    request.setAttribute("vID",request.getParameter("txtVID") );
                    request.setAttribute("pID",request.getParameter("txtPID") );
                    request.setAttribute("sID",request.getParameter("txtSID") );                     
                }
            } else if (request.getParameter("btnCancel") != null) {
                url = "/accommodation.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Error occurred in Inserting.Contact System Admininstrator";
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
