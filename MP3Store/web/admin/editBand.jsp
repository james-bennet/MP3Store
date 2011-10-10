<%-- 
    Document   : editBand
    Created on : 08-Oct-2011, 18:29:25
    Author     : james
--%>

<%@page import="MP3Store.Util.AdminLoginHelper"%>
<%@page import="MP3Store.Models.BandStore"%>
<%@page import="MP3Store.Connectors.BandConnector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" type="text/css" href="../style.css" />
        <title>MP3Store Admin Area - Edit a Band</title>
    </head>
    <body>
        <%
            if (session.getAttribute("Username") != null) {
                if (AdminLoginHelper.verifyUsername(session.getAttribute("Username").toString())) {
        %>
        <h1>MP3Store Admin Area - Edit a Band</h1>

        <%
                    BandStore myBand = new BandStore();
                    if (request.getParameter("BandID") != null) {
                        BandConnector myBandConn = new BandConnector();
                        myBand = myBandConn.getBand(Integer.parseInt(request.getParameter("BandID")));
                        out.println("<u><b>Band #</b>" + myBand.getBandID() + "-" + myBand.getBandName() + "</u><br />");
                        out.println("<form name=\"editBand\" action=\"/MP3Store/admin/Band\" method=\"POST\">");
                        out.println("Band Name: <input type=\"text\" name=\"BandName\" value=\"" + myBand.getBandName() + "\"/><br />");
                        out.println("Band Manager: <input type=\"text\" name=\"BandManager\" value=\"" + myBand.getBandManager() + "\"/><br />");
                        out.println("Description:<br /><textarea rows=\"10\" cols=\"30\" name=\"BandDesc\">" + myBand.getBandDesc() + "</textarea><br />");
                        out.println("Musical Genre: <input type=\"text\" name=\"BandGenre\" value=\"" + myBand.getBandGenre() + "\"/><br />");
                        out.println("<input type=\"hidden\" name=\"BandID\" value=\"" + myBand.getBandID() + "\">");
                        out.println("<input type=\"hidden\" name=\"Mode\" value=\"POST\">");
                        out.println("<input type=\"submit\" value=\"Edit Band\" />");
                        out.println("</form>");
                        out.println("<form name=\"delete\" action=\"/MP3Store/admin/Band\" method=\"POST\"><input type=\"hidden\" name=\"BandID\" value=\"" + myBand.getBandID() + "\"><input type=\"hidden\" name=\"Mode\" value=\"DELETE\"><input type=\"submit\" value=\"Delete Band\" /></form>");
                    } else {
                        out.println("<b>Please supply the BandID to edit!");
                    }
                }
            } else {
                out.println("<h3>You must be logged in as an Administrator in order to access this area!</h3>");
            }
            out.println("<u><b><a href=\"/MP3Store/admin/index.jsp\">Back to Administration Homepage</a></b></u>");
        %>
    </body> 
</html>
