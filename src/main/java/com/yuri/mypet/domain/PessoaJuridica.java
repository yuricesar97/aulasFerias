package com.yuri.mypet.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yuri.mypet.domain.enums.Perfil;
import com.yuri.mypet.domain.enums.TipoCliente;


@Entity
public class PessoaJuridica implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String razaoSocial;
	
	@Column(unique = true) // faz o banco de dados garantir que não vai ter repetição com esse campo 
	private String email;
	private String cnpj;
	private Integer tipoPerfil;
	
	@JsonIgnore// para não aparecer no json
	private String senha;
	
	private String fotoPerfil;
	
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
	
	@OneToMany(mappedBy = "pessoaJuridica",cascade = CascadeType.ALL) // cascade, toda modificação que ocorrer no cliente ocorre em endereço com efeito cascata (quando apgar um cliente apaga um endereço tb)
	private List<EnderecoJuridico> enderecos = new ArrayList<>();
	
	@JsonIgnore // pedidos do clinete não sera serealizados.
	
	
	@ElementCollection
	@CollectionTable(name = "TELEFONE_JURIDICO")//razaoSocial da tabela
	private Set<String>telefones = new HashSet<>();//permite não repetir valores(represanta os conjuntos de valores )
	
	@ElementCollection(fetch = FetchType.EAGER)//traz o perfil junto
	@CollectionTable(name="PERFIS_JURIDICO")
	private Set<Integer> perfis = new HashSet<>();
	
	
	
	public PessoaJuridica(){
		addPerfil(Perfil.SERVICO); // ja colocar que é um cliente
	}

	
	
	

	public PessoaJuridica(Integer id, String razaoSocial, String email, String cnpj, TipoCliente tipoPerfil, String senha,
			String fotoPerfil, boolean farmacia, boolean banho,
			boolean tosa, boolean loja, boolean vacinacao, boolean consulta, boolean exames, boolean apartamento,
			boolean casa, boolean fumante, boolean telado, boolean petVet,boolean petClient,boolean petHome,boolean petShop) {
		super();
		this.id = id;
		this.razaoSocial = razaoSocial;
		this.email = email;
		this.cnpj = cnpj;
		this.tipoPerfil = (tipoPerfil==null) ? null : tipoPerfil.getCod();;
		this.senha = senha;
		this.fotoPerfil = fotoPerfil;
		this.farmacia = farmacia;
		this.banho = banho;
		this.tosa = tosa;
		this.loja = loja;
		this.vacinacao = vacinacao;
		this.consulta = consulta;
		this.exames = exames;
		this.apartamento = apartamento;
		this.casa = casa;
		this.fumante = fumante;
		this.telado = telado;
		this.petVet = petVet;
		this.petClient = petClient;
		this.petHome = petHome;
		this.petShop = petShop;
		
		addPerfil(Perfil.SERVICO);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

	public TipoCliente getTipoPerfil() {
		return TipoCliente.toEnum(tipoPerfil);
	}
	
	public  Set<Perfil> getPerfis(){
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet()); //converte para perfil
	}
	
	public void addPerfil (Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	public void setTipoPerfil(TipoCliente tipoPerfil) {
		this.tipoPerfil = tipoPerfil.getCod();
	}

	

	

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}



	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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





	public List<EnderecoJuridico> getEnderecos() {
		return enderecos;
	}


	public void setEnderecos(List<EnderecoJuridico> enderecos) {
		this.enderecos = enderecos;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaJuridica other = (PessoaJuridica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	



	
	
	
}
