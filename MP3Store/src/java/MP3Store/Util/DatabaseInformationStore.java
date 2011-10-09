/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package MP3Store.Util;


/**
 *
 * @author james
 */
public class DatabaseInformationStore {
                            // NOTE zeroDateTimeBehavior=convertToNull!
    private static String connectionString = "jdbc:mysql://localhost:3306/mp3_store?zeroDateTimeBehavior=convertToNull";
    private static String dbUser = "mp3_store";
    private static String dbPass = "password";

    /**
     * @return the connectionString
     */
    public static String getConnectionString() {
        return connectionString;
    }

    /**
     * @param aConnectionString the connectionString to set
     */
    public static void setConnectionString(String aConnectionString) {
        connectionString = aConnectionString;
    }

    /**
     * @return the dbUser
     */
    public static String getDbUser() {
        return dbUser;
    }

    /**
     * @param aDbUser the dbUser to set
     */
    public static void setDbUser(String aDbUser) {
        dbUser = aDbUser;
    }

    /**
     * @return the dbPass
     */
    public static String getDbPass() {
        return dbPass;
    }

    /**
     * @param aDbPass the dbPass to set
     */
    public static void setDbPass(String aDbPass) {
        dbPass = aDbPass;
    }
}
