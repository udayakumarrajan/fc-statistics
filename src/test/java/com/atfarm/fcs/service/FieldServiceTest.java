/**
 * 
 */
package com.atfarm.fcs.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.atfarm.fcs.exception.BadRequestException;
import com.atfarm.fcs.exception.NoDataFoundException;
import com.atfarm.fcs.payload.FCSRequest;
import com.atfarm.fcs.payload.FCSResponse;
import com.atfarm.fcs.payload.Vegetation;

import reactor.core.publisher.Mono;

/**
 * @author udayakumar.rajan
 *
 */
@RunWith(SpringRunner.class)
public class FieldServiceTest {
	
	@MockBean
	FieldService mockService;
	
	@Test(expected = BadRequestException.class)
	public void testWithNullOccuranceAt() throws Exception {
		FCSRequest request = new FCSRequest();
		request.setVegetation(10.2d);
		when(mockService.storeFieldCondition(request)).thenThrow(BadRequestException.class);
		assertThat(mockService.storeFieldCondition(request));
	}
	
	@Test(expected = BadRequestException.class)
	public void testWithNullVegetation() throws Exception {
		FCSRequest request = new FCSRequest();
		request.setOccurrenceAt(new Date());
		when(mockService.storeFieldCondition(request)).thenThrow(BadRequestException.class);
		assertThat(mockService.storeFieldCondition(request));
	}
	
	@Test(expected = BadRequestException.class)
	public void testWithNullVegetationAndOccuranceAt() throws Exception {
		FCSRequest request = new FCSRequest();
		when(mockService.storeFieldCondition(request)).thenThrow(BadRequestException.class);
		assertThat(mockService.storeFieldCondition(request));
	}
	
	@Test(expected = BadRequestException.class)
	public void testWithPayLoadNull() throws Exception {
		when(mockService.storeFieldCondition(null)).thenThrow(BadRequestException.class);
		assertThat(mockService.storeFieldCondition(null));
	}
	
	@Test(expected = BadRequestException.class)
	public void testWithPayLoadWithInvalidDate() throws Exception {
		FCSRequest request = new FCSRequest();
		request.setVegetation(10.2d);
		Calendar cal = Calendar.getInstance();
		cal.set(-1, 12, 56);
		request.setOccurrenceAt(cal.getTime());
		when(mockService.storeFieldCondition(request)).thenThrow(BadRequestException.class);
		assertThat(mockService.storeFieldCondition(request));
	}
	
	@Test
	public void testWithValidInput() throws Exception {
		FCSRequest request = new FCSRequest();
		request.setVegetation(10.2d);
		request.setOccurrenceAt(new Date());
		FCSResponse response = new FCSResponse();
		response.setMessage("Field condition added successfully");
		when(mockService.storeFieldCondition(request)).thenReturn(Mono.just(response));
		assertThat(mockService.storeFieldCondition(request).equals(Mono.just(response)));
	}
	
	@Test(expected = NoDataFoundException.class)
	public void testWhenNoDataFound() throws Exception {
		when(mockService.getFieldStatistics()).thenThrow(NoDataFoundException.class);
		assertThat(mockService.getFieldStatistics());
	}
	
	@Test
	public void testWhenStatisticsFound() throws Exception {
		FCSResponse response = new FCSResponse();
		Vegetation veg = new Vegetation();
		veg.setAvg(10.2d);
		veg.setMax(10.2d);
		veg.setMin(10.2d);
		response.setVegetation(veg);
		when(mockService.getFieldStatistics()).thenReturn(Mono.just(response));
		assertThat(mockService.getFieldStatistics().equals(Mono.just(response)));
	}


}
