package project.shimozukuri.datastoremicroservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.shimozukuri.datastoremicroservice.model.MeasurementType;
import project.shimozukuri.datastoremicroservice.model.Summary;
import project.shimozukuri.datastoremicroservice.model.SummaryType;
import project.shimozukuri.datastoremicroservice.model.exception.SensorNotFoundException;
import project.shimozukuri.datastoremicroservice.repository.SummaryRepository;
import project.shimozukuri.datastoremicroservice.service.SummaryService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {

    private final SummaryRepository summaryRepository;

    @Override
    public Summary get(
            long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes
    ) {
        return summaryRepository.findBySensorId(
                        sensorId,
                        measurementTypes == null ? Set.of(MeasurementType.values()) : measurementTypes,
                        summaryTypes == null ? Set.of(SummaryType.values()) : summaryTypes
                )
                .orElseThrow(SensorNotFoundException::new);
    }
}
