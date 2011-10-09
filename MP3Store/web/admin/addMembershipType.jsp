<%-- 
    Document   : addMembershipType
    Created on : 08-Oct-2011, 18:29:25
    Author     : james
--%>

<%@page import="MP3Store.Util.AdminLoginHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" type="text/css" href="../style.css" />
        <title>MP3Store Admin Area - Add a MembershipType</title>
    </head>
    <body>
        <%
            if (session.getAttribute("Username") != null) {
                if (AdminLoginHelper.verifyUsername(session.getAttribute("Username").toString())) {
        %>
        <h1>MP3Store Admin Area- Add a MembershipType</h1>
        <form name="addMembershipType" action="/MP3Store/admin/MembershipType" method="POST">
            Name <input type="text" name="MembershipName"><br />
            Description:<br /><textarea rows="10" cols="30" name="MembershipTypeDesc"></textarea><br />
            Can Download: <input type="checkbox" name="CanDownload" value="CanDownload"/>
            Can Re-Download: <input type="checkbox" name="CanRedownload" value="CanRedownload"/>
            Can Upload <input type="checkbox" name="CanUpload" value="CanUpload"/>
            Can Download Unlimited Tracks: <input type="checkbox" name="CanDownloadUnlimited" value="CanDownloadUnlimited"/>
            <input type="hidden" name="Mode" value="PUT">
            <input type="submit" value="Add New MembershipType" />
        </form> 
        <%                }
            } else {
                out.println("<h3>You must be logged in as an Administrator in order to access this area!</h3>");
            }
            out.println("<u><b><a href=\"/MP3Store/admin/index.jsp\">Back to Administration Homepage</a></b></u>");
        %>
    </body> 
</html>
