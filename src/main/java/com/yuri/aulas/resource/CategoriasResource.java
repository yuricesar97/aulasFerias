package com.yuri.aulas.resource;




import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuri.aulas.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriasResource {
 
	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> lista() {
		
		Categoria cat1 = new Categoria(1, "informatica");
		Categoria cat2 = new Categoria(2, "Escritorio");
		
		List<Categoria>lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		
		
		 
		return lista;
		
	}
	
}
