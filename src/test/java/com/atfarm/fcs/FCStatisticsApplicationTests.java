package com.atfarm.fcs;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.atfarm.fcs.mapper.FieldConditionMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=FCStatisticsApplication.class)
@Configuration
public class FCStatisticsApplicationTests {
	
	@Autowired
	private ApplicationContext context;
	
	@MockBean
	FieldConditionMapper mapper;

	@Test
	public void contextLoads() {
		assertThat(context, notNullValue());
	}
	

    @Before
    public void springUp() {
        context = new AnnotationConfigApplicationContext( getClass() );
        context.getAutowireCapableBeanFactory().autowireBean( this );
    }
    
    @Bean
    public FieldConditionMapper mapper () {
        return Mappers.getMapper(FieldConditionMapper.class);
    }


}
