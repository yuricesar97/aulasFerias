package com.yuri.aulas.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {
	
	public ObjectNotFoundException(String msg) {
		super(msg);//envoca o metado que esta sendo estendido
	}
	
	public ObjectNotFoundException(String msg, Throwable cause)  {//construtor
		super(msg,cause); //envica o metado passando a mensagem  ea causa do erro
	}
	
}
