package com.yuri.mypet.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.yuri.mypet.service.validation.JuridicoInsert;

@JuridicoInsert
public class PessoaJuridicaNewDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Prenchimento obrigatório")
	@Length(min=5,max=120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String razaoSocial;
	
	@NotEmpty(message = "Preechimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	
	
	@NotEmpty(message = "Preechimento obrigatório")
	private String cnpj;
	
	
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
	
	private boolean petVet = false;
	private boolean petClient = false;
	private boolean petHome = false;
	private boolean petShop = false;
	
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
	
	@NotEmpty(message = "Preechimento obrigatório")
	private String cep;
	
	@NotEmpty(message = "Preechimento obrigatório")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	
	
	
	public PessoaJuridicaNewDTO() {
		
	}
	
	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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

	public boolean isPetVet() {
		return petVet;
	}

	public void setPetVet(boolean petVet) {
		this.petVet = petVet;
	}

	public boolean isPetClient() {
		return petClient;
	}

	public void setPetClient(boolean petClient) {
		this.petClient = petClient;
	}

	public boolean isPetHome() {
		return petHome;
	}

	public void setPetHome(boolean petHome) {
		this.petHome = petHome;
	}

	public boolean isPetShop() {
		return petShop;
	}

	public void setPetShop(boolean petShop) {
		this.petShop = petShop;
	}
	
	
	
	
	

}
