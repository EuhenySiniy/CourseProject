package com.adding;

import com.entities.UserAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class AddingAddress {
    private UserAddress newAddress;

    public AddingAddress() {
    }

    public List addingNewAddress(List<String> info) {
        List<UserAddress> newAddresses = new ArrayList<>();
        ListIterator<String> iterator = info.listIterator();
        while(iterator.hasNext()) {
            newAddress = new UserAddress(iterator.next(),
                                        iterator.next(),
                                        iterator.next(),
                                        Integer.parseInt(iterator.next()),
                                        iterator.next());
            if(iterator.nextIndex() == 5) {
                while(iterator.hasPrevious()) {
                    iterator.previous();
                    iterator.remove();
                };
            }
            newAddresses.add(newAddress);
        }
        return newAddresses;
    }
}
