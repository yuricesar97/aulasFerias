package com.yuri.aulas.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.yuri.aulas.service.DBService;

@Configuration
@Profile("dev")// todos os bins dessa classe vai ser ativo apenas quando o test tiver ativo no application properties
public class DevConfig {

	@Autowired
	private DBService  dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}") // pega o valor da estrategia do banco do mysql
	private String strategy;
	
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if(!"create".equals(strategy)){
			return false;
		}		
		dbService.instantiateTestDataBase();
		return true;
	}
	
}
