package br.com.maccommerce.customerservice.auth;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.maccommerce.customerservice.entity.Customer;
import br.com.maccommerce.customerservice.entity.Login;


@FeignClient(name="auth-service", url="localhost:8000")
public interface AuthServiceProxy {
	
	@PostMapping("/register/1003")
	public Customer saveLogin (@Valid @RequestBody Login login);
	
}
