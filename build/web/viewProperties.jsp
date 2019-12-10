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
        <title>View Properties Page</title>
        <link rel="stylesheet" href="formatHomePage.css">
        <link rel="stylesheet" href="formatViewProperties.css">
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
        <form action="DisplayProperty" method="post">                       
                <div class="product">
                    <div class="image">                    
                        <img src="DisplayImage?ID=${propOb.getPropID()}" alt="Produce Image" width="450" height="350">
                    </div>
                    <input type="hidden" name="code[]" value="${propOb.getPropID()}" >
                    <div class="text">
                        <label class="fields">Type : ${propertyType}</label><br>
                        <label class="fields">Location : ${propOb.getAddNo()},${propOb.getAddStreet()},${propOb.getAddCity()}</label><br>
                        <label class="fields">Rent :  ‎£${propOb.getRent()}</label><br>
                        <label class="fileds">Maximum Number of Tenants :  ‎${propOb.getNumOfTenants()}</label><br>
                        <label class="fileds">Maximum Number of Bedrooms :  ‎${propOb.getNoOfBedrooms()}</label><br>
                        <label class="fileds">Maximum Number of Bathrooms :  ‎${propOb.getNoOfBathrooms()}</label><br>
                        <label class="fileds">has Kitchen :  ‎${propOb.getKitchen()}</label><br>
                        <label class="fileds">has Electricity :  ‎${propOb.getElectricity()}</label><br>
                        <label class="fileds">has Water :  ‎${propOb.getWater()}</label><br>
                        <label class="fileds">Rent :  ‎${propOb.getRent()}</label><br>
                        <label class="fileds">More Info :  ‎${propOb.getAddInfo()}</label><br>
                        <label class="fileds">Landlord Name :  ‎${landlordOb.getL_fName()} ${landlordOb.getL_lName()}</label><br>
                        <label class="fileds">Contact No-Land :  ‎${landlordOb.getTele_land()}</label><br>
                        <label class="fileds">Contact No-Mobile :  ‎${landlordOb.getTele_mob()}</label><br>
                        <div class="buttonSections">                            	
                            <div class="buy" id="buy">
                                <button type="submit" name="rent" value="${propOb.getPropID()}" id="rent" class="buttons">Rent Now</button>
                            </div>
                            <div class="view" id="viewProp">
                                <button type="submit" name="view" value="${propOb.getPropID()}" id="rent" class="buttons">Request View</button>
                            </div>
                            <div class="back" id="back">
                                <button type="button" name="btnBack" value="" id="btnBak" class="buttons" onclick="javascript:history.go(-1)">Back</button>
                            </div>
                        </div>
                    </div>            
                </div>           
        </form>                      

        <div id="footer">
            <div id="footer_para">
                <p>Copyright © 2016 Student Accommodation. All Rights Reserved.</p>
            </div>
        </div>
        
    </body>
</html>
