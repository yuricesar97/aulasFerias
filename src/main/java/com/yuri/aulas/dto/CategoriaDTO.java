package com.yuri.aulas.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.yuri.aulas.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")// validação do campo nome (caso esteja vazio)
	@Length(min=5 , max= 80, message = "Tamanho deve ser entre 5 a 80 caracteres")// validação tamanho, caso não valide mostra mensagem
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
