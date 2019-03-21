package com.yuri.aulas.service.exceptions;

public class DataInternalException extends RuntimeException {
	
	public DataInternalException(String msg) {
		super(msg);//envoca o metado que esta sendo estendido
	}
	
	public DataInternalException(String msg, Throwable cause)  {//construtor
		super(msg,cause); //envica o metado passando a mensagem  ea causa do erro
	}
	
}
