package MP3Store.Connectors;

import MP3Store.Models.TrackStore;
import MP3Store.Util.DatabaseInformationStore;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author James
 */
public final class TrackConnector {

    private Connection con =
            null;

    public TrackConnector() {
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

    public boolean updateTrack(TrackStore newTrack) {

        PreparedStatement stmt =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// Execute an SQL non-query, dont get a result set. Note used of prepared, paramaterised statement here

            String qryString = "UPDATE Track SET TrackNumber=?,AlbumID=?,TrackName=?,FilePath=?,Price=? WHERE TrackID=?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setInt(1, newTrack.getTrackNumber());
            stmt.setInt(2, newTrack.getAlbumID());
            stmt.setString(3, newTrack.getTrackName());
            stmt.setString(4, newTrack.getFilePath());                                
            stmt.setBigDecimal(5, newTrack.getPrice());
            stmt.setInt(6, newTrack.getTrackID());
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

    public ArrayList<TrackStore> getAllTracks() {
        ArrayList<TrackStore> foundTrack = new ArrayList<TrackStore>();

        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// Execute an SQL query to show all Tracks, giving us a ResultSet.

            String qryString = "SELECT TrackID,TrackNumber,AlbumID,TrackName,FilePath,Price FROM Track";
            stmt = getCon().prepareStatement(qryString);

            rs =
                    stmt.executeQuery();
            while (rs.next()) {
                TrackStore tmpTrack = new TrackStore();
                
            tmpTrack.setTrackID(rs.getInt("TrackID"));
            tmpTrack.setTrackNumber(rs.getInt("TrackNumber"));
            tmpTrack.setAlbumID(rs.getInt("AlbumID"));
            tmpTrack.setTrackName(rs.getString("TrackName"));
            tmpTrack.setFilePath(rs.getString("FilePath"));                                
            tmpTrack.setPrice(rs.getBigDecimal("Price"));

                // Add this Track to list of returned Tracks
                foundTrack.add(tmpTrack);
            }

        } catch (SQLException e) {
            // Handle errors with the connection
            System.out.println("SQLException caught in getAllTracks(): "
                    + e.getMessage());
            return null;
        } finally {
// Always close the database Connection to release the database resources immediately.
            disconnectFromDB(getCon()); // Use helper method
        }

        return foundTrack;
    }

    public TrackStore getTrack(int TrackID) {
        TrackStore foundTrack = new TrackStore();

        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }
// Execute an SQL query to show a specific Tracks, giving us a ResultSet. Note used of prepared, paramaterised statement here.

            String qryString = "SELECT TrackID,TrackNumber,AlbumID,TrackName,FilePath,Price FROM Track WHERE TrackID = ?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setInt(1, TrackID);

            // TODO: test
            rs =
                    stmt.executeQuery();
            rs.first();

            foundTrack.setTrackID(rs.getInt("TrackID"));
            foundTrack.setTrackNumber(rs.getInt("TrackNumber"));
            foundTrack.setAlbumID(rs.getInt("AlbumID"));
            foundTrack.setTrackName(rs.getString("TrackName"));
            foundTrack.setFilePath(rs.getString("FilePath"));                                
            foundTrack.setPrice(rs.getBigDecimal("Price"));

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

        return foundTrack;
    }

    public boolean insertTrack(TrackStore newTrack) {

        PreparedStatement stmt =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// The rest of the code for querying the db goeTests here.

            // Execute an SQL non-query, dont get a result set

            String qryString = "INSERT INTO Track (TrackID,TrackNumber,AlbumID,TrackName,FilePath,Price)"
                    + " VALUES (?,?,?,?,?,?)";

            stmt = getCon().prepareStatement(qryString);
            stmt.setNull(1, java.sql.Types.INTEGER);
            stmt.setInt(2, newTrack.getTrackNumber());
            stmt.setInt(3, newTrack.getAlbumID());
            stmt.setString(4, newTrack.getTrackName());
            stmt.setString(5, newTrack.getFilePath());                                
            stmt.setBigDecimal(6, newTrack.getPrice());

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

    public boolean deleteTrack(Integer TrackID) {

        PreparedStatement stmt =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// The rest of the code for querying the db goes here.

            // Execute an SQL non-query, dont get a result set
            String qryString = "DELETE FROM Track WHERE TrackID = ?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setInt(1, TrackID);
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