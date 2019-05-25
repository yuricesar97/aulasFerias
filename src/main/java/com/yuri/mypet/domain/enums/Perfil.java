package com.yuri.mypet.domain.enums;

public enum Perfil {

	
	ADMIN(1, "ROLE_ADMIN"), //ROLE É EXIGENCIA DO SPRING
	CLIENTE(2, "ROLE_CLIENTE"),
	SERVICO(3, "ROLE_SERVICO");
	

	
	private Integer cod;
	private String descricao;
	
	
	private Perfil(Integer cod,String descricao) {
		
		this.cod = cod;
		this.descricao = descricao;
	}
	
	
	public Integer getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	
	public static Perfil toEnum(Integer cod) { //static porque essa operação precisa ser executada mesmo sem instaciar objetos
		
		if(cod == null) {
			return null;
		}
		
		for(Perfil x : Perfil.values()) { //percorre todos os valores possiveis do tipoEnumerado
		   if(cod.equals(x.getCod())) {//varrendo todas as possibilidades
			 return x;
		   }
			
		} 
		   throw new IllegalArgumentException("Id inválido: " + cod);
		   
		   
		
	
	
	}
}
	
	

