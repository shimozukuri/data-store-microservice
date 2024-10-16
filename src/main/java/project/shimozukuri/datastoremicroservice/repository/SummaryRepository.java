package project.shimozukuri.datastoremicroservice.repository;

import project.shimozukuri.datastoremicroservice.model.Data;
import project.shimozukuri.datastoremicroservice.model.MeasurementType;
import project.shimozukuri.datastoremicroservice.model.Summary;
import project.shimozukuri.datastoremicroservice.model.SummaryType;

import java.util.Optional;
import java.util.Set;

public interface SummaryRepository {

    Optional<Summary> findBySensorId(
            long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes
    );

    void handle(
            Data data
    );
}
