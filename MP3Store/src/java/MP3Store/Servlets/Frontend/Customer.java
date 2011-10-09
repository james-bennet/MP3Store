/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package MP3Store.Servlets.Frontend;

import MP3Store.Connectors.CustomerConnector;
import MP3Store.Models.CustomerStore;
import MP3Store.Util.CustomerLoginHelper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author james
 */
public class Customer extends HttpServlet {

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        CustomerConnector myCustConn = new CustomerConnector();

        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>MP3Store Customer Area - Your Customer Profile</title>");
             out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../style.css\" />");
            out.println("</head>");
            out.println("<body>");

            // Verify user
            HttpSession session = request.getSession();

            if (session.getAttribute("Username") != null) {
                if (CustomerLoginHelper.verifyUsername(session.getAttribute("Username").toString())) {

                    out.println("<h1>MP3Store Customer Area -  Your Customer Profile</h1>");

                    // Details for just you. Secure?
                        CustomerStore customerDetails = myCustConn.getCustomer(session.getAttribute("Username").toString());
                        if (customerDetails != null) {
                            out.println("<ul>");
                            out.println("<li><u><b>Customer #</b>" + customerDetails.getUsername() + "</u></li>");
                            out.println("<li><b>" + customerDetails.getCustomerTitle() + " " + customerDetails.getCustomerForename() + " " + customerDetails.getCustomerSurname() + "</b></li>");
                            out.println("<li><b>Email / Password: </b><u>" + customerDetails.getCustomerEmail() + "</u>, '" + customerDetails.getPassword() + "'</li>");
                            out.println("<li><b>Membership type: </b>" + customerDetails.getMembershipType() + ". <b>Verified: </b>" + customerDetails.getVerified() + "</li>");
                            out.println("<li><b>Address: </b><i>" + customerDetails.getCustomerAddress() + "</i></li>");
                            out.println("</ul>");
                            // TODO: Edit/delete own profile?
                        } else {
                            out.println("<h3>Error loading your profile!</h3>");
                        }
                    out.println("<u><b><a href=\"/MP3Store/index.jsp\">Back to Global Homepage</a></b></u>");
                } else {
                    out.println("<h3>Authentication Error!</h3>");
                }
            } else {
                out.println("<h3>Please <a href=\"/MP3Store/frontend/index.jsp\">Login!</a></h3>");
            }
        } catch (Exception e) {
            out.println("<h3>Sorry, there was an error!</h3>");
            System.out.println("Exception in doGet()" + e.toString());
        } finally {
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>MP3Store Customer Area - Registration</title>");
            out.println("</head>");
            out.println("<body>");

                    if (request.getParameter("Mode") != null) {
                        if (request.getParameter("Mode").equalsIgnoreCase("PUT")) {
                            doPut(request, response);
                        }
                    }

        } catch (Exception e) {
            out.println("<h3>Sorry, there was an error!</h3>");
            System.out.println("Exception in doPost()" + e.toString());
        } finally {
            out.println("</body>");
            out.println("</html>");
            out.close();
        }

    }

    /** 
     * Handles the HTTP <code>PUT</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>MP3Store Customer Area - Registration</title>");
            out.println("</head>");
            out.println("<body>");

                    //  Process customer registration form. TODO: DANGER! - More validation!
                    CustomerConnector myCustConn = new CustomerConnector();
                    if (request.getParameter("Username") != null && request.getParameter("Title") != null && request.getParameter("Forename") != null && request.getParameter("Surname") != null && request.getParameter("Adddress") != null && request.getParameter("Email") != null && request.getParameter("Password") != null) {
                        myCustConn.insertCustomer(new CustomerStore(request.getParameter("Username"), request.getParameter("Forename"), request.getParameter("Surname"), request.getParameter("Title"), request.getParameter("Email"), request.getParameter("Adddress"), null, false, 0, request.getParameter("Password")));
                                    // Verify user
            HttpSession session = request.getSession();
            session.setAttribute("Username", request.getParameter("Username"));
                        out.println("<h3>Registration Successful!</h3>");
                            response.sendRedirect("/MP3Store/frontend/index.jsp");
                    }
        } catch (Exception e) {
            out.println("<h3>Sorry, there was an error!</h3>");
            System.out.println("Exception in doPut()" + e.toString());
        } finally {
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
