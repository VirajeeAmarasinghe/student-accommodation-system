<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.DataInputStream"%>
<%@page import="java.sql.*" %>
<html>

    <head>
        <style type="text/css">
            body{
                background-color:#d0e4fe;
            }
        </style>    
    </head>

    <body>
        
        <form method="post" action="testFile" onsubmit="txtvalidate()" enctype=multipart/form-data>
            <table>
                <tr>
                    <td>
                        Customer Name&nbsp;<input type="text" name="Customername" id="customername">
                    </td>
                </tr>
                <tr>
                    <td>
                        Customer Mob: <input type="text" onkeypress="return isNumberKey(event)" name="Customerphone" id="customerphone" >
                    </td>
                </tr>
                <tr>
                    <td>
                        Upload File:&nbsp;&nbsp;<input type="file" id="f" name="f" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="SUBMIT" id="submit">
                    </td>
                </tr>



            </table>
        </form>
        

        
    </body>

</html>