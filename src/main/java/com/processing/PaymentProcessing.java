package com.processing;

import com.DAO.AddressDao;
import com.DAO.SampleDao;
import com.DAO.UserDao;
import com.entities.Payment;
import com.service.AddressDaoImplementation;
import com.service.PaymentDaoImplementation;
import com.service.SampleDaoImplementation;
import com.service.UserDaoImplementation;
import com.workWithFile.WriteResultInFile;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class PaymentProcessing implements Runnable{
    private static final String SUCCESS_PAYMENT = "Paid";
    private static final String FAILED_PAYMENT = "Rejected";
    private boolean newIsPresent = true;
    private WriteResultInFile writeResult;
    private UserDao userDAO = new UserDaoImplementation();
    private AddressDao addressDao = new AddressDaoImplementation();
    private SampleDao sampleDao = new SampleDaoImplementation();
    private PaymentDaoImplementation paymentDao = new PaymentDaoImplementation();

    public PaymentProcessing() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (newIsPresent) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkNewPayment();
        }
        userDAO.getAllUsers();
        addressDao.getAllAddress();
        sampleDao.getAllSamples();
        paymentDao.getAllPayments();
    }

    public synchronized void checkNewPayment() {
        Date beforeStart = new Date();
        writeResult = new WriteResultInFile();
        newIsPresent = false;
        PaymentDaoImplementation paymentDao = new PaymentDaoImplementation();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant = timestamp.toInstant();
        List<Payment> newPayment = paymentDao.getNewPayments();
        if(newPayment.size() > 0) {
            List<Payment> afterTwoSeconds = newPayment.stream()
                    .filter(payment -> payment.getDateCreate().toInstant().plusSeconds(2)
                            .isBefore(instant))
                    .collect(Collectors.toList());
            newPaymentProcessing(afterTwoSeconds);
            newIsPresent = true;
        } else {
            writeResult.writeResultInFile("Новых платежей нет.");
        }
        Date now = new Date();
        long executionTime = now.getTime() - beforeStart.getTime();
        writeResult.writeResultInFile("Время проверки платежей: " + executionTime + "мс." + "\n");
    }

    public synchronized void newPaymentProcessing(List<Payment> payments) {
        Date beforeStart = new Date();
        writeResult = new WriteResultInFile();
        writeResult.writeResultInFile("Проводим платежи: ");
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
            writeResult.writeResultInFile("Платеж id: " + newPayment.getPaymentId()
                    + " статус оплаты: " + newPayment.getStatus());
        }
        paymentDao.updateStatusPayment(updatePayments);
        Date now = new Date();
        long executionTime = now.getTime() - beforeStart.getTime();
        writeResult.writeResultInFile("Время выполнения платежей: " + executionTime + "мс.");
    }
}
