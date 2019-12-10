package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class adminLog_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Login Form-Administrator</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"formatLoginAdmin.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>        \n");
      out.write("        <div id=\"main_content\">\n");
      out.write("            <div id=\"title\">\n");
      out.write("                <a href=\"homePage.jsp\"><h1 id=\"main_title\">STUDENT ACCOMMODATION</h1></a>           \n");
      out.write("            </div>\n");
      out.write("            <div id=\"registration_form\" class=\"registration_form\">\n");
      out.write("                <div class=\"page_header\">\n");
      out.write("                    <h2>Log In</h2>\n");
      out.write("                </div>\n");
      out.write("                <div id=\"registration_form_area\">                    \n");
      out.write("                    <div id=\"message_div\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${message}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</div>\n");
      out.write("                    <form method=\"post\" name=\"registration_form\" action=\"gtLg\">                \n");
      out.write("                        <div class=\"control\">                   \n");
      out.write("                            <input id=\"username\" name=\"username\" type=\"email\" placeholder=\"Email\" class=\"field\" value=\"");
 if (request.getParameter("username") == null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("username"));
                                }
      out.write("\">\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"control\">                   \n");
      out.write("                            <input id=\"password\" name=\"password\" type=\"password\" placeholder=\"Password\" class=\"field\" value=\"\">\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"form_action\">\n");
      out.write("                            <button type=\"submit\" name=\"btn_log\" id=\"log_button\" class=\"buttons\">Login</button><br>\n");
      out.write("                            <button type=\"submit\" name=\"btn_cancel\" id=\"cancel_button\" class=\"buttons\">Cancel</button>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"action\">\n");
      out.write("                            <a id=\"account\" href=\"adminReg.jsp\">Need an Account?</a>\n");
      out.write("                            <a id=\"forget_pass\" href=\"mngHm?link=adminResetLink\">Forgot your Password?</a>\n");
      out.write("                        </div>                \n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
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
