package com;

import com.adding.AddingPayment;
import com.adding.AddingSample;
import com.service.AddressDaoImplementation;
import com.adding.AddingAddress;
import com.adding.RegistrationUser;
import com.service.PaymentDaoImplementation;
import com.service.SampleDaoImplementation;
import com.service.UserDaoImplementation;
import com.transactionprocessing.PaymentProcessing;
import com.workWithFile.ReaderFile;

public class Start {
    public static void main(String[] args) {
        ReaderFile reader = new ReaderFile("C:\\Users\\Евгений\\IdeaProjects\\CourseProject\\src\\main\\resources\\commands.txt");
        UserDaoImplementation userDAO = new UserDaoImplementation();
        PaymentDaoImplementation paymentDao = new PaymentDaoImplementation();
        AddressDaoImplementation addressDao = new AddressDaoImplementation();
        SampleDaoImplementation sampleDao = new SampleDaoImplementation();
        RegistrationUser registrationNewUser = new RegistrationUser();
        AddingPayment payment = new AddingPayment();
        AddingAddress address = new AddingAddress();
        AddingSample sample = new AddingSample();
        PaymentProcessing processing = new PaymentProcessing();
//        userDAO.createNewUsers(registrationNewUser.registerUser(reader.getUsersInfo()));
//        addressDao.createNewAddress(address.addingNewAddress(reader.getAddressInfo()));
//        sampleDao.createNewSample(sample.addingNewSample(reader.getSampleInfo()));
        paymentDao.createNewPayment(payment.addNewPayment(reader.getPaymentInfo()));
//        paymentDao.getPaymentById(200002);

    }
}
