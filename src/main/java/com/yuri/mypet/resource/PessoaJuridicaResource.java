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

import com.yuri.mypet.domain.PessoaJuridica;
import com.yuri.mypet.dto.PessoaJuridicaDTO;
import com.yuri.mypet.dto.PessoaJuridicaNewDTO;
import com.yuri.mypet.service.PessoaJuridicaService;

@RestController
@RequestMapping(value = "/pessoajuridica")
public class PessoaJuridicaResource {

	@Autowired
	private PessoaJuridicaService service;
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)// para bater em um end pont com id
	public ResponseEntity<PessoaJuridica> buscar(@PathVariable Integer id) {
		PessoaJuridica obj = service.find(id);
		return ResponseEntity.ok().body(obj) ;
	}
		
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PessoaJuridicaNewDTO objDto) { // requestBody faz o json ser convertido para obj// java automaticamente
		PessoaJuridica obj = service.fromDto(objDto);//coverto Dto para objeto entidade
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				  .path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}	
	
	
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PessoaJuridicaDTO objDto, @PathVariable Integer id) {// receber o obejto json e
																								// tambem o parametro da
		PessoaJuridica obj = service.fromDto(objDto);																						// URL
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
	public ResponseEntity<List<PessoaJuridicaDTO>> findAll() {

		List<PessoaJuridica> list = service.findAll();
		List<PessoaJuridicaDTO> listDto = list.stream().map(obj -> new PessoaJuridicaDTO(obj)).collect(Collectors.toList());  //stream percorre a lista, map realiza uma operação para cada elemento da lista
		return ResponseEntity.ok().body(listDto);										                               //obj função anonima que recebece uma obj com argumento 
																												  // collector realiza a transformação para lista novamente
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/page", method = RequestMethod.GET) //paginação
	public ResponseEntity<Page<PessoaJuridicaDTO>> findPage(
			@RequestParam (value = "page", defaultValue="0")Integer page,
			@RequestParam (value = "linesPerPage", defaultValue="24")Integer  linesPerPage,
			@RequestParam (value = "orderBy", defaultValue="nome")String orderBy, 
			@RequestParam (value = "direction", defaultValue="ASC")String direction) {

		Page<PessoaJuridica> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<PessoaJuridicaDTO> listDto = list.map(obj -> new PessoaJuridicaDTO(obj));  //stream percorre a lista, map realiza uma operação para cada elemento da lista
		return ResponseEntity.ok().body(listDto);										                               //obj função anonima que recebece uma obj com argumento 
																												  // collector realiza a transformação para lista novamente
	}
		
	}
	

