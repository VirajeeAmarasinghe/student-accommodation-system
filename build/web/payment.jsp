<%-- 
    Document   : homePage
    Created on : 29-Dec-2016, 18:21:50
    Author     : Virajee
--%>

<%@page import="dao.PaymentDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Page</title>
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
                    <li><a href="findPropertyByAdmin.jsp">PROPERTY</a></li>
                    <li><a href="#" class="home">PAYMENT</a></li>
                </ul>
            </div>
        </nav>
        <div id="registration_form" class="registration_form">
            <div class="page_header">            
                <h2>Payments</h2>
            </div>
            <div id="registration_form_area">
                <div id="message_div">${message}</div>
                <%
                        if(request.getParameter("btnFind")==null){
                            PaymentDAO pDAO = new PaymentDAO();
                    int pID = pDAO.getNxtId();
                    request.setAttribute("pID", pID);
                        }                    
                %>
                <form method="post" name="registration_form" action="ManagePaymentAdmin">
                    <table> 
                        <tr>
                            <td class="headline">Payment ID : </td>                                    
                            <td class="valueFields"><input type="text" name="txtID" id="txtID" value="${pID}" class="fields" onchange="validateFloatKeyPress2(this)"></td>
                            <td><input type="submit" name="btnSubmit" value="Submit" class="buttons"></td>                                                       
                        </tr>
                        <tr>
                            <td class="headline">Amount : </td>                                    
                            <td class="valueFields"><input type="text" name="txtAmount" id="txtAmount" value="${payObj.getAmount()}" class="fields" onchange="validateFloatKeyPress(this)"></td>
                            <td><input type="submit" name="btnDelete" value="Delete" class="buttons"></td>                                                       
                        </tr>
                        <tr>
                            <td class="headline">Payment Date : </td>                                    
                            <td class="valueFields"><input type="text" name="txtDate" id="txtDate" class="fields" value="${payObj.getPayDate()}" placeholder="Format : yyyy-mm-dd"></td>
                            <td><input type="submit" name="btnUpdate" value="Update" class="buttons"></td>                                                
                        </tr>                                 
                        <tr>
                            <td class="headline">Property ID :</td>                                  
                            <td class="valueFields"><input type="text" name="txtProperty" id="txtProperty" class="fields" value="${payObj.getpID()}" onchange="validateFloatKeyPress2(this)"></td>
                            <td><input type="submit" name="btnFind" value="Find" class="buttons"></td>                                                     
                        </tr>
                        <tr>
                            <td class="headline"></td>                                  
                            <td class="valueFields"></td>               
                            <td><input type="submit" name="btnViewAll" value="View All" class="buttons"></td>                                                                 
                        </tr>                  
                        <tr>
                            <td class="headline"></td>                                  
                            <td class="valueFields"></td>
                            <td><input type="submit" name="btnReset" value="Reset" class="buttons"></td>                        
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
        <script>
            function validateFloatKeyPress(el) {
                var v = parseFloat(el.value);
                el.value = (isNaN(v)) ? '' : v.toFixed(2);
            }

            function validateFloatKeyPress2(el) {
                var v = parseFloat(el.value);
                el.value = (isNaN(v)) ? '' : v.toFixed(0);
            }
        </script>
    </body>
</html>
