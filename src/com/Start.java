package com;

import com.adding.AddingAddress;
import com.adding.RegistrationUser;
import com.workWithFile.ReaderFile;

public class Start {
    public static void main(String[] args) {
        ReaderFile reader = new ReaderFile("C:\\Users\\Евгений\\Desktop\\newUser.txt");
        RegistrationUser reg = new RegistrationUser();
        AddingAddress addAddr = new AddingAddress();
        reg.registerUser(reader.registrationUser());
        addAddr.addingNewAddress(reader.addingAddress());

    }
}
