package com.yuri.aulas.dto;

import java.io.Serializable;

import com.yuri.aulas.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	private String nome;
	
	public CategoriaDTO() {
		//algumas bibliotecas precisam disso
	}
	
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome= obj.getNome();
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
