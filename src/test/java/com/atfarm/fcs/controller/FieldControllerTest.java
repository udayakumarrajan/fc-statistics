/**
 * 
 */
package com.atfarm.fcs.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import com.atfarm.fcs.FCStatisticsApplication;
import com.atfarm.fcs.builder.FieldBuilder;
import com.atfarm.fcs.exception.BadRequestException;
import com.atfarm.fcs.exception.NoDataFoundException;
import com.atfarm.fcs.mapper.FieldConditionMapper;
import com.atfarm.fcs.payload.FCSRequest;
import com.atfarm.fcs.payload.FCSResponse;
import com.atfarm.fcs.payload.Vegetation;
import com.atfarm.fcs.service.FieldService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

/**
 * @author udayakumar.rajan
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(FieldController.class)
@ContextConfiguration(classes = { FCStatisticsApplication.class })
public class FieldControllerTest {
	
	@Autowired
	private MockMvc mvc;
	@MockBean
	FieldService mockService;
	@MockBean
	FieldBuilder mockBuilder;
	

	@Test
	public void testWithNullOccuranceAt() throws Exception {
		FCSRequest request = new FCSRequest();
		request.setVegetation(10.2d);
		when(mockService.storeFieldCondition(request)).thenThrow(BadRequestException.class);
		mvc.perform(post("/field-condition").contentType("application/json").content(toJson(request)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testWithNullVegetation() throws Exception {
		FCSRequest request = new FCSRequest();
		request.setOccurrenceAt(new Date());
		when(mockService.storeFieldCondition(request)).thenThrow(BadRequestException.class);
		mvc.perform(post("/field-condition").contentType("application/json").content(toJson(request)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testWithNullVegetationAndOccuranceAt() throws Exception {
		FCSRequest request = new FCSRequest();
		when(mockService.storeFieldCondition(request)).thenThrow(BadRequestException.class);
		mvc.perform(post("/field-condition").contentType("application/json").content(toJson(request)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testWithPayLoadNull() throws Exception {
		when(mockService.storeFieldCondition(null)).thenThrow(BadRequestException.class);
		mvc.perform(post("/field-condition").contentType("application/json"));
	}
	
	@Test
	public void testWithPayLoadWithInvalidDate() throws Exception {
		FCSRequest request = new FCSRequest();
		request.setVegetation(10.2d);
		Calendar cal = Calendar.getInstance();
		cal.set(-1, 12, 56);
		request.setOccurrenceAt(cal.getTime());
		when(mockService.storeFieldCondition(request)).thenThrow(BadRequestException.class);
		mvc.perform(post("/field-condition").contentType("application/json").content(toJson(request)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testWithValidInput() throws Exception {
		FCSRequest request = new FCSRequest();
		request.setVegetation(10.2d);
		request.setOccurrenceAt(new Date());
		FCSResponse response = new FCSResponse();
		response.setMessage("Field condition added successfully");
		when(mockService.storeFieldCondition(request)).thenReturn(Mono.just(response));
		mvc.perform(post("/field-condition").contentType("application/json").content(toJson(request)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testWhenNoDataFound() throws Exception {
		when(mockService.getFieldStatistics()).thenThrow(NoDataFoundException.class);
		mvc.perform(get("/field-statistics")).andExpect(status().isNoContent())
		.andExpect(content().contentTypeCompatibleWith("application/json")).andExpect(new ResultMatcher() {

			@Override
			public void match(MvcResult arg0) throws Exception {
				assertTrue(arg0.getResponse().getContentAsString().contains("No field satistics"));

			}
		});
	}
	
	@Test
	public void testWhenStatisticsFound() throws Exception {
		testWithValidInput();
		FCSResponse response = new FCSResponse();
		Vegetation veg = new Vegetation();
		veg.setAvg(10.2d);
		veg.setMax(10.2d);
		veg.setMin(10.2d);
		response.setVegetation(veg);
		when(mockService.getFieldStatistics()).thenReturn(Mono.just(response));
		mvc.perform(get("/field-statistics")).andExpect(status().isOk())
		.andExpect(new ResultMatcher() {

			@Override
			public void match(MvcResult arg0) throws Exception {
				assertTrue(arg0.getAsyncResult().equals(response));

			}
		});
	}

	private String toJson(FCSRequest data) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(data);
	}

}
