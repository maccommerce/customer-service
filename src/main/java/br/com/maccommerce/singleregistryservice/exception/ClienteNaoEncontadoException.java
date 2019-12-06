package br.com.maccommerce.singleregistryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNaoEncontadoException extends RuntimeException {

	public ClienteNaoEncontadoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
