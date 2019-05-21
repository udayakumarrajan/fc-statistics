/**
 * 
 */
package com.atfarm.fcs.builder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.atfarm.fcs.FCStatisticsApplication;
import com.atfarm.fcs.exception.NoDataFoundException;
import com.atfarm.fcs.mapper.FieldConditionMapper;
import com.atfarm.fcs.payload.FCSRequest;
import com.atfarm.fcs.payload.FCSResponse;
import com.atfarm.fcs.payload.Vegetation;
import com.atfarm.fcs.util.DateUtil;

import reactor.core.publisher.Mono;

/**
 * @author Udayakumar.Rajan
 *
 */
@RunWith(SpringRunner.class)
public class FieldBuilderTest {
	
	@MockBean
	FieldBuilder mockBuilder;
	
	@MockBean
	FieldConditionMapper mapper;
	
	@Test
	public void testWithSaveFieldData () throws Exception {
		FCSRequest request = new FCSRequest();
		request.setVegetation(10.2d);
		request.setOccurrenceAt(new Date());
		FCSResponse response = new FCSResponse();
		response.setMessage("Field condition added successfully");
		mockBuilder.with(request);
		when(mockBuilder.save()).thenReturn(Mono.just(response));
		assertThat(mockBuilder.save().equals(Mono.just(response)));
		
	}
	
	@Test(expected=NoDataFoundException.class)
	public void testWhenStatisticsNotAvailable () throws Exception {
		mockBuilder.with(null, new Date(), new Date());
		when(mockBuilder.get()).thenThrow(NoDataFoundException.class);
		assertThat(mockBuilder.get());
			
	}
	
	@Test
	public void testWhenStatisticsAvailable () throws Exception {
		Date from = DateUtil.addDays(new Date(), -30);
		mockBuilder.with(null, from, new Date());
		
		FCSResponse response = new FCSResponse();
		Vegetation veg = new Vegetation();
		veg.setAvg(10.2d);
		veg.setMax(10.2d);
		veg.setMin(10.2d);
		response.setVegetation(veg);
		
		when(mockBuilder.get()).thenReturn(Mono.just(response));
		assertThat(mockBuilder.get().equals(Mono.just(response)));
		
	}
 
	 
    @Bean
    public FieldConditionMapper mapper () {
        return Mappers.getMapper(FieldConditionMapper.class);
    }

}
