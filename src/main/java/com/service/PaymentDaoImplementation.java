package com.service;

import com.DAO.PaymentDao;
import com.entities.Payment;
import com.jdbc.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImplementation implements PaymentDao {
    @Override
    public boolean createNewPayment(List<Payment> payments) {
        String sql = "INSERT INTO payments (sample_id, card_number, sum_payment) VALUES (?,?,?)";


        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Payment newPayment : payments) {
                statement.setLong(1, newPayment.getSampleId());
                statement.setLong(2, newPayment.getCardNum());
                statement.setDouble(3, newPayment.getSum());
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Payment getPaymentById(long paymentId) {
        String sql = "SELECT * FROM payments WHERE payment_id = ?";
        Payment payment = null;

        try (Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, paymentId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                payment = new Payment(resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getLong(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5));
                System.out.println(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }

    @Override
    public boolean updateStatusPayment(List<Payment> payments) {
        String sql = "UPDATE payments SET status = ?, date_status = now() WHERE payment_id = ?";

        try(Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            for(Payment payment : payments) {
                statement.setString(1, payment.getStatus());
                statement.setLong(2, payment.getPaymentId());
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Payment> getNewPayments() {
        String sql = "SELECT * FROM payments WHERE status = ?";
        List<Payment> newPayments = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "New");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Payment payment = new Payment(resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getLong(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5));
                newPayments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(newPayments.size());
        return newPayments;
    }
}
