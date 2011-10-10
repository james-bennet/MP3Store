package MP3Store.Connectors;

import MP3Store.Models.TransactionLineStore;
import MP3Store.Models.TransactionStore;
import MP3Store.Util.DatabaseInformationStore;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author James
 */
public final class TransactionConnector {

    private Connection con =
            null;

    public TransactionConnector() {
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

    public ArrayList<TransactionStore> getAllTransactions() {
        ArrayList<TransactionStore> foundTransaction = new ArrayList<TransactionStore>();

        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// Execute an SQL query to show all Transactions, giving us a ResultSet.

            String qryString = "SELECT TransactionID,Username,PaymentRef,TransactionDate FROM Transaction";
            stmt = getCon().prepareStatement(qryString);

            rs =
                    stmt.executeQuery();
            while (rs.next()) {
                TransactionStore tmpTransaction = new TransactionStore();

                tmpTransaction.setTransactionID(rs.getInt("TransactionID"));
                tmpTransaction.setUsername(rs.getString("Username"));
                tmpTransaction.setPaymentRef(rs.getInt("PaymentRef"));
                tmpTransaction.setTransactionDate(rs.getTimestamp("TransactionDate"));
                // Add this Transaction to list of returned Transactions
                foundTransaction.add(tmpTransaction);
            }

        } catch (SQLException e) {
            // Handle errors with the connection
            System.out.println("SQLException caught in getAllTransactions(): "
                    + e.getMessage());
            return null;
        } finally {
// Always close the database Connection to release the database resources immediately.
            disconnectFromDB(getCon()); // Use helper method
        }

        return foundTransaction;
    }

    public TransactionStore getTransaction(int TransactionID) {
        TransactionStore foundTransaction = new TransactionStore();

        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }
// Execute an SQL query to show a specific Transactions, giving us a ResultSet. Note used of prepared, paramaterised statement here.

            String qryString = "SELECT TransactionID,Username,PaymentRef,TransactionDate FROM Transaction WHERE TransactionID = ?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setInt(1, TransactionID);

            // TODO: test
            rs =
                    stmt.executeQuery();
            rs.first();

                foundTransaction.setTransactionID(rs.getInt("TransactionID"));
                foundTransaction.setUsername(rs.getString("Username"));
                foundTransaction.setPaymentRef(rs.getInt("PaymentRef"));
                foundTransaction.setTransactionDate(rs.getTimestamp("TransactionDate"));

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

        return foundTransaction;
    }
    
    // Special helper method
        public ArrayList<TransactionLineStore> getTransactionDetails(int TransactionID) {
         ArrayList<TransactionLineStore> foundTransaction = new  ArrayList<TransactionLineStore>();

        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }
// Execute an SQL query to show a specific Transactions, giving us a ResultSet. Note used of prepared, paramaterised statement here.

            String qryString = "SELECT TransactionID,TransactionLineID,TrackID FROM TransactionLine WHERE TransactionID = ?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setInt(1, TransactionID);

            // TODO: test

             rs =
                    stmt.executeQuery();
            while (rs.next()) {
                TransactionLineStore tmpTransaction = new TransactionLineStore();

                tmpTransaction.setTransactionID(rs.getInt("TransactionID"));
                tmpTransaction.setTransactionLineID(rs.getInt("TransactionLineID"));
                tmpTransaction.setTrackID(rs.getInt("TrackID"));
                // Add this Transaction to list of returned Transactions
                foundTransaction.add(tmpTransaction);
            }

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

        return foundTransaction;
    }

    public boolean insertTransaction(TransactionStore newTransaction) {

        PreparedStatement stmt =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// The rest of the code for querying the db goeTests here.

            // Execute an SQL non-query, dont get a result set

            String qryString = "INSERT INTO Transaction (TransactionID,Username,PaymentRef,TransactionDate)"
                    + " VALUES (?,?,?,?)";

            stmt = getCon().prepareStatement(qryString);
            stmt.setNull(1, java.sql.Types.INTEGER);
            stmt.setString(2, newTransaction.getUsername());
            stmt.setInt(3, newTransaction.getPaymentRef());
            stmt.setNull(4, java.sql.Types.TIMESTAMP);

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

    public boolean deleteTransaction(Integer TransactionID) {

        PreparedStatement stmt =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// The rest of the code for querying the db goes here.

            // Execute an SQL non-query, dont get a result set
            String qryString = "DELETE FROM Transaction WHERE TransactionID = ?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setInt(1, TransactionID);
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