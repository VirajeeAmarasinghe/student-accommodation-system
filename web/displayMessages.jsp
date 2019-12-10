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
        <title>Messages</title>
        <link rel="stylesheet" href="formatHomePage.css"> 
        <link rel="stylesheet" href="formatDisplayMessages.css"> 
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
                    <li><a href="landlord.jsp">LANDLORD</a></li>
                    <li><a href="about.html">ABOUT US</a></li>
                    <li><a href="contact.html">CONTACT US</a></li>
                </ul>
            </div>
        </nav>
        <div id="registration_form" class="registration_form">
            <div class="page_header">               
                <h2>Your Confirmation is required for below view requests</h2>
            </div>
            <div id="registration_form_area">
                <div id="message_div">${message}</div>
                <form method="post" name="registration_form" action="ManageMessages">
                    <table class="${visibility}">
                        <tr>
                            <th>View ID</th>
                            <th>Property ID</th>
                            <th>Student ID</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <% int i = 1;%>
                        <c:forEach var="item" items="${messageList}">                       
                            <tr>                                    
                                <td class="valueFields"><input type="text" name="txtVID" id="txtVID" class="fields" readonly="readonly" value="${item.getViewID()}"><input type="hidden" name="code[]" value="${item.getViewID()}"></td>                                
                                <td class="valueFields"><input type="text" name="txtPID" id="txtPID" value="${item.getpID()}" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtSID" id="txtSID" value="<c:if test="${item.getStuLocalID()==4}">${item.getStuInternationalID()}</c:if><c:if test="${item.getStuInternationalID()==5}">${item.getStuLocalID()}</c:if>" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtDate" id="txtDate" class="fields" value="${item.getViewDate()}"></td>
                                <td class="valueFields"><input type="text" name="txtTime" id="txtTime" class="fields" value="${item.getViewTime()}"></td>
                                <td><button type="submit" name="btnConfirm" class="buttons" value="${item.getViewID()}">Confirm</button></td>
                                <td><button type="submit" name="btnAdjsut" class="buttons" value="${item.getViewID()}">Adjust</button></td>                              
                                <td><input type="hidden" name="rowNo" value="<%=i%>"></td>                
                            </tr>
                            <% i++;%>
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
