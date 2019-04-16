package com.yuri.aulas.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.yuri.aulas.domain.Cidade;
import com.yuri.aulas.domain.Cliente;
import com.yuri.aulas.domain.Endereco;
import com.yuri.aulas.domain.enums.TipoCliente;
import com.yuri.aulas.dto.ClienteDTO;
import com.yuri.aulas.dto.ClienteNewDTO;
import com.yuri.aulas.repositories.CidadeRepositoty;
import com.yuri.aulas.repositories.ClienteRepositoty;
import com.yuri.aulas.repositories.EnderecoRepositoty;
import com.yuri.aulas.service.exceptions.DataInternalException;
import com.yuri.aulas.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	ClienteRepositoty repo;
	@Autowired
	EnderecoRepositoty enderecoRepository;
	

	public Cliente find(Integer id) {

		Optional<Cliente> op = repo.findById(id);

		return op.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", tipo: " + Cliente.class.getName()));
	}

	@Transactional // para que tudo ocorra de forma trasicional (salava endereço e cliente em uma
					// tra)
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj); // salva cliente
		enderecoRepository.saveAll(obj.getEndereço()); // salva endereço
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId()); // instanciar um cliente a parir do banco dados
		updateData(newObj, obj); // atulaiza os dados como o obj que foi enviado na requisição
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

		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null); // nulo porque não temos os
																								// daddos no DTO

	}

	public Cliente fromDto(ClienteNewDTO objDto) { // metado auxiliar que instacia uma categoria a partir de um DTO
			
		Cliente cli1 = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cid, cli1);//endereços conhece os clientes
		cli1.getEndereço().add(end); // cliente conhece seus endereços
		cli1.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2() != null) {
			cli1.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3() != null) {
			cli1.getTelefones().add(objDto.getTelefone3());
		}
		
		return cli1;
	}

	private void updateData(Cliente newObj, Cliente obj) { // metado aux para atualizar os campos do cliente, pegando o
															// novo e colocando no antigo
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
