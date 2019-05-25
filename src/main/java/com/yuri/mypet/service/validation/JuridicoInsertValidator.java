package com.yuri.mypet.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.yuri.mypet.domain.PessoaJuridica;
import com.yuri.mypet.domain.enums.TipoCliente;
import com.yuri.mypet.dto.PessoaJuridicaNewDTO;
import com.yuri.mypet.repositories.PessoaJuridicaRepository;
import com.yuri.mypet.service.exception.FieldMessage;
import com.yuri.mypet.service.validation.utils.BR;

public class JuridicoInsertValidator implements ConstraintValidator<JuridicoInsert, PessoaJuridicaNewDTO> {
	
	@Autowired
	PessoaJuridicaRepository repo;
	
	
	@Override
	public void initialize(JuridicoInsert ann) {
	}

	@Override
	public boolean isValid(PessoaJuridicaNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipoPerfil().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCnpj())) {
			list.add(new FieldMessage("cnpj", "CNPJ invalido"));
		}
		
		
		PessoaJuridica aux = repo.findByEmail(objDto.getEmail());
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