<%-- 
    Document   : rentForm
    Created on : 11-Jan-2017, 12:47:33
    Author     : Virajee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rent Page</title>
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
                    <li><a href="accommodation.jsp" class="home">ACCOMMODATION</a></li>
                    <li><a href="student.jsp">STUDENT</a></li>
                    <li><a href="landlord.jsp">LANDLORD</a></li>
                    <li><a href="about.html">ABOUT US</a></li>
                    <li><a href="contact.html">CONTACT US</a></li>
                </ul>
            </div>
        </nav>
                <div id="registration_form" class="registration_form">
            <div class="page_header">               
                <h2>Rent Form-Provide Below Details</h2>
            </div>
            <div id="registration_form_area">
                <div id="message_div">${message}</div>
                <form method="post" name="registration_form" action="MangeRent">
                    <table>
                        <tr>
                            <td class="headline">Rent ID : </td>                                    
                            <td class="valueFields"><input type="text" name="txtRID" id="txtRID" class="fields" readonly="readonly" value="${rID}"></td>
                            <td><input type="submit" name="btnConfirm" value="Confirm" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">Property ID* : </td>                                    
                            <td class="valueFields"><input type="text" name="txtPID" id="txtPName" value="${pID}" class="fields" readonly="readonly"></td>
                            <td><input type="submit" name="btnCancel" value="Cancel" class="buttons"></td>
                        </tr> 
                        <tr>
                            <td class="headline">Student ID* : </td>                                    
                            <td class="valueFields"><input type="text" name="txtSID" id="txtSID" value="${sID}" class="fields" readonly="readonly"></td>
                            <td><input type="reset" name="btnReset" value="Reset" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">Start Date* : </td>                                    
                            <td class="valueFields"><input type="date" name="txtStartDate" id="txtDate" class="fields">
                        <tr>
                            <td class="headline">End Date* : </td>                                  
                            <td class="valueFields"><input type="date" name="txtEndDate" id="txtEndDate" class="fields"></td>                          
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
    </body>
</html>
