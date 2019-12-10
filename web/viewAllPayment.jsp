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
        <title>All Payments</title>
        <link rel="stylesheet" href="formatHomePage.css"> 
        <link rel="stylesheet" href="formatPayment.css"> 
    </head>
    <body>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                <h2>All Payment Details</h2>
            </div>
            <div id="registration_form_area">
                <div id="message_div">${message}</div>
                <form method="post" name="registration_form" action="ManagePayments">
                    <table class="${visibility}">
                        <tr>
                            <th>Payment ID</th>
                            <th>Property ID</th>
                            <th>Amount</th>
                            <th>Payment Date</th>            
                        </tr>                        
                        <c:forEach var="item" items="${paymentList}">                       
                            <tr>                                    
                                <td class="valueFields"><input type="text" name="txtPayID" id="txtPayIDID" class="fields" readonly="readonly" value="${item.getPayID()}"></td>                                
                                <td class="valueFields"><input type="text" name="txtPID" id="txtPID" value="${item.getpID()}" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtAmount" id="txtAmount" value="${item.getAmount()}" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtDate" id="txtDate" class="fields" value="${item.getPayDate()}"></td>                                                                            
                            </tr>                            
                        </c:forEach>
                        <tr>
                            <td class="notBorder"><input type="submit" name="btnCancel" value="Cancel" class="buttons"></td>                            
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
