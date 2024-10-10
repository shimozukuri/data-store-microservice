package project.shimozukuri.datastoremicroservice.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import project.shimozukuri.datastoremicroservice.model.MeasurementType;
import project.shimozukuri.datastoremicroservice.model.Summary;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@ToString
public class SummaryDto {

    private long sensorId;
    private Map<MeasurementType, List<Summary.SummaryEntry>> values;
}
