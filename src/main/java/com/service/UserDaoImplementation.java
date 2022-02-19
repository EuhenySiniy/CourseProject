package com.service;

import com.DAO.UserDao;
import com.entities.User;
import com.jdbc.Connector;
import com.workWithFile.WriteResultInFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UserDaoImplementation implements UserDao {
    private WriteResultInFile writeResult;

    @Override
    public boolean createNewUsers(List<User> userList) {
        Date beforeStart = new Date();
        writeResult = new WriteResultInFile();
        writeResult.writeResultInFile("Добавляем информацию о новом пользователе в базу данных: ");
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
                writeResult.writeResultInFile("Имя: " + user.getFirstName()
                        + ", отчество: " + user.getMiddleName()
                        + ", фамилия: " + user.getLastName()
                        + ", email:" + user.getEmail()
                        + ", телефон: " + user.getTel());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        Date now = new Date();
        long executionTime = now.getTime() - beforeStart.getTime();
        writeResult.writeResultInFile("Время выполнения: " + executionTime + "мс." + "\n");
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

    @Override
    public boolean getAllUsers() {
        Date beforeStart = new Date();
        writeResult = new WriteResultInFile();
        writeResult.writeResultInFile("Таблица пользователей: ");
        String sql = "SELECT * FROM users";

        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
                writeResult.writeResultInFile(user.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        Date now = new Date();
        long executionTime = now.getTime() - beforeStart.getTime();
        writeResult.writeResultInFile("Время выполнения: " + executionTime + "мс." + "\n");
        return true;
    }
}
