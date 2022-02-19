package com.service;

import com.DAO.UserDao;
import com.entities.User;
import com.jdbc.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImplementation implements UserDao {
    @Override
    public boolean createNewUsers(List<User> userList) {
        String sql = "INSERT INTO users (first_name, middle_name, last_name, email, tel) VALUES (?,?,?,?,?)";

        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (User user : userList) {
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getMiddleName());
                statement.setString(3, user.getLastName());
                statement.setString(4, user.getEmail());
                statement.setString(5, user.getTel());
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public User getUserByLogin(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        User user = null;

        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                user = new User(resultSet.getLong("user_id"),
                        resultSet.getString("last_name"),
                        resultSet.getString("first_name"),
                        resultSet.getString("middle_name"),
                        resultSet.getString("email"),
                        resultSet.getString("tel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean deleteUserByLogin(String email) {
        String sql = "DELETE FROM users WHERE email = ?";

        try (Connection connection = Connector.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
