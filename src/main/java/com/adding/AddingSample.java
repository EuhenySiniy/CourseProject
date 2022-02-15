package com.adding;

import com.entities.Sample;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class AddingSample {

    public List addingNewSample(List<String> info) {
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
            if (iterator.nextIndex() == 7) {
                while(iterator.hasPrevious()) {
                    iterator.previous();
                    iterator.remove();
                };
            }
            newSamples.add(newSample);
        }
        return newSamples;
    }
}
