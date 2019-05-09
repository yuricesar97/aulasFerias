package com.yuri.aulas.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yuri.aulas.domain.enums.TipoCliente;


@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@Column(unique = true) // faz o banco de dados garantir que não vai ter repetição com esse campo 
	private String email;
	private String cpfOuCnpj;
	private Integer tipo;
	
	@JsonIgnore// para não aparecer no json
	private String senha;
	
	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL) // cascade, toda modificação que ocorrer no cliente ocorre em endereço com efeito cascata (quando apgar um cliente apaga um endereço tb)
	private List<Endereco> enderecos = new ArrayList<>();
	
	@JsonIgnore // pedidos do clinete não sera serealizados.
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>(); 
	
	@ElementCollection
	@CollectionTable(name = "TELEFONE")//nome da tabela
	private Set<String>telefones = new HashSet<>();//permite não repetir valores(represanta os conjuntos de valores )
	
	
	
	public Cliente(){
		
	}

	public Cliente(Integer id, String nome,String email, String cpfOuCnpj, TipoCliente tipo,String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = (tipo==null) ? null : tipo.getCod(); //operador ternario ..  na intaciação não aceita nullo precisa de uma condicional por conta do getCod
		this.senha = senha;
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
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}

	public List<Endereco> getEndereço() {
		return enderecos;
	}

	public void setEndereço(List<Endereco> endereco) {
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	
	
	
}
