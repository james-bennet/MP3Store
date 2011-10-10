/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package MP3Store.Servlets.Admin;

import MP3Store.Connectors.BandConnector;
import MP3Store.Models.BandStore;
import MP3Store.Util.AdminLoginHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author james
 */
public class Band extends HttpServlet {

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
        BandConnector myBandConn = new BandConnector();

        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>MP3Store Admin Area - Bands</title>");
                         out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../style.css\" />");
            out.println("</head>");
            out.println("<body>");

            // Verify user
            HttpSession session = request.getSession();

            if (session.getAttribute("Username") != null) {
                if (AdminLoginHelper.verifyUsername(session.getAttribute("Username").toString())) {

                    out.println("<h1>MP3Store Admin Area - Band Servlet</h1>");

                    // Details for just one Band - TODO: Band not found message
                    if (request.getParameter("BandID") != null) {
                        BandStore BandDetails = myBandConn.getBand(Integer.parseInt(request.getParameter("BandID")));
                        if (BandDetails != null) {
                            out.println("<ul>");
                            out.println("<li><u><b>Band #" + BandDetails.getBandID() + " - " + BandDetails.getBandName() + "</b></u></li>");
                            out.println("<li><b>Band Manager: </b><i>" + BandDetails.getBandManager() + "</i></li>");
                            out.println("<li><b>Band Genre: </b><i>" + BandDetails.getBandGenre() + "</i></li>");
                            out.println("<li><b>Band Description: </b><i>" + BandDetails.getBandDesc() + "</i></li>");
                            out.println("</ul>");
                            out.println("<form name=\"delete\" action=\"/MP3Store/admin/Band\" method=\"POST\"><input type=\"hidden\" name=\"BandID\" value=\"" + BandDetails.getBandID() + "\"><input type=\"hidden\" name=\"Mode\" value=\"DELETE\"><input type=\"submit\" value=\"Delete Band\" /></form>");
                            out.println("<u><b><a href=\"/MP3Store/admin/editBand.jsp?BandID=" + BandDetails.getBandID() + "\">Edit Band</a></b></u><br />");
                        } else {
                            out.println("<h3>No Band found with this ID!</h3>");
                        }
                    } else // Details for all Bands
                    {
                        out.println("<ol>");

                        ArrayList<BandStore> allBandsList = new ArrayList<BandStore>();
                        allBandsList = myBandConn.getAllBands();

                        if (!allBandsList.isEmpty()) {
// Display all Bands using forach
                            for (BandStore i : allBandsList) {
                                out.println("<li>");
                                out.println("<ul>");
                                out.println("<li><u><a href=\"/MP3Store/admin/Band?BandID=" + i.getBandID() + "\"> <b>Band #</b>" + i.getBandID() + " - " + i.getBandName() + "</u></a></li>");
                                out.println("<li><b>" + i.getBandName() + "</b></li>");
                                out.println("<li><b>Band Manager: </b><i>" + i.getBandManager() + "</i></li>");
                                out.println("<li><b>Band Genre: </b><i>" + i.getBandGenre() + "</i></li>");
                                out.println("<li><B>Band Description: </b><i>" + i.getBandDesc() + "</i></li>");
                                out.println("</ul>");
                                out.println("</li>");
                            }
                            out.println("</ol>");
                        } else {
                            out.println("<h3>No Bands found!</h3>");
                        }
                        out.println("<u><b><a href=\"/MP3Store/admin/addBand.jsp\">Add Band</a></b></u><br />");
                    }
                    out.println("<u><b><a href=\"/MP3Store/admin/index.jsp\">Back to Administration Homepage</a></b></u>");
                } else {
                    out.println("<h3>Authentication Error!</h3>");
                }
            } else {
                out.println("<h3>Please <a href=\"/MP3Store/admin/index.jsp\">Login!</a></h3>");
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
            out.println("<title>MP3Store Admin Area -Bands</title>");
                         out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../style.css\" />");
            out.println("</head>");
            out.println("<body>");

            // Verify user
            HttpSession session = request.getSession();

            if (session.getAttribute("Username") != null) {
                if (AdminLoginHelper.verifyUsername(session.getAttribute("Username").toString())) {

                    out.println("<h1>MP3Store Admin Area - Band Servlet at: " + request.getContextPath() + "</h1>");

                    if (request.getParameter("Mode") != null) {
                        if (request.getParameter("Mode").equalsIgnoreCase("DELETE")) {
                            out.println("<b>Deleting Band.....</b>");
                            doDelete(request, response);
                        } else if (request.getParameter("Mode").equalsIgnoreCase("PUT")) {
                            out.println("<b>Adding New Band.....</b>");
                            doPut(request, response);
                        } else {
                            if (request.getParameter("BandID") != null && request.getParameter("BandManager") != null && request.getParameter("BandName") != null && request.getParameter("BandDesc") != null && request.getParameter("BandGenre") != null) {
                                BandConnector myBandConn = new BandConnector();
                                BandStore editedBand = new BandStore();
                                editedBand.setBandID(Integer.parseInt(request.getParameter("BandID")));
                                editedBand.setBandManager(request.getParameter("BandManager"));
                                editedBand.setBandName(request.getParameter("BandName"));
                                editedBand.setBandDesc(request.getParameter("BandDesc"));
                                editedBand.setBandDesc(request.getParameter("BandGenre"));
                                out.println("<b>Updating Band Details....</b>");
                                myBandConn.updateBand(editedBand);
                                out.println("<b>Band Updated!</b>");
                            }
                        }

                    }
                    out.println("<u><b><a href=\"/MP3Store/admin/index.jsp\">Back to Administration Homepage</a></b></u>");
                } else {
                    out.println("<h3>Authentication Error!</h3>");
                }
            } else {
                out.println("<h3>Please <a href=\"/MP3Store/admin/index.jsp\">Login!</a></h3>");
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
            out.println("<title>MP3Store Admin Area -Bands</title>");
                         out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../style.css\" />");
            out.println("</head>");
            out.println("<body>");

            // Verify user
            HttpSession session = request.getSession();

            if (session.getAttribute("Username") != null) {
                if (AdminLoginHelper.verifyUsername(session.getAttribute("Username").toString())) {

                    out.println("<h1>MP3Store Admin Area - Band Servlet at: " + request.getContextPath() + "</h1>");

                    BandConnector myBandConn = new BandConnector();
                    if (request.getParameter("BandManager") != null && request.getParameter("BandName") != null && request.getParameter("BandDesc") != null && request.getParameter("BandGenre") != null) {
                        myBandConn.insertBand(new BandStore(0, request.getParameter("BandManager"), request.getParameter("BandName"),request.getParameter("BandDesc"),Integer.parseInt(request.getParameter("BandGenre"))));
                        out.println("<b>Band Added!</b>");
                    }
                } else {
                    out.println("<h3>Authentication Error!</h3>");
                }
            } else {
                out.println("<h3>Please <a href=\"/MP3Store/admin/index.jsp\">Login!</a></h3>");
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
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>MP3Store Admin Area -Bands</title>");
                         out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../style.css\" />");
            out.println("</head>");
            out.println("<body>");

            // Verify user
            HttpSession session = request.getSession();

            if (session.getAttribute("Username") != null) {
                if (AdminLoginHelper.verifyUsername(session.getAttribute("Username").toString())) {


                    out.println("<h1>MP3Store Admin Area - Band Servlet at: " + request.getContextPath() + "</h1>");

                    BandConnector myBandConn = new BandConnector();
                    if (request.getParameter("BandID") != null) {
                        myBandConn.deleteBand(Integer.parseInt(request.getParameter("BandID")));
                        out.println("<b>Band Deleted!</b>");
                    }
                } else {
                    out.println("<h3>Authentication Error!</h3>");
                }
            } else {
                out.println("<h3>Please <a href=\"/MP3Store/admin/index.jsp\">Login!</a></h3>");
            }
        } catch (Exception e) {
            out.println("<h3>Sorry, there was an error!</h3>");
            System.out.println("Exception in doDelete()" + e.toString());
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
