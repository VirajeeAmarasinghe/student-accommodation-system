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
        <title>Student Registration Page</title>
        <link rel="stylesheet" href="formatHomePage.css">
        <link rel="stylesheet" href="formatStudentReg.css"> 

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
                    <li><a href="student.jsp">STUDENT</a></li>
                    <li><a href="landlord.jsp">LANDLORD</a></li>
                    <li><a href="about.html">ABOUT US</a></li>
                    <li><a href="contact.html">CONTACT US</a></li>
                </ul>
            </div>
        </nav>                
        <div id="registration_form" class="registration_form">
            <div class="page_header">
                <h2>Sign up for an account</h2>
            </div>
            <div id="registration_form_area">
                <div id="message_div">${message}</div>
                <form method="post" name="registration_form" action="RegiStu">
                    <table>
                        <tr>
                            <td class="headline">First Name : </td>                                    
                            <td class="valueFields"><input type="text" name="txtFName" id="txtFName" value="<% if (request.getParameter("txtFName") == null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtFName"));
                                }%>" class="fields"></td>
                            <td><input type="submit" name="btnSubmit" value="Submit" class="buttons" onclick="clickSubButton()"></td>
                        </tr> 
                        <tr>
                            <td class="headline">Last Name : </td>                                    
                            <td class="valueFields"><input type="text" name="txtLName" id="txtLName" value="<% if (request.getParameter("txtLName") == null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtLName"));
                                }%>" class="fields"></td>
                            <td><input type="reset" name="btnReset" value="Reset" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">Current Address : </td>                                    
                            <td class="valueFields"><input type="text" name="txtCurrentAdd" id="txtCurrentAdd" value="<% if (request.getParameter("txtCurrentAdd") == null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtCurrentAdd"));
                                }%>" class="fields"></td> 
                            <td><input type="submit" name="btnCancel" value="Cancel" class="buttons"></td>
                        </tr>                                 
                        <tr>
                            <td class="headline">Contact No : </td>                                  
                            <td class="valueFields"><input type="text" name="txtTele" id="txtTele" placeholder="+44 XXXX XXX XXX" value="<% if (request.getParameter("txtTele") == null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtTele"));
                                }%>" class="fields"></td>
                        </tr>
                        <tr>
                            <td class="headline">Email : </td>                                  
                            <td class="valueFields"><input type="email" name="txtEmail" id="txtEmail" value="<% if (request.getParameter("txtEmail") == null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtEmail"));
                                }%>" class="fields"></td>
                        </tr>
                        <tr>
                            <td class="headline">Password : </td>                                  
                            <td class="valueFields"><input type="password" name="txtPass" id="txtPass" class="fields"></td>
                        </tr>
                        <tr>
                            <td class="headline">Confirm Password : </td>                                  
                            <td class="valueFields"><input type="password" name="txtConfirmPass" id="txtConfirmPass" class="fields"></td>
                        </tr>
                        <tr>
                            <td class="headline">Student Type : </td>
                            <td class="valueFields"><label for="rbtnLocal">Local</label><input type="radio" name="rbtnType" id="rbtnLocal" value="Local" onclick="clickLocal()" ${checked_local}></td>                                    
                        </tr>
                        <tr>
                            <td class="headline"></td>
                            <td class="valueFields"><label for="rbtnInternational">International</label><input type="radio" name="rbtnType" id="rbtnInternational" value="International" onclick="clickInternatinal()" ${checked_inter}></td>
                        </tr>
                        <tr>
                            <td class="headline">NIC No : </td>                                    
                            <td class="valueFields"><input type="text" name="txtNic" id="txtNic" value="<% if (request.getParameter("txtNic") == null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtNic"));
                            }%>" class="fields"></td>
                        </tr>
                        <tr>
                            <td class="headline">Passport ID : </td>                                    
                            <td class="valueFields"><input type="text" name="txtPassId" id="txtPassId" value="<% if (request.getParameter("txtPassId") == null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtPassId"));
                                }%>" class="fields"></td>
                        </tr>
                        <tr>
                            <td class="headline">Native Address : </td>                                    
                            <td class="valueFields"><input type="text" name="txtNativeAdd" id="txtNativeAdd" value="<% if (request.getParameter("txtNativeAdd") == null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtNativeAdd"));
                                }%>" onkeypress="selectCountry()" class="fields"><td>
                            <td><span class="popuptext" id="myPopup">Press ENTER After Selecting</span></td>
                        </tr>
                        <tr>
                            <td class="headline">Country : </td>
                            <td class="valueFields"><input type="text" name="txtCountry" id="txtCountry" value="<% if (request.getParameter("txtCountry") == null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtCountry"));
                                }%>" class="fields"></td>
                        </tr>                        
                    </table>
                        <p><input type="checkbox" name="chkAgree" id="chkAgree">I Agree to Terms and Conditions of Student Accommodation</p>
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

                new google.maps.places.Autocomplete(document.getElementById('txtNativeAdd'), {types: ['geocode']});
            });

            if (localStorage !== null) {
                if(localStorage.getItem('status')!==null){
                    getDefaultValue();
                }
                if(localStorage.getItem('status_2')!==null){
                    setDisable();
                }
                if(localStorage.getItem('status_3')!==null){
                    setDisable_2();
                }
            }


            function selectCountry() {
                var nativeAdd = document.getElementById("txtNativeAdd").value;
                var nativeAddArray = nativeAdd.split(",");
                var length = nativeAddArray.length;
                var country = nativeAddArray[length - 1];
                document.getElementById("txtCountry").value = country;
                localStorage.setItem('status', 'rbtnInternational');
            }
            function clickLocal() {
                document.getElementById("txtNativeAdd").disabled = "true";
                document.getElementById("txtCountry").disabled = "true";
                document.getElementById("txtPassId").disabled = "true";
                document.getElementById("txtNic").removeAttribute("disabled");
                document.getElementById("txtNativeAdd").value = "";
                document.getElementById("txtCountry").value = "";
                document.getElementById("txtPassId").value = "";
            }
            function clickInternatinal() {
                document.getElementById("txtNic").disabled = "true";
                document.getElementById("txtNativeAdd").removeAttribute("disabled");
                document.getElementById("txtCountry").removeAttribute("disabled");
                document.getElementById("txtPassId").removeAttribute("disabled");
                document.getElementById("txtNic").value = "";
            }

            function getDefaultValue()
            {
                var valId = localStorage.getItem('status');
                document.getElementById(valId).checked = true;                
                localStorage.removeItem('status');
            }  
            
            function clickSubButton(){
                if(document.getElementById("rbtnInternational").checked){
                    localStorage.setItem('status_2', 'txtNic');
                }else if(document.getElementById("rbtnLocal").checked){
                    localStorage.setItem('status_3','txtPassId');
                    localStorage.setItem('status_4','txtNativeAdd');
                    localStorage.setItem('status_5','txtCountry');
                }               
            }
            
            function setDisable(){
                var valId = localStorage.getItem('status_2');
                document.getElementById(valId).disabled = true;                
                localStorage.removeItem('status_2');
            }
            
            function setDisable_2(){
                var valId = localStorage.getItem('status_3');
                document.getElementById(valId).disabled = true;                
                localStorage.removeItem('status_3');
                var valId = localStorage.getItem('status_4');
                document.getElementById(valId).disabled = true;                
                localStorage.removeItem('status_4');
                var valId = localStorage.getItem('status_5');
                document.getElementById(valId).disabled = true;                
                localStorage.removeItem('status_5');
            }
            
        </script>
    </body>
</html>
