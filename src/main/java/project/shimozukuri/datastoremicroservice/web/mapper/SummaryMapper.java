package project.shimozukuri.datastoremicroservice.web.mapper;

import org.mapstruct.Mapper;
import project.shimozukuri.datastoremicroservice.model.Summary;
import project.shimozukuri.datastoremicroservice.web.dto.SummaryDto;

@Mapper(componentModel = "spring")
public interface SummaryMapper extends Mappable<Summary, SummaryDto> {
}
