/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package MP3Store.Util;

import java.sql.*;

/**
 *
 * @author james
 */
public class AdminLoginHelper {

    public static final Connection connectToDB() {
        Connection tmpCon = null;
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
    }

    public static boolean disconnectFromDB(Connection con) {
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

    public static boolean verifyPassword(String username, String password) {
        Connection con =
                connectToDB();
        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {
            String qryString = "SELECT Username,Password FROM AdminLogin WHERE Username = ? AND Password = ?";
            stmt = con.prepareStatement(qryString);
            stmt.setString(1, username);
            stmt.setString(2, password);

            // TODO: test
            rs =
                    stmt.executeQuery();
            rs.first();

            // User exists, and correct username / password combo
            if (rs.getString("Username") != null && rs.getString("Password") != null) {
                if (rs.getString("Username").equalsIgnoreCase(username) && rs.getString("Password").equals(password)) {
                    return true;
                }
            } else {
                return false;
            }

        } catch (SQLException e) {
            // Handle errors with the connection
            System.out.println("SQLException caught: "
                    + e.getMessage());
            // Get any further (more detailed) exceptions
            return false;
        } finally {
// Always close the database Connection to release the database resources immediately.
            disconnectFromDB(con); // Use helper method
        }
        return false;
    }

    public static boolean verifyUsername(String username) {
        Connection con =
                connectToDB();
        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {
            String qryString = "SELECT Username,Password FROM AdminLogin WHERE Username = ?";
            stmt = con.prepareStatement(qryString);
            stmt.setString(1, username);

            // TODO: test
            rs =
                    stmt.executeQuery();
            rs.first();

            // User exists, and correct username / password combo
            if (rs.getString("Username") != null && rs.getString("Password") != null) {
                if (rs.getString("Username").equalsIgnoreCase(username)) {
                    return true;
                }
            } else {
                return false;
            }

        } catch (SQLException e) {
            // Handle errors with the connection
            System.out.println("SQLException caught: "
                    + e.getMessage());
            // Get any further (more detailed) exceptions
            return false;
        } finally {
// Always close the database Connection to release the database resources immediately.
            disconnectFromDB(con); // Use helper method
        }
        return false;
    }
}
