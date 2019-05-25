package com.yuri.mypet.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.yuri.mypet.domain.EnderecoFisico;
import com.yuri.mypet.domain.PessoaJuridica;
import com.yuri.mypet.service.validation.JuridicoUpdate;

@JuridicoUpdate
public class PessoaJuridicaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	
	@NotEmpty(message = "Prenchimento obrigatório")
	@Length(min=5,max=120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String razaoSocial;
	
	@NotEmpty(message = "Preechimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	private String cnpj;
	
	private boolean farmacia = false;
	private boolean banho = false;
	private boolean tosa = false;
	private boolean loja = false;
	
	private boolean vacinacao = false;
	private boolean consulta = false;
	private boolean exames = false;
	
	private boolean apartamento = false;
	private boolean casa = false;
	private boolean fumante = false;
	private boolean telado = false;
	

	private EnderecoFisico endereco;
	
	
	
	public PessoaJuridicaDTO() {
		
	}


	


	public PessoaJuridicaDTO(PessoaJuridica obj) { // construtor sera respnosavel por instanciar um DTO com os dados que desejo
	
		id = obj.getId();
		razaoSocial = obj.getRazaoSocial();
	   email = obj.getEmail();
	   cnpj = obj.getCnpj();
	  
	}




	public String getCnpj() {
		return cnpj;
	}




	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getRazaoSocial() {
		return razaoSocial;
	}




	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
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




	public boolean isFarmacia() {
		return farmacia;
	}




	public void setFarmacia(boolean farmacia) {
		this.farmacia = farmacia;
	}




	public boolean isBanho() {
		return banho;
	}




	public void setBanho(boolean banho) {
		this.banho = banho;
	}




	public boolean isTosa() {
		return tosa;
	}




	public void setTosa(boolean tosa) {
		this.tosa = tosa;
	}




	public boolean isLoja() {
		return loja;
	}




	public void setLoja(boolean loja) {
		this.loja = loja;
	}




	public boolean isVacinacao() {
		return vacinacao;
	}




	public void setVacinacao(boolean vacinacao) {
		this.vacinacao = vacinacao;
	}




	public boolean isConsulta() {
		return consulta;
	}




	public void setConsulta(boolean consulta) {
		this.consulta = consulta;
	}




	public boolean isExames() {
		return exames;
	}




	public void setExames(boolean exames) {
		this.exames = exames;
	}




	public boolean isApartamento() {
		return apartamento;
	}




	public void setApartamento(boolean apartamento) {
		this.apartamento = apartamento;
	}




	public boolean isCasa() {
		return casa;
	}




	public void setCasa(boolean casa) {
		this.casa = casa;
	}




	public boolean isFumante() {
		return fumante;
	}




	public void setFumante(boolean fumante) {
		this.fumante = fumante;
	}




	public boolean isTelado() {
		return telado;
	}




	public void setTelado(boolean telado) {
		this.telado = telado;
	}


	


	
	
	
}

	