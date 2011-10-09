<%-- 
    Document   : editCustomer
    Created on : 08-Oct-2011, 18:29:25
    Author     : james
--%>

<%@page import="MP3Store.Util.AdminLoginHelper"%>
<%@page import="MP3Store.Models.CustomerStore"%>
<%@page import="MP3Store.Connectors.CustomerConnector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MP3Store Admin Area - Edit a customer</title>
    </head>
    <body>
        <%
            if (session.getAttribute("Username") != null) {
                if (AdminLoginHelper.verifyUsername(session.getAttribute("Username").toString())) {
        %>
        <h1>MP3Store Admin Area- Edit a customer</h1>

        <%
                    CustomerStore myCust = new CustomerStore();
                    if (request.getParameter("CustomerID") != null) {
                        CustomerConnector myCustConn = new CustomerConnector();
                        myCust = myCustConn.getCustomer(Integer.parseInt(request.getParameter("CustomerID")));
                        out.println("<u><b>Customer #</b>" + myCust.getCustomerID() + "</u><br />");
                        out.println("<b>" + myCust.getCustomerTitle() + " " + myCust.getCustomerForename() + " " + myCust.getCustomerSurname() + "</b><br />");

                        out.println("<form name=\"editCustomer\" action=\"/MP3Store/admin/Customer\" method=\"POST\">");
                        out.println("Title: <input type=\"text\" name=\"Title\" value=\"" + myCust.getCustomerTitle() + "\"/><br />");
                        out.println("Forename: <input type=\"text\" name=\"Forename\" value=\"" + myCust.getCustomerForename() + "\"/><br />");
                        out.println("Surname: <input type=\"text\" name=\"Surname\" value=\"" + myCust.getCustomerSurname() + "\"/><br />");
                        out.println("Street Address:<br /><textarea rows=\"10\" cols=\"30\" name=\"Adddress\">" + myCust.getCustomerAddress() + "</textarea><br />");
                        out.println("Email Address: <input type=\"text\" name=\"Email\" value=\"" + myCust.getCustomerEmail() + "\"/><br />");
                        out.println("New Password: <input type=\"password\" name=\"Password\"\"/><br />");
                        // Fields below are TODO
                        out.println("Membership Type: <input type=\"text\" name=\"MembershipType\" value=\"" + myCust.getMembershipType() + "\"/><br />");
                        out.println("Verified <input type=\"checkbox\" name=\"Verified\" value=\"Verified\"");

                        if (myCust.getVerified()) {
                            out.println("checked=\"yes\"");
                        }
                        out.println("/><br />");
                        out.println("<input type=\"hidden\" name=\"CustomerID\" value=\"" + myCust.getCustomerID() + "\">");
                        out.println("<input type=\"hidden\" name=\"Mode\" value=\"POST\">");
                        out.println("<input type=\"submit\" value=\"Edit Customer\" />");
                        out.println("</form>");
                        out.println("<form name=\"delete\" action=\"/MP3Store/admin/Customer\" method=\"POST\"><input type=\"hidden\" name=\"CustomerID\" value=\"" + myCust.getCustomerID() + "\"><input type=\"hidden\" name=\"Mode\" value=\"DELETE\"><input type=\"submit\" value=\"Delete Customer\" /></form>");
                    } else {
                        out.println("<b>Please supply the CustomerID to edit!");
                    }
                }
            } else {
                out.println("<h3>You must be logged in as an Administrator in order to access this area!</h3>");
            }
            out.println("<u><b><a href=\"/MP3Store/admin/index.jsp\">Back to Administration Homepage</a></b></u>");
        %>
    </body> 
</html>
