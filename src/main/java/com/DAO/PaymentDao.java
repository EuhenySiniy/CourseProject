package com.DAO;

import com.entities.Payment;

import java.util.List;

public interface PaymentDao {
    boolean createNewPayment(List<Payment> payments);

    Payment getPayment(long paymentId);

    boolean updatePaymentStatus(long paymentId);

    boolean deletePayment(long paymentId);
}
