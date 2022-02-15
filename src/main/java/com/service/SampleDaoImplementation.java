package com.service;

import com.DAO.SampleDao;
import com.entities.Sample;
import com.jdbc.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

public class SampleDaoImplementation implements SampleDao {
    private int getAddressId(String email, String street, int apartNum) {
        int addressId = 0;
        int userId;
        String getUserId = "SELECT user_id FROM users WHERE email = ?";
        String getAddrId = "SELECT address_id FROM addresses WHERE user_id = ? AND street = ? AND apartment_num = ?";
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(getUserId);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            resultSet.next();
            userId = resultSet.getInt(1);
            statement = connection.prepareStatement(getAddrId);
            statement.setInt(1, userId);
            statement.setString(2, street);
            statement.setInt(3, apartNum);
            resultSet = statement.executeQuery();
            resultSet.next();
            addressId = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releasingResources(statement, resultSet);
        }
        return addressId;
    }

    @Override
    public boolean createNewSample(List<Sample> sampleInfo) {
        String sql = "INSERT INTO samples (name, iban, okpo, appointment, address_id) VALUES (?,?,?,?,?)";
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Sample sample;
        ListIterator<Sample> iterator = sampleInfo.listIterator();

        while (iterator.hasNext()) {
            sample = iterator.next();
            int addressId = getAddressId(sample.getUserLogin(), sample.getStreet(), sample.getApartNum());
            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, sample.getSampleName());
                statement.setString(2, sample.getIban());
                statement.setString(3, sample.getOkpo());
                statement.setString(4, sample.getAppointment());
                statement.setInt(5, addressId);
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
    public Sample getSample(String iban) {
        String sql = "SELECT sample_id, name, iban, okpo, appointment FROM samples WHERE iban = ?";
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Sample sample = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, iban);
            resultSet = statement.executeQuery();
            resultSet.next();
            sample = new Sample(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releasingResources(statement, resultSet);
        }
        System.out.println(sample);
        return sample;
    }

    @Override
    public boolean updateSample(Sample sample) {
        /*Todo*/
        return false;
    }

    @Override
    public boolean deleteSample(String email, String street, int apartNum, String iban) {
        String sql = "DELETE FROM samples WHERE iban = ? AND address_id = ?";
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        int addressId = getAddressId(email, street, apartNum);

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, iban);
            statement.execute();
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
