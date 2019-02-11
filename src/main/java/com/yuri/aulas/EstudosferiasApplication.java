package com.yuri.aulas;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yuri.aulas.domain.Categoria;
import com.yuri.aulas.repositories.CategoriasRepositoty;
import com.yuri.aulas.service.CategoriaService;

@SpringBootApplication
public class EstudosferiasApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepositoty categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EstudosferiasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
	}

}

