/**
 * 
 */
package com.atfarm.fcs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author udayakumar.rajan
 *
 */
@Entity
public class Vegetation {
	
	/**
	 * Using the primitive type, since it does not accept null values.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private Double vegetation;
	
	@Column(nullable = false)
	private Date occurrenceAt;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the vegetation
	 */
	public Double getVegetation() {
		return vegetation;
	}

	/**
	 * @param vegetation the vegetation to set
	 */
	public void setVegetation(Double vegetation) {
		this.vegetation = vegetation;
	}

	/**
	 * @return the occurrenceAt
	 */
	public Date getOccurrenceAt() {
		return occurrenceAt;
	}

	/**
	 * @param occurrenceAt the occurrenceAt to set
	 */
	public void setOccurrenceAt(Date occurrenceAt) {
		this.occurrenceAt = occurrenceAt;
	}

	
	

}
