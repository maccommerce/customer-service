package br.com.maccommerce.singleregistryservice.exception;

//@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UserNameJaExisteException extends RuntimeException{
	
	public UserNameJaExisteException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
