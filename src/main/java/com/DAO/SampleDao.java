package com.DAO;

import com.entities.Sample;

import java.util.List;

public interface SampleDao {
    boolean createNewSample(List<Sample> sampleInfo);

    Sample getSampleById(long id);

    boolean deleteSampleById(long id);
}
