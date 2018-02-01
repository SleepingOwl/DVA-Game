package models;

import sql.SqliteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    Connection conection;
    public LoginModel() {
        conection = SqliteConnection.Connector();
        if (conection == null) {

            System.out.println("connection not successful");
            System.exit(1);}
    }

    public boolean isDbConnected() {
        try {
            return !conection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isLogin(String user, String pass) throws SQLException {
        boolean f = false;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            preparedStatement = conection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString("username") != null && resultSet.getString("password") != null)
                    f = true;
            }

            resultSet.close();
            preparedStatement.close();
        }  catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return f;
    }
}
