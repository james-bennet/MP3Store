/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package MP3Store.Servlets.Admin;

import MP3Store.Connectors.MembershipTypeConnector;
import MP3Store.Models.MembershipTypeStore;
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
public class MembershipType extends HttpServlet {

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
        MembershipTypeConnector myMembershipTypeConn = new MembershipTypeConnector();

        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>MP3Store Admin Area -MembershipTypes</title>");
            out.println("</head>");
            out.println("<body>");

            // Verify user
            HttpSession session = request.getSession();

            if (session.getAttribute("Username") != null) {
                if (AdminLoginHelper.verifyUsername(session.getAttribute("Username").toString())) {

                    out.println("<h1>MP3Store Admin Area - MembershipType Servlet</h1>");

                    if (request.getParameter("MembershipTypeID") != null) {
                        MembershipTypeStore MembershipTypeDetails = myMembershipTypeConn.getMembershipType(Integer.parseInt(request.getParameter("MembershipTypeID")));
                        if (MembershipTypeDetails != null) {
                            out.println("<ul>");
                            out.println("<li><u><b>Membership Type #" + MembershipTypeDetails.getMembershipTypeID() + " - " + MembershipTypeDetails.getMembershipName() + "</b></u></li>");
                            out.println("<li><b>Membership Description: </b><i>" + MembershipTypeDetails.getMembershipTypeDesc() + "</i></li>");
                            out.println("<li><B>Can Download? </b><i>" + MembershipTypeDetails.getCanDownload() + "</i><B> Can Re-Download? </b><i>" + MembershipTypeDetails.getCanRedownload() + "</i><B> Can Upload? </b><i>" + MembershipTypeDetails.getCanUpload() + "</i><B> Can Download Unlimited Tracks? </b><i>" + MembershipTypeDetails.getCanDownloadUnlimited() + "</i></li>");
                            out.println("</ul>");
                            out.println("<form name=\"delete\" action=\"/MP3Store/admin/MembershipType\" method=\"POST\"><input type=\"hidden\" name=\"MembershipTypeID\" value=\"" + MembershipTypeDetails.getMembershipTypeID() + "\"><input type=\"hidden\" name=\"Mode\" value=\"DELETE\"><input type=\"submit\" value=\"Delete Membership Type\" /></form>");
                            out.println("<u><b><a href=\"/MP3Store/admin/editMembershipType.jsp?MembershipTypeID=" + MembershipTypeDetails.getMembershipTypeID() + "\">Edit Membership Type</a></b></u><br />");
                        } else {
                            out.println("<h3>No MembershipType found with this ID!</h3>");
                        }
                    } else // Details for all MembershipTypes
                    {
                        out.println("<ol>");

                        ArrayList<MembershipTypeStore> allMembershipTypesList = new ArrayList<MembershipTypeStore>();
                        allMembershipTypesList = myMembershipTypeConn.getAllMembershipTypes();

                        if (!allMembershipTypesList.isEmpty()) {
// Display all MembershipTypes using forach
                            for (MembershipTypeStore i : allMembershipTypesList) {
                                out.println("<li>");
                                out.println("<ul>");
                                out.println("<li><u><a href=\"/MP3Store/admin/MembershipType?MembershipTypeID=" + i.getMembershipTypeID() + "\"> <b>MembershipType #</b>" + i.getMembershipTypeID() + " - " + i.getMembershipName() + "</u></a></li>");
                                out.println("<li><b>" + i.getMembershipName() + "</b></li>");
                                out.println("<li><B>Membership Description: </b><i>" + i.getMembershipTypeDesc() + "</i></li>");
                                out.println("<li><B>Can Download? </b><i>" + i.getCanDownload() + "</i><B> Can Re-Download? </b><i>" + i.getCanRedownload() + "</i><B> Can Upload? </b><i>" + i.getCanUpload() + "</i><B> Can Download Unlimited Tracks? </b><i>" + i.getCanDownloadUnlimited() + "</i></li>");
                                out.println("</ul>");
                                out.println("</li>");
                            }
                            out.println("</ol>");
                        } else {
                            out.println("<h3>No Membership Types found!</h3>");
                        }
                        out.println("<u><b><a href=\"/MP3Store/admin/addMembershipType.jsp\">Add Membership Type</a></b></u><br />");
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
            out.println("<title>MP3Store Admin Area -MembershipTypes</title>");
            out.println("</head>");
            out.println("<body>");
            
                        // Verify user
            HttpSession session = request.getSession();

            if (session.getAttribute("Username") != null) {
                if (AdminLoginHelper.verifyUsername(session.getAttribute("Username").toString())) {
                   
            out.println("<h1>MP3Store Admin Area - MembershipType Servlet at: " + request.getContextPath() + "</h1>");

            if (request.getParameter("Mode") != null) {
                if (request.getParameter("Mode").equalsIgnoreCase("DELETE")) {
                    out.println("<b>Deleting Membership Type.....</b>");
                    doDelete(request, response);
                } else if (request.getParameter("Mode").equalsIgnoreCase("PUT")) {
                    out.println("<b>Adding New Membership Type.....</b>");
                    doPut(request, response);
                } else {
                    if (request.getParameter("MembershipTypeID") != null && request.getParameter("MembershipName") != null && request.getParameter("MembershipTypeDesc") != null) {
                        MembershipTypeConnector myMembershipTypeConn = new MembershipTypeConnector();
                        MembershipTypeStore editedMembershipType = new MembershipTypeStore();
                        editedMembershipType.setMembershipTypeID(Integer.parseInt(request.getParameter("MembershipTypeID")));
                        editedMembershipType.setMembershipName(request.getParameter("MembershipName"));
                        editedMembershipType.setMembershipTypeDesc(request.getParameter("MembershipTypeDesc"));

                        if (request.getParameter("CanDownload").equals("1")) {
                            editedMembershipType.setCanDownload(true);
                        } else {
                            editedMembershipType.setCanDownload(false);
                        }

                        if (request.getParameter("CanRedownload").equals("1")) {
                            editedMembershipType.setCanRedownload(true);
                        } else {
                            editedMembershipType.setCanDownload(false);
                        }

                        if (request.getParameter("CanUpload").equals("1")) {
                            editedMembershipType.setCanUpload(true);
                        } else {
                            editedMembershipType.setCanUpload(false);
                        }

                        if (request.getParameter("CanDownloadUnlimited").equals("1")) {
                            editedMembershipType.setCanDownloadUnlimited(true);
                        } else {
                            editedMembershipType.setCanDownloadUnlimited(false);
                        }

                        out.println("<b>Updating Membership Type Details....</b>");
                        myMembershipTypeConn.updateMembershipType(editedMembershipType);
                        out.println("<b>Membership Type Updated!</b>");
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
            out.println("<title>MP3Store Admin Area -MembershipTypes</title>");
            out.println("</head>");
            out.println("<body>");
            
                        // Verify user
            HttpSession session = request.getSession();

            if (session.getAttribute("Username") != null) {
                if (AdminLoginHelper.verifyUsername(session.getAttribute("Username").toString())) {
                    
            out.println("<h1>MP3Store Admin Area - MembershipType Servlet at: " + request.getContextPath() + "</h1>");

            MembershipTypeConnector myMembershipTypeConn = new MembershipTypeConnector();
            if (request.getParameter("MembershipName") != null && request.getParameter("MembershipTypeDesc") != null) {
                MembershipTypeStore tmpMembershipType = new MembershipTypeStore();

                tmpMembershipType.setMembershipTypeID(0);
                tmpMembershipType.setMembershipName(request.getParameter("MembershipName"));
                tmpMembershipType.setMembershipTypeDesc(request.getParameter("MembershipTypeDesc"));

                if (request.getParameter("CanDownload").equals("1")) {
                    tmpMembershipType.setCanDownload(true);
                } else {
                    tmpMembershipType.setCanDownload(false);
                }

                if (request.getParameter("CanRedownload").equals("1")) {
                    tmpMembershipType.setCanRedownload(true);
                } else {
                    tmpMembershipType.setCanDownload(false);
                }

                if (request.getParameter("CanUpload").equals("1")) {
                    tmpMembershipType.setCanUpload(true);
                } else {
                    tmpMembershipType.setCanUpload(false);
                }

                if (request.getParameter("CanDownloadUnlimited").equals("1")) {
                    tmpMembershipType.setCanDownloadUnlimited(true);
                } else {
                    tmpMembershipType.setCanDownloadUnlimited(false);
                }

                myMembershipTypeConn.insertMembershipType(tmpMembershipType);
                out.println("<b>Membership Type Added!</b>");
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
            out.println("<title>MP3Store Admin Area -MembershipTypes</title>");
            out.println("</head>");
            out.println("<body>");
            
                        // Verify user
            HttpSession session = request.getSession();

            if (session.getAttribute("Username") != null) {
                if (AdminLoginHelper.verifyUsername(session.getAttribute("Username").toString())) {
                    
            out.println("<h1>MP3Store Admin Area - MembershipType Servlet at: " + request.getContextPath() + "</h1>");

            MembershipTypeConnector myMembershipTypeConn = new MembershipTypeConnector();
            if (request.getParameter("MembershipTypeID") != null) {
                myMembershipTypeConn.deleteMembershipType(Integer.parseInt(request.getParameter("MembershipTypeID")));
                out.println("<b>Membership Type Deleted!</b>");
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
