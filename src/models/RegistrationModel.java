package models;

import sql.SqliteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationModel {
    Connection conection;
    public RegistrationModel() {
        conection = SqliteConnection.Connector();
        if (conection == null) {

            System.out.println("connection not successful");
            System.exit(1);}
    }

    public void registerUser(String username, String password, String firstName, String lastName, int age){
        PreparedStatement preparedStatement;
        String query = "INSERT INTO users(username, password, first_name, last_name, age) VALUES(?,?,?,?,?)";
        try {
            preparedStatement = conection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4,lastName);
            preparedStatement.setInt(5, age);

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
