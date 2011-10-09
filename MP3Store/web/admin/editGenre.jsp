<%-- 
    Document   : editGenre
    Created on : 08-Oct-2011, 18:29:25
    Author     : james
--%>

<%@page import="MP3Store.Models.GenreStore"%>
<%@page import="MP3Store.Connectors.GenreConnector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MP3Store Admin Area - Edit a Genre</title>
    </head>
    <body>
        <h1>MP3Store Admin Area- Edit a Genre</h1>

        <%
            GenreStore myGenre = new GenreStore();
            if (request.getParameter("GenreID") != null) {
                GenreConnector myGenreConn = new GenreConnector();
                myGenre = myGenreConn.getGenre(Integer.parseInt(request.getParameter("GenreID")));
                out.println("<u><b>Genre #</b>" + myGenre.getGenreID() + "-" + myGenre.getGenreName() + "</u><br />");
                out.println("<form name=\"editGenre\" action=\"/MP3Store/admin/Genre\" method=\"POST\">");
                out.println("Name: <input type=\"text\" name=\"GenreName\" value=\"" + myGenre.getGenreName() + "\"/><br />");
                out.println("Description:<br /><textarea rows=\"10\" cols=\"30\" name=\"GenreDesc\">" + myGenre.getGenreDesc() + "</textarea><br />");
                out.println("<input type=\"hidden\" name=\"GenreID\" value=\"" + myGenre.getGenreID() +"\">");
                out.println("<input type=\"hidden\" name=\"Mode\" value=\"POST\">");
                out.println("<input type=\"submit\" value=\"Edit Genre\" />");
                out.println("</form>");
                out.println("<form name=\"delete\" action=\"/MP3Store/admin/Genre\" method=\"POST\"><input type=\"hidden\" name=\"GenreID\" value=\"" + myGenre.getGenreID()  +"\"><input type=\"hidden\" name=\"Mode\" value=\"DELETE\"><input type=\"submit\" value=\"Delete Genre\" /></form>");
            } else {
                out.println("<b>Please supply the GenreID to edit!");
            }
        %>
    </body> 
</html>
