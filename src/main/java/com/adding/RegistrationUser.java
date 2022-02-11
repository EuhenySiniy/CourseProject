package com.adding;

import com.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class RegistrationUser {

    public List registerUser(List<String> info) {
        List<User> newUsers = new ArrayList<>();
        User newUser;
        ListIterator<String> iterator = info.listIterator();
        while(iterator.hasNext()) {
            newUser = new User(iterator.next(),
                                iterator.next(),
                                iterator.next(),
                                iterator.next(),
                                iterator.next());
            if(iterator.nextIndex() == 5) {
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
