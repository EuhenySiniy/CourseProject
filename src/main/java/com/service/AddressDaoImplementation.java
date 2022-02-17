package com.service;

import com.DAO.AddressDao;
import com.entities.UserAddress;
import com.jdbc.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class AddressDaoImplementation implements AddressDao {
    private long getUserId(String email) {
        long userId = 0;
        String sql = "SELECT user_id FROM users WHERE email = ?";
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            resultSet.next();
            userId = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releasingResources(statement, resultSet);
        }

        return userId;
    }

    @Override
    public boolean createNewAddress(List<UserAddress> addressInfo) {
        String sql = "INSERT INTO addresses (city, street, house_num, apartment_num, user_id) VALUES (?,?,?,?,?)";
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        UserAddress address;
        ListIterator<UserAddress> iterator = addressInfo.listIterator();

        while (iterator.hasNext()) {
            address = iterator.next();
            long userId = getUserId(address.getUserLogin());
            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, address.getCity());
                statement.setString(2, address.getStreet());
                statement.setString(3, address.getHouseNum());
                statement.setInt(4, address.getApartmentNum());
                statement.setLong(5, userId);
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
    public UserAddress getUserAddress(String email, String street, int apartNum) {
        String sql = "SELECT address_id, city, street, house_num, apartment_num FROM addresses WHERE user_id = ? AND street = ? AND apartment_num = ?";
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        UserAddress address = null;
        long userId = getUserId(email);

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);
            statement.setString(2, street);
            statement.setInt(3, apartNum);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                address = new UserAddress(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releasingResources(statement, resultSet);
        }
        return address;
    }

    @Override
    public List<UserAddress> getAllUserAddresses(String email) {
        String sql = "SELECT address_id, city, street, house_num, apartment_num FROM addresses WHERE user_id = ?";
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long userId = getUserId(email);
        UserAddress userAddress;
        List<UserAddress> userAddresses = new ArrayList<>();

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                userAddress = new UserAddress(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5));
                userAddresses.add(userAddress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releasingResources(statement, resultSet);
        }
        return userAddresses;
    }

    @Override
    public boolean updateAddress(UserAddress address) {
        /* TODO */
        return false;
    }

    @Override
    public boolean deleteAddress(String email, String street, int apartNum) {
        String sql = "DELETE FROM addresses WHERE user_id = ? AND street = ? AND apartment_num = ?";
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        long userId = getUserId(email);

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);
            statement.setString(2, street);
            statement.setInt(3, apartNum);
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
