package com.yuri.aulas.service;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.yuri.aulas.domain.Categoria;
import com.yuri.aulas.repositories.CategoriasRepositoty;
import com.yuri.aulas.service.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {

	@Autowired
	CategoriasRepositoty repo;
	
	public Categoria buscar(Integer id) {
		
		Optional<Categoria> op  = repo.findById(id);
		
		return op.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id +", tipo: " + Categoria.class.getName()));
	}
	
}


