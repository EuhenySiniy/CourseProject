package com;

import com.entities.adding.AddingPayment;
import com.entities.adding.AddingSample;
import com.service.AddressDaoImplementation;
import com.entities.adding.AddingAddress;
import com.entities.adding.RegistrationUser;
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
        userDAO.createNewUsers(registrationNewUser.registerUser(reader.getUsersInfo()));
        addressDao.createNewAddress(address.addingNewAddress(reader.getAddressInfo()));
        sampleDao.createNewSample(sample.addingNewSample(reader.getSampleInfo()));
        paymentDao.createNewPayment(payment.addNewPayment(reader.getPaymentInfo()));
        try {
            processing.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
