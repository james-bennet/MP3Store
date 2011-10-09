<%-- 
    Document   : editMembershipType
    Created on : 08-Oct-2011, 18:29:25
    Author     : james
--%>

<%@page import="MP3Store.Util.AdminLoginHelper"%>
<%@page import="MP3Store.Models.MembershipTypeStore"%>
<%@page import="MP3Store.Connectors.MembershipTypeConnector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" type="text/css" href="../style.css" />
        <title>MP3Store Admin Area - Edit a MembershipType</title>
    </head>
    <body>
        <%
            if (session.getAttribute("Username") != null) {
                if (AdminLoginHelper.verifyUsername(session.getAttribute("Username").toString())) {
        %>
        <h1>MP3Store Admin Area- Edit a MembershipType</h1>

        <%
                    MembershipTypeStore myMembershipType = new MembershipTypeStore();
                    if (request.getParameter("MembershipTypeID") != null) {
                        MembershipTypeConnector myMembershipTypeConn = new MembershipTypeConnector();
                        myMembershipType = myMembershipTypeConn.getMembershipType(Integer.parseInt(request.getParameter("MembershipTypeID")));
                        out.println("<u><b>MembershipType #</b>" + myMembershipType.getMembershipTypeID() + "-" + myMembershipType.getMembershipName() + "</u><br />");
                        out.println("<form name=\"editMembershipType\" action=\"/MP3Store/admin/MembershipType\" method=\"POST\">");
                        out.println("Name: <input type=\"text\" name=\"MembershipName\" value=\"" + myMembershipType.getMembershipName() + "\"/><br />");
                        out.println("Description:<br /><textarea rows=\"10\" cols=\"30\" name=\"MembershipTypeDesc\">" + myMembershipType.getMembershipTypeDesc() + "</textarea><br />");
                        
            out.println("Can Download: <input type=\"checkbox\" name=\"CanDownload\" value=\"CanDownload\"");
                               if (myMembershipType.getCanDownload()) {
                            out.println("checked=\"yes\"/>");
                        }
            else
                               {
                out.println("/>");
                               }
            out.println("Can Re-Download: <input type=\"checkbox\" name=\"CanRedownload\" value=\"CanRedownload\"");
                               if (myMembershipType.getCanRedownload()) {
                            out.println("checked=\"yes\"/>");
                        }
                        else
                               {
                out.println("/>");
                               }
            out.println("Can Upload: <input type=\"checkbox\" name=\"CanUpload\" value=\"CanUpload\"");
                                       if (myMembershipType.getCanUpload()) {
                            out.println("checked=\"yes\"/>");
                        }
                        else
                               {
                out.println("/>");
                               }
            out.println("Can Download Unlimited Tracks: <input type=\"checkbox\" name=\"CanDownloadUnlimited\" value=\"CanDownloadUnlimited\"");
                                       if (myMembershipType.getCanDownloadUnlimited()) {
                            out.println("checked=\"yes\"/>");
                        }
                                    else
                               {
                out.println("/>");
                               }
            
                        out.println("<input type=\"hidden\" name=\"MembershipTypeID\" value=\"" + myMembershipType.getMembershipTypeID() + "\">");
                        out.println("<input type=\"hidden\" name=\"Mode\" value=\"POST\">");
                        out.println("<input type=\"submit\" value=\"Edit MembershipType\" />");
                        out.println("</form>");
                        out.println("<form name=\"delete\" action=\"/MP3Store/admin/MembershipType\" method=\"POST\"><input type=\"hidden\" name=\"MembershipTypeID\" value=\"" + myMembershipType.getMembershipTypeID() + "\"><input type=\"hidden\" name=\"Mode\" value=\"DELETE\"><input type=\"submit\" value=\"Delete MembershipType\" /></form>");
                    } else {
                        out.println("<b>Please supply the MembershipTypeID to edit!");
                    }
                }
            } else {
                out.println("<h3>You must be logged in as an Administrator in order to access this area!</h3>");
            }
            out.println("<u><b><a href=\"/MP3Store/admin/index.jsp\">Back to Administration Homepage</a></b></u>");
        %>
    </body> 
</html>
