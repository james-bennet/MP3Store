package MP3Store.Connectors;

import MP3Store.Models.CustomerStore;
import MP3Store.Util.DatabaseInformationStore;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author James
 */
public final class CustomerConnector {

    private Connection con =
            null;

    public CustomerConnector() {
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

    public boolean updateCustomer(CustomerStore newCustomer) {

        PreparedStatement stmt =
                null;
        try {
            
            if (con == null)
            {
                con = connectToDB();
            }

// Execute an SQL non-query, dont get a result set. Note used of prepared, paramaterised statement here

            String qryString = "UPDATE Customer SET CustomerForename=?,CustomerSurname=?,CustomerTitle=?,CustomerEmail=?,CustomerAddress=?,Verified=?,MembershipType=?,Password=? WHERE Username=?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setString(1, newCustomer.getCustomerForename());
            stmt.setString(2, newCustomer.getCustomerSurname());
            stmt.setString(3, newCustomer.getCustomerTitle());
            stmt.setString(4, newCustomer.getCustomerEmail());
            stmt.setString(5, newCustomer.getCustomerAddress());
            stmt.setBoolean(6, newCustomer.getVerified());
            stmt.setInt(7, newCustomer.getMembershipType());
            stmt.setString(8, newCustomer.getPassword());
            stmt.setString(9, newCustomer.getUsername());
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

    public ArrayList<CustomerStore> getAllCustomers() {
        ArrayList<CustomerStore> foundCustomer = new ArrayList<CustomerStore>();

        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// Execute an SQL query to show all Customers, giving us a ResultSet.

            String qryString = "SELECT Username,CustomerForename,CustomerSurname,CustomerTitle,CustomerEmail,CustomerAddress,CustomerSince,Verified,MembershipType,Password FROM Customer";
            stmt = getCon().prepareStatement(qryString);

            rs =
                    stmt.executeQuery();
            while (rs.next()) {
                CustomerStore tmpCustomer = new CustomerStore();

                tmpCustomer.setUsername(rs.getString("Username"));
                tmpCustomer.setCustomerForename(rs.getString("CustomerForename"));
                tmpCustomer.setCustomerSurname(rs.getString("CustomerSurname"));
                tmpCustomer.setCustomerTitle(rs.getString("CustomerTitle"));
                tmpCustomer.setCustomerEmail(rs.getString("CustomerEmail"));
                tmpCustomer.setCustomerAddress(rs.getString("CustomerAddress"));
                tmpCustomer.setCustomerSince(rs.getString("CustomerSince"));
                tmpCustomer.setVerified(rs.getBoolean("Verified"));
                tmpCustomer.setMembershipType(rs.getInt("MembershipType"));
                tmpCustomer.setPassword(rs.getString("Password"));

                // Add this Customer to list of returned Customers
                foundCustomer.add(tmpCustomer);
            }

        } catch (SQLException e) {
            // Handle errors with the connection
            System.out.println("SQLException caught in getAllCustomers(): "
                    + e.getMessage());
            return null;
        } finally {
// Always close the database Connection to release the database resources immediately.
            disconnectFromDB(getCon()); // Use helper method
        }

        return foundCustomer;
    }

    public CustomerStore getCustomer(String Username) {
        CustomerStore foundCustomer = new CustomerStore();

        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {
// Execute an SQL query to show a specific Customers, giving us a ResultSet. Note used of prepared, paramaterised statement here.

                        if (con == null)
            {
                con = connectToDB();
            }
                        
            String qryString = "SELECT Username,CustomerForename,CustomerSurname,CustomerTitle,CustomerEmail,CustomerAddress,CustomerSince,Verified,MembershipType,Password FROM Customer WHERE Username = ?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setString(1, Username);

            // TODO: test
            rs =
                    stmt.executeQuery();
            rs.first();

            foundCustomer.setUsername(rs.getString("Username"));
            foundCustomer.setCustomerForename(rs.getString("CustomerForename"));
            foundCustomer.setCustomerSurname(rs.getString("CustomerSurname"));
            foundCustomer.setCustomerTitle(rs.getString("CustomerTitle"));
            foundCustomer.setCustomerEmail(rs.getString("CustomerEmail"));
            foundCustomer.setCustomerAddress(rs.getString("CustomerAddress"));
            foundCustomer.setCustomerSince(rs.getString("CustomerSince"));
            foundCustomer.setVerified(rs.getBoolean("Verified"));
            foundCustomer.setMembershipType(rs.getInt("MembershipType"));
            foundCustomer.setPassword(rs.getString("Password"));

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

        return foundCustomer;
    }

    public boolean insertCustomer(CustomerStore newCustomer) {

        PreparedStatement stmt =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// The rest of the code for querying the db goeTests here.

            // Execute an SQL non-query, dont get a result set

            String qryString = "INSERT INTO Customer (Username,CustomerForename,CustomerSurname,CustomerTitle,CustomerEmail,CustomerAddress,CustomerSince,Verified,MembershipType,Password)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?)";

            stmt = getCon().prepareStatement(qryString);
            stmt.setString(1, newCustomer.getUsername());
            stmt.setString(2, newCustomer.getCustomerForename());
            stmt.setString(3, newCustomer.getCustomerSurname());
            stmt.setString(4, newCustomer.getCustomerTitle());
            stmt.setString(5, newCustomer.getCustomerEmail());
            stmt.setString(6, newCustomer.getCustomerAddress());
            stmt.setNull(7, java.sql.Types.TIMESTAMP);
            stmt.setBoolean(8, newCustomer.getVerified());
            stmt.setInt(9, newCustomer.getMembershipType());
            stmt.setString(10, newCustomer.getPassword());

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

    public boolean deleteCustomer(Integer Username) {

        PreparedStatement stmt =
                null;
        try {
            
                        if (con == null)
            {
                con = connectToDB();
            }

// The rest of the code for querying the db goes here.

            // Execute an SQL non-query, dont get a result set
            String qryString = "DELETE FROM Customer WHERE Username = ?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setInt(1, Username);
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