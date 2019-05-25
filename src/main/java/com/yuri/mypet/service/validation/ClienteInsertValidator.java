package com.yuri.mypet.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.yuri.mypet.domain.PessoaFisica;
import com.yuri.mypet.domain.enums.TipoCliente;
import com.yuri.mypet.dto.PessoaFisicaNewDTO;
import com.yuri.mypet.repositories.PessoaFisicaRepository;
import com.yuri.mypet.service.exception.FieldMessage;
import com.yuri.mypet.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, PessoaFisicaNewDTO> {
	
	@Autowired
	PessoaFisicaRepository repo;
	
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(PessoaFisicaNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipoPerfil().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpf())) {
			list.add(new FieldMessage("cpf", "CPF invalido"));
		}
		
		
		PessoaFisica aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) { // verificando se email já existe
			list.add(new FieldMessage("Email", "Email já existente"));
		}
		

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}