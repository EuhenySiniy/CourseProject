package com.DAO;

import com.entities.Payment;

import java.util.List;

public interface PaymentDao {
    boolean createNewPayment(List<Payment> payments);

    Payment getPaymentById(long paymentId);

    boolean updateStatusPayment(List<Payment> payments);

    List<Payment> getNewPayments();
}
