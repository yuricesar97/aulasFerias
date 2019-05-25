package com.yuri.mypet.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yuri.mypet.domain.EnderecoFisico;
import com.yuri.mypet.domain.PessoaFisica;
import com.yuri.mypet.domain.enums.TipoCliente;
import com.yuri.mypet.dto.PessoaFisicaDTO;
import com.yuri.mypet.dto.PessoaFisicaNewDTO;
import com.yuri.mypet.repositories.EnderecoFisicoRepository;
import com.yuri.mypet.repositories.PessoaFisicaRepository;
import com.yuri.mypet.service.exceptions.DataInternalException;
import com.yuri.mypet.service.exceptions.ObjectNotFoundException;

@Service
public class PessoaFisicaService {

	@Autowired
	PessoaFisicaRepository repo;
	@Autowired
	EnderecoFisicoRepository enderecoRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	public PessoaFisica find(Integer id) {

		Optional<PessoaFisica> op = repo.findById(id);

		return op.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", tipo: " + PessoaFisica.class.getName()));
	}

	@Transactional // para que tudo ocorra de forma trasicional (salava endereço e cliente em uma
					// tra)
	public PessoaFisica insert(PessoaFisica obj) {
		obj.setId(null);
		obj = repo.save(obj); // salva cliente
		enderecoRepository.saveAll(obj.getEndereço()); // salva endereço
		return obj;
	}

	public PessoaFisica update(PessoaFisica obj) {
		PessoaFisica newObj = find(obj.getId()); // instanciar um cliente a parir do banco dados
		updateData(newObj, obj); // atulaiza os dados como o obj que foi enviado na requisição
		return repo.save(newObj); // save vale quanto para inserir quanto para update unica coisa que ele olha é
									// se o Id esta nulo ele insere se não atualiza
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new DataInternalException("Não é possivel exclui porque há pedidos relacionadas");
		}
	}

	public List<PessoaFisica> findAll() {
		return repo.findAll();
	}

	public Page<PessoaFisica> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {// Page vai
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

	public PessoaFisica fromDto(PessoaFisicaDTO objDto) { // metado auxiliar que instacia uma categoria a partir de um DTO

		return new PessoaFisica(objDto.getId(), objDto.getNomeCompleto(), objDto.getEmail(), null, null,null,null,null,false,objDto.getDataNascimento()); // nulo porque não temos os
																								// daddos no DTO
//		PessoaFisica p = new PessoaFisica(id, nomeCompleto, email, cpf, tipoPerfil, senha, fotoPerfil, descricao, petWalker, dataNascimento)
	}

	public PessoaFisica fromDto(PessoaFisicaNewDTO objDto) { // metado auxiliar que instacia uma categoria a partir de um DTO
			
		PessoaFisica cli1 = new PessoaFisica(null, objDto.getNomeCompleto(), objDto.getEmail(), objDto.getCpf(), TipoCliente.toEnum(objDto.getTipoPerfil()),bCryptPasswordEncoder.encode(objDto.getSenha()),null,objDto.getDescricao(),false,objDto.getDataNascimento());
	
		EnderecoFisico end = new EnderecoFisico(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(),objDto.getCidade(),objDto.getEstado(), cli1);//endereços conhece os clientes
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

	private void updateData(PessoaFisica newObj, PessoaFisica obj) { // metado aux para atualizar os campos do cliente, pegando o
															// novo e colocando no antigo
		newObj.setNomeCompleto(obj.getNomeCompleto());
		newObj.setEmail(obj.getEmail());
	
		
	}
}
