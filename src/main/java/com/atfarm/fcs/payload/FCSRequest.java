package com.atfarm.fcs.payload;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "vegetation", "occurrenceAt" })
/**
 * 
 * @author udayakumar.rajan
 *
 */
public class FCSRequest {

	@JsonProperty("vegetation")
	private Double vegetation;
	@JsonProperty("occurrenceAt")
	private Date occurrenceAt;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("vegetation")
	public Double getVegetation() {
		return vegetation;
	}

	@JsonProperty("vegetation")
	public void setVegetation(Double vegetation) {
		this.vegetation = vegetation;
	}

	@JsonProperty("occurrenceAt")
	public Date getOccurrenceAt() {
		return occurrenceAt;
	}

	@JsonProperty("occurrenceAt")
	public void setOccurrenceAt(Date occurrenceAt) {
		this.occurrenceAt = occurrenceAt;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalProperties == null) ? 0 : additionalProperties.hashCode());
		result = prime * result + ((occurrenceAt == null) ? 0 : occurrenceAt.hashCode());
		result = prime * result + ((vegetation == null) ? 0 : vegetation.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FCSRequest other = (FCSRequest) obj;
		if (additionalProperties == null) {
			if (other.additionalProperties != null)
				return false;
		} else if (!additionalProperties.equals(other.additionalProperties))
			return false;
		if (occurrenceAt == null) {
			if (other.occurrenceAt != null)
				return false;
		} else if (!occurrenceAt.equals(other.occurrenceAt))
			return false;
		if (vegetation == null) {
			if (other.vegetation != null)
				return false;
		} else if (!vegetation.equals(other.vegetation))
			return false;
		return true;
	}
	
	

}