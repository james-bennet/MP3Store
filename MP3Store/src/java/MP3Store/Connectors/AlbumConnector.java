package MP3Store.Connectors;

import MP3Store.Models.AlbumStore;
import MP3Store.Util.DatabaseInformationStore;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author James
 */
public final class AlbumConnector {

    private Connection con =
            null;

    public AlbumConnector() {
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
                        DriverManager.getConnection(DatabaseInformationStore.getConnectionString(),
                        DatabaseInformationStore.getDbUser(),
                        DatabaseInformationStore.getDbPass());
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

    public boolean updateAlbum(AlbumStore newAlbum) {

        PreparedStatement stmt =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// Execute an SQL non-query, dont get a result set. Note used of prepared, paramaterised statement here

            String qryString = "UPDATE Album SET AlbumName=?,AlbumGenre=?,AlbumDesc,Rating=? WHERE AlbumID=?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setString(1, newAlbum.getAlbumName());
            stmt.setInt(2, newAlbum.getAlbumGenre());
            stmt.setString(3, newAlbum.getAlbumDesc());
            stmt.setInt(4, newAlbum.getRating());                                   
            stmt.setInt(5, newAlbum.getAlbumID());
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

    public ArrayList<AlbumStore> getAllAlbums() {
        ArrayList<AlbumStore> foundAlbum = new ArrayList<AlbumStore>();

        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// Execute an SQL query to show all Albums, giving us a ResultSet.

            String qryString = "SELECT AlbumID,BandID,AlbumName,AlbumGenre,AlbumDesc,ReleaseDate,Rating FROM Album";
            stmt = getCon().prepareStatement(qryString);

            rs =
                    stmt.executeQuery();
            while (rs.next()) {
                AlbumStore tmpAlbum = new AlbumStore();

                tmpAlbum.setAlbumID(rs.getInt("AlbumID"));
                tmpAlbum.setBandID(rs.getInt("BandID"));
                tmpAlbum.setAlbumName(rs.getString("AlbumName"));
                tmpAlbum.setAlbumGenre(rs.getInt("AlbumGenre"));
                tmpAlbum.setReleaseDate(rs.getTimestamp("ReleaseDate"));
                tmpAlbum.setAlbumDesc(rs.getString("Rating"));
                tmpAlbum.setRating(rs.getInt("AlbumGenre"));
                // Add this Album to list of returned Albums
                foundAlbum.add(tmpAlbum);
            }

        } catch (SQLException e) {
            // Handle errors with the connection
            System.out.println("SQLException caught in getAllAlbums(): "
                    + e.getMessage());
            return null;
        } finally {
// Always close the database Connection to release the database resources immediately.
            disconnectFromDB(getCon()); // Use helper method
        }

        return foundAlbum;
    }

    public AlbumStore getAlbum(int AlbumID) {
        AlbumStore foundAlbum = new AlbumStore();

        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }
// Execute an SQL query to show a specific Albums, giving us a ResultSet. Note used of prepared, paramaterised statement here.

            String qryString = "SELECT AlbumID,BandID,AlbumName,AlbumGenre,AlbumDesc,ReleaseDate,Rating FROM Album WHERE AlbumID = ?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setInt(1, AlbumID);

            // TODO: test
            rs =
                    stmt.executeQuery();
            rs.first();

                foundAlbum.setAlbumID(rs.getInt("AlbumID"));
                foundAlbum.setBandID(rs.getInt("BandID"));
                foundAlbum.setAlbumName(rs.getString("AlbumName"));
                foundAlbum.setAlbumGenre(rs.getInt("AlbumGenre"));
                foundAlbum.setReleaseDate(rs.getTimestamp("ReleaseDate"));
                foundAlbum.setAlbumDesc(rs.getString("Rating"));
                foundAlbum.setRating(rs.getInt("AlbumGenre"));

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

        return foundAlbum;
    }

    public boolean insertAlbum(AlbumStore newAlbum) {

        PreparedStatement stmt =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// The rest of the code for querying the db goeTests here.

            // Execute an SQL non-query, dont get a result set

            String qryString = "INSERT INTO Album (AlbumID,BandID,AlbumName,AlbumGenre,AlbumDesc,ReleaseDate,Rating)"
                    + " VALUES (?,?,?,?,?,?,?)";

            stmt = getCon().prepareStatement(qryString);
            stmt.setNull(1, java.sql.Types.INTEGER);
            stmt.setString(2, newAlbum.getAlbumName());
            stmt.setInt(3, newAlbum.getAlbumGenre());
            stmt.setString(4, newAlbum.getAlbumDesc());
            stmt.setNull(5, java.sql.Types.TIMESTAMP);
            stmt.setInt(6, newAlbum.getRating());                                   
            stmt.setInt(7, newAlbum.getAlbumID());

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

    public boolean deleteAlbum(Integer AlbumID) {

        PreparedStatement stmt =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// The rest of the code for querying the db goes here.

            // Execute an SQL non-query, dont get a result set
            String qryString = "DELETE FROM Album WHERE AlbumID = ?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setInt(1, AlbumID);
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