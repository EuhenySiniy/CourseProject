package com.entities.adding;

import com.entities.User;
import com.workWithFile.WriteResultInFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class RegistrationUser {

    public List registerUser(List<String> info) {
        Date beforeStart = new Date();
        WriteResultInFile writeResult = new WriteResultInFile();
        writeResult.writeResultInFile("Получены данные о новом пользователе: ");
        List<User> newUsers = new ArrayList<>();
        User newUser;
        ListIterator<String> iterator = info.listIterator();
        while(iterator.hasNext()) {
            newUser = new User(iterator.next(),
                    iterator.next(),
                    iterator.next(),
                    iterator.next(),
                    iterator.next());
            writeResult.writeResultInFile("Имя: " + newUser.getFirstName()
                    + ", отчество: " + newUser.getMiddleName()
                    + ", фамилия: " + newUser.getLastName()
                    + ", email:" + newUser.getEmail()
                    + ", телефон: " + newUser.getTel());
            if(iterator.nextIndex() == 5) {
                while(iterator.hasPrevious()) {
                    iterator.previous();
                    iterator.remove();
                }
            }
            newUsers.add(newUser);
        }
        Date now = new Date();
        long executionTime = now.getTime() - beforeStart.getTime();
        writeResult.writeResultInFile("Время выполнения: " + executionTime + "мс." + "\n");
        return newUsers;
    }
}
