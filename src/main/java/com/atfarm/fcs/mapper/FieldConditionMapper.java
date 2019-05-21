/**
 * 
 */
package com.atfarm.fcs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.atfarm.fcs.model.Vegetation;
import com.atfarm.fcs.model.FCStatistics;
import com.atfarm.fcs.payload.FCSRequest;

/**
 * @author udayakumar.rajan
 *
 */
@Mapper(componentModel = "spring")
public interface FieldConditionMapper {

	 @Mappings({
	      @Mapping(target="vegetation", source="dto.vegetation"),
	      @Mapping(target="occurrenceAt", source="dto.occurrenceAt")
	    })
	Vegetation mapToModel (FCSRequest dto);
	 

	 @Mappings({
	      @Mapping(target="min", source="model.min"),
	      @Mapping(target="max", source="model.max"),
	      @Mapping(target="avg", source="model.avg")
	    })
	com.atfarm.fcs.payload.Vegetation mapToStatistics (FCStatistics model);
}
