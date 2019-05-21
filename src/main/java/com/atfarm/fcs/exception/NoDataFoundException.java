/**
 * 
 */
package com.atfarm.fcs.exception;

/**
 * @author udayakumar.rajan
 *
 */
public class NoDataFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4355564731465841493L;
	
	/**
	 * 
	 */
	public NoDataFoundException() {
		super();
	}

	/**
	 * 
	 * @param message
	 */
	public NoDataFoundException(final String message) {
		super(message);
	}

}
