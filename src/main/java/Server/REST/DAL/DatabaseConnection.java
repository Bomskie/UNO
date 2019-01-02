package Server.REST.DAL;

import Shared.Logging.LogLevel;
import Shared.Logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String CONNECTIONSTRING = "jdbc:mysql://localhost/uno?useSSL=false";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection;

    private DatabaseConnection()
    {
        throw new IllegalStateException("DatabaseConnection class");
    }

    public static Connection getConnection()
    {
        try
        {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(CONNECTIONSTRING, "root", "");
        }
        catch (SQLException ex)
        {
            Logger.getInstance().log("Error: unable to connect to database.", LogLevel.ERROR);
            Logger.getInstance().log(ex);
        }
        catch (Exception ex)
        {
            Logger.getInstance().log(ex);
        }
        return connection;
    }
}
