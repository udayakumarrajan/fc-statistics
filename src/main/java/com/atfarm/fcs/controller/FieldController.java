/**
 * 
 */
package com.atfarm.fcs.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atfarm.fcs.exception.BadRequestException;
import com.atfarm.fcs.exception.NoDataFoundException;
import com.atfarm.fcs.payload.ErrorResponse;
import com.atfarm.fcs.payload.FCSRequest;
import com.atfarm.fcs.payload.FCSResponse;
import com.atfarm.fcs.service.FieldService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import reactor.core.publisher.Mono;

/**
 * @author udayakumar.rajan
 *
 */
@RestController
@Api(value = "Field Condition Controller")
public class FieldController {
	
	private final Logger logger = LoggerFactory.getLogger(FieldController.class);
	
	@Autowired
	private FieldService fcsService;

	@PostMapping("/field-condition")
	@ApiOperation(value = "Store Field Condition data", response = ErrorResponse.class)
	public Mono<FCSResponse> addFieldCondition(
			@ApiParam(value = "Field condition payload to store in database table", required = true) @Valid @RequestBody FCSRequest request)
			throws BadRequestException {
		logger.info("request received to store field condition data");
		return fcsService.storeFieldCondition(request);
	}

	@GetMapping("/field-statistics")
	@ApiOperation(value = "View a historical statistic field controller information", response = FCSResponse.class)
	public Mono<FCSResponse> getFieldStatistics() throws NoDataFoundException {
		logger.info("request received to get historical field condition statistics");
		return fcsService.getFieldStatistics();
	}

}
