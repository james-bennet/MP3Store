<%-- 
    Document   : customerLogin
    Created on : 09-Oct-2011, 15:06:53
    Author     : james
--%>

<%@page import="MP3Store.Util.CustomerLoginHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MP3Store Customer Home</title>
    </head>
    <body>
             <%

            if (request.getParameter("logout") != null) {
                session.invalidate(); // end session   
                response.sendRedirect("/MP3Store/frontend/index.jsp");
            } else {
                if (session.getAttribute("Username") == null) {

                    out.println("<h3>You must be logged in with your Customer Login in order to access this area!</h3>");
                    if (request.getParameter("Username") != null && request.getParameter("Password") != null) {
                        // Process logon
                        boolean success = CustomerLoginHelper.verifyPassword(request.getParameter("Username"), request.getParameter("Password"));
                        if (success) {

                            session.setAttribute("Username", request.getParameter("Username"));
                            out.println("<h3>Login OK!</h3>");
                            response.sendRedirect("/MP3Store/frontend/index.jsp");
                        } else {
                            out.println("<h3>Incorrect Username/Password!</h3>");
                        }
                    } else {
                        out.println("<h3>Please login below:</h3>");
        %>
        <form name="CustomerLogin" action="/MP3Store/frontend/index.jsp" method="POST">
            Username: <input type="text" name="Username"><br />
            Password: <input type="password" name="Password"><br />
            <input type="submit" value="Login" />
        </form> 

        <%
          out.println("<u><b><a href=\"/MP3Store/frontend/register.jsp\">Dont Have An Account? Click here to register one!</a></b></u>");
            }
        } else {
            if (CustomerLoginHelper.verifyUsername(session.getAttribute("Username").toString())) {
        %>

        <h1>MP3Store Customer Home</h1>
<form name="CustomerLogout" action="/MP3Store/frontend/index.jsp">
    <input type="hidden" name="logout"><br />
    <input type="submit" value="Logout" />
</form> 
<%            } else {
                out.println("<h3>Incorrect Username/Password!</h3>");
            }

        }
    }
%>
    </body>
</html>
