package MP3Store.Connectors;

import MP3Store.Models.BandStore;
import MP3Store.Util.DatabaseInformationStore;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author James
 */
public final class BandConnector {

    private Connection con =
            null;

    public BandConnector() {
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

    public boolean updateBand(BandStore newBand) {

        PreparedStatement stmt =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// Execute an SQL non-query, dont get a result set. Note used of prepared, paramaterised statement here

            String qryString = "UPDATE Band SET BandManager=?,BandName=?,BandDesc,BandGenre=? WHERE BandID=?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setString(1, newBand.getBandManager());
            stmt.setString(2, newBand.getBandName());
            stmt.setString(3, newBand.getBandDesc());
            stmt.setInt(4, newBand.getBandGenre());
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

    public ArrayList<BandStore> getAllBands() {
        ArrayList<BandStore> foundBand = new ArrayList<BandStore>();

        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// Execute an SQL query to show all Bands, giving us a ResultSet.

            String qryString = "SELECT BandID,BandManager,BandName,BandDesc,BandGenre FROM Band";
            stmt = getCon().prepareStatement(qryString);

            rs =
                    stmt.executeQuery();
            while (rs.next()) {
                BandStore tmpBand = new BandStore();

                tmpBand.setBandID(rs.getInt("BandID"));
                tmpBand.setBandManager(rs.getString("BandManager"));
                tmpBand.setBandName(rs.getString("BandName"));
                tmpBand.setBandDesc(rs.getString("BandDesc"));
                tmpBand.setBandGenre(rs.getInt("BandGenre"));
                // Add this Band to list of returned Bands
                foundBand.add(tmpBand);
            }

        } catch (SQLException e) {
            // Handle errors with the connection
            System.out.println("SQLException caught in getAllBands(): "
                    + e.getMessage());
            return null;
        } finally {
// Always close the database Connection to release the database resources immediately.
            disconnectFromDB(getCon()); // Use helper method
        }

        return foundBand;
    }

    public BandStore getBand(int BandID) {
        BandStore foundBand = new BandStore();

        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }
// Execute an SQL query to show a specific Bands, giving us a ResultSet. Note used of prepared, paramaterised statement here.

            String qryString = "SELECT BandID,BandManager,BandName,BandDesc,BandGenre FROM Band WHERE BandID = ?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setInt(1, BandID);

            // TODO: test
            rs =
                    stmt.executeQuery();
            rs.first();

                foundBand.setBandID(rs.getInt("BandID"));
                foundBand.setBandManager(rs.getString("BandManager"));
                foundBand.setBandName(rs.getString("BandName"));
                foundBand.setBandDesc(rs.getString("BandDesc"));
                foundBand.setBandGenre(rs.getInt("BandGenre"));

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

        return foundBand;
    }

    public boolean insertBand(BandStore newBand) {

        PreparedStatement stmt =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// The rest of the code for querying the db goeTests here.

            // Execute an SQL non-query, dont get a result set

            String qryString = "INSERT INTO Band (BandID,BandManager,BandName,BandDesc,BandGenre)"
                    + " VALUES (?,?,?,?,?)";

            stmt = getCon().prepareStatement(qryString);
            stmt.setNull(1, java.sql.Types.INTEGER);
            stmt.setString(2, newBand.getBandManager());
            stmt.setString(3, newBand.getBandName());
            stmt.setString(4, newBand.getBandDesc());
            stmt.setInt(5, newBand.getBandGenre());

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

    public boolean deleteBand(Integer BandID) {

        PreparedStatement stmt =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// The rest of the code for querying the db goes here.

            // Execute an SQL non-query, dont get a result set
            String qryString = "DELETE FROM Band WHERE BandID = ?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setInt(1, BandID);
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