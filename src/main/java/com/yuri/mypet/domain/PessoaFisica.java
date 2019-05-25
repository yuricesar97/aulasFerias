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
public class PessoaFisica implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nomeCompleto;
	
	@Column(unique = true) // faz o banco de dados garantir que não vai ter repetição com esse campo 
	private String email;
	private String cpf;
	private Integer tipoPerfil;
	
	@JsonIgnore// para não aparecer no json
	private String senha;
	
	private String fotoPerfil;
	private boolean petWalker = false;
	private String descricao;
	private String dataNascimento;
	
	@OneToMany(mappedBy = "pessoaFisica",cascade = CascadeType.ALL) // cascade, toda modificação que ocorrer no cliente ocorre em endereço com efeito cascata (quando apgar um cliente apaga um endereço tb)
	private List<EnderecoFisico> enderecos = new ArrayList<>();
	
	@JsonIgnore // pedidos do clinete não sera serealizados.
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>(); 
	
	@ElementCollection
	@CollectionTable(name = "TELEFONE")//nomeCompleto da tabela
	private Set<String>telefones = new HashSet<>();//permite não repetir valores(represanta os conjuntos de valores )
	
	@ElementCollection(fetch = FetchType.EAGER)//traz o perfil junto
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	
	
	public PessoaFisica(){
		addPerfil(Perfil.CLIENTE); // ja colocar que é um cliente
	}

	public PessoaFisica(Integer id, String nomeCompleto,String email, String cpf, TipoCliente tipoPerfil,String senha,String fotoPerfil,String descricao,boolean petWalker,String dataNascimento) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.cpf = cpf;
		this.tipoPerfil = (tipoPerfil==null) ? null : tipoPerfil.getCod(); //operador ternario ..  na intaciação não aceita nullo precisa de uma condicional por conta do getCod
		this.senha = senha;
		this.fotoPerfil = fotoPerfil;
		this.petWalker = petWalker;
		this.descricao = descricao;
		this.dataNascimento = dataNascimento;
		addPerfil(Perfil.CLIENTE);
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
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public List<EnderecoFisico> getEndereço() {
		return enderecos;
	}

	public void setEndereço(List<EnderecoFisico> endereco) {
		this.enderecos = endereco;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
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
		PessoaFisica other = (PessoaFisica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean isPetWalker() {
		return petWalker;
	}

	public void setPetWalker(boolean petWalker) {
		petWalker = petWalker;
	}

	



	
	
	
}
