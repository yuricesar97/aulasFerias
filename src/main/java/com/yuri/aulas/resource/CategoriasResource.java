package com.yuri.aulas.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuri.aulas.domain.Categoria;
import com.yuri.aulas.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriasResource {

	@Autowired
	private CategoriaService cat;
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)// para bater em um end pont com id
	public ResponseEntity<?> buscar(@PathVariable Integer id) {
		
		
		Categoria obj = cat.buscar(id);
		
		
		return ResponseEntity.ok().body(obj) ;
		
	}
	
}
