/**
 * 
 */
package com.atfarm.fcs.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.atfarm.fcs.payload.ErrorResponse;


/**
 * @author udayakumar.rajan
 *
 */
@ControllerAdvice
public class ExceptionAdvice {
	
	private final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);
	
	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequest (BadRequestException ex) {
		logger.error("_________required information is missing. {}_______", ex.getMessage());
		ErrorResponse res = new ErrorResponse(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(res);
	}
	
	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponse> handleNoMatch (HttpRequestMethodNotSupportedException ex) {
		logger.error("{}", ex);
		ErrorResponse res = new ErrorResponse(ex.getMessage());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
				.body(res);
	}

	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleUnknowException(Exception ex) {
		logger.error("error occured. {}", ex);
		ErrorResponse res = new ErrorResponse("Something went wrong!.Please check server log for more details");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
	}

	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoDataFound (NoDataFoundException ex) {
		logger.error("_______ No data found for the search criteria.__________");
		ErrorResponse res = new ErrorResponse("No field satistics found!");
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.body(res);
	}

}
