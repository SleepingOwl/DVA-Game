package models;

import sql.SqliteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    Connection connection;
    public LoginModel() {
        connection = SqliteConnection.Connector();
        if (connection == null) {

            System.out.println("connection not successful");
            System.exit(1);}
    }

    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
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
            preparedStatement = connection.prepareStatement(query);
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

    public int getUserId(String username){
        int id = 0;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM users WHERE username = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            id = resultSet.getInt("id_user");
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return id;
    }
}
