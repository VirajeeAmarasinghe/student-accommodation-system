<%-- 
    Document   : landlord
    Created on : 29-Dec-2016, 21:04:35
    Author     : Virajee
--%>

<%@page import= "dao.LoginDAO" %>
<%@page import= "business.Login" %>
<%@page import= "business.Landlord" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "javax.servlet.RequestDispatcher" %>
<%@page import= "dao.LandlordDAO" %>
<%@page import= "javax.servlet.ServletException"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Landlord Page</title>
        <link rel="stylesheet" href="formatHomePage.css">
        <link rel="stylesheet" href="formatLandlord.css"> 

        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBHkXiZ0cayXXPUkM6ELT1cN5J0MKmgE5E&libraries=places"></script> 

    </head>
    <body>   
        <div id="top_stripe">
            <div id="homePage">
                <a href="homePage.jsp"><img src="home.png" alt="Home button" width="40" height="40"></a>
            </div>            
            <div id="registration_nav">
                <ul>
                    <li><a href="#" class="home">REGISTER</a>
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
                    <li><a href="#" class="home">LANDLORD</a></li>
                    <li><a href="about.html">ABOUT US</a></li>
                    <li><a href="contact.html">CONTACT US</a></li>
                </ul>
            </div>
        </nav>                
        <div id="registration_form" class="registration_form">
            <div class="page_header">
                <%
                    String name = "";
                    Landlord l = null;
                    Login log = null;
                    LandlordDAO lDAO = null;
                    LoginDAO logDAO = null;
                    if (request.getSession(false) != null) {
                        if (request.getSession(false).getAttribute("user") != null) {
                            String email = (String) request.getSession(false).getAttribute("user");
                            lDAO = new LandlordDAO();
                            logDAO = new LoginDAO();
                            name = lDAO.getName(email);
                            if (name.equals("")) {
                                RequestDispatcher rd = request.getRequestDispatcher("/MangeError");
                                request.setAttribute("error", "First Log In to the profile");
                                request.setAttribute("type", "landlord");
                                rd.forward(request, response);
                            } else {
                                l = (Landlord) lDAO.get(email);
                                log = (Login) logDAO.get(email, "landlordlogindata");

                                if (request.getParameter("btnReset") != null) {
                                    l.setL_fName(" ");
                                    l.setL_lName(" ");
                                    l.setAdd(" ");
                                    l.setTele_land(" ");
                                    l.setTele_mob(" ");
                                }

                                request.setAttribute("landlordObj", l);
                                request.setAttribute("logObj", log);
                            }
                        } else {
                            RequestDispatcher rd = request.getRequestDispatcher("/MangeError");
                            request.setAttribute("error", "First Log In to the profile");
                            request.setAttribute("type", "landlord");
                            rd.forward(request, response);
                        }
                    }
                %>
                <h2>Hello, <%=name%> .Welcome to your profile</h2>
            </div>
            <div id="registration_form_area">
                <div id="message_div">${message}</div>
                <form method="post" name="registration_form" action="MngLndlrd">
                    <table>
                        <tr>
                            <td class="headline">Landlord ID : </td>                                    
                            <td class="valueFields"><input type="text" name="txtID" id="txtID" class="fields" readonly="readonly" value="${landlordObj.getL_id()}"></td>
                            <td><input type="submit" name="btnUpdate" value="Update" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">First Name* : </td>                                    
                            <td class="valueFields"><input type="text" name="txtFName" id="txtFName" value="${landlordObj.getL_fName()}" class="fields"></td>
                            <td><input type="submit" name="btnDelete" value="Delete" class="buttons"></td>
                        </tr> 
                        <tr>
                            <td class="headline">Last Name* : </td>                                    
                            <td class="valueFields"><input type="text" name="txtLName" id="txtLName" value="${landlordObj.getL_lName()}" class="fields"></td>
                            <td><input type="submit" name="btnReset" value="Reset" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">Address* : </td>                                    
                            <td class="valueFields"><input type="text" name="txtCurrentAdd" id="txtCurrentAdd" value="${landlordObj.getAdd()}" class="fields"></td> 
                            <td><input type="submit" name="btnManageProp" value="Manage Property" class="buttons"></td>
                        </tr>                                 
                        <tr>
                            <td class="headline">Land Tele No* : </td>                                  
                            <td class="valueFields"><input type="text" name="txtTele" id="txtTele" placeholder="+44 XXXX XXX XXX" value="${landlordObj.getTele_land()}" class="fields"></td>
                            <td><input type="submit" name="btnResetPass" value="Reset Password" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">Mobile Tele No* : </td>                                  
                            <td class="valueFields"><input type="text" name="txtTeleMob" id="txtTeleMob" placeholder="+XXXXX XXXXXX" value="${landlordObj.getTele_mob()}" class="fields"></td>
                            <td><input type="submit" name="btnViewMessages" value="Messages" class="buttons"></td>
                        </tr>                        
                        <tr>
                            <td class="headline">Email : </td>                                  
                            <td class="valueFields"><input type="email" name="txtEmail" id="txtEmail" value="${landlordObj.getEmail()}" class="fields" readonly="readonly"></td>
                            <td><input type="submit" name="btnSearchPayment" value="SearchPayment" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">Password : </td>                                  
                            <td class="valueFields"><input type="password" name="txtPass" id="txtPass" class="fields" readonly="readonly" value="${logObj.getPass()}"></td>
                            <td><input type="submit" name="btnViewPayment" value="ViewPayment" class="buttons"></td>
                        </tr> 
                        <tr>
                            <td class="headline"></td> 
                            <td class="valueFields"></td>
                            <td><input type="submit" name="btnCancel" value="Cancel" class="buttons"></td>  
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
        <script type="text/javascript">
            google.maps.event.addDomListener(window, 'load', function() {

                var options = {
                    componentRestrictions: {country: "uk"}
                };

                new google.maps.places.Autocomplete(document.getElementById('txtCurrentAdd'), options);

            });

        </script>
    </body>
</html>


