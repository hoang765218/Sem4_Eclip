package com.mytech.shopmgmt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.mytech.shopmgmt.db.DbConnector;
import com.mytech.shopmgmt.models.User;

public class UserDao {
    private String SELECT_ALL_USERS = "SELECT * FROM sem4_shop.users;";
    private String INSERT_USER = "INSERT INTO sem4_shop.users (username, password, email, imagePath) VALUES (?, ?, ?, ?);";
    private String UPDATE_IMAGE_PATH = "UPDATE sem4_shop.users SET imagePath = ? WHERE username = ?;";

    public ArrayList<User> getUsers() {
        ArrayList<User> listUsers = new ArrayList<>();
        Connection connection = DbConnector.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                Timestamp lastLogin = resultSet.getTimestamp("lastLogin");
                String imagePath = resultSet.getString("imagePath");

                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.setLastLogin(lastLogin != null ? new java.util.Date(lastLogin.getTime()) : null);
                user.setImagePath(imagePath);

                listUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listUsers;
    }

    public void createUser(User user) throws SQLException {
        Connection connection = DbConnector.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getImagePath());
            preparedStatement.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void updateImagePath(String username, String imagePath) throws SQLException {
        Connection connection = DbConnector.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_IMAGE_PATH);
            preparedStatement.setString(1, imagePath);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}