package com.yuri.mypet.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.yuri.mypet.domain.PessoaJuridica;
import com.yuri.mypet.dto.PessoaJuridicaDTO;
import com.yuri.mypet.repositories.PessoaJuridicaRepository;
import com.yuri.mypet.service.exception.FieldMessage;

public class JuridicoUpdateValidator implements ConstraintValidator<JuridicoUpdate, PessoaJuridicaDTO> {
	
	@Autowired
	private HttpServletRequest request; //permite pegar o parametro da uri
	@Autowired
	private PessoaJuridicaRepository repo;
	
	
	@Override
	public void initialize(JuridicoUpdate ann) {
	}

	public boolean isValid(PessoaJuridicaDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		//Map estrutura de dados, chave e valor exemplo(chave id, valor 2)
		
		List<FieldMessage> list = new ArrayList<>();
		
		PessoaJuridica aux = repo.findByEmail(objDto.getEmail());
		if(aux != null && !aux.getId().equals(uriId)) { // verificando se email já existe
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