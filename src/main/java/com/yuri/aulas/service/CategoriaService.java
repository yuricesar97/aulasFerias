package com.yuri.aulas.service;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;


import com.yuri.aulas.domain.Categoria;
import com.yuri.aulas.repositories.CategoriasRepositoty;
import com.yuri.aulas.service.exceptions.DataInternalException;
import com.yuri.aulas.service.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {

	@Autowired
	CategoriasRepositoty repo;
	
	public Categoria find(Integer id) {
		
		Optional<Categoria> op  = repo.findById(id);
		
		return op.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id +", tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);// se o obj tiver valendo alguma coisa o metado ira connsiderar uma atualização
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj); // save vale quanto para inserir quanto para update unica coisa que ele olha é se o Id esta nulo ele insere se não atualiza
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		 repo.deleteById(id);
	
		}
		catch(DataIntegrityViolationException e) {
			throw new DataInternalException("Não é possivel exclui uma categoria que possui produtos");
		}
		}
	
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page , Integer linesPerPage, String orderBy, String direction){// Page vai encapsular informações e operações sobre a paginação 
	
	PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);    //prepara as informações para que faça a consulta que retorne a pagina de dados
	return repo.findAll(pageRequest);																								 //Direction convertendo do tipo String para o tipo Direction
	}
	
}


