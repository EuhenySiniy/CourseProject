package com.transactionprocessing;

import com.entities.Payment;
import com.service.PaymentDaoImplementation;

import java.util.*;

public class PaymentProcessing implements Runnable{
    private static final String SUCCESS_PAYMENT = "Paid";
    private static final String FAILED_PAYMENT = "Rejected";
    private boolean newIsPresent = true;
    Thread thread;

    public PaymentProcessing() {
        thread = new Thread(this::run);
        thread.start();
    }

    @Override
    public void run() {
        while (newIsPresent) {
            checkNewPayment();
        }
    }

    public synchronized void checkNewPayment() {
        newIsPresent = false;
        PaymentDaoImplementation paymentDao = new PaymentDaoImplementation();
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Payment> newPayment = paymentDao.getNewPayments();
        if(newPayment.size() > 0) {
            newPaymentProcessing(newPayment);
            newIsPresent = true;
        }
    }

    public synchronized void newPaymentProcessing(List<Payment> payments) {
        PaymentDaoImplementation paymentDao = new PaymentDaoImplementation();
        Random random = new Random();
        List<Payment> updatePayments = new ArrayList<>();
        Payment newPayment;
        int result;
        for (Payment payment : payments) {
            newPayment = payment;
            result = random.nextInt(3);
            if (result == 1) {
                newPayment.setStatus(SUCCESS_PAYMENT);
            } else if (result == 2) {
                newPayment.setStatus(FAILED_PAYMENT);
            }
            updatePayments.add(newPayment);
        }
        paymentDao.updateStatusPayment(updatePayments);
    }
}
