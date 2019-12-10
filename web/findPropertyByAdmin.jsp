<%-- 
    Document   : homePage
    Created on : 29-Dec-2016, 18:21:50
    Author     : Virajee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Find Property Page</title>
        <link rel="stylesheet" href="formatHomePage.css">
        <link rel="stylesheet" href="formatLandlord.css">
    </head>
    <body>
        <div id="top_stripe">
            <div id="homePage">
                <a href="homePage.jsp"><img src="home.png" alt="Home button" width="40" height="40"></a>
            </div>            
            <div id="registration_nav">
                <ul>
                    <li><a href="adminReg.jsp">REGISTER</a>                        
                    </li>
                    <%
                        if (session != null) {
                            if (session.getAttribute("user") != null) {
                                out.print("<li><a href='mngHm?link=logoutLink'>LOG OUT</a></li>");
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
                    <li><a href="adminHome.jsp">HOME</a></li>
                    <li><a href="findLandlord.jsp">LANDLORD</a></li>
                    <li><a href="findStudent.jsp">STUDENT</a></li>
                    <li><a href="#" class="home">PROPERTY</a></li>
                </ul>
            </div>
        </nav>
        <div id="registration_form" class="registration_form">
            <div class="page_header">                
                <h2>Find Property</h2>
            </div>
            <div id="registration_form_area">
                <div id="message_div">${message}</div>
                <form method="post" name="registration_form" action="FindDisplayProperty">
                    <table>
                        <tr>
                            <td class="headline">Property ID : </td>                                    
                            <td class="valueFields"><input type="text" name="txtID" id="txtID" class="fields" onchange="validateFloatKeyPress(this)" value="${propObj.getPropID()}"></td>                            
                            <td><input type="submit" name="btnFind" value="Search" class="buttons"></td>                                                      
                        </tr>
                        <tr>
                            <td class="headline">Address No : </td>                                    
                            <td class="valueFields"><input type="text" name="txtNo" id="txtNo" value="${propObj.getAddNo()}" class="fields" readonly="readonly"></td>
                            <td><input type="submit" name="btnViewAll" value="View All" class="buttons"></td>                                                      
                        </tr>
                        <tr>
                            <td class="headline">Address Street : </td>                                    
                            <td class="valueFields"><input type="text" name="txtStreet" id="txtStreet" value="${propObj.getAddStreet()}" class="fields" readonly="readonly"></td>
                            <td><input type="submit" name="btnCancel" value="Cancel" class="buttons"></td>                                               
                        </tr>                                 
                        <tr>
                            <td class="headline">Address City : </td>                                  
                            <td class="valueFields"><input type="text" name="txtCity" id="txtCity" value="${propObj.getAddCity()}" class="fields" readonly="readonly"></td>
                            <td></td>                                                   
                        </tr>
                        <tr>
                            <td class="headline">Type : </td>                                  
                            <td class="valueFields"><input type="text" name="txtType" id="txtType" value="${propObj.gettID()}" class="fields" readonly="readonly"></td>                                                                                        
                        </tr>                  
                        <tr>
                            <td class="headline">Maximum Number of Tenants : </td>                                  
                            <td class="valueFields"><input type="text" name="txtTenant" id="txtTenant" class="fields" value="${propObj.getNumOfTenants()}"></td>                                              
                        </tr>                        
                        <tr>
                            <td class="headline">Number of Bedrooms : </td>
                            <td class="valueFields"><input type="text" name="txtBedrooms" id="txtBedrooms" value="${propObj.getNoOfBedrooms()}" class="fields"></td>                                   
                            
                        </tr>
                        <tr>
                            <td class="headline">Number of Bathrooms : </td>
                            <td class="valueFields"><input type="text" name="txtBathrooms" id="txtBathrooms" value="${propObj.getNoOfBathrooms()}" class="fields"></td>                             
                        </tr>
                        <tr>
                            <td class="headline">Kitchen : </td>                                   
                            <td class="valueFields"><input type="checkbox" name="chkKitchen" id="chkKitchen" ${checked_kitchen}></td>
                        </tr>
                        <tr>
                            <td class="headline">Electricity : </td>                                    
                            <td class="valueFields"><input type="checkbox" name="chkElectricity" id="chkElectricity" ${checked_electricity}></td>
                        </tr>                        
                        <tr>
                            <td class="headline">Water : </td>
                            <td class="valueFields"><input type="checkbox" name="chkWater" id="chkWater" ${checked_water}></td>
                        </tr> 
                        <tr>
                            <td class="headline">Rent : </td>
                            <td class="valueFields"><input type="text" name="txtRent" id="txtRent" value="${propObj.getRent()}" class="fields" readonly="readonly"></td>     
                        </tr>
                        <tr>
                            <td class="headline">Additional Info : </td>
                            <td class="valueFields"><textarea rows="8" cols="28" name="txtAddInfo" id="txtAddInfo" readonly="readonly">${propObj.getAddInfo()}</textarea></td>
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
                el.value = (isNaN(v)) ? '' : v.toFixed(0);
            }      
        
        </script>                 
                        
    </body>
</html>
