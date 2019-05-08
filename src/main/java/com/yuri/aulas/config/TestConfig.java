package com.yuri.aulas.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.yuri.aulas.service.DBService;
import com.yuri.aulas.service.EmailService;
import com.yuri.aulas.service.MockEmailService;

@Configuration
@Profile("test")// todos os bins dessa classe vai ser ativo apenas quando o test tiver ativo no application properties
public class TestConfig {

	@Autowired
	private DBService  dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDataBase();
		return true;
	}
	
	@Bean // fica disponvel como um componente no sistema 
	public EmailService emailService() {
		return new MockEmailService();
	}
	
}
