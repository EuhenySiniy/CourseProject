package com;

import com.adding.AddingPayment;
import com.adding.AddingSample;
import com.service.AddressDaoImplementation;
import com.adding.AddingAddress;
import com.adding.RegistrationUser;
import com.service.PaymentDaoImplementation;
import com.service.SampleDaoImplementation;
import com.service.UserDaoImplementation;
import com.processing.PaymentProcessing;
import com.workWithFile.ReaderFile;

public class Start {
    public static void main(String[] args) {
        ReaderFile reader = new ReaderFile("C:\\Users\\Евгений\\IdeaProjects\\CourseProject\\src\\main\\resources\\commands.txt");
        RegistrationUser registrationNewUser = new RegistrationUser();
        AddingAddress address = new AddingAddress();
        AddingSample sample = new AddingSample();
        AddingPayment payment = new AddingPayment();
        UserDaoImplementation userDAO = new UserDaoImplementation();
        AddressDaoImplementation addressDao = new AddressDaoImplementation();
        SampleDaoImplementation sampleDao = new SampleDaoImplementation();
        PaymentDaoImplementation paymentDao = new PaymentDaoImplementation();
        Thread processing = new Thread(new PaymentProcessing());
        try {
            processing.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userDAO.createNewUsers(registrationNewUser.registerUser(reader.getUsersInfo()));
        addressDao.createNewAddress(address.addingNewAddress(reader.getAddressInfo()));
        sampleDao.createNewSample(sample.addingNewSample(reader.getSampleInfo()));
        paymentDao.createNewPayment(payment.addNewPayment(reader.getPaymentInfo()));
    }
}
