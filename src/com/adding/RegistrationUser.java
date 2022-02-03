package com.adding;

import com.entities.User;

import java.util.ArrayList;
import java.util.ListIterator;

public class RegistrationUser {
    private User newUser;
    ArrayList<User> newUsers = new ArrayList<>();

    public RegistrationUser() {
    }

    public ArrayList registerUser(ArrayList<String> info) {
        ListIterator<String> iterator = info.listIterator();
        while(iterator.hasNext()) {
            newUser = new User(iterator.next(), iterator.next(), iterator.next());
            if(iterator.nextIndex() == 3) {
                while(iterator.hasPrevious()) {
                    iterator.previous();
                    iterator.remove();
                }
            }
            newUsers.add(newUser);
        }
        return newUsers;
    }
}
