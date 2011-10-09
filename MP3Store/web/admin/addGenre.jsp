<%-- 
    Document   : addGenre
    Created on : 08-Oct-2011, 18:29:25
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MP3Store Admin Area - Add a Genre</title>
    </head>
    <body>
        <h1>MP3Store Admin Area- Add a Genre</h1>
        <form name="addGenre" action="/MP3Store/admin/Genre" method="POST">
            Name <input type="text" name="GenreName"><br />
            Description:<br /><textarea rows="10" cols="30" name="GenreDesc"></textarea><br />
            <input type="hidden" name="Mode" value="PUT">
            <input type="submit" value="Add New Genre" />
        </form> 
    </body> 
</html>
