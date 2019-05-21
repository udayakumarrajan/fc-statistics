/**
 * 
 */
package com.atfarm.fcs.exception;

/**
 * @author udayakumar.rajan
 *
 */
public class BadRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2670162183133367025L;

	/**
	 * 
	 */
	public BadRequestException() {
		super();
	}

	/**
	 * 
	 * @param message
	 */
	public BadRequestException(final String message) {
		super(message);
	}

}
