/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import business.International;
import business.Local;
import business.Rent;
import business.Validator;
import dao.RentDAO;
import dao.StudentDAO;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Virajee
 */
public class MangeRent extends HttpServlet {

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
        String message="";
        String url="/rentForm.jsp";
        
        Validator v=new Validator();
        RentDAO rDAO=new RentDAO();
        
        String rID=request.getParameter("txtRID");
        String pID=request.getParameter("txtPID");
        String sID=request.getParameter("txtSID");
        String startDate=request.getParameter("txtStartDate");
        String endDate=request.getParameter("txtEndDate");
        
        try {
            if(request.getParameter("btnConfirm")!=null){
                 if(v.isPresent(rID)&&v.isPresent(pID)&&v.isPresent(sID)&&v.isPresent(startDate)&&v.isPresent(endDate)){
                         if(v.isValidDate(startDate)&&v.isValidDate(endDate)){
                                int rentID=Integer.parseInt(rID);
                                int propertyID=Integer.parseInt(pID);
                                int studentID=Integer.parseInt(sID);
                                
                                Date startDate_2=Date.valueOf(startDate);
                                Date endDate_2=Date.valueOf(endDate);
                                
                                boolean result=rDAO.canRent(propertyID, startDate_2, endDate_2);
                                
                                String email=(String) request.getSession(false).getAttribute("user");
                                StudentDAO sDAO=new StudentDAO();
                                Object ob=sDAO.get(email);
                                
                                int stu_Local=4;
                                int stu_Intenational=5;
                                
                                if(ob instanceof Local){
                                    stu_Local=studentID;
                                }else if(ob instanceof International){
                                   stu_Intenational=studentID;
                                }
                                if(result){
                                   //store data
                                    Rent r=new Rent(rentID, propertyID, stu_Local, stu_Intenational, startDate_2, endDate_2);
                                    int result_2=rDAO.insert(r);
                                    if(result_2>0){
                                      message="Booked Successfully";
                                    }else{
                                      message="Error Occurred.Contact System Administrator";
                                      url="/error.jsp";
                                    }
                                }else{
                                   message="This Property is already rented";
                                }
                         }else{
                           message="Enter Valid Dates";
                         }
                 }else{
                    message="Fill out all the text boxes";
                 } 
            }else if(request.getParameter("btnCancel")!=null){
                 url="/accommodation.jsp";
            }
        } catch (NumberFormatException e) {
            message="Error occurred in Inserting.Contact System Admininstrator";
            url="/error.jsp";
        }finally {
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
