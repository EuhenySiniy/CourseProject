package com;

import com.DAO.AddressDaoImplementation;
import com.adding.AddingAddress;
import com.adding.RegistrationUser;
import com.DAO.UserDaoImplementation;
import com.workWithFile.ReaderFile;

public class Start {
    public static void main(String[] args) {
        ReaderFile reader = new ReaderFile("C:\\Users\\Евгений\\IdeaProjects\\CourseProject\\src\\main\\resources\\commands.txt");
        UserDaoImplementation userDAO = new UserDaoImplementation();
        AddressDaoImplementation addressDao = new AddressDaoImplementation();
        RegistrationUser registrationNewUser = new RegistrationUser();
        AddingAddress address = new AddingAddress();
        // userDAO.createNewUsers(registrationNewUser.registerUser(reader.getUsersInfo()));
        // addressDao.createNewAddress(address.addingNewAddress(reader.getAddressInfo()));
        addressDao.deleteAddress("ibookmack@gmail.com");
    }
}
