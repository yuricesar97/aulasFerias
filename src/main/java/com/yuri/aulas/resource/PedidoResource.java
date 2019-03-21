package com.yuri.aulas.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuri.aulas.domain.Pedido;
import com.yuri.aulas.service.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService cat;
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)// para bater em um end pont com id
	public ResponseEntity<Pedido> buscar(@PathVariable Integer id) {
		
		
		Pedido obj = cat.find(id);
		
		 
		return ResponseEntity.ok().body(obj) ;
		
	}
	
}
