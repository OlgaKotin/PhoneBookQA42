package db;

import models.Contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseConnection {

    public void contactDatabaseRecorder(String id, Contact contact) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/phonebook";
        String username = "root";
        String password = "Ytpyfrjvrf05_";

        Connection connection = DriverManager.getConnection(url, username, password);
        String insertQuery = "INSERT INTO contacts(id, name, lastName, email, phone, address, description)"
                +"VALUES(?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE "
                +"name = VALUES(name), lastName = VALUES(lastName), email = VALUES(email), phone = VALUES(phone), address = VALUES(address), description = VALUES(description)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, contact.getName());
        preparedStatement.setString(3, contact.getLastName());
        preparedStatement.setString(4, contact.getEmail());
        preparedStatement.setString(5, contact.getPhone());
        preparedStatement.setString(6, contact.getAddress());
        preparedStatement.setString(7, contact.getDescription());
        int rows = preparedStatement.executeUpdate();
        if(rows > 0) {
            System.out.println("Insert successful!");
        } else {
            System.out.println("Not successful...");
        }
        preparedStatement.close();
        connection.close();
    }
}
