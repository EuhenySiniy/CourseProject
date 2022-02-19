package com.service;

import com.DAO.SampleDao;
import com.entities.Sample;
import com.jdbc.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SampleDaoImplementation implements SampleDao {
    private long getAddressId(String email, String street, int apartNum) {
        long addressId = 0;
        long userId;
        String getUserId = "SELECT user_id FROM users WHERE email = ?";
        String getAddressId = "SELECT address_id FROM addresses WHERE user_id = ? AND street = ? AND apartment_num = ?";

        try (Connection connection = Connector.getConnection();
             PreparedStatement statementUserId = connection.prepareStatement(getUserId);
             PreparedStatement statementAddressId = connection.prepareStatement(getAddressId)) {
            statementUserId.setString(1, email);
            ResultSet resultSet = statementUserId.executeQuery();
            resultSet.next();
            userId = resultSet.getLong(1);
            statementAddressId.setLong(1, userId);
            statementAddressId.setString(2, street);
            statementAddressId.setInt(3, apartNum);
            resultSet = statementAddressId.executeQuery();
            resultSet.next();
            addressId = resultSet.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressId;
    }

    @Override
    public boolean createNewSample(List<Sample> sampleInfo) {
        String sql = "INSERT INTO samples (name, iban, okpo, appointment, address_id) VALUES (?,?,?,?,?)";
        Sample sample;

        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Sample value : sampleInfo) {
                sample = value;
                long addressId = getAddressId(sample.getUserLogin(), sample.getStreet(), sample.getApartNum());
                statement.setString(1, sample.getSampleName());
                statement.setString(2, sample.getIban());
                statement.setString(3, sample.getOkpo());
                statement.setString(4, sample.getAppointment());
                statement.setLong(5, addressId);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Sample getSampleById(long id) {
        String sql = "SELECT sample_id, name, iban, okpo, appointment FROM samples WHERE sample_id = ?";
        Sample sample = null;

        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            sample = new Sample(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sample;
    }

    @Override
    public boolean deleteSampleById(long id) {
        String sql = "DELETE FROM samples WHERE sample_id = ?";

        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
