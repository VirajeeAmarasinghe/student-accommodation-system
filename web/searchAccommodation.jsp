<%-- 
    Document   : homePage
    Created on : 29-Dec-2016, 18:21:50
    Author     : Virajee
--%>

<%@page import="business.Property"%>
<%@page import="dao.PropertyTypeDAO"%>
<%@page import="business.PropertyType"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="dao.PropertyDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Accommodation Page</title>
        <link rel="stylesheet" href="formatHomePage.css">
        <link rel="stylesheet" href="formatAccommodation.css">
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
                    <li><a href="accommodation.jsp" class="home">ACCOMMODATION</a></li>
                    <li><a href="student.jsp">STUDENT</a></li>
                    <li><a href="landlord.jsp">LANDLORD</a></li>
                    <li><a href="about.html">ABOUT US</a></li>
                    <li><a href="contact.html">CONTACT US</a></li>
                </ul>
            </div>
        </nav>
        <%
            PropertyDAO propDAO = new PropertyDAO();
            Set<String> streetList = propDAO.getStreets();

            ArrayList<PropertyType> p = new ArrayList<PropertyType>();
            PropertyTypeDAO pDAO = new PropertyTypeDAO();
            p = pDAO.getAllObj();

        %>
        <div id="search_criteria" class="search_criteria">
            <form method="post" action="DisplayProperty">
                <ul>
                    <li>Street
                        <select name="streets" class="fields">
                            <%                                for (Iterator<String> it = streetList.iterator(); it.hasNext();) {
                                    String street = it.next();
                            %>                       
                            <option value="<%=street%>" name="typeOption"><%=street%></option>
                            <%}%>

                        </select> 
                    </li>
                    <li>Type
                        <select name="typeProperty" class="fields">
                            <% for (int i = 0; i < p.size(); i++) {%>                        
                            <option value="<%=p.get(i).getT_id()%>" name="typeOption"><%=p.get(i).getTypeName()%></option>
                            <%}%>
                        </select> 
                    </li>
                    <li>Min Price
                        <input type="text" name="txtMin" onchange="validateFloatKeyPress(this)" class="fields">
                    </li>
                    <li>Max Price
                        <input type="text" name="txtMax" onchange="validateFloatKeyPress(this)" class="fields">
                    </li>
                    <li><input type="submit" name="btnSearch" value="Search" class="buttons"></li>
                </ul>
            </form>                    
        </div>

        <form action="DisplayProperty" method="post"> 
            <div id="message_section">${message}</div>
            <c:forEach var="item" items="${propList}">
                <div class="product">
                    <div class="image">                    
                        <img src="DisplayImage?ID=${item.getPropID()}" alt="Produce Image" width="360" height="238">
                    </div>
                    <input type="hidden" name="code[]" value="${item.getPropID()}" >
                    <div class="text">
                        <label class="pro_name">${propertyType}</label><br>
                        <label class="pro_name">Location : ${item.getAddNo()},${item.getAddStreet()},${item.getAddCity()}</label><br>
                        <label class="price">Rent :  ‎£${item.getRent()}</label><br>
                        <div class="buttonSections">
                            <div class="view" id="viewMore">
                                <button type="submit" name="viewMore" value="${item.getPropID()}" class="buttons">View more</button>
                            </div>	
                            <div class="buy" id="buy">
                                <button type="submit" name="rent" value="${item.getPropID()}" id="rent" class="buttons">Rent Now</button>
                            </div>
                            <div class="view" id="viewProp">
                                <button type="submit" name="view" value="${item.getPropID()}" id="rent" class="buttons">Request View</button>
                            </div>
                        </div>
                    </div>            
                </div>

            </c:forEach> 
        </form>                       

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
        </script>
    </body>
</html>
