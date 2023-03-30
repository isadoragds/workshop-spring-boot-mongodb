package com.isadora.workshopmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.isadora.workshopmongo.services.exception.ObjectNotFoundException;

//manipulador de excecoes na camada de resource
@ControllerAdvice //indica que é responsavel por tratar possiveis erros nas requisicoes
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class) //quando ocorrer essa excecao, é pra tratar assim
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
