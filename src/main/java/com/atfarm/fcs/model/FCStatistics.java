/**
 * 
 */
package com.atfarm.fcs.model;

/**
 * @author udayakumar.rajan
 *
 */
public class FCStatistics {
	
	private Double min;
	
	private Double max;
	
	private Double avg;
	
	
	/**
	 * @param min
	 * @param max
	 * @param avg
	 */
	public FCStatistics() {
	}


	/**
	 * @param min
	 * @param max
	 * @param avg
	 */
	public FCStatistics(Double min, Double max, Double avg) {
		this.min = min;
		this.max = max;
		this.avg = avg;
	}

	/**
	 * @return the min
	 */
	public Double getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(Double min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public Double getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(Double max) {
		this.max = max;
	}

	/**
	 * @return the avg
	 */
	public Double getAvg() {
		return avg;
	}

	/**
	 * @param avg the avg to set
	 */
	public void setAvg(Double avg) {
		this.avg = avg;
	}
	
	

}
