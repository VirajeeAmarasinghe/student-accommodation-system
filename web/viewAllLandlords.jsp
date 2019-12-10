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
        <title>View All Landlord Page</title>
        <link rel="stylesheet" href="formatHomePage.css">
        <link rel="stylesheet" href="formatDisplayAllLandlords.css">
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
                    <li><a href="findLandlord.jsp" class="home">LANDLORD</a></li>
                    <li><a href="">STUDENT</a></li>                    
                </ul>
            </div>
        </nav>
        <div id="registration_form" class="registration_form">
            <div class="page_header">                
                <h2>Display All Landlords</h2>
            </div>
            <div id="registration_form_area">
                <div id="message_div">${message}</div>
                <form method="post" name="registration_form" action="ManageAdminLandlord">
                    <table class="${visibility}">
                        <tr>
                            <th>Landlord ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Address</th>
                            <th>Land Tele No</th>
                            <th>Mobile Tele No</th>
                            <th>Email</th>
                        </tr>
                        <c:forEach var="item" items="${landlordList}"> 
                            <tr>                                                               
                                <td class="valueFields"><input type="text" name="txtID" id="txtID" class="fields" value="${item.getL_id()}" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtFName" id="txtFName" value="${item.getL_fName()}" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtLName" id="txtLName" value="${item.getL_lName()}" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtCurrentAdd" id="txtCurrentAdd" value="${item.getAdd()}" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtTele" id="txtTele" value="${item.getTele_land()}" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtTeleMob" id="txtTeleMob" value="${item.getTele_mob()}" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="email" name="txtEmail" id="txtEmail" value="${item.getEmail()}" class="fields" readonly="readonly"></td>
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
