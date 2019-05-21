/**
 * 
 */
package com.atfarm.fcs.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author udayakumar.rajan
 *
 */
public class DateUtil {
	
	/**
	 * To add days and return the new date from given one
	 * @param to
	 * @param days
	 * @return
	 */
	public static Date addDays (Date to, long days)  {
		Instant instant = to.toInstant();
		LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
		localDate = localDate.plusDays(days);
		return Date.from(localDate.atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
	}
}
