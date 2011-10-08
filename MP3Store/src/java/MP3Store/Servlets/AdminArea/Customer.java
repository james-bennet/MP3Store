/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MP3Store.Servlets.AdminArea;

import MP3Store.Connectors.CustomerConnector;
import MP3Store.Models.CustomerStore;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author james
 */
public class Customer extends HttpServlet {

    /** 
     * Handles the HTTP <code>GET</code> method.
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
            out.println("<title>Servlet Customer</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Customer at " + request.getContextPath () + "</h1>");
            
            // Details for just one customer
                    if (request.getParameter("CustomerID") != null)
        {
            CustomerStore customerDetails = myCustConn.getCustomer(Integer.parseInt(request.getParameter("CustomerID")));
                out.println("<ul>");
    out.println("<li><u><b>Customer #</b>" + customerDetails.getCustomerID() + "</u></li>");
    out.println("<li><b>" + customerDetails.getCustomerTitle() + " " + customerDetails.getCustomerForename() + " " + customerDetails.getCustomerSurname() + "</b></li>");
    out.println("<li><b>Email / Password: </b><u>" + customerDetails.getCustomerEmail() + "</u>, '" + customerDetails.getPassword() + "'</li>");
    out.println("<li><b>Membership type: </b>" + customerDetails.getMembershipType()+ ". <b>Verified: </b>" + customerDetails.getVerified() + "</li>");
    out.println("<li><b>Address: </b><i>" + customerDetails.getCustomerAddress() + "</i></li>");
    out.println("</ul>");
out.println("<form name=\"delete\" action=\"/MP3Store/admin/Customer\" method=\"POST\"><input type=\"hidden\" name=\"CustomerID\" value=\"" + customerDetails.getCustomerID()  +"\"><input type=\"hidden\" name=\"Mode\" value=\"DELETE\"><input type=\"submit\" value=\"Delete Customer\" /></form>");
        }
                    else // Details for all customers
                    {
            out.println("<ol>");

ArrayList <CustomerStore> allCustsList = new ArrayList<CustomerStore>();
allCustsList = myCustConn.getAllCustomers();

// Display all cistomers using forach
for (CustomerStore i : allCustsList)
{
     out.println("<li>");
    out.println("<ul>");
    out.println("<li><u><a href=\"/MP3Store/admin/Customer?CustomerID=" + i.getCustomerID() + "\"> <b>Customer #</b>" + i.getCustomerID() + "</u></a></li>");
    out.println("<li><b>" + i.getCustomerTitle() + " " + i.getCustomerForename() + " " + i.getCustomerSurname() + "</b></li>");
    out.println("<li><b>Email / Password: </b><u>" + i.getCustomerEmail() + "</u>, '" + i.getPassword() + "'</li>");
    out.println("<li><b>Membership type: </b>" + i.getMembershipType()+ ". <b>Verified: </b>" + i.getVerified() + "</li>");
    out.println("<li><B>Address: </b><i>" + i.getCustomerAddress() + "</i></li>");
    out.println("</ul>");
     out.println("</li>");
}
            out.println("</ol>");
                    }
            out.println("</body>");
            out.println("</html>");
        }
        catch (Exception e)
         {
             System.out.println("Exception in doGet()" + e.toString());
         }
        finally {            
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
        try
        {
        if (request.getParameter("Mode") != null)
        {
            if(request.getParameter("Mode").equalsIgnoreCase("DELETE"))
            {
                doDelete(request,response);
            }
            else if (request.getParameter("Mode").equalsIgnoreCase("PUT"))
            {
                doPut(request,response);
            }
            else
            {
                // TODO: Editing functionality
            }
        }
                }
        catch (Exception e)
         {
             System.out.println("Exception in doPost()" + e.toString());
         }
        finally {            
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
try
{
    //  Process customer registration form. TODO: DANGER! - More validation!
     CustomerConnector myCustConn = new CustomerConnector();
      if (request.getParameter("Title") != null && request.getParameter("Forename") != null && request.getParameter("Surname") != null && request.getParameter("Adddress") != null && request.getParameter("Email") != null && request.getParameter("Password") != null)
      {
      myCustConn.insertCustomer(new CustomerStore(0,request.getParameter("Forename"),request.getParameter("Surname"),request.getParameter("Title"), request.getParameter("Email"), request.getParameter("Adddress"),null,false,0,request.getParameter("Password")));
      }
        }
        catch (Exception e)
         {
             System.out.println("Exception in doPut()" + e.toString());
         }
        finally {            
            out.close();
        }
    }
    
        /** 
     * Handles the HTTP <code>DELETE</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
      CustomerConnector myCustConn = new CustomerConnector();
      if (request.getParameter("CustomerID") != null)
      {
      myCustConn.deleteCustomer(Integer.parseInt(request.getParameter("CustomerID")));
      }
              }
        catch (Exception e)
         {
             System.out.println("Exception in doDelete()" + e.toString());
         }
        finally {            
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
