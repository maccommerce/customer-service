package com.maccommerce.customerservice;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.maccommerce.exception.UsuarioNaoEncontadoException;

@Service
public class CustomerDetailsService {
	
	
	@Autowired
	public CustomerRepository customerRepository;
	
	public boolean userNameExists(String username) {
		boolean exist=false;
		if(!customerRepository.findByUsername(username).isEmpty())
		{
		exist=true;
		}
		return exist;
	}
	
	public Optional<Customer> retrieveOneCustomer(@PathVariable Integer id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer==null) {
			//throw new UsuarioNaoEncontadoException("id - "+ id);
			throw new UsuarioNaoEncontadoException("id - "+ id);
		}
		
		return customer;

	}
	
	 //Adiciona o cliente
	public boolean insertCustomer(@Valid @RequestBody Customer newCustomer) throws Exception{
		 boolean added = false;
 
		if (userNameExists(newCustomer.getUsername())) {
			 throw new Exception("username já existe!");
		 }
		 Customer savedCustomer = customerRepository.save(newCustomer);
		 
		 return added=true;
	 }
	
	
	 //Altera o cliente
	 public boolean updateCustomer(@Valid @RequestBody Customer newCustomer,@PathVariable Integer id)throws Exception{
		 boolean updated = false;
		 Optional<Customer> customerChanged = customerRepository.findById(id);		
		 if (!customerChanged.isPresent()) {
				//throw new UsuarioNaoEncontadoException("id - "+ id);
				throw new UsuarioNaoEncontadoException("id - "+ id);
		}
		 if (userNameExists(newCustomer.getUsername())) {
			 throw new Exception("username já existe!");
		 }
		 newCustomer.setId(id);
		 Customer savedCustomer = customerRepository.saveAndFlush(newCustomer);
		 
		 return updated = true;
	 }
	 
	 //Apaga um cliente
	public void deleteCustomer(@PathVariable Integer id) {
		customerRepository.deleteById(id);
	}
	
}