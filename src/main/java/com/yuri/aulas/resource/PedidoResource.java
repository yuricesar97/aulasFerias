package com.yuri.aulas.resource;

import java.net.URI;
import java.util.Date;

import javax.validation.Valid;
import javax.xml.crypto.dsig.keyinfo.PGPData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yuri.aulas.domain.Categoria;
import com.yuri.aulas.domain.PagamentoComBoleto;
import com.yuri.aulas.domain.PagamentoComCartao;
import com.yuri.aulas.domain.Pedido;
import com.yuri.aulas.domain.enums.EstadoPagamento;
import com.yuri.aulas.dto.CategoriaDTO;
import com.yuri.aulas.service.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)// para bater em um end pont com id
	public ResponseEntity<Pedido> buscar(@PathVariable Integer id) {
		
		
		Pedido obj = service.find(id);
		
		 
		return ResponseEntity.ok().body(obj) ;
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) { // requestBody faz o json ser convertido para obj// java automaticamente
		 obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				  .path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}
	
	
	
	
}
	
