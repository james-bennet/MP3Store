package MP3Store.Connectors;

import MP3Store.Models.TransactionLineStore;
import MP3Store.Util.DatabaseInformationStore;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author James
 */
public final class TransactionLineConnector {

    private Connection con =
            null;

    public TransactionLineConnector() {
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

    public ArrayList<TransactionLineStore> getAllTransactionLines() {
        ArrayList<TransactionLineStore> foundTransactionLine = new ArrayList<TransactionLineStore>();

        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// Execute an SQL query to show all TransactionLines, giving us a ResultSet.

            String qryString = "SELECT TransactionID,TransactionLineID,TrackID FROM TransactionLine";
            stmt = getCon().prepareStatement(qryString);

            rs =
                    stmt.executeQuery();
            while (rs.next()) {
                TransactionLineStore tmpTransactionLine = new TransactionLineStore();

                tmpTransactionLine.setTransactionID(rs.getInt("TransactionID"));
                tmpTransactionLine.setTransactionLineID(rs.getInt("TransactionLineID"));
                tmpTransactionLine.setTrackID(rs.getInt("TrackID"));
                                
                // Add this TransactionLine to list of returned TransactionLines
                foundTransactionLine.add(tmpTransactionLine);
            }

        } catch (SQLException e) {
            // Handle errors with the connection
            System.out.println("SQLException caught in getAllTransactionLines(): "
                    + e.getMessage());
            return null;
        } finally {
// Always close the database Connection to release the database resources immediately.
            disconnectFromDB(getCon()); // Use helper method
        }

        return foundTransactionLine;
    }

    public TransactionLineStore geTransactionLine(int TransactionLineID) {
        TransactionLineStore foundTransactionLine = new TransactionLineStore();

        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }
// Execute an SQL query to show a specific TransactionLines, giving us a ResultSet. Note used of prepared, paramaterised statement here.

            String qryString = "SELECT TransactionID,TransactionLineID,TrackID FROM TransactionLine WHERE TransactionLineID = ?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setInt(1, TransactionLineID);

            // TODO: test
            rs =
                    stmt.executeQuery();
            rs.first();

                foundTransactionLine.setTransactionID(rs.getInt("TransactionID"));
                foundTransactionLine.setTransactionLineID(rs.getInt("TransactionLineID"));
                foundTransactionLine.setTrackID(rs.getInt("TrackID"));

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

        return foundTransactionLine;
    }
    
    public boolean inserTransactionLine(TransactionLineStore newTransactionLine) {

        PreparedStatement stmt =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// The rest of the code for querying the db goeTests here.

            // Execute an SQL non-query, dont get a result set

            String qryString = "INSERT INTO TransactionLine (TransactionID,TransactionLineID,TrackID)"
                    + " VALUES (?,?,?)";

            stmt = getCon().prepareStatement(qryString);
            stmt.setInt(1, newTransactionLine.getTransactionID());
            stmt.setNull(2, java.sql.Types.INTEGER);
            stmt.setInt(3, newTransactionLine.getTrackID());

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

    public boolean deleteTransactionLine(Integer TransactionLineID) {

        PreparedStatement stmt =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// The rest of the code for querying the db goes here.

            // Execute an SQL non-query, dont get a result set
            String qryString = "DELETE FROM TransactionLine WHERE TransactionLineID = ?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setInt(1, TransactionLineID);
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