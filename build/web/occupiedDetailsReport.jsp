<%-- 
    Document   : homePage
    Created on : 29-Dec-2016, 18:21:50
    Author     : Virajee
--%>


<%@page import="dao.LandlordDAO"%>
<%@page import="business.Landlord"%>
<%@page import="business.Property"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.PropertyDAO"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Property Availability Report</title>
        <link rel="stylesheet" href="formatOccupiedReport.css">
    </head>
    <body> 
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <nav class="main">
            <div id="Nav">
                <form action="ManageReportPropertyAvailability" method="post">
                    <ul>
                        <li><input type="submit" name="btnAvailable" id="btnAvailable" class="buttons" value="Available Properties"></li>
                        <li><input type="submit" name="btnNotAvailable" id="btnNotAvailable" class="buttons" value="Not Available Properties"></li>
                        <li><button type="button" name="btnBack" id="btnBack" class="buttons" onclick="javascript:history.go(-1)">Back</button></li>
                    </ul>  
                </form>
            </div>
        </nav>
        
        <div id="registration_form" class="registration_form">
            <div class="page_header">                
                <h2>Property Occupied Report</h2>
                <%
                   DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                    String date = df.format(new Date());
                    request.setAttribute("todayDate", date); 
                %>
                <h3>Date: ${todayDate}</h3>
            </div>
            
            <div id="registration_form_area"> 
                <form method="post" action="">
                <table>
                        <tr>
                            <th>Property ID</th>
                            <th>Landlord ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Address</th>
                            <th>Land Tele No</th>
                            <th>Mobile Tele No</th>
                            <th>Email</th>
                        </tr>
                         
                            <%
                                 PropertyDAO pDAO=new PropertyDAO();
                                 ArrayList<Property> pList=pDAO.getAllProps();
                                 
                                 Landlord l=new Landlord();
                                 LandlordDAO lDAO=new LandlordDAO();
                                 
                                 for(Property p2:pList){
                                     Property p3=p2; 
                                     l=lDAO.getByPropID(p3.getPropID());
                                 
                            %>
                            <tr> 
                                <td class="valueFields"><input type="text" name="txtpID" id="txtpID" class="fields" value="<%=p3.getPropID()%>" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtID" id="txtID" class="fields" value="<%=l.getL_id()%>" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtFName" id="txtFName" value="<%=l.getL_fName()%>" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtLName" id="txtLName" value="<%=l.getL_lName()%>" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtCurrentAdd" id="txtCurrentAdd" value="<%=l.getAdd()%>" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtTele" id="txtTele" value="<%=l.getTele_land()%>" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtTeleMob" id="txtTeleMob" value="<%=l.getTele_mob()%>" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="email" name="txtEmail" id="txtEmail" value="<%=l.getEmail()%>" class="fields" readonly="readonly"></td>
                            </tr>
                           <%} %>                                                                     
                    </table>
                </form>
            </div>           
            <div id="footer_para"><p>Page No: 1</p>                  
        </div>
        </div>
        
    </body>
</html>
