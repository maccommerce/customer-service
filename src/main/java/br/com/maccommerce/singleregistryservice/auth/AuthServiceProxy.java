package br.com.maccommerce.singleregistryservice.auth;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.maccommerce.singleregistryservice.entity.Customer;
import br.com.maccommerce.singleregistryservice.entity.Login;


@FeignClient(name="auth-service", url="localhost:8000")
public interface AuthServiceProxy {
	
	@PostMapping("/register/1003")
	public Customer saveLogin (@Valid @RequestBody Login login);
	
}
