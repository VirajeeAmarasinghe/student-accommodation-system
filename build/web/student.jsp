<%-- 
    Document   : homePage
    Created on : 29-Dec-2016, 18:21:50
    Author     : Virajee
--%>

<%@page import="business.International"%>
<%@page import="business.Local"%>
<%@page import="business.StudentFactory"%>
<%@page import="dao.LoginDAO"%>
<%@page import="dao.StudentDAO"%>
<%@page import= "business.Login" %>
<%@page import= "business.Student" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Page</title>
        <link rel="stylesheet" href="formatHomePage.css">
        <link rel="stylesheet" href="formatStudent.css"> 

        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBHkXiZ0cayXXPUkM6ELT1cN5J0MKmgE5E&libraries=places"></script> 

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
                            <li><a href="#">AS A STUDENT</a></li>
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
                    <li><a href="#" class="home">STUDENT</a></li>
                    <li><a href="landlord.jsp">LANDLORD</a></li>
                    <li><a href="about.html">ABOUT US</a></li>
                    <li><a href="contact.html">CONTACT US</a></li>
                </ul>
            </div>
        </nav>                
        <div id="registration_form" class="registration_form">
            <div class="page_header">
                <%
                    String name = "";
                    Student s = null;
                    Login log = null;
                    StudentDAO sDAO = null;
                    LoginDAO logDAO = null;

                    if (request.getSession(false) != null) {
                        if (request.getSession(false).getAttribute("user") != null) {
                            String email = (String) request.getSession(false).getAttribute("user");
                            sDAO = new StudentDAO();
                            logDAO = new LoginDAO();
                            name = sDAO.getName(email);

                            if (name.equals("")) {
                                RequestDispatcher rd = request.getRequestDispatcher("/MangeError");
                                request.setAttribute("error", "First Log In to the profile");
                                request.setAttribute("type", "student");
                                rd.forward(request, response);
                            } else {

                                s = (Student) sDAO.get(email);

                                log = (Login) logDAO.get(email, "studentlogindata");

                                if (s instanceof Local) {
                                    Local l = (Local) s;
                                    request.setAttribute("checked_local", "checked");
                                    request.setAttribute("readOnlyPassport", "readonly='readonly'");
                                    request.setAttribute("readOnlyCountry", "readonly='readonly'");
                                    request.setAttribute("stuID", l.getStu_id());
                                    request.setAttribute("stuFName", l.getStu_fName());
                                    request.setAttribute("stuLName", l.getStu_lName());
                                    request.setAttribute("stuAdd", l.getStu_currentAdd());
                                    request.setAttribute("stuTele", l.getStu_tele());
                                    request.setAttribute("stuNic", l.getNic());
                                    request.setAttribute("stuEmail", l.getEmail());
                                    request.setAttribute("stuPassport", " ");
                                    request.setAttribute("stuCountry", " ");
                                    request.setAttribute("password", log.getPass());
                                } else if (s instanceof International) {
                                    International i = (International) s;
                                    request.setAttribute("checked_inter", "checked");
                                    request.setAttribute("readOnlyNic", "readonly='readonly'");
                                    request.setAttribute("stuID", i.getStu_id());
                                    request.setAttribute("stuFName", i.getStu_fName());
                                    request.setAttribute("stuLName", i.getStu_lName());
                                    request.setAttribute("stuAdd", i.getStu_currentAdd());
                                    request.setAttribute("stuTele", i.getStu_tele());
                                    request.setAttribute("stuNic", " ");
                                    request.setAttribute("stuEmail", i.getEmail());
                                    request.setAttribute("stuPassport", i.getPassport());
                                    request.setAttribute("stuCountry", i.getCountry());
                                    request.setAttribute("password", log.getPass());
                                }

                                if (request.getParameter("btnReset") != null) {
                                    if (s instanceof Local) {
                                        request.setAttribute("stuFName", "");
                                        request.setAttribute("stuLName", "");
                                        request.setAttribute("stuAdd", "");
                                        request.setAttribute("stuTele", "");
                                        request.setAttribute("stuNic", "");
                                    } else if (s instanceof International) {
                                        request.setAttribute("stuFName", "");
                                        request.setAttribute("stuLName", "");
                                        request.setAttribute("stuAdd", "");
                                        request.setAttribute("stuTele", "");
                                        request.setAttribute("stuPassport", "");
                                        request.setAttribute("stuCountry", "");
                                    }
                                }
                            }
                        } else {
                            RequestDispatcher rd = request.getRequestDispatcher("/MangeError");
                            request.setAttribute("error", "First Log In to the profile");
                            request.setAttribute("type", "student");
                            rd.forward(request, response);
                        }
                    }
                %>
                <h2>Hello, <%=name%> .Welcome to your profile</h2>
            </div>
            <div id="registration_form_area">
                <div id="message_div">${message}</div>
                <form method="post" name="registration_form" action="MngStu">
                    <table>
                        <tr>
                            <td class="headline">Student ID : </td>                                    
                            <td class="valueFields"><input type="text" name="txtID" id="txtID" class="fields" readonly="readonly" value="${stuID}"></td>
                            <td><input type="submit" name="btnUpdate" value="Update" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">First Name* : </td>                                    
                            <td class="valueFields"><input type="text" name="txtFName" id="txtFName" value="${stuFName}" class="fields"></td>
                            <td><input type="submit" name="btnDelete" value="Delete" class="buttons"></td>
                        </tr> 
                        <tr>
                            <td class="headline">Last Name* : </td>                                    
                            <td class="valueFields"><input type="text" name="txtLName" id="txtLName" value="${stuLName}" class="fields"></td>
                            <td><input type="submit" name="btnReset" value="Reset" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">Current Address* : </td>                                    
                            <td class="valueFields"><input type="text" name="txtCurrentAdd" id="txtCurrentAdd" value="${stuAdd}" class="fields"></td> 
                            <td><input type="submit" name="btnCancel" value="Cancel" class="buttons"></td>
                        </tr>                                 
                        <tr>
                            <td class="headline">Contact No* : </td>                                  
                            <td class="valueFields"><input type="text" name="txtTele" id="txtTele" placeholder="+44 XXXX XXX XXX" value="${stuTele}" class="fields"></td>
                            <td><input type="submit" name="btnResetPass" value="Reset Password" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">Email : </td>                                  
                            <td class="valueFields"><input type="email" name="txtEmail" id="txtEmail" value="${stuEmail}" class="fields" readonly="readonly"></td> 
                            <td><input type="submit" name="btnViewMessages" value="Messages" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">Password : </td>                                  
                            <td class="valueFields"><input type="password" name="txtPass" id="txtPass" class="fields" readonly="readonly" value="${password}"></td>
                        </tr>                        
                        <tr>
                            <td class="headline">Student Type* : </td>
                            <td class="valueFields"><label for="rbtnLocal">Local</label><input type="radio" name="rbtnType" id="rbtnLocal" value="Local" onclick="clickLocal()" ${checked_local}></td>                                    
                        </tr>
                        <tr>
                            <td class="headline"></td>
                            <td class="valueFields"><label for="rbtnInternational">International</label><input type="radio" name="rbtnType" id="rbtnInternational" value="International" onclick="clickInternatinal()" ${checked_inter}></td>
                        </tr>
                        <tr>
                            <td class="headline">NIC No* : </td>                                    
                            <td class="valueFields"><input type="text" name="txtNic" id="txtNic" value="${stuNic}" class="fields" ${readOnlyNic}></td>
                        </tr>
                        <tr>
                            <td class="headline">Passport ID* : </td>                                    
                            <td class="valueFields"><input type="text" name="txtPassId" id="txtPassId" value="${stuPassport}" class="fields" ${readOnlyPassport}></td>
                        </tr>                        
                        <tr>
                            <td class="headline">Country* : </td>
                            <td class="valueFields"><input type="text" name="txtCountry" id="txtCountry" value="${stuCountry}" class="fields" ${readOnlyCountry}></td>
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


            function clickLocal() {
                document.getElementById("txtCountry").disabled = "true";
                document.getElementById("txtPassId").disabled = "true";
                document.getElementById("txtNic").removeAttribute("disabled");
                document.getElementById("txtCountry").value = "";
                document.getElementById("txtPassId").value = "";
            }
            function clickInternatinal() {
                document.getElementById("txtNic").disabled = "true";
                document.getElementById("txtCountry").removeAttribute("disabled");
                document.getElementById("txtPassId").removeAttribute("disabled");
                document.getElementById("txtNic").value = "";
            }
        </script>
    </body>
</html>
