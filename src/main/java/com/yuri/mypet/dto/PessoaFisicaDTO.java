package com.yuri.mypet.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;


import com.yuri.mypet.domain.EnderecoFisico;
import com.yuri.mypet.domain.PessoaFisica;
import com.yuri.mypet.service.validation.ClienteUpdate;

@ClienteUpdate
public class PessoaFisicaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	
	@NotEmpty(message = "Prenchimento obrigatório")
	@Length(min=5,max=120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nomeCompleto;
	
	@NotEmpty(message = "Preechimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	private String cpf;
	private String dataNascimento;
	private EnderecoFisico endereco;
	
	
	
	public PessoaFisicaDTO() {
		
	}




	public PessoaFisicaDTO(PessoaFisica obj) { // construtor sera respnosavel por instanciar um DTO com os dados que desejo
	
		id = obj.getId();
		nomeCompleto = obj.getNomeCompleto();
	   email = obj.getEmail();
	   cpf = obj.getCpf();
	   dataNascimento = obj.getDataNascimento();
	}




	public String getCpf() {
		return cpf;
	}




	public void setCpf(String cpf) {
		this.cpf = cpf;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getNomeCompleto() {
		return nomeCompleto;
	}




	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public EnderecoFisico getEndereco() {
		return endereco;
	}




	public void setEndereco(EnderecoFisico endereco) {
		this.endereco = endereco;
	}




	public String getDataNascimento() {
		return dataNascimento;
	}




	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
	
}

	