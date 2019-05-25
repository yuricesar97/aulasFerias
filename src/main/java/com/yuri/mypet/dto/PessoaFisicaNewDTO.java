package com.yuri.mypet.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.yuri.mypet.service.validation.ClienteInsert;

@ClienteInsert
public class PessoaFisicaNewDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Prenchimento obrigatório")
	@Length(min=5,max=120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nomeCompleto;
	
	@NotEmpty(message = "Preechimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	private boolean petWalker = false;
	private String descricao;
	
	@NotEmpty(message = "Preechimento obrigatório")
	private String cpf;
	
	private String DataNascimento;
	
	private Integer tipoPerfil;
	
	@NotEmpty(message = "Preechimento obrigatório")
	private String senha;
	
	@NotEmpty(message = "Preechimento obrigatório")
	
	private String logradouro;
	@NotEmpty(message = "Preechimento obrigatório")
	
	@NotEmpty(message = "Preechimento obrigatório")
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	
	@NotEmpty(message = "Preechimento obrigatório")
	private String cep;
	
	@NotEmpty(message = "Preechimento obrigatório")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	
	
	
	public PessoaFisicaNewDTO() {
		
	}
	
	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
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



	public Integer getTipoPerfil() {
		return tipoPerfil;
	}


	public void setTipoPerfil(Integer tipoPerfil) {
		this.tipoPerfil = tipoPerfil;
	}


	public String getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public String getTelefone1() {
		return telefone1;
	}


	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}


	public String getTelefone2() {
		return telefone2;
	}


	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}


	public String getTelefone3() {
		return telefone3;
	}


	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}




	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDataNascimento() {
		return DataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		DataNascimento = dataNascimento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isPetWalker() {
		return petWalker;
	}

	public void setPetWalker(boolean petWalker) {
		this.petWalker = petWalker;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
	

}
