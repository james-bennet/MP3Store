/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package MP3Store.Servlets.AdminArea;

import MP3Store.Connectors.GenreConnector;
import MP3Store.Models.GenreStore;
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
public class Genre extends HttpServlet {

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
        GenreConnector myGenreConn = new GenreConnector();

        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>MP3Store Admin Area -Genres</title>");
            out.println("</head>");
            out.println("<body>");
            
                        // Verify user
             HttpSession session = request.getSession();
             
                        if (session.getAttribute("Username") != null) {
                if (AdminLoginHelper.verifyUsername(session.getAttribute("Username").toString()))
                {

            out.println("<h1>MP3Store Admin Area - Genre Servlet</h1>");

            // Details for just one Genre - TODO: Genre not found message
            if (request.getParameter("GenreID") != null) {
                GenreStore GenreDetails = myGenreConn.getGenre(Integer.parseInt(request.getParameter("GenreID")));
                if (GenreDetails != null) {
                    out.println("<ul>");
                    out.println("<li><u><b>Genre #" + GenreDetails.getGenreID() + " - " + GenreDetails.getGenreName() + "</b></u></li>");
                    out.println("<li><b>Genre Description: </b><i>" + GenreDetails.getGenreDesc() + "</i></li>");
                    out.println("</ul>");
                    out.println("<form name=\"delete\" action=\"/MP3Store/admin/Genre\" method=\"POST\"><input type=\"hidden\" name=\"GenreID\" value=\"" + GenreDetails.getGenreID() + "\"><input type=\"hidden\" name=\"Mode\" value=\"DELETE\"><input type=\"submit\" value=\"Delete Genre\" /></form>");
                    out.println("<u><b><a href=\"/MP3Store/admin/editGenre.jsp?GenreID=" + GenreDetails.getGenreID() + "\">Edit Genre</a></b></u><br />");
                } else {
                    out.println("<h3>No Genre found with this ID!</h3>");
                }
            } else // Details for all Genres
            {
                out.println("<ol>");

                ArrayList<GenreStore> allGenresList = new ArrayList<GenreStore>();
                allGenresList = myGenreConn.getAllGenres();

                if (!allGenresList.isEmpty()) {
// Display all genres using forach
                    for (GenreStore i : allGenresList) {
                        out.println("<li>");
                        out.println("<ul>");
                        out.println("<li><u><a href=\"/MP3Store/admin/Genre?GenreID=" + i.getGenreID() + "\"> <b>Genre #</b>" + i.getGenreID() + " - " + i.getGenreName() + "</u></a></li>");
                        out.println("<li><b>" + i.getGenreName() + "</b></li>");
                        out.println("<li><B>Genre Description: </b><i>" + i.getGenreDesc() + "</i></li>");
                        out.println("</ul>");
                        out.println("</li>");
                    }
                    out.println("</ol>");
                } else {
                    out.println("<h3>No Genres found!</h3>");
                }
                out.println("<u><b><a href=\"/MP3Store/admin/addGenre.jsp\">Add Genre</a></b></u><br />");
            }
            out.println("<u><b><a href=\"/MP3Store/admin/index.jsp\">Back to Administration Homepage</a></b></u>");
                            }
                else
                {
                   out.println("<h3>Authentication Error!</h3>");  
                }
                        }
                        else
                        {
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
            out.println("<title>MP3Store Admin Area -Genres</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>MP3Store Admin Area - Genre Servlet at: " + request.getContextPath() + "</h1>");

            if (request.getParameter("Mode") != null) {
                if (request.getParameter("Mode").equalsIgnoreCase("DELETE")) {
                    out.println("<b>Deleting Genre.....</b>");
                    doDelete(request, response);
                } else if (request.getParameter("Mode").equalsIgnoreCase("PUT")) {
                    out.println("<b>Adding New Genre.....</b>");
                    doPut(request, response);
                } else {
                    if (request.getParameter("GenreID") != null && request.getParameter("GenreName") != null && request.getParameter("GenreDesc") != null) {
                        GenreConnector myGenreConn = new GenreConnector();
                        GenreStore editedGenre = new GenreStore();
                        editedGenre.setGenreID(Integer.parseInt(request.getParameter("GenreID")));
                        editedGenre.setGenreName(request.getParameter("GenreName"));
                        editedGenre.setGenreDesc(request.getParameter("GenreDesc"));
                        out.println("<b>Updating Genre Details....</b>");
                        myGenreConn.updateGenre(editedGenre);
                        out.println("<b>Genre Updated!</b>");
                    }
                }

            }
            out.println("<u><b><a href=\"/MP3Store/admin/index.jsp\">Back to Administration Homepage</a></b></u>");
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
            out.println("<title>MP3Store Admin Area -Genres</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>MP3Store Admin Area - Genre Servlet at: " + request.getContextPath() + "</h1>");

            GenreConnector myGenreConn = new GenreConnector();
            if (request.getParameter("GenreName") != null && request.getParameter("GenreDesc") != null) {
                myGenreConn.insertGenre(new GenreStore(0, request.getParameter("GenreName"), request.getParameter("GenreDesc")));
                out.println("<b>Genre Added!</b>");
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
            out.println("<title>MP3Store Admin Area -Genres</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>MP3Store Admin Area - Genre Servlet at: " + request.getContextPath() + "</h1>");

            GenreConnector myGenreConn = new GenreConnector();
            if (request.getParameter("GenreID") != null) {
                myGenreConn.deleteGenre(Integer.parseInt(request.getParameter("GenreID")));
                out.println("<b>Genre Deleted!</b>");
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
