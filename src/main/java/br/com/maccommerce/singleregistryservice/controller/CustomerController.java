package br.com.maccommerce.singleregistryservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.maccommerce.singleregistryservice.auth.AuthServiceProxy;
import br.com.maccommerce.singleregistryservice.entity.Customer;
import br.com.maccommerce.singleregistryservice.entity.Login;
import br.com.maccommerce.singleregistryservice.service.CustomerDetailsService;

@RestController
@CrossOrigin
public class CustomerController {
	
    Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	AuthServiceProxy authServiceProxy;

//
	@GetMapping("/customers")
	public List<Customer> listAllCustomers() {
		return customerDetailsService.retrieveAllCustomer();

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
	 public ResponseEntity<Object> addCustomer(@Valid @RequestBody String user) throws Exception{
		 //Adiciona o Usuário

		 ObjectMapper objectMapper = 
				    new ObjectMapper()
				        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	        //read json file and convert to customer object
	     Customer newCustomer = objectMapper.readValue(user, Customer.class);
	     Login newLogin = objectMapper.readValue(user, Login.class);

		 
		 logger.info("Adicionando usuário...");
		 URI local=null;

		 try {
			 logger.info("Registrando usuário...");
			 logger.info(newLogin.toString());

			 authServiceProxy.saveLogin(newLogin);
			 customerDetailsService.insertCustomer(newCustomer);

				 //Monta a URI de resposta baseado no usuário criado
				 local = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCustomer.getId()).toUri();
				  
				 logger.info("Usuário registrado!!!!");

				 return ResponseEntity.created(local).build();

				
		 }catch (Exception e) {
			logger.error("Erro ao salvar usuário");
			
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST)
		            .body("Error Message");
		}


	 }
	 
//Altera um customer
	 @PostMapping("/customers/{id}") 
	 public void alterCustomer(@Valid @RequestBody Customer newCustomer,@PathVariable Integer id) throws Exception{
		 customerDetailsService.updateCustomer(newCustomer, id);
	 }
}
