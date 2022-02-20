package com.entities.adding;

import com.entities.Sample;
import com.workWithFile.WriteResultInFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class AddingSample {

    public List addingNewSample(List<String> info) {
        Date beforeStart = new Date();
        WriteResultInFile writeResult = new WriteResultInFile();
        writeResult.writeResultInFile("Получены данные о новом шаблоне: ");
        Sample newSample;
        List<Sample> newSamples = new ArrayList<Sample>();
        ListIterator<String> iterator = info.listIterator();
        while (iterator.hasNext()) {
            newSample = new Sample(iterator.next(),
                    iterator.next(),
                    iterator.next(),
                    iterator.next(),
                    iterator.next(),
                    iterator.next(),
                    Integer.parseInt(iterator.next()));
            writeResult.writeResultInFile("Наименование: " + newSample.getSampleName()
                    + ", Iban: " + newSample.getIban()
                    + ", ОКПО: " + newSample.getOkpo()
                    + ", назначение платежа: " + newSample.getAppointment()
                    + ", логин: " + newSample.getUserLogin()
                    + ", улица: " + newSample.getStreet()
                    + ", номер квартиры: " + newSample.getApartNum());
            if (iterator.nextIndex() == 7) {
                while(iterator.hasPrevious()) {
                    iterator.previous();
                    iterator.remove();
                };
            }
            newSamples.add(newSample);
        }
        Date now = new Date();
        long executionTime = now.getTime() - beforeStart.getTime();
        writeResult.writeResultInFile("Время выполнения: " + executionTime + "мс." + "\n");
        return newSamples;
    }
}
