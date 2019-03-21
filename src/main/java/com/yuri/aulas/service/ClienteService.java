package com.yuri.aulas.service;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.yuri.aulas.domain.Cliente;
import com.yuri.aulas.repositories.ClienteRepositoty;
import com.yuri.aulas.service.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {

	@Autowired
	ClienteRepositoty repo;
	
	public Cliente find(Integer id) {
		
		Optional<Cliente> op  = repo.findById(id);
		
		return op.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id +", tipo: " + Cliente.class.getName()));
	}
	
}


