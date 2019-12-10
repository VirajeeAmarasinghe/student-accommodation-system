<%-- 
    Document   : homePage
    Created on : 29-Dec-2016, 18:21:50
    Author     : Virajee
--%>


<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Property Availability Report</title>
        <link rel="stylesheet" href="formatPropertyAvailabilityReport.css">
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
                <h2>All Available Properties</h2>
                <%
                   DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                    String date = df.format(new Date());
                    request.setAttribute("todayDate", date);  
                %>
                <h3>Date: ${todayDate}</h3>
            </div>
            <div id="registration_form_area"> 
                <form method="post" action="ManageReportPropertyAvailability">
                <table>
                    <tr>
                        <th>Property ID</th>
                        <th>Address No</th>
                        <th>Address Street</th>
                        <th>Address City</th>
                        <th>Type</th>
                        <th>Rent</th>                           
                    </tr>
                    
                    <c:forEach var="item" items="${propertyList}"> 
                        <tr>                                                               
                            <td class="valueFields"><input type="text" name="txtID" id="txtID" class="fields" value="${item.getPropID()}" readonly="readonly"></td>
                            <td class="valueFields"><input type="text" name="txtFName" id="txtFName" value="${item.getAddNo()}" class="fields" readonly="readonly"></td>
                            <td class="valueFields"><input type="text" name="txtLName" id="txtLName" value="${item.getAddStreet()}" class="fields" readonly="readonly"></td>
                            <td class="valueFields"><input type="text" name="txtCurrentAdd" id="txtCurrentAdd" value="${item.getAddCity()}" class="fields" readonly="readonly"></td>
                            <td class="valueFields"><input type="text" name="txtTele" id="txtTele" value="${item.gettID()}" class="fields" readonly="readonly"></td>
                            <td class="valueFields"><input type="text" name="txtTeleMob" id="txtTeleMob" value="${item.getRent()}" class="fields" readonly="readonly"></td>                                
                        </tr>
                    </c:forEach>                    
                </table> 
                </form>
            </div>           
            <div id="footer_para"><p>1</p>                  
        </div>
        </div>
        
    </body>
</html>
