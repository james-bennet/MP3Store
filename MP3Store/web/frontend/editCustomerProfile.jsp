<%-- 
    Document   : editCustomer
    Created on : 08-Oct-2011, 18:29:25
    Author     : james
--%>

<%@page import="MP3Store.Util.CustomerLoginHelper"%>
<%@page import="MP3Store.Models.CustomerStore"%>
<%@page import="MP3Store.Connectors.CustomerConnector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" type="text/css" href="../style.css" />
        <title>MP3Store Admin Area - Edit a customer</title>
    </head>
    <body>
        <%
            if (session.getAttribute("Username") != null) {
                if (CustomerLoginHelper.verifyUsername(session.getAttribute("Username").toString())) {
        %>
        <h1>MP3Store Admin Area- Edit a customer</h1>

        <%
                    CustomerStore myCust = new CustomerStore();
                    if (session.getAttribute("Username") != null) {
                        CustomerConnector myCustConn = new CustomerConnector();
                        myCust = myCustConn.getCustomer(session.getAttribute("Username").toString());
                        out.println("<u><b>Customer #</b>" + myCust.getUsername() + "</u><br />");
                        out.println("<b>" + myCust.getCustomerTitle() + " " + myCust.getCustomerForename() + " " + myCust.getCustomerSurname() + "</b><br />");

                        out.println("<form name=\"editCustomer\" action=\"/MP3Store/frontend/Customer\" method=\"POST\">");
                        out.println("Title: <input type=\"text\" name=\"Title\" value=\"" + myCust.getCustomerTitle() + "\"/><br />");
                        out.println("Forename: <input type=\"text\" name=\"Forename\" value=\"" + myCust.getCustomerForename() + "\"/><br />");
                        out.println("Surname: <input type=\"text\" name=\"Surname\" value=\"" + myCust.getCustomerSurname() + "\"/><br />");
                        out.println("Street Address:<br /><textarea rows=\"10\" cols=\"30\" name=\"Adddress\">" + myCust.getCustomerAddress() + "</textarea><br />");
                        out.println("Email Address: <input type=\"text\" name=\"Email\" value=\"" + myCust.getCustomerEmail() + "\"/><br />");
                        // TODO: double type password
                        out.println("New Password: <input type=\"password\" name=\"Password\"\"/><br />");
                        // TODO: Allow upgrading membership type
                        out.println("<br />");
                        out.println("<input type=\"hidden\" name=\"Username\" value=\"" + myCust.getUsername() + "\">");
                        out.println("<input type=\"hidden\" name=\"Mode\" value=\"POST\">");
                        out.println("<input type=\"submit\" value=\"Edit Customer\" />");
                        out.println("</form>");
                        out.println("<form name=\"delete\" action=\"/MP3Store/frontend/Customer\" method=\"POST\"><input type=\"hidden\" name=\"Username\" value=\"" + myCust.getUsername() + "\"><input type=\"hidden\" name=\"Mode\" value=\"DELETE\"><input type=\"submit\" value=\"Delete Customer\" /></form>");
                    } else {
                        out.println("<b>Please supply the Username to edit!");
                    }
                }
            } else {
                out.println("<h3>You must be logged in as an Administrator in order to access this area!</h3>");
            }
            out.println("<u><b><a href=\"/MP3Store/admin/index.jsp\">Back to Administration Homepage</a></b></u>");
        %>
    </body> 
</html>
