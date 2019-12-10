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
        <title>View All Payment Page</title>
        <link rel="stylesheet" href="formatHomePage.css">
        <link rel="stylesheet" href="formatViewAllPaymentsAdmin.css">
    </head>
    <body>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                    <li><a href="payment.jsp" class="home">PAYMENT</a></li>                  
                </ul>
            </div>
        </nav>
        <div id="registration_form" class="registration_form">
            <div class="page_header">                
                <h2>Display All Payments</h2>
            </div>
            <div id="registration_form_area">
                <div id="message_div">${message}</div>
                <form method="post" name="registration_form" action="ManageAdminLandlord">
                    <table class="${visibility}">
                        <tr>
                            <th>Payment ID</th>
                            <th>Amount</th>
                            <th>Payment Date</th>
                            <th>Property ID</th>                            
                        </tr>
                        <c:forEach var="item" items="${paymentList}"> 
                            <tr>                                                               
                                <td class="valueFields"><input type="text" name="txtID" id="txtID" class="fields" value="${item.getPayID()}" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtFName" id="txtFName" value="${item.getAmount()}" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtLName" id="txtLName" value="${item.getPayDate()}" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtCurrentAdd" id="txtCurrentAdd" value="${item.getpID()}" class="fields" readonly="readonly"></td>                                
                            </tr>
                        </c:forEach>  
                        <tr>
                            <td class="notBorder"><button type="button" name="btnBack" value="" id="btnBak" class="buttons" onclick="javascript:history.go(-1)">Back</button></td>  
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
