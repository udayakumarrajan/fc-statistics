/**
 * 
 */
package com.atfarm.fcs.builder;

import java.util.Date;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.atfarm.fcs.exception.NoDataFoundException;
import com.atfarm.fcs.mapper.FieldConditionMapper;
import com.atfarm.fcs.model.Vegetation;
import com.atfarm.fcs.model.FCStatistics;
import com.atfarm.fcs.payload.FCSRequest;
import com.atfarm.fcs.payload.FCSResponse;
import com.atfarm.fcs.repository.VegetationRepository;

import reactor.core.publisher.Mono;

/**
 * @author udayakumar.rajan
 *
 */
@Component
@Scope(value = "prototype")
public class FieldBuilder {

	private static final Logger logger = LoggerFactory.getLogger(FieldBuilder.class);

	@Autowired
	private VegetationRepository repository;
	
	@Autowired
	private FieldConditionMapper mapper;


	private SearchCriteria param;
	
	private Vegetation model;

	
	/**
	 * Builder method to hold the search criteria to retrieve the data from
	 * database.
	 * 
	 * @param key
	 * @param operation
	 * @param value
	 * @return
	 */
	public FieldBuilder with(String key, Date from, Date to) {
		logger.debug("construct required information to call database");
		param = new SearchCriteria(key, from, to);
		return this;
	}
	

	/**
	 * Builder method to hold the payload data and store them into database
	 * database.
	 * 
	 * @param key
	 * @param operation
	 * @param value
	 * @return
	 */
	public FieldBuilder with(FCSRequest request) {
		logger.debug("construct required information to call database");
		this.model = mapper.mapToModel(request);
		return this;
	}
	
	/**
	 * Builder method to save the field condition data into database.
	 * 
	 * @return
	 * @throws EncoderException
	 */
	public Mono<FCSResponse> save() {
		logger.debug("calling save to store field condition data.");
		return Mono.fromCallable(() -> Mono.just(repository.save(model))).flatMap(success -> {
			FCSResponse response = new FCSResponse();
			response.setMessage("Field condition added successfully");
			return Mono.just(response);
		});

	}

	/**Builder method to get statistics data from database for given date range.
	 * 
	 * @return
	 * @throws NoDataFoundException
	 */
	public Mono<FCSResponse> get() throws NoDataFoundException {
		logger.debug("calling repo to get statistics information");
		Optional<FCStatistics> response = repository.findStatistics(param.getFromDate(), param.getToDate());
		FCStatistics resp = response.get();
		if (resp.getMin() == null)
			throw new NoDataFoundException();
		logger.info("historical data available for past configured days" );
		return Mono.defer(() ->Mono.just(resp)).flatMap(result -> {
			com.atfarm.fcs.payload.Vegetation vegetation = mapper.mapToStatistics(result);
			FCSResponse fcsResp = new FCSResponse();
			fcsResp.setVegetation(vegetation);
			return Mono.just(fcsResp);
		});

	}

}
