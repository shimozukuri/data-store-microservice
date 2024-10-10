package project.shimozukuri.datastoremicroservice.service;

import project.shimozukuri.datastoremicroservice.model.MeasurementType;
import project.shimozukuri.datastoremicroservice.model.Summary;
import project.shimozukuri.datastoremicroservice.model.SummaryType;

import java.util.Set;

public interface SummaryService {

    Summary get(
            long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes
    );
}
