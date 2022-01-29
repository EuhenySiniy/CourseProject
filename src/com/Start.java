package com;

import com.adding.RegistrationUser;
import com.entities.User;

import java.io.FileNotFoundException;

public class Start {
    public static void main(String[] args) throws FileNotFoundException {
        RegistrationUser regUs = new RegistrationUser();
        regUs.checkResult();
    }
}
