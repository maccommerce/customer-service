package com.maccommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNaoEncontadoException extends RuntimeException {

	public UsuarioNaoEncontadoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
