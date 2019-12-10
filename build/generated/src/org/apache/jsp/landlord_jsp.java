package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import dao.LoginDAO;
import business.Login;
import business.Landlord;
import javax.servlet.RequestDispatcher;
import dao.LandlordDAO;
import javax.servlet.ServletException;

public final class landlord_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Landlord Page</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"formatHomePage.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"formatLandlord.css\"> \n");
      out.write("\n");
      out.write("        <script type=\"text/javascript\" src=\"http://maps.googleapis.com/maps/api/js?key=AIzaSyBHkXiZ0cayXXPUkM6ELT1cN5J0MKmgE5E&libraries=places\"></script> \n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>   \n");
      out.write("        <div id=\"top_stripe\">\n");
      out.write("            <div id=\"homePage\">\n");
      out.write("                <a href=\"homePage.jsp\"><img src=\"home.png\" alt=\"Home button\" width=\"40\" height=\"40\"></a>\n");
      out.write("            </div>            \n");
      out.write("            <div id=\"registration_nav\">\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"#\" class=\"home\">REGISTER</a>\n");
      out.write("                        <ul class=\"dropdown\">\n");
      out.write("                            <li><a href=\"studentReg.jsp\">AS A STUDENT</a></li>\n");
      out.write("                            <li><a href=\"landlordReg.jsp\">AS A LANDLORD</a></li>\n");
      out.write("                        </ul>\n");
      out.write("                    </li>\n");
      out.write("                    ");

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
                    
      out.write("\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"second_stripe\">\n");
      out.write("            <a href=\"homePage.jsp\">\n");
      out.write("                <h1 id=\"title_1\">STUDENT</h1>\n");
      out.write("                <h1 id=\"title_2\">ACCOMMODATION</h1>\n");
      out.write("            </a>\n");
      out.write("        </div> \n");
      out.write("        <nav class=\"main\">\n");
      out.write("            <div id=\"Nav\">\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"homePage.jsp\">HOME</a></li>\n");
      out.write("                    <li><a href=\"accommodation.jsp\">ACCOMMODATION</a></li>\n");
      out.write("                    <li><a href=\"student.jsp\">STUDENT</a></li>\n");
      out.write("                    <li><a href=\"#\" class=\"home\">LANDLORD</a></li>\n");
      out.write("                    <li><a href=\"about.html\">ABOUT US</a></li>\n");
      out.write("                    <li><a href=\"contact.html\">CONTACT US</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("        </nav>                \n");
      out.write("        <div id=\"registration_form\" class=\"registration_form\">\n");
      out.write("            <div class=\"page_header\">\n");
      out.write("                ");

                    String name = "";
                    Landlord l = null;
                    Login log = null;
                    LandlordDAO lDAO = null;
                    LoginDAO logDAO = null;
                    if (request.getSession(false) != null) {
                        if (request.getSession(false).getAttribute("user") != null) {
                            String email = (String) request.getSession(false).getAttribute("user");
                            lDAO = new LandlordDAO();
                            logDAO = new LoginDAO();
                            name = lDAO.getName(email);
                            if (name.equals("")) {
                                RequestDispatcher rd = request.getRequestDispatcher("/MangeError");
                                request.setAttribute("error", "First Log In to the profile");
                                request.setAttribute("type", "landlord");
                                rd.forward(request, response);
                            } else {
                                l = (Landlord) lDAO.get(email);
                                log = (Login) logDAO.get(email, "landlordlogindata");

                                if (request.getParameter("btnReset") != null) {
                                    l.setL_fName(" ");
                                    l.setL_lName(" ");
                                    l.setAdd(" ");
                                    l.setTele_land(" ");
                                    l.setTele_mob(" ");
                                }

                                request.setAttribute("landlordObj", l);
                                request.setAttribute("logObj", log);
                            }
                        } else {
                            RequestDispatcher rd = request.getRequestDispatcher("/MangeError");
                            request.setAttribute("error", "First Log In to the profile");
                            request.setAttribute("type", "landlord");
                            rd.forward(request, response);
                        }
                    }
                
      out.write("\n");
      out.write("                <h2>Hello, ");
      out.print(name);
      out.write(" .Welcome to your profile</h2>\n");
      out.write("            </div>\n");
      out.write("            <div id=\"registration_form_area\">\n");
      out.write("                <div id=\"message_div\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${message}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</div>\n");
      out.write("                <form method=\"post\" name=\"registration_form\" action=\"MngLndlrd\">\n");
      out.write("                    <table>\n");
      out.write("                        <tr>\n");
      out.write("                            <td class=\"headline\">Landlord ID : </td>                                    \n");
      out.write("                            <td class=\"valueFields\"><input type=\"text\" name=\"txtID\" id=\"txtID\" class=\"fields\" readonly=\"readonly\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${landlordObj.getL_id()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></td>\n");
      out.write("                            <td><input type=\"submit\" name=\"btnUpdate\" value=\"Update\" class=\"buttons\"></td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td class=\"headline\">First Name* : </td>                                    \n");
      out.write("                            <td class=\"valueFields\"><input type=\"text\" name=\"txtFName\" id=\"txtFName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${landlordObj.getL_fName()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"fields\"></td>\n");
      out.write("                            <td><input type=\"submit\" name=\"btnDelete\" value=\"Delete\" class=\"buttons\"></td>\n");
      out.write("                        </tr> \n");
      out.write("                        <tr>\n");
      out.write("                            <td class=\"headline\">Last Name* : </td>                                    \n");
      out.write("                            <td class=\"valueFields\"><input type=\"text\" name=\"txtLName\" id=\"txtLName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${landlordObj.getL_lName()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"fields\"></td>\n");
      out.write("                            <td><input type=\"submit\" name=\"btnReset\" value=\"Reset\" class=\"buttons\"></td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td class=\"headline\">Address* : </td>                                    \n");
      out.write("                            <td class=\"valueFields\"><input type=\"text\" name=\"txtCurrentAdd\" id=\"txtCurrentAdd\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${landlordObj.getAdd()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"fields\"></td> \n");
      out.write("                            <td><input type=\"submit\" name=\"btnManageProp\" value=\"Manage Property\" class=\"buttons\"></td>\n");
      out.write("                        </tr>                                 \n");
      out.write("                        <tr>\n");
      out.write("                            <td class=\"headline\">Land Tele No* : </td>                                  \n");
      out.write("                            <td class=\"valueFields\"><input type=\"text\" name=\"txtTele\" id=\"txtTele\" placeholder=\"+44 XXXX XXX XXX\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${landlordObj.getTele_land()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"fields\"></td>\n");
      out.write("                            <td><input type=\"submit\" name=\"btnResetPass\" value=\"Reset Password\" class=\"buttons\"></td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td class=\"headline\">Mobile Tele No* : </td>                                  \n");
      out.write("                            <td class=\"valueFields\"><input type=\"text\" name=\"txtTeleMob\" id=\"txtTeleMob\" placeholder=\"+XXXXX XXXXXX\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${landlordObj.getTele_mob()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"fields\"></td>\n");
      out.write("                            <td><input type=\"submit\" name=\"btnViewMessages\" value=\"Messages\" class=\"buttons\"></td>\n");
      out.write("                        </tr>                        \n");
      out.write("                        <tr>\n");
      out.write("                            <td class=\"headline\">Email : </td>                                  \n");
      out.write("                            <td class=\"valueFields\"><input type=\"email\" name=\"txtEmail\" id=\"txtEmail\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${landlordObj.getEmail()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"fields\" readonly=\"readonly\"></td>\n");
      out.write("                            <td><input type=\"submit\" name=\"btnSearchPayment\" value=\"SearchPayment\" class=\"buttons\"></td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td class=\"headline\">Password : </td>                                  \n");
      out.write("                            <td class=\"valueFields\"><input type=\"password\" name=\"txtPass\" id=\"txtPass\" class=\"fields\" readonly=\"readonly\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${logObj.getPass()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></td>\n");
      out.write("                            <td><input type=\"submit\" name=\"btnViewPayment\" value=\"ViewPayment\" class=\"buttons\"></td>\n");
      out.write("                        </tr> \n");
      out.write("                        <tr>\n");
      out.write("                            <td class=\"headline\"></td> \n");
      out.write("                            <td class=\"valueFields\"></td>\n");
      out.write("                            <td><input type=\"submit\" name=\"btnCancel\" value=\"Cancel\" class=\"buttons\"></td>  \n");
      out.write("                        </tr>\n");
      out.write("                    </table>                        \n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div id=\"footer\">\n");
      out.write("            <div id=\"footer_para\">\n");
      out.write("                <p>Copyright © 2016 Student Accommodation. All Rights Reserved.</p>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            google.maps.event.addDomListener(window, 'load', function() {\n");
      out.write("\n");
      out.write("                var options = {\n");
      out.write("                    componentRestrictions: {country: \"uk\"}\n");
      out.write("                };\n");
      out.write("\n");
      out.write("                new google.maps.places.Autocomplete(document.getElementById('txtCurrentAdd'), options);\n");
      out.write("\n");
      out.write("            });\n");
      out.write("\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
