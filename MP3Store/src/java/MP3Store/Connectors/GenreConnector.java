package MP3Store.Connectors;

import MP3Store.Models.GenreStore;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author James
 */
public final class GenreConnector {

    private Connection con =
            null;

    public GenreConnector() {
        con = connectToDB();
    }

    public final Connection connectToDB() {
        Connection tmpCon = null;
        if (getCon() == null) {
            try {

                // Main connection code here
                // Load and register the database driver
// Could throw a ClassNotFoundException if driver doesnt exist
                Class.forName("com.mysql.jdbc.Driver");
// Get a connection to the database - Might throw an SQLException if i.e unreachable
                tmpCon =
                        // NOTE zeroDateTimeBehavior=convertToNull!
                        DriverManager.getConnection("jdbc:mysql://localhost:3306/mp3_store?zeroDateTimeBehavior=convertToNull",
                        "mp3_store",
                        "password");
            } catch (ClassNotFoundException e) {
// Handle an error loading the database driver
                System.out.println("Couldn't load database driver: "
                        + e.getMessage());
                return null;
            } catch (SQLException e) {
                // Handle errors with the connection
                System.out.println("SQLException caught: "
                        + e.getMessage());
                return null;
            }
            return tmpCon;
        } else {
            return getCon(); // Alrady connected
        }
    }

    public boolean disconnectFromDB(Connection con) {
        try {
            if (con
                    != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("SQLException caught when disconnecting: "
                    + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean updateGenre(GenreStore newGenre) {

        PreparedStatement stmt =
                null;
        try {

// Execute an SQL non-query, dont get a result set. Note used of prepared, paramaterised statement here

            String qryString = "UPDATE Genre SET GenreName=?,GenreDesc=? WHERE GenreID=?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setString(1, newGenre.getGenreName());
            stmt.setString(2, newGenre.getGenreDesc());
            stmt.setInt(3, newGenre.getGenreID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Handle errors with the connection
            System.out.println("SQLException caught: "
                    + e.getMessage());
            return false;
        } finally {
// Always close the database Connection to release the database resources immediately.
            disconnectFromDB(getCon()); // Use helper method
        }
        return false;
    }

    public ArrayList<GenreStore> getAllGenres() {
        ArrayList<GenreStore> foundGenre = new ArrayList<GenreStore>();

        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {

// Execute an SQL query to show all Genres, giving us a ResultSet.

            String qryString = "SELECT GenreID,GenreName,GenreDesc FROM Genre";
            stmt = getCon().prepareStatement(qryString);

            rs =
                    stmt.executeQuery();
            while (rs.next()) {
                GenreStore tmpGenre = new GenreStore();

                tmpGenre.setGenreID(rs.getInt("GenreID"));
                tmpGenre.setGenreName(rs.getString("GenreName"));
                tmpGenre.setGenreDesc(rs.getString("GenreDesc"));

                // Add this Genre to list of returned Genres
                foundGenre.add(tmpGenre);
            }

        } catch (SQLException e) {
            // Handle errors with the connection
            System.out.println("SQLException caught in getAllGenres(): "
                    + e.getMessage());
            return null;
        } finally {
// Always close the database Connection to release the database resources immediately.
            disconnectFromDB(getCon()); // Use helper method
        }

        return foundGenre;
    }

    public GenreStore getGenre(int GenreID) {
        GenreStore foundGenre = new GenreStore();

        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {
// Execute an SQL query to show a specific Genres, giving us a ResultSet. Note used of prepared, paramaterised statement here.

            String qryString = "SELECT GenreID,GenreName,GenreDesc FROM Genre WHERE GenreID = ?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setInt(1, GenreID);

            // TODO: test
            rs =
                    stmt.executeQuery();
            rs.first();

            foundGenre.setGenreID(rs.getInt("GenreID"));
            foundGenre.setGenreName(rs.getString("GenreName"));
            foundGenre.setGenreDesc(rs.getString("GenreDesc"));

        } catch (SQLException e) {
            // Handle errors with the connection
            System.out.println("SQLException caught: "
                    + e.getMessage());
            // Get any further (more detailed) exceptions
            return null;
        } finally {
// Always close the database Connection to release the database resources immediately.
            disconnectFromDB(getCon()); // Use helper method
        }

        return foundGenre;
    }

    public boolean insertGenre(GenreStore newGenre) {

        PreparedStatement stmt =
                null;
        try {

// The rest of the code for querying the db goeTests here.

            // Execute an SQL non-query, dont get a result set

            String qryString = "INSERT INTO Genre (GenreID,GenreName,GenreDesc)"
                    + " VALUES (?,?,?)";

            stmt = getCon().prepareStatement(qryString);
            stmt.setNull(1, java.sql.Types.INTEGER);
            stmt.setString(2, newGenre.getGenreName());
            stmt.setString(3, newGenre.getGenreDesc());

            stmt.executeUpdate();

        } catch (Exception e) {
            // Handle errors with the connection
            System.out.println("SQLException caught: "
                    + e.getMessage());
            return false;
        } finally {
// Always close the database Connection to release the database resources immediately.
            disconnectFromDB(getCon()); // Use helper method
        }
        return true;
    }

    public boolean deleteGenre(Integer GenreID) {

        PreparedStatement stmt =
                null;
        try {

// The rest of the code for querying the db goes here.

            // Execute an SQL non-query, dont get a result set
            String qryString = "DELETE FROM Genre WHERE GenreID = ?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setInt(1, GenreID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            // Handle errors with the connection
            System.out.println("SQLException caught: "
                    + e.getMessage());
            // Get any further (more detailed) exceptions
            return false;
        } finally {
// Always close the database Connection to release the database resources immediately.
            disconnectFromDB(getCon()); // Use helper method
        }
        return true;
    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Connection con) {
        this.con = con;
    }
}