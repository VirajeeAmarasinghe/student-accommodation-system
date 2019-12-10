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
        <title>Find Student Page</title>
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
                    <li><a href="#" class="home">STUDENT</a></li>                    
                </ul>
            </div>
        </nav>
        <div id="registration_form" class="registration_form">
            <div class="page_header">                
                <h2>Find Student</h2>
            </div>
            <div id="registration_form_area">
                <div id="message_div">${message}</div>
                <form method="post" name="registration_form" action="FindDisplayStudent">
                    <table>
                        <tr>
                            <td class="headline">Student ID : </td>                                    
                            <td class="valueFields"><input type="text" name="txtID" id="txtID" class="fields" value="${stuObj.getStu_id()}" onchange="validateFloatKeyPress(this)"></td>
                            <td><input type="submit" name="btnFind" value="Search" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">First Name : </td>                                    
                            <td class="valueFields"><input type="text" name="txtFName" id="txtFName" value="${stuObj.getStu_fName()}" class="fields"></td>
                            <td><input type="submit" name="btnViewAll" value="View All" class="buttons"></td>
                        </tr> 
                        <tr>
                            <td class="headline">Last Name : </td>                                    
                            <td class="valueFields"><input type="text" name="txtLName" id="txtLName" value="${stuObj.getStu_lName()}" class="fields" readonly="readonly"></td>
                            <td><input type="submit" name="btnDelete" value="Delete" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">Current Address : </td>                                    
                            <td class="valueFields"><input type="text" name="txtCurrentAdd" id="txtCurrentAdd" value="${stuObj.getStu_currentAdd()}" class="fields" readonly="readonly"></td> 
                            <td><input type="submit" name="btnCancel" value="Cancel" class="buttons"></td>
                        </tr>                                 
                        <tr>
                            <td class="headline">Contact No : </td>                                  
                            <td class="valueFields"><input type="text" name="txtTele" id="txtTele" value="${stuObj.getStu_tele()}" class="fields" readonly="readonly"></td>
                            <td><input type="submit" name="btnReset" value="Reset" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">Email : </td>                                  
                            <td class="valueFields"><input type="email" name="txtEmail" id="txtEmail" value="${stuObj.getEmail()}" class="fields" readonly="readonly"></td> 
                            <td></td>
                        </tr>                                                
                        <tr>
                            <td class="headline">Student Type : </td>
                            <td class="valueFields"><input type="text" name="txtStuType" id="txtStuType" value="${stuType}" class="fields" readonly="readonly"></td>                                    
                        </tr>                        
                        <tr>
                            <td class="headline">NIC No : </td>                                    
                            <td class="valueFields"><input type="text" name="txtNic" id="txtNic" value="${nic}" class="fields" readonly="readonly"></td>
                        </tr>
                        <tr>
                            <td class="headline">Passport ID : </td>                                    
                            <td class="valueFields"><input type="text" name="txtPassId" id="txtPassId" value="${passport}" class="fields" readonly="readonly"></td>
                        </tr>                        
                        <tr>
                            <td class="headline">Country : </td>
                            <td class="valueFields"><input type="text" name="txtCountry" id="txtCountry" value="${country}" class="fields" readonly="readonly"></td>
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
