<%-- 
    Document   : landlordResetPass
    Created on : 30-Dec-2016, 12:19:57
    Author     : Virajee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password-Landlord</title>
        <link rel="stylesheet" href="formatPasswordReset.css">
    </head>
    <body>        
        <div id="main_content">
            <div id="title">
                <a href="homePage.jsp"><h1 id="main_title">STUDENT ACCOMMODATION</h1></a>           
            </div>
            <div id="registration_form" class="registration_form">
                <div class="page_header">
                    <h2>Reset Password</h2>
                </div>
                <div id="registration_form_area">                    
                    <div id="message_div">${message}</div>
                    <form method="post" name="registration_form" action="RstPass">                
                        <div class="control">                   
                            <input id="username" name="username" type="email" placeholder="Email" class="field" value="<% if (request.getParameter("username") == null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("username"));
                                }%>">
                        </div>
                        <div class="control">                   
                            <input id="password" name="password" type="password" placeholder="Password" class="field" value="">
                        </div>
                        <div class="control">                   
                            <input id="confirmPass" name="confirmPass" type="password" placeholder="Confirm Password" class="field" value="">
                        </div>
                        <div class="form_action">
                            <button type="submit" name="btn_log" id="log_button" class="buttons">Reset</button><br>
                            <button type="button" name="btn_cancel" id="cancel_button" class="buttons"><a href="homePage.jsp">Cancel</a></button>
                        </div>                                        
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

