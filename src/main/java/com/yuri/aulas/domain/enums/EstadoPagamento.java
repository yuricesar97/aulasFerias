package com.yuri.aulas.domain.enums;

public enum EstadoPagamento {

	
	PENDENTE(1, "PENDENTE"),
	QUITADO(2, "QUITADO"),
	CANCELADO(3,"CANCELADO");

	
	private Integer cod;
	private String descricao;
	
	
	private EstadoPagamento(Integer cod,String descricao) {
		
		this.cod = cod;
		this.descricao = descricao;
	}
	
	
	public Integer getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	
	public static EstadoPagamento toEnum(Integer cod) { //static porque essa operação precisa ser executada mesmo sem instaciar objetos
		
		if(cod == null) {
			return null;
		}
		
		for(EstadoPagamento x : EstadoPagamento.values()) { //percorre todos os valores possiveis do tipoEnumerado
		   if(cod.equals(x.getCod())) {//varrendo todas as possibilidades
			 return x;
		   }
			
		} 
		   throw new IllegalArgumentException("Id inválido: " + cod);
		   
		   
		
	
	
	}
}
	
	

