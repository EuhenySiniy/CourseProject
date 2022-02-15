package com.DAO;

import com.entities.Sample;

import java.util.List;

public interface SampleDao {
    boolean createNewSample(List<Sample> sampleInfo);

    Sample getSample(String iban);

    boolean updateSample(Sample sample);

    boolean deleteSample(String email, String street, int apartNum, String iban);
}
