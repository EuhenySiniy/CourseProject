package com.entities.adding;

import com.entities.UserAddress;
import com.workWithFile.WriteResultInFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class AddingAddress {

    public List addingNewAddress(List<String> info) {
        Date beforeStart = new Date();
        WriteResultInFile writeResult = new WriteResultInFile();
        writeResult.writeResultInFile("Получены данные о новом адресе: ");
        UserAddress newAddress;
        List<UserAddress> newAddresses = new ArrayList<>();
        ListIterator<String> iterator = info.listIterator();
        while(iterator.hasNext()) {
            newAddress = new UserAddress(iterator.next(),
                    iterator.next(),
                    iterator.next(),
                    Integer.parseInt(iterator.next()),
                    iterator.next());
            writeResult.writeResultInFile("Город: " + newAddress.getCity()
                    + ", улица: " + newAddress.getStreet()
                    + ", номер дома: " + newAddress.getHouseNum()
                    + ", квартира: " + newAddress.getApartmentNum()
                    + ", логин пользователя: " + newAddress.getUserLogin());
            if(iterator.nextIndex() == 5) {
                while(iterator.hasPrevious()) {
                    iterator.previous();
                    iterator.remove();
                }
            }
            newAddresses.add(newAddress);
        }
        Date now = new Date();
        long executionTime = now.getTime() - beforeStart.getTime();
        writeResult.writeResultInFile("Время выполнения: " + executionTime + "мс." + "\n");
        return newAddresses;
    }
}
