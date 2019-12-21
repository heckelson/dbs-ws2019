import java.sql.*;

public interface DBAccessor {

    // executes the query as-is in the connection.
    // DANGER: these queries are not being checked before being sent to the database. Do not toy around with this!
    ResultSet executeQuery(String query);

    // turns Autocommit on or off
    void setAutoCommit(boolean set);

    void commit();

    // Your one-stop shop to close everything. Goodbye!
    void close();
}
