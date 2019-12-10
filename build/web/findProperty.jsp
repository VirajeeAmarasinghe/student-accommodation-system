<%-- 
    Document   : property
    Created on : 05-Jan-2017, 01:25:54
    Author     : Virajee
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Find Property Page</title>
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
                <h2>Find Property</h2>
            </div>
            <div id="registration_form_area">
                <div id="message_div">${message}</div>                
                <form method="post" name="registration_form" action="FindProperty">
                    <table> 
                        <tr>
                            <td class="headline">Property ID : </td>                                    
                            <td class="valueFields"><input type="text" name="txtID" id="txtID" class="fields" onchange="validateFloatKeyPress(this)" value="${propObj.getPropID()}"></td>                            
                            <td><input type="submit" name="btnSearch" value="Search" class="buttons"></td>                                                      
                        </tr>
                        <tr>
                            <td class="headline">Address No : </td>                                    
                            <td class="valueFields"><input type="text" name="txtNo" id="txtNo" value="${propObj.getAddNo()}" class="fields" readonly="readonly"></td>
                            <td class="notBorder"><button type="button" name="btnBack" value="" id="btnBak" class="buttons" onclick="javascript:history.go(-1)">Back</button></td>                                                      
                        </tr>
                        <tr>
                            <td class="headline">Address Street : </td>                                    
                            <td class="valueFields"><input type="text" name="txtStreet" id="txtStreet" value="${propObj.getAddStreet()}" class="fields" readonly="readonly"></td>
                            <td></td>                                               
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
                            <td class="valueFields"><textarea rows="8" name="txtAddInfo" id="txtAddInfo" readonly="readonly">${propObj.getAddInfo()}</textarea></td>
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
