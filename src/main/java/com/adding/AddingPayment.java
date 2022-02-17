package com.adding;

import com.entities.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class AddingPayment {

    public List addPayment(List<String> info) {
        List<Payment> newPayment = new ArrayList<>();
        Payment payment;
        ListIterator<String> iterator = info.listIterator();
        while(iterator.hasNext()) {
            payment = new Payment(Long.parseLong(iterator.next()),
                    Integer.parseInt(iterator.next()),
                    Double.parseDouble(iterator.next()));
            if(iterator.nextIndex() == 3) {
                while (iterator.hasPrevious()) {
                    iterator.previous();
                    iterator.remove();
                }
            }
            newPayment.add(payment);
        }
        return newPayment;
    }
}
