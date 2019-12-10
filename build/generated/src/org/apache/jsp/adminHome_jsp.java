package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class adminHome_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Admin Home Page</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"formatAdminHome.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"top_stripe\">\n");
      out.write("            <div id=\"homePage\">\n");
      out.write("                <a href=\"homePage.jsp\"><img src=\"home.png\" alt=\"Home button\" width=\"40\" height=\"40\"></a>\n");
      out.write("            </div>            \n");
      out.write("            <div id=\"registration_nav\">\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"adminReg.jsp\">REGISTER</a>                        \n");
      out.write("                    </li>\n");
      out.write("                    ");

                        if (session != null) {
                            if (session.getAttribute("user") != null) {
                                out.print("<li><a href='mngHm?link=logoutLink'>LOG OUT</a></li>");
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
      out.write("                    <li><a href=\"adminHome.jsp\" class=\"home\">HOME</a></li>\n");
      out.write("                    <li><a href=\"findLandlord.jsp\">LANDLORD</a></li>\n");
      out.write("                    <li><a href=\"findStudent.jsp\">STUDENT</a></li>\n");
      out.write("                    <li><a href=\"findPropertyByAdmin.jsp\">PROPERTY</a></li>\n");
      out.write("                    <li><a href=\"payment.jsp\">PAYMENT</a></li>\n");
      out.write("                        ");

                            out.print("<li><a href='#'>REPORTS</a>");
                            out.print("<ul class='dropdown'>");
                            out.print("<li><a href='propertyAvailabilityReport.jsp'>PROPERTY AVAILABILITY REPORT</a></li>");
                            out.print("<li><a href='occupiedDetailsReport.jsp'>OCCUPIED DETAILS REPORT</a></li>");
                            out.print("<li><a href='propertyTypeReport.jsp'>PROPERTY TYPE REPORT</a></li>");
                            out.print("<li><a href='monthlyRentReport.jsp'>MONTHLY RENT REPORT</a></li>");
                            out.print("</li>");
                        
      out.write("\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("             ");
      out.write("\n");
      out.write("        <div id=\"top_banner\">           \n");
      out.write("            <img src=\"top banner-admin.jpeg\" alt=\"top_banner\" height=\"500\">        \n");
      out.write("        </div>\n");
      out.write("        <div id=\"footer\">\n");
      out.write("            <div id=\"footer_para\">\n");
      out.write("                <p>Copyright © 2016 Student Accommodation. All Rights Reserved.</p>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
