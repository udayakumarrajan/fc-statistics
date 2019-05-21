
package com.atfarm.fcs.payload;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "min", "max", "avg" })
/**
 * 
 * @author udayakumar.rajan
 *
 */
public class Vegetation {

	@JsonProperty("min")
	private Double min;
	@JsonProperty("max")
	private Double max;
	@JsonProperty("avg")
	private Double avg;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("min")
	public Double getMin() {
		return min;
	}

	@JsonProperty("min")
	public void setMin(Double min) {
		this.min = min;
	}

	@JsonProperty("max")
	public Double getMax() {
		return max;
	}

	@JsonProperty("max")
	public void setMax(Double max) {
		this.max = max;
	}

	@JsonProperty("avg")
	public Double getAvg() {
		return avg;
	}

	@JsonProperty("avg")
	public void setAvg(Double avg) {
		this.avg = avg;
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
		result = prime * result + ((avg == null) ? 0 : avg.hashCode());
		result = prime * result + ((max == null) ? 0 : max.hashCode());
		result = prime * result + ((min == null) ? 0 : min.hashCode());
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
		Vegetation other = (Vegetation) obj;
		if (additionalProperties == null) {
			if (other.additionalProperties != null)
				return false;
		} else if (!additionalProperties.equals(other.additionalProperties))
			return false;
		if (avg == null) {
			if (other.avg != null)
				return false;
		} else if (!avg.equals(other.avg))
			return false;
		if (max == null) {
			if (other.max != null)
				return false;
		} else if (!max.equals(other.max))
			return false;
		if (min == null) {
			if (other.min != null)
				return false;
		} else if (!min.equals(other.min))
			return false;
		return true;
	}
	
	

}
