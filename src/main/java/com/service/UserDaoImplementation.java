package com.service;

import com.DAO.UserDao;
import com.entities.User;
import com.jdbc.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

public class UserDaoImplementation implements UserDao {
    @Override
    public boolean createNewUsers(List<User> userList) {
        String sql = "INSERT INTO users (first_name, middle_name, last_name, email, tel) VALUES (?,?,?,?,?)";
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        User user;
        ListIterator<User> iterator = userList.listIterator();

        while (iterator.hasNext()) {
            user = iterator.next();
            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getMiddleName());
                statement.setString(3, user.getLastName());
                statement.setString(4, user.getEmail());
                statement.setString(5, user.getTel());
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                Connector.closeConnection();
                if(statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return true;
    }

    @Override
    public User getUserFromDB(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                user = new User(resultSet.getString("last_name"),
                                resultSet.getString("first_name"),
                                resultSet.getString("middle_name"),
                                resultSet.getString("email"),
                                resultSet.getString("tel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releasingResources(statement, resultSet);
        }
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        /* TODO */
        return false;
    }

    @Override
    public boolean deleteUser(String email) {
        String sql = "DELETE FROM users WHERE email = ?";
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Connector.closeConnection();
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    private void releasingResources(PreparedStatement statement, ResultSet resultSet) {
        Connector.closeConnection();
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
