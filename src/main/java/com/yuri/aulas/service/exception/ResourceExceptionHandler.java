 package com.yuri.aulas.service.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.yuri.aulas.service.exceptions.DataInternalException;
import com.yuri.aulas.service.exceptions.ObjectNotFoundException;

@ControllerAdvice//para intercptar erros 
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)//indica que é um tratador de excessão de ObjectNotFoundException.class
	public ResponseEntity<StandarError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandarError err = new StandarError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err); 
	
	}
	
	
	@ExceptionHandler(DataInternalException.class)//indica que é um tratador de excessão de ObjectNotFoundException.class
	public ResponseEntity<StandarError> DataInternalException(DataInternalException e, HttpServletRequest request){
		
		StandarError err = new StandarError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err); 
	
	}
}
