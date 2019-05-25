package com.yuri.mypet.resource;


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

import com.yuri.mypet.domain.PessoaFisica;
import com.yuri.mypet.dto.PessoaFisicaDTO;
import com.yuri.mypet.dto.PessoaFisicaNewDTO;
import com.yuri.mypet.service.PessoaFisicaService;

@RestController
@RequestMapping(value = "/pessoafisica")
public class PessoaFisicaResource {

	@Autowired
	private PessoaFisicaService service;
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)// para bater em um end pont com id
	public ResponseEntity<PessoaFisica> buscar(@PathVariable Integer id) {
		PessoaFisica obj = service.find(id);
		return ResponseEntity.ok().body(obj) ;
	}
		
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PessoaFisicaNewDTO objDto) { // requestBody faz o json ser convertido para obj// java automaticamente
		PessoaFisica obj = service.fromDto(objDto);//coverto Dto para objeto entidade
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				  .path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}	
	
	
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PessoaFisicaDTO objDto, @PathVariable Integer id) {// receber o obejto json e
																								// tambem o parametro da
		PessoaFisica obj = service.fromDto(objDto);																						// URL
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

//	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // para bater em um end pont com id
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();

	}
	
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping( method = RequestMethod.GET) 
	public ResponseEntity<List<PessoaFisicaDTO>> findAll() {

		List<PessoaFisica> list = service.findAll();
		List<PessoaFisicaDTO> listDto = list.stream().map(obj -> new PessoaFisicaDTO(obj)).collect(Collectors.toList());  //stream percorre a lista, map realiza uma operação para cada elemento da lista
		return ResponseEntity.ok().body(listDto);										                               //obj função anonima que recebece uma obj com argumento 
																												  // collector realiza a transformação para lista novamente
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/page", method = RequestMethod.GET) //paginação
	public ResponseEntity<Page<PessoaFisicaDTO>> findPage(
			@RequestParam (value = "page", defaultValue="0")Integer page,
			@RequestParam (value = "linesPerPage", defaultValue="24")Integer  linesPerPage,
			@RequestParam (value = "orderBy", defaultValue="nome")String orderBy, 
			@RequestParam (value = "direction", defaultValue="ASC")String direction) {

		Page<PessoaFisica> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<PessoaFisicaDTO> listDto = list.map(obj -> new PessoaFisicaDTO(obj));  //stream percorre a lista, map realiza uma operação para cada elemento da lista
		return ResponseEntity.ok().body(listDto);										                               //obj função anonima que recebece uma obj com argumento 
																												  // collector realiza a transformação para lista novamente
	}
		
	}
	

