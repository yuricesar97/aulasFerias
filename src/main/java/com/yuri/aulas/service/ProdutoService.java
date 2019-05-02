package com.yuri.aulas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.yuri.aulas.domain.Categoria;
import com.yuri.aulas.domain.Produto;
import com.yuri.aulas.repositories.CategoriasRepository;
import com.yuri.aulas.repositories.produtoRepositoty;
import com.yuri.aulas.service.exceptions.ObjectNotFoundException;



@Service
public class ProdutoService {

	@Autowired
	private produtoRepositoty produtoRepository;

	@Autowired
	private CategoriasRepository categoriaRepository;

	public Produto find(Integer id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);	
	}
}

