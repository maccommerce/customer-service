package com.maccommerce.customerservice;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.maccommerce.exception.UsuarioNaoEncontadoException;

@RestController
public class CustomerController {

	
	@Autowired
	public CustomerRepository customerRepository;
	
	@Autowired
	CustomerDetailsService customerDetailsService;

//
	@GetMapping("/customers")
	public List<Customer> retrieveAllCustomers() {
		return customerRepository.findAll();

	}

//Consulta um customer por id
	
	@GetMapping("/customers/{id}")
	public Optional<Customer> findOneCustomer(@PathVariable Integer id) {
		return customerDetailsService.retrieveOneCustomer(id); 

	}

//Apaga um customer
	@DeleteMapping("/customers/{id}")
	public void deleteOneCustomer(@PathVariable Integer id) {
		customerDetailsService.deleteCustomer(id);
	}

	
//Insere um customer
	 @PostMapping("/customers") 
	 public ResponseEntity<Object> addCustomer(@Valid @RequestBody Customer newCustomer) throws Exception{
		 //Adiciona o Usuário
		 
		 URI local=null;
		 if(customerDetailsService.insertCustomer(newCustomer)) {
		 //Monta a URI de resposta baseado no usuário criado
		 local = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCustomer.getId()).toUri();
		 }
		 
		 return ResponseEntity.created(local).build();
	 }
	 
//Altera um customer
	 @PostMapping("/customers/{id}") 
	 public void alterCustomer(@Valid @RequestBody Customer newCustomer,@PathVariable Integer id) throws Exception{
		 customerDetailsService.updateCustomer(newCustomer, id);
	 }
}
