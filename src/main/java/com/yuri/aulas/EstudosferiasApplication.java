package com.yuri.aulas;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yuri.aulas.domain.Categoria;
import com.yuri.aulas.domain.Cidade;
import com.yuri.aulas.domain.Estado;
import com.yuri.aulas.domain.Produto;
import com.yuri.aulas.repositories.CategoriasRepositoty;
import com.yuri.aulas.repositories.CidadeRepositoty;
import com.yuri.aulas.repositories.EstadoRepositoty;
import com.yuri.aulas.repositories.ProdutoRepositoty;



@SpringBootApplication
public class EstudosferiasApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepositoty categoriaRepository;
	@Autowired
	private ProdutoRepositoty produtoRepository;
	@Autowired
	private CidadeRepositoty cidadeRepositoty;
	@Autowired
	private EstadoRepositoty  estadoRepositoty;
	
	public static void main(String[] args)  {
		SpringApplication.run(EstudosferiasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);
		
		
		cat1.getProdutos().addAll(Arrays.asList(produto1,produto2,produto3));
		cat2.getProdutos().addAll(Arrays.asList(produto2));
		
		produto1.getCategorias().addAll(Arrays.asList(cat1));
		produto2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		produto3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(produto1,produto2,produto3));
		
	
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia",est1);
		Cidade cid2 = new Cidade(null, "São Paulo",est2);
		Cidade cid3 = new Cidade(null, "Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		
		estadoRepositoty.saveAll(Arrays.asList(est1,est2));
		cidadeRepositoty.saveAll(Arrays.asList(cid1,cid2,cid3));
	
		
		
	}

}

