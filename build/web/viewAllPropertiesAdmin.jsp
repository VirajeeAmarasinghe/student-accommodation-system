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
        <title>Display All Property Page</title>
        <link rel="stylesheet" href="formatHomePage.css">
        <link rel="stylesheet" href="formatDisplayAllProperties.css">
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
                    <li><a href="findPropertyByAdmin.jsp" class="home">PROPERTY</a></li>
                </ul>
            </div>
        </nav>
        <div id="registration_form" class="registration_form">
            <div class="page_header">                
                <h2>All Properties</h2>
            </div>
            <div id="registration_form_area">
                <div id="message_div">${message}</div>
                <form method="post" name="registration_form" action="">
                    <table>
                        <tr>
                            <th>Property ID</th>
                            <th>Address No</th>
                            <th>Address Street</th>
                            <th>Address City</th>
                            <th>Type</th>
                            <th>Rent</th>                           
                        </tr>
                        <c:forEach var="item" items="${propertyList}"> 
                            <tr>                                                               
                                <td class="valueFields"><input type="text" name="txtID" id="txtID" class="fields" value="${item.getPropID()}" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtFName" id="txtFName" value="${item.getAddNo()}" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtLName" id="txtLName" value="${item.getAddStreet()}" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtCurrentAdd" id="txtCurrentAdd" value="${item.getAddCity()}" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtTele" id="txtTele" value="${item.gettID()}" class="fields" readonly="readonly"></td>
                                <td class="valueFields"><input type="text" name="txtTeleMob" id="txtTeleMob" value="${item.getRent()}" class="fields" readonly="readonly"></td>                                
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
