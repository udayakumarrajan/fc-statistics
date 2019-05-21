/**
 * 
 */
package com.atfarm.fcs.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.atfarm.fcs.builder.FieldBuilder;
import com.atfarm.fcs.exception.BadRequestException;
import com.atfarm.fcs.exception.NoDataFoundException;
import com.atfarm.fcs.payload.FCSRequest;
import com.atfarm.fcs.payload.FCSResponse;
import static com.atfarm.fcs.util.DateUtil.addDays;

import reactor.core.publisher.Mono;

/**
 * @author udayakumar.rajan
 *
 */
@Service
public class FieldService {
	
	private final Logger logger = LoggerFactory.getLogger(FieldService.class);
	
	
	@Autowired
	private FieldBuilder builder;
	
	@Value("#{new Long('${fcs.statistics.days}')}")
	private long satisticsDays;

	/**
	 * Stored requested field condition payload into database
	 * @param request
	 * @return
	 * @throws BadRequestException
	 */
	public Mono<FCSResponse> storeFieldCondition(FCSRequest request) throws BadRequestException {
		Double vegetation = request.getVegetation();
		Date occurrenceAt = request.getOccurrenceAt();
		validateRequest(request);
		logger.info(" validating the incoming request.");
		logger.debug("data received vegetation: {}, occurrance at : {}", vegetation.toString(),
				occurrenceAt.toString());
		logger.info("validation is successful. calling fcs repo to add data.");
		return builder.with(request).save();

	}
	
	/**
	 * To get statistics information by configured dates
	 * @return
	 * @throws NoDataFoundException
	 */
	public Mono<FCSResponse> getFieldStatistics () throws NoDataFoundException {
		logger.info("calling service to get historical field condition statistics");
		Date to = new Date();
		Date from = addDays(to, satisticsDays);
		return builder.with("occurrenceAt", from, to).get();
	}
	
	/**
	 * To validate the incoming payload
	 * @param request
	 * @throws BadRequestException
	 */
	private void validateRequest (FCSRequest request) throws BadRequestException {
		logger.debug("inside validate request method");
		if (request.getOccurrenceAt() == null)
			throw new BadRequestException("Occurance Date is missing");
		if (request.getVegetation() == null)
			throw new BadRequestException("Vegetation is missing");
	}

}
