import java.sql.*;

public class JDBC_DBAccessor implements DBAccessor {
    private Connection con;

    public JDBC_DBAccessor(String url, String username, String password) {
        this.loadDriver();
        this.getConnection(url, username, password);
    }

    private void loadDriver() {
        try {
            System.out.println("Loading driver: oracle.jdbc.driver.OracleDriver");
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            System.out.println("Error loading the driver:");
            e.printStackTrace();
        }
    }

    private void getConnection(String url, String username, String password) {
        try {
            System.out.println("Getting a new connection from " + url);
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Error getting a connection.");
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            System.out.println("Closing the connection.");
            con.close();
        } catch (Exception e) {
            System.out.println("Error closing the connection");
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet executeQuery(String query) {
        ResultSet rs = null;
        Statement st = null;
        try {
            System.out.println("Executing query: " + query);
            st = con.createStatement();
            rs = st.executeQuery(query);
        } catch (Exception e) {
            System.out.println("Error executing the SQL query.");
            e.printStackTrace();
        }
        finally {
            try {
                st.close();
            } catch (SQLException e) {
                System.out.println("The statement could not be closed.");
                e.printStackTrace();
            }
        }

        return rs;
    }

    @Override
    public void setAutoCommit(boolean set) {
        try {
            System.out.println("Setting autocommitting to " + set);
            con.setAutoCommit(set);
        }
        catch (Exception e) {
            System.out.println("Error setting the auto commit to " + set);
        }
    }

    @Override
    public void commit() {
        try {
            con.commit();
            System.out.println("Committing the changes to the database.");
        }
        catch (Exception e) {
            System.out.println("Error committing the changes to the database.");
        }
    }


    @Override
    public void close() {
        this.closeConnection();
    }

}
