<%-- 
    Document   : property
    Created on : 05-Jan-2017, 01:25:54
    Author     : Virajee
--%>

<%@page import="dao.PropertyDAO"%>
<%@page import="business.PropertyType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.PropertyTypeDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Property Page</title>
        <link rel="stylesheet" href="formatHomePage.css">
        <link rel="stylesheet" href="formatProperty.css"> 
    </head>
    <body>       
        <div id="top_stripe">
            <div id="homePage">
                <a href="homePage.jsp"><img src="home.png" alt="Home button" width="40" height="40"></a>
            </div>            
            <div id="registration_nav">
                <ul>
                    <li><a href="#">REGISTER</a>
                        <ul class="dropdown">
                            <li><a href="studentReg.jsp">AS A STUDENT</a></li>
                            <li><a href="landlordReg.jsp">AS A LANDLORD</a></li>
                        </ul>
                    </li>  
                    <%
                        if (session != null) {
                            if (session.getAttribute("user") != null) {
                                out.print("<li><a href='mngHm?link=logoutLink'>LOG OUT</a></li>");
                            } else {
                                out.print("<li><a href='#'>LOGIN</a>");
                                out.print("<ul class='dropdown'>");
                                out.print("<li><a href='mngHm?link=studentLink'>AS A STUDENT</a></li>");
                                out.print("<li><a href='mngHm?link=landlordLink'>AS A LANDLORD</a></li>");
                                out.print("</li>");
                            }
                        }
                    %>
                </ul>
            </div>
        </div>
        <div id="second_stripe">
            <a href="homePage.jsp">
                <h1 id="title_1">STUDENT</h1>
                <h1 id="title_2">ACCOMMODATION</h1>
            </a>
        </div>
        <nav class="main">
            <div id="Nav">
                <ul>
                    <li><a href="homePage.jsp">HOME</a></li>
                    <li><a href="accommodation.jsp">ACCOMMODATION</a></li>
                    <li><a href="student.jsp">STUDENT</a></li>
                    <li><a href="landlord.jsp" class="home">LANDLORD</a></li>
                    <li><a href="about.html">ABOUT US</a></li>
                    <li><a href="contact.html">CONTACT US</a></li>
                </ul>
            </div>
        </nav>
        <div id="registration_form" class="registration_form">
            <div class="page_header">            
                <h2>Update, Delete And Find Property</h2>
            </div>
            <div id="registration_form_area">
                <div id="message_div">${message}</div>
                <%
                    PropertyTypeDAO propDAO = new PropertyTypeDAO();
                    ArrayList<PropertyType> propTypeList = propDAO.getAllObj();                    
                %>
                <form method="post" name="registration_form" action="MngDelteUpdateFindProperty">
                    <table> 
                        <tr>
                            <td class="headline">Property ID* : </td>                                    
                            <td class="valueFields"><input type="number" name="txtID" id="txtID" class="fields"></td>                            
                            <td><input type="submit" name="btnUpdate" value="Update" class="buttons"></td>                                                      
                        </tr>
                        <tr>
                            <td class="headline">Address No* : </td>                                    
                            <td class="valueFields"><input type="text" name="txtNo" id="txtNo" value="" class="fields"></td>
                            <td><input type="submit" name="btnDelete" value="Delete" class="buttons"></td>                                                       
                        </tr>
                        <tr>
                            <td class="headline">Address Street* : </td>                                    
                            <td class="valueFields"><input type="text" name="txtStreet" id="txtStreet" value="" class="fields"></td>
                            <td><input type="submit" name="btnUploadImage" value="Upload Image" class="buttons"></td>                                               
                        </tr>                                 
                        <tr>
                            <td class="headline">Address City* : </td>                                  
                            <td class="valueFields"><input type="text" name="txtCity" id="txtCity" value="" class="fields"></td>
                            <td><input type="reset" name="btnReset" value="Reset" class="buttons"></td>                                                   
                        </tr>
                        <tr>
                            <td class="headline">Type* : </td>                                  
                            <td class="valueFields"><select name="typeProperty" class="fields">
                                    <% for (int i = 0; i < propTypeList.size(); i++) {%>                        
                                    <option value="<%=propTypeList.get(i).getT_id()%>" name="typeOption"><%=propTypeList.get(i).getTypeName()%></option>
                                    <%}%>
                                </select> 
                            </td>
                            <td><input type="submit" name="btnCancel" value="Cancel" class="buttons"></td>                                                                  
                        </tr>                  
                        <tr>
                            <td class="headline">Maximum Number of Tenants* : </td>                                  
                            <td class="valueFields"><input type="number" name="txtTenant" id="txtTenant" class="fields" value="" min="0"></td>
                            <td><input type="submit" name="btnFind" value="Find" class="buttons"></td>
                        </tr>                        
                        <tr>
                            <td class="headline">Number of Bedrooms* : </td>
                            <td class="valueFields"><input type="number" name="txtBedrooms" id="txtBedrooms" value="" min="0" class="fields"></td>                                   
                            
                        </tr>
                        <tr>
                            <td class="headline">Number of Bathrooms* : </td>
                            <td class="valueFields"><input type="number" name="txtBathrooms" id="txtBathrooms" value="" min="0" class="fields"></td>                             
                        </tr>
                        <tr>
                            <td class="headline">Kitchen : </td>                                   
                            <td class="valueFields"><input type="checkbox" name="chkKitchen" id="chkKitchen"></td>
                        </tr>
                        <tr>
                            <td class="headline">Electricity : </td>                                    
                            <td class="valueFields"><input type="checkbox" name="chkElectricity" id="chkElectricity"></td>
                        </tr>                        
                        <tr>
                            <td class="headline">Water : </td>
                            <td class="valueFields"><input type="checkbox" name="chkWater" id="chkWater"></td>
                        </tr> 
                        <tr>
                            <td class="headline">Rent* : </td>
                            <td class="valueFields"><input type="text" name="txtRent" id="txtRent" value="" class="fields" onchange="validateFloatKeyPress(this)" placeholder="Amount of Sterling Pound"></td>     
                        </tr>
                        <tr>
                            <td class="headline">Additional Info : </td>
                            <td class="valueFields"><textarea rows="8" name="txtAddInfo" id="txtAddInfo"></textarea></td>
                        </tr>                        
                    </table>                        
                </form>
            </div>
        </div>

        <div id="footer">
            <div id="footer_para">
                <p>Copyright © 2016 Student Accommodation. All Rights Reserved.</p>
            </div>
        </div>
        <script>
            function validateFloatKeyPress(el) {
                var v = parseFloat(el.value);
                el.value = (isNaN(v)) ? '' : v.toFixed(2);
            }
        </script>
    </body>
</html>
