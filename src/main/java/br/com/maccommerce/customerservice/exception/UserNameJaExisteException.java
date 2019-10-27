package br.com.maccommerce.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UserNameJaExisteException extends RuntimeException{
	
	public UserNameJaExisteException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
