package com;

import com.adding.AddingSample;
import com.service.AddressDaoImplementation;
import com.adding.AddingAddress;
import com.adding.RegistrationUser;
import com.service.SampleDaoImplementation;
import com.service.UserDaoImplementation;
import com.workWithFile.ReaderFile;

public class Start {
    public static void main(String[] args) {
        ReaderFile reader = new ReaderFile("C:\\Users\\Евгений\\IdeaProjects\\CourseProject\\src\\main\\resources\\commands.txt");
        UserDaoImplementation userDAO = new UserDaoImplementation();
        AddressDaoImplementation addressDao = new AddressDaoImplementation();
        SampleDaoImplementation sampleDao = new SampleDaoImplementation();
        RegistrationUser registrationNewUser = new RegistrationUser();
        AddingAddress address = new AddingAddress();
        AddingSample sample = new AddingSample();
        // userDAO.createNewUsers(registrationNewUser.registerUser(reader.getUsersInfo()));
        // addressDao.createNewAddress(address.addingNewAddress(reader.getAddressInfo()));
        // addressDao.deleteAddress("ibookmack@gmail.com");
        // sample.addingNewSample(reader.getSampleInfo());
        // addressDao.getAllUserAddresses("ibookmack@gmail.com");
        sampleDao.getSample("UA000000000000000000000000000");

    }
}
