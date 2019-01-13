package server.rest.dal;

import shared.logging.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class AccountContext implements IAccountContext {
    @Override
    public String requestPassword(String username) {
        String password = null;
        String query = "SELECT `Password` FROM `user` WHERE `Name` = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement statement = conn.prepareCall(query))
        {
            statement.setString(1, username);
            try (ResultSet result = statement.executeQuery())
            {
                while (result.next())
                {
                    password = result.getString("password");
                }
            }
        }
        catch (Exception e)
        {
            Logger.getInstance().log(e);
        }
        return password;
    }

    @Override
    public boolean userExistsUsername(String username) {
        Boolean response = false;
        String query = "SELECT `Name` FROM `user` WHERE `Name` = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement statement = conn.prepareCall(query))
        {
            statement.setString(1, username);
            try (ResultSet result = statement.executeQuery())
            {
                while (result.next())
                {
                    String r = result.getString("Name");
                    if(r.equals(username)){
                        return true;
                    }
                }
            }
        }
        catch (Exception e)
        {
            Logger.getInstance().log(e);
            return false;
        }
        return response;
    }
}
