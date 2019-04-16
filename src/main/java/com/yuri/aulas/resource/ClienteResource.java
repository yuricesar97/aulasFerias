package com.yuri.aulas.resource;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yuri.aulas.domain.Cliente;
import com.yuri.aulas.dto.ClienteDTO;
import com.yuri.aulas.dto.ClienteNewDTO;
import com.yuri.aulas.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)// para bater em um end pont com id
	public ResponseEntity<Cliente> buscar(@PathVariable Integer id) {
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj) ;
	}
		
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) { // requestBody faz o json ser convertido para obj// java automaticamente
		Cliente obj = service.fromDto(objDto);//coverto Dto para objeto entidade
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				  .path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}	
	
	
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {// receber o obejto json e
																								// tambem o parametro da
		Cliente obj = service.fromDto(objDto);																						// URL
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // para bater em um end pont com id
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();

	}
	
	
	
	@RequestMapping( method = RequestMethod.GET) 
	public ResponseEntity<List<ClienteDTO>> findAll() {

		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());  //stream percorre a lista, map realiza uma operação para cada elemento da lista
		return ResponseEntity.ok().body(listDto);										                               //obj função anonima que recebece uma obj com argumento 
																												  // collector realiza a transformação para lista novamente
	}
	
	
	@RequestMapping(value = "/page", method = RequestMethod.GET) //paginação
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam (value = "page", defaultValue="0")Integer page,
			@RequestParam (value = "linesPerPage", defaultValue="24")Integer  linesPerPage,
			@RequestParam (value = "orderBy", defaultValue="nome")String orderBy, 
			@RequestParam (value = "direction", defaultValue="ASC")String direction) {

		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));  //stream percorre a lista, map realiza uma operação para cada elemento da lista
		return ResponseEntity.ok().body(listDto);										                               //obj função anonima que recebece uma obj com argumento 
																												  // collector realiza a transformação para lista novamente
	}
		
	}
	

