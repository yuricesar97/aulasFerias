package com.yuri.aulas.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuri.aulas.domain.Cliente;
import com.yuri.aulas.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService cat;
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)// para bater em um end pont com id
	public ResponseEntity<?> buscar(@PathVariable Integer id) {
		
		
		Cliente obj = cat.buscar(id);
		
		 
		return ResponseEntity.ok().body(obj) ;
		
	}
	
}
