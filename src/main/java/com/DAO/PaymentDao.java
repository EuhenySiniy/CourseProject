package com.DAO;

import com.entities.Payment;
import com.entities.UserAddress;

import java.util.List;

public interface PaymentDao {
    boolean createNewPayment(List<Payment> payments);

    Payment getPaymentById(long paymentId);

    boolean updateStatusPayment(List<Payment> payments);

    List<Payment> getNewPayments();

    boolean getAllPayments();
}
