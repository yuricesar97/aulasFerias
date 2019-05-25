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

import com.yuri.mypet.domain.EnderecoJuridico;
import com.yuri.mypet.domain.PessoaJuridica;
import com.yuri.mypet.domain.enums.TipoCliente;
import com.yuri.mypet.dto.PessoaJuridicaDTO;
import com.yuri.mypet.dto.PessoaJuridicaNewDTO;
import com.yuri.mypet.repositories.EnderecoJuridicoRepository;
import com.yuri.mypet.repositories.PessoaJuridicaRepository;
import com.yuri.mypet.service.exceptions.DataInternalException;
import com.yuri.mypet.service.exceptions.ObjectNotFoundException;

@Service
public class PessoaJuridicaService {

	@Autowired
	PessoaJuridicaRepository repo;
	@Autowired
	EnderecoJuridicoRepository enderecoRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	public PessoaJuridica find(Integer id) {

		Optional<PessoaJuridica> op = repo.findById(id);

		return op.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", tipo: " + PessoaJuridica.class.getName()));
	}

	@Transactional // para que tudo ocorra de forma trasicional (salava endereço e cliente em uma
					// tra)
	public PessoaJuridica insert(PessoaJuridica obj) {
		obj.setId(null);
		obj = repo.save(obj); // salva cliente
		enderecoRepository.saveAll(obj.getEnderecos()); // salva endereço
		return obj;
	}

	public PessoaJuridica update(PessoaJuridica obj) {
		PessoaJuridica newObj = find(obj.getId()); // instanciar um cliente a parir do banco dados
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

	public List<PessoaJuridica> findAll() {
		return repo.findAll();
	}

	public Page<PessoaJuridica> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {// Page vai
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

	public PessoaJuridica fromDto(PessoaJuridicaDTO objDto) { // metado auxiliar que instacia uma categoria a partir de um DTO

		
		return new PessoaJuridica(objDto.getId(), objDto.getRazaoSocial(), objDto.getEmail(), objDto.getCnpj(), null, null, null, false, false, false, false, false, false, false, false, false, false, false,false,false,false,false);
	}

	public PessoaJuridica fromDto(PessoaJuridicaNewDTO objDto) { // metado auxiliar que instacia uma categoria a partir de um DTO
			
	//	PessoaJuridica cli1 = new PessoaJuridica PessoaJuridica(id, razaoSocial, email, cnpj, tipoPerfil, senha, fotoPerfil, farmacia, banho, tosa, loja, vacinacao, consulta, exames, apartamento, casa, fumante, telado, petVet, petClient, petHome, petShop)
		PessoaJuridica cli1 = new PessoaJuridica(null, objDto.getRazaoSocial(), objDto.getEmail(), objDto.getCnpj(), TipoCliente.toEnum(objDto.getTipoPerfil()),bCryptPasswordEncoder.encode(objDto.getSenha()),null,objDto.isFarmacia(),
				              objDto.isBanho(),objDto.isTosa(),objDto.isLoja(),objDto.isVacinacao(),objDto.isConsulta(),objDto.isExames(),objDto.isApartamento(),objDto.isCasa(),objDto.isFumante(),objDto.isTelado(), objDto.isPetVet(),objDto.isPetClient(),objDto.isPetHome(),objDto.isPetShop());
	
		EnderecoJuridico end = new EnderecoJuridico(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(),objDto.getCidade(),objDto.getEstado(), cli1);//endereços conhece os clientes
		cli1.getEnderecos().add(end); // cliente conhece seus endereços
		cli1.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2() != null) {
			cli1.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3() != null) {
			cli1.getTelefones().add(objDto.getTelefone3());
		}
		
		return cli1;
	}

	private void updateData(PessoaJuridica newObj, PessoaJuridica obj) { // metado aux para atualizar os campos do cliente, pegando o
															// novo e colocando no antigo
		newObj.setRazaoSocial(obj.getRazaoSocial());
		newObj.setEmail(obj.getEmail());
	
		
	}
}
