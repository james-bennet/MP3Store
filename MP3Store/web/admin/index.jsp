<%-- 
    Document   : index
    Created on : 09-Oct-2011, 10:44:14
    Author     : james
--%>

<%@page import="MP3Store.Util.AdminLoginHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MP3Store Admin Area</title>
    </head>
    <body>
        <%
        
        if (request.getParameter("logout") != null)
        {
                    session.invalidate(); // end session   
                    response.sendRedirect("/MP3Store/admin/index.jsp");
        }
        else
                       {
            if (session.getAttribute("Username") == null) {
                
                             out.println("<h3>You must be logged in as an Administrator in order to access this area!</h3>"); 
                if (request.getParameter("Username") != null && request.getParameter("Password") != null)
                {
                    // Process logon
                    boolean success = AdminLoginHelper.verifyPassword(request.getParameter("Username"), request.getParameter("Password"));
                    if (success)
                    {
                                   
                        session.setAttribute("Username",request.getParameter("Username"));
                        out.println("<h3>Login OK!</h3>"); 
                        response.sendRedirect("/MP3Store/admin/index.jsp");
                    }
                    else
                    {
                      out.println("<h3>Incorrect Username/Password!</h3>");                         
                    }
                }
                else
                {
                out.println("<h3>Please login below:</h3>"); 
        %>
            <form name="AdminLogin" action="/MP3Store/admin/index.jsp" method="POST">
            Username: <input type="text" name="Username"><br />
            Password: <input type="password" name="Password"><br />
            <input type="submit" value="Login" />
        </form> 
        
        <%
               } 
                             }
                else {
                if (AdminLoginHelper.verifyUsername(session.getAttribute("Username").toString())) {
        %>

        <h1>MP3Store Admin Area</h1>
        <ul>
            <li><u><b><a href="/MP3Store/admin/Customer">Customer Administration</a></b></u> </li>
        <li><u><b><a href="/MP3Store/admin/Genre">Add/Edit Music Genres</a></b></u> </li>
            <form name="AdminLogin" action="/MP3Store/admin/index.jsp">
            <input type="hidden" name="logout"><br />
            <input type="submit" value="Logout" />
        </form> 
</ul>
<%                
    }
                    else
                    {
                      out.println("<h3>Incorrect Username/Password!</h3>");                         
                    }                
  
    }
        }
%>
</body>
</html>
