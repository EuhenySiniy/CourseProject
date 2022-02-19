package com.service;

import com.DAO.PaymentDao;
import com.entities.Payment;
import com.jdbc.Connector;
import com.workWithFile.WriteResultInFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentDaoImplementation implements PaymentDao {
    private WriteResultInFile writeResult;

    @Override
    public boolean createNewPayment(List<Payment> payments) {
        Date beforeStart = new Date();
        writeResult = new WriteResultInFile();
        writeResult.writeResultInFile("Добавляем данные о новом платеже в базу данных: ");
        String sql = "INSERT INTO payments (sample_id, card_number, sum_payment) VALUES (?,?,?)";
        Payment payment;

        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Payment newPayment : payments) {
                payment = newPayment;
                statement.setLong(1, payment.getSampleId());
                statement.setLong(2, payment.getCardNum());
                statement.setDouble(3, payment.getSum());
                statement.execute();
                writeResult.writeResultInFile("id шаблона: " + payment.getSampleId()
                        + " номер карты: " + payment.getCardNum()
                        + " сумма оплаты: " + payment.getSum());
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
                        resultSet.getString(5),
                        resultSet.getTimestamp(6),
                        resultSet.getTimestamp(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }

    @Override
    public boolean updateStatusPayment(List<Payment> payments) {
        String sql = "UPDATE payments SET status = ?, date_status = now() WHERE payment_id = ?";
        Payment payment;

        try(Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            for(Payment value : payments) {
                payment = value;
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
        writeResult = new WriteResultInFile();
        writeResult.writeResultInFile("Получаем платежи со статусом новый: ");
        String sql = "SELECT * FROM payments WHERE status = ?";
        List<Payment> newPayments = new ArrayList<>();
        Payment payment;

        try (Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "New");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                payment = new Payment(resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getLong(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5),
                        resultSet.getTimestamp(6),
                        resultSet.getTimestamp(7));
                newPayments.add(payment);
                writeResult.writeResultInFile(payment.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newPayments;
    }

    @Override
    public boolean getAllPayments() {
        Date beforeStart = new Date();
        writeResult = new WriteResultInFile();
        writeResult.writeResultInFile("Таблица платежей: ");
        String sql = "SELECT * FROM payments";

        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Payment payment = new Payment(resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getLong(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5),
                        resultSet.getTimestamp(6),
                        resultSet.getTimestamp(7));
                writeResult.writeResultInFile(payment.toString());
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
