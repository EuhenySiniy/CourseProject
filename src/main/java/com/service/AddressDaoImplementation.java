package com.service;

import com.DAO.AddressDao;
import com.entities.UserAddress;
import com.jdbc.Connector;
import com.workWithFile.WriteResultInFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddressDaoImplementation implements AddressDao {
    private WriteResultInFile writeResult;

    private long getUserId(String email) {
        long userId = 0;
        String sql = "SELECT user_id FROM users WHERE email = ?";

        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            userId = resultSet.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    @Override
    public boolean createNewAddress(List<UserAddress> addressInfo) {
        Date beforeStart = new Date();
        writeResult = new WriteResultInFile();
        writeResult.writeResultInFile("Добавляем адресс в базу данных: ");
        String sql = "INSERT INTO addresses (city, street, house_num, apartment_num, user_id) VALUES (?,?,?,?,?)";
        UserAddress address;

            try (Connection connection = Connector.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                for (UserAddress userAddress : addressInfo) {
                    address = userAddress;
                    long userId = getUserId(address.getUserLogin());
                    statement.setString(1, address.getCity());
                    statement.setString(2, address.getStreet());
                    statement.setString(3, address.getHouseNum());
                    statement.setInt(4, address.getApartmentNum());
                    statement.setLong(5, userId);
                    statement.execute();
                    writeResult.writeResultInFile("Город: " + address.getCity()
                            + ", улица: " + address.getStreet()
                            + ", номер дома: " + address.getHouseNum()
                            + ", квартира: " + address.getApartmentNum()
                            + ", id пользователя: " + userId);
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
    public List<UserAddress> getAllUserAddresses(String email) {
        String sql = "SELECT address_id, city, street, house_num, apartment_num FROM addresses WHERE user_id = ?";
        long userId = getUserId(email);
        UserAddress userAddress;
        List<UserAddress> userAddresses = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
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
        }
        return userAddresses;
    }

    @Override
    public boolean deleteAddressById(long addressId) {
        String sql = "DELETE FROM addresses WHERE address_id = ?";

        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, addressId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean getAllAddress() {
        Date beforeStart = new Date();
        writeResult = new WriteResultInFile();
        writeResult.writeResultInFile("Таблица адресов: ");
        String sql = "SELECT * FROM addresses";

        try (Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserAddress address = new UserAddress(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getLong(6));
                writeResult.writeResultInFile(address.toString());
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
