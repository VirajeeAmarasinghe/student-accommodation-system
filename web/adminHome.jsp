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
        <title>Admin Home Page</title>
        <link rel="stylesheet" href="formatAdminHome.css">
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
                    <li><a href="adminHome.jsp" class="home">HOME</a></li>
                    <li><a href="findLandlord.jsp">LANDLORD</a></li>
                    <li><a href="findStudent.jsp">STUDENT</a></li>
                    <li><a href="findPropertyByAdmin.jsp">PROPERTY</a></li>
                    <li><a href="payment.jsp">PAYMENT</a></li>
                        <%
                            out.print("<li><a href='#'>REPORTS</a>");
                            out.print("<ul class='dropdown'>");
                            out.print("<li><a href='propertyAvailabilityReport.jsp'>PROPERTY AVAILABILITY REPORT</a></li>");
                            out.print("<li><a href='occupiedDetailsReport.jsp'>OCCUPIED DETAILS REPORT</a></li>");
                            out.print("<li><a href='propertyTypeReport.jsp'>PROPERTY TYPE REPORT</a></li>");
                            out.print("<li><a href='monthlyRentReport.jsp'>MONTHLY RENT REPORT</a></li>");
                            out.print("</li>");
                        %>
                </ul>
            </div>
        </nav>
             <%--This image is quoted from this link=http://www.studentaccommodation.co.za/--%>
        <div id="top_banner">           
            <img src="top banner-admin.jpeg" alt="top_banner" height="500">        
        </div>
        <div id="footer">
            <div id="footer_para">
                <p>Copyright © 2016 Student Accommodation. All Rights Reserved.</p>
            </div>
        </div>
    </body>
</html>
