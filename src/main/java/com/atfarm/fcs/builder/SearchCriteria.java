/**
 * 
 */
package com.atfarm.fcs.builder;

import java.util.Date;

/**
 * @author udayakumar.rajan
 *
 */
public class SearchCriteria {
	
	private String key;
	
	private Date fromDate;
    
    private Date toDate;
    
    
    public SearchCriteria() {
	}

	/**
	 * @param key
	 * @param operation
	 * @param value
	 */
	public SearchCriteria(String key, Date fromDate, Date toDate) {
		super();
		this.key = key;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	
	

}
