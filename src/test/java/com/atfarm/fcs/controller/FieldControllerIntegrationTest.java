/**
 * 
 */
package com.atfarm.fcs.controller;

import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.atfarm.fcs.FCStatisticsApplication;
import com.atfarm.fcs.mapper.FieldConditionMapper;
import com.atfarm.fcs.payload.FCSRequest;

/**
 * @author Udayakumar.Rajan
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FCStatisticsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes=FCStatisticsApplication.class)
public class FieldControllerIntegrationTest {
	
	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	
	
	@Mock
	FieldConditionMapper mapper;
	
	@Test
	public void testWithNullOccuranceAt() throws Exception { 
		FCSRequest request = new FCSRequest();
		request.setVegetation(10.23d);
		HttpEntity<FCSRequest> entity = new HttpEntity<FCSRequest>(request, headers);
		ResponseEntity<String> responseEnty = restTemplate.exchange(
				createURLWithPort("/field-condition"), HttpMethod.POST, entity,
				String.class);
		assertTrue(responseEnty.getStatusCode().equals(HttpStatus.BAD_REQUEST));
	}
	
	@Test
	public void testWithNullVegetation() throws Exception {
		FCSRequest request = new FCSRequest();
		request.setOccurrenceAt(new Date());
		HttpEntity<FCSRequest> entity = new HttpEntity<FCSRequest>(request, headers);
		ResponseEntity<String> responseEnty = restTemplate.exchange(
				createURLWithPort("/field-condition"), HttpMethod.POST, entity,
				String.class);
		assertTrue(responseEnty.getStatusCode().equals(HttpStatus.BAD_REQUEST));
	}
	
	@Test
	public void testWithNullVegetationAndOccuranceAt() throws Exception {
		FCSRequest request = new FCSRequest();
		HttpEntity<FCSRequest> entity = new HttpEntity<FCSRequest>(request, headers);
		ResponseEntity<String> responseEnty = restTemplate.exchange(
				createURLWithPort("/field-condition"), HttpMethod.POST, entity,
				String.class);
		assertTrue(responseEnty.getStatusCode().equals(HttpStatus.BAD_REQUEST));
	}
	
	@Test
	public void testWithPayLoadNull() throws Exception {
		HttpEntity<FCSRequest> entity = new HttpEntity<FCSRequest>(null, headers);
		ResponseEntity<String> responseEnty = restTemplate.exchange(
				createURLWithPort("/field-condition"), HttpMethod.POST, entity,
				String.class);
		assertTrue(responseEnty.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	@Test
	public void testWithPayLoadWithInvalidDate() throws Exception {
		FCSRequest request = new FCSRequest();
		request.setVegetation(10.2d);
		Calendar cal = Calendar.getInstance();
		cal.set(-1, 12, 56);
		request.setOccurrenceAt(cal.getTime());
		HttpEntity<FCSRequest> entity = new HttpEntity<FCSRequest>(request, headers);
		ResponseEntity<String> responseEnty = restTemplate.exchange(
				createURLWithPort("/field-condition"), HttpMethod.POST, entity,
				String.class);
		assertTrue(responseEnty.getStatusCode().equals(HttpStatus.OK));
		
	}
	
	@Test
	public void testWithValidInput() throws Exception {
		FCSRequest request = new FCSRequest();
		request.setVegetation(10.2d);
		request.setOccurrenceAt(new Date());
		HttpEntity<FCSRequest> entity = new HttpEntity<FCSRequest>(request, headers);
		ResponseEntity<String> responseEnty = restTemplate.exchange(
				createURLWithPort("/field-condition"), HttpMethod.POST, entity,
				String.class);
		assertTrue(responseEnty.getStatusCode().equals(HttpStatus.OK));
	}
	
	@Test
	public void testWhenNoDataFound() throws Exception {
		ResponseEntity<String>  responseEnty = restTemplate.getForEntity(new URI(createURLWithPort("/field-statistics")), String.class);
		assertTrue(responseEnty.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void testWhenStatisticsFound() throws Exception {
		FCSRequest request = new FCSRequest();
		request.setVegetation(10.2d);
		request.setOccurrenceAt(new Date());
		HttpEntity<FCSRequest> entity = new HttpEntity<FCSRequest>(request, headers);
		restTemplate.exchange(
				createURLWithPort("/field-condition"), HttpMethod.POST, entity,
				String.class);
		
		ResponseEntity<String>  responseEnty = restTemplate.getForEntity(new URI(createURLWithPort("/field-statistics")), String.class);
		String expected = "{'vegetation':{'min':10.2,'max':10.2,'avg':10.2}}";
		JSONAssert.assertEquals(expected, responseEnty.getBody(), false);
	}
	

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + "/fcs"+ uri;
	}
	
 	@Bean
    public FieldConditionMapper mapper () {
        return Mappers.getMapper(FieldConditionMapper.class);
    }

	
}
