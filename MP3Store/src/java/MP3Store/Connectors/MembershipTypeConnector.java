package MP3Store.Connectors;

import MP3Store.Models.MembershipTypeStore;
import MP3Store.Util.DatabaseInformationStore;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author James
 */
public final class MembershipTypeConnector {

    private Connection con =
            null;

    public MembershipTypeConnector() {
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

    public boolean updateMembershipType(MembershipTypeStore newMembershipType) {

        PreparedStatement stmt =
                null;
        try {

            if (con == null) {
                con = connectToDB();
            }

// Execute an SQL non-query, dont get a result set. Note used of prepared, paramaterised statement here

            String qryString = "UPDATE MembershipType SET MembershipName=?,MembershipTypeDesc=?,CanDownload=?,CanRedownload=?,CanUpload=?,CanDownloadUnlimited=? WHERE MembershipTypeID=?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setString(1, newMembershipType.getMembershipName());
            stmt.setString(2, newMembershipType.getMembershipTypeDesc());
            stmt.setBoolean(3, newMembershipType.getCanDownload());
            stmt.setBoolean(4, newMembershipType.getCanRedownload());
            stmt.setBoolean(5, newMembershipType.getCanUpload());
            stmt.setBoolean(6, newMembershipType.getCanDownloadUnlimited());
            stmt.setInt(7, newMembershipType.getMembershipTypeID());
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

    public ArrayList<MembershipTypeStore> getAllMembershipTypes() {
        ArrayList<MembershipTypeStore> foundMembershipType = new ArrayList<MembershipTypeStore>();

        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {

            if (con == null) {
                con = connectToDB();
            }

// Execute an SQL query to show all MembershipTypes, giving us a ResultSet.

            String qryString = "SELECT MembershipTypeID,MembershipName,MembershipTypeDesc,CanDownload,CanRedownload,CanUpload,CanDownloadUnlimited FROM MembershipType";
            stmt = getCon().prepareStatement(qryString);

            rs =
                    stmt.executeQuery();
            while (rs.next()) {
                MembershipTypeStore tmpMembershipType = new MembershipTypeStore();

                tmpMembershipType.setMembershipTypeID(rs.getInt("MembershipTypeID"));
                tmpMembershipType.setMembershipName(rs.getString("MembershipName"));
                tmpMembershipType.setMembershipTypeDesc(rs.getString("MembershipTypeDesc"));
                tmpMembershipType.setCanDownload(rs.getBoolean("CanDownload"));
                tmpMembershipType.setCanRedownload(rs.getBoolean("CanRedownload"));
                tmpMembershipType.setCanUpload(rs.getBoolean("CanUpload"));
                tmpMembershipType.setCanDownloadUnlimited(rs.getBoolean("CanDownloadUnlimited"));

                // Add this MembershipType to list of returned MembershipTypes
                foundMembershipType.add(tmpMembershipType);
            }

        } catch (SQLException e) {
            // Handle errors with the connection
            System.out.println("SQLException caught in getAllMembershipTypes(): "
                    + e.getMessage());
            return null;
        } finally {
// Always close the database Connection to release the database resources immediately.
            disconnectFromDB(getCon()); // Use helper method
        }

        return foundMembershipType;
    }

    public MembershipTypeStore getMembershipType(int MembershipTypeID) {
        MembershipTypeStore foundMembershipType = new MembershipTypeStore();

        PreparedStatement stmt =
                null;
        ResultSet rs =
                null;
        try {
// Execute an SQL query to show a specific MembershipTypes, giving us a ResultSet. Note used of prepared, paramaterised statement here.

            if (con == null) {
                con = connectToDB();
            }

            String qryString = "SELECT MembershipTypeID,MembershipName,MembershipTypeDesc,CanDownload,CanRedownload,CanUpload,CanDownloadUnlimited FROM MembershipType WHERE MembershipTypeID = ?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setInt(1, MembershipTypeID);

            // TODO: test
            rs =
                    stmt.executeQuery();
            rs.first();

            foundMembershipType.setMembershipTypeID(rs.getInt("MembershipTypeID"));
            foundMembershipType.setMembershipName(rs.getString("MembershipName"));
            foundMembershipType.setMembershipTypeDesc(rs.getString("MembershipTypeDesc"));
            foundMembershipType.setCanDownload(rs.getBoolean("CanDownload"));
            foundMembershipType.setCanRedownload(rs.getBoolean("CanRedownload"));
            foundMembershipType.setCanUpload(rs.getBoolean("CanUpload"));
            foundMembershipType.setCanDownloadUnlimited(rs.getBoolean("CanDownloadUnlimited"));

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

        return foundMembershipType;
    }

    public boolean insertMembershipType(MembershipTypeStore newMembershipType) {

        PreparedStatement stmt =
                null;
        try {

            if (con == null) {
                con = connectToDB();
            }

// The rest of the code for querying the db goeTests here.

            // Execute an SQL non-query, dont get a result set

            String qryString = "INSERT INTO MembershipType (MembershipTypeID,MembershipName,MembershipTypeDesc,CanDownload,CanRedownload,CanUpload,CanDownloadUnlimited)"
                    + " VALUES (?,?,?,?,?,?,?)";

            stmt = getCon().prepareStatement(qryString);
            stmt.setNull(1, java.sql.Types.INTEGER);
            stmt.setString(2, newMembershipType.getMembershipTypeDesc());
            stmt.setBoolean(3, newMembershipType.getCanDownload());
            stmt.setBoolean(4, newMembershipType.getCanRedownload());
            stmt.setBoolean(5, newMembershipType.getCanUpload());
            stmt.setBoolean(6, newMembershipType.getCanDownloadUnlimited());
            stmt.setInt(7, newMembershipType.getMembershipTypeID());
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

    public boolean deleteMembershipType(Integer MembershipTypeID) {

        PreparedStatement stmt =
                null;
        try {

            if (con == null) {
                con = connectToDB();
            }

// The rest of the code for querying the db goes here.

            // Execute an SQL non-query, dont get a result set
            String qryString = "DELETE FROM MembershipType WHERE MembershipTypeID = ?";
            stmt = getCon().prepareStatement(qryString);
            stmt.setInt(1, MembershipTypeID);
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