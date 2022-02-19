package com.adding;

import com.entities.Payment;
import com.workWithFile.WriteResultInFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class AddingPayment {

    public List addNewPayment(List<String> info) {
        Date beforeStart = new Date();
        WriteResultInFile writeResult = new WriteResultInFile();
        writeResult.writeResultInFile("Получены данные о новом платеже: ");
        List<Payment> newPayment = new ArrayList<>();
        Payment payment;
        ListIterator<String> iterator = info.listIterator();
        while(iterator.hasNext()) {
            payment = new Payment(Long.parseLong(iterator.next()),
                    Long.parseLong(iterator.next()),
                    Double.parseDouble(iterator.next()));
            writeResult.writeResultInFile("id шаблона: " + payment.getSampleId()
                    + " номер карты: " + payment.getCardNum()
                    + " сумма оплаты: " + payment.getSum());
            if(iterator.nextIndex() == 3) {
                while (iterator.hasPrevious()) {
                    iterator.previous();
                    iterator.remove();
                }
            }
            newPayment.add(payment);
        }
        Date now = new Date();
        long executionTime = now.getTime() - beforeStart.getTime();
        writeResult.writeResultInFile("Время выполнения: " + executionTime + "мс." + "\n");
        return newPayment;
    }
}
