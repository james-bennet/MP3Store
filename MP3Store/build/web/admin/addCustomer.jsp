<%-- 
    Document   : addCustomer
    Created on : 08-Oct-2011, 18:29:25
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MP3Store Admin Area - Add a customer</title>
    </head>
    <body>
        <h1>MP3Store Admin Area- Add a customer</h1>
        <form name="addCustomer" action="/MP3Store/admin/Customer" method="POST">
Title: <input type="text" name="Title"><br />
Forename: <input type="text" name="Forename" /><br />
Surname: <input type="text" name="Surname" /><br />
Street Address:<br /><textarea rows="10" cols="30" name="Adddress"></textarea><br />
Email Address: <input type="text" name="Email" /><br />
Password: <input type="password" name="Password" /><br />
<input type="hidden" name="Mode" value="PUT">
<input type="submit" value="Add New Customer" />
</form> 
    </body> 
</html>
