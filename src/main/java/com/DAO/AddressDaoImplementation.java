package com.DAO;

import com.entities.UserAddress;
import com.jdbc.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

public class AddressDaoImplementation implements AddressDao{
    @Override
    public boolean createNewAddress(List<UserAddress> addressInfo) {
        String sql = "INSERT INTO addresses (city, street, house_num, apartment_num, user_id) VALUES (?,?,?,?,?)";
        String getUserId = "SELECT user_id FROM users WHERE email = ?";
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        UserAddress address = null;
        ListIterator<UserAddress> iterator = addressInfo.listIterator();

        while (iterator.hasNext()) {
            address = iterator.next();
            try {
                statement = connection.prepareStatement(getUserId);
                statement.setString(1, address.getUserLogin());
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int userId = resultSet.getInt(1);
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, address.getCity());
                    statement.setString(2, address.getStreet());
                    statement.setString(3, address.getHouseNum());
                    statement.setInt(4, address.getApartmentNum());
                    statement.setInt(5, userId);
                    statement.execute();
                }
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
                if(resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return true;
    }

    @Override
    public UserAddress getUserAddress(String email) {
        String sql = "SELECT * FROM addresses WHERE user_id = ?";
        String getUserId = "SELECT user_id FROM users WHERE email = ?";
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        UserAddress address = null;

        try {
            statement = connection.prepareStatement(getUserId);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            resultSet.next();
            int userId = resultSet.getInt(1);
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            resultSet.next();
            address = new UserAddress(resultSet.getInt("address_id"),
                    resultSet.getString("City"),
                    resultSet.getString("street"),
                    resultSet.getString("house_num"),
                    resultSet.getInt("apartment_num"));
            System.out.println(address);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

        return address;
    }

    @Override
    public boolean updateAddress(UserAddress address) {
        /* TODO */
        return false;
    }

    @Override
    public boolean deleteAddress(String email) {
        String sql = "DELETE FROM addresses WHERE user_id = ?";
        String getUserId = "SELECT user_id FROM users WHERE email = ?";
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(getUserId);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            resultSet.next();
            int userId = resultSet.getInt(1);
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
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
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
