package com.adding;

import com.entities.User;
import com.entities.UserAddress;

import java.util.ArrayList;
import java.util.ListIterator;

public class AddingAddress {
    private UserAddress newAddress;
    ArrayList<UserAddress> newAddresses = new ArrayList<>();
    private int id = 1;

    public AddingAddress() {
    }

    public ArrayList addingNewAddress(ArrayList<String> info) {
        ListIterator<String> iterator = info.listIterator();
        while(iterator.hasNext()) {
            newAddress = new UserAddress(id, iterator.next());
            if(iterator.nextIndex() == 1) {
                while(iterator.hasPrevious()) {
                    iterator.previous();
                    iterator.remove();
                }
                id++;
            }
            newAddresses.add(newAddress);
        }
        System.out.println(newAddresses.get(2).getAddressId());
        return newAddresses;
    }
}
