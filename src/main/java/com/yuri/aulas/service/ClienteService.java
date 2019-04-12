package com.yuri.aulas.service;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.yuri.aulas.domain.Cliente;

import com.yuri.aulas.dto.ClienteDTO;
import com.yuri.aulas.repositories.ClienteRepositoty;
import com.yuri.aulas.service.exceptions.DataInternalException;
import com.yuri.aulas.service.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {

	@Autowired
	ClienteRepositoty repo;
	
	public Cliente find(Integer id) {
		
		Optional<Cliente> op  = repo.findById(id);
		
		return op.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id +", tipo: " + Cliente.class.getName()));
	}
	

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId()); //instanciar um cliente a parir do banco dados
		updateData(newObj, obj); //atulaiza os dados que criou com base no que veio de argumento
		return repo.save(newObj); // save vale quanto para inserir quanto para update unica coisa que ele olha é
								// se o Id esta nulo ele insere se não atualiza
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new DataInternalException("Não é possivel exclui porque há entidades relacionadas");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {// Page vai
																											// encapsular
																											// informações
																											// e
																											// operações
																											// sobre a
																											// paginação

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy); // prepara
																												// as
																												// informações
																												// para
																												// que
																												// faça
																												// a
																												// consulta
																												// que
																												// retorne
																												// a
																												// pagina
																												// de
																												// dados
		return repo.findAll(pageRequest); // Direction convertendo do tipo String para o tipo Direction
	}
	
	public Cliente fromDto(ClienteDTO objDto) { // metado auxiliar que instacia uma categoria a partir de um DTO
		
		return new Cliente(objDto.getId(),objDto.getNome(), objDto.getEmail(), null, null); //nulo porque não temos os daddos no DTO
		
	}
	
	
	private void updateData(Cliente newObj, Cliente obj) { // metado aux para atualizar os campos do cliente, pegando o novo e colocando no antigo
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail()); 
	}
}


