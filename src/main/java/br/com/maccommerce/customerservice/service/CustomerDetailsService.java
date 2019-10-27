package br.com.maccommerce.customerservice.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.maccommerce.customerservice.entity.Customer;
import br.com.maccommerce.customerservice.exception.ClienteNaoEncontadoException;
import br.com.maccommerce.customerservice.exception.ListaVaziaException;
import br.com.maccommerce.customerservice.exception.UserNameJaExisteException;
import br.com.maccommerce.customerservice.repository.CustomerRepository;

@Service
public class CustomerDetailsService {
	
	
	@Autowired
	public CustomerRepository customerRepository;
	
	public boolean userNameExists(String username, Integer id) {
		boolean exist=false;
		List<Customer> customerResult = customerRepository.findByUsername(username);
		if(!(customerResult.isEmpty())&& (customerResult.get(0).getId()!=id))
		{
		exist=true;
		}
		return exist;
	}
	
	//Busca um cliente
	public Optional<Customer> retrieveOneCustomer(@PathVariable Integer id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new ClienteNaoEncontadoException("Cliente não encontrado (id - "+ id +")");
		}
		
		return customer;

	}
	 //Lista todos os clientes
		public List<Customer> retrieveAllCustomer() {
			List<Customer> customers = customerRepository.findAll();
			if (customers.isEmpty()) {
				throw new ListaVaziaException("Nenhum cliente cadastrado");
			}
			return customers;
		}
		
	 //Adiciona o cliente
	public boolean insertCustomer(@Valid @RequestBody Customer newCustomer) throws Exception{
		 boolean added = false;
 
		if (userNameExists(newCustomer.getUsername(), newCustomer.getId())) {
			 throw new UserNameJaExisteException("username já existe!");
		 }
		 Customer savedCustomer = customerRepository.save(newCustomer);
		 
		 return added=true;
	 }
	
	
	 //Altera o cliente
	 public boolean updateCustomer(@Valid @RequestBody Customer newCustomer,@PathVariable Integer id)throws Exception{
		 boolean updated = false;
		 Optional<Customer> customerChanged = customerRepository.findById(id);		
		 if (!customerChanged.isPresent()) {
				throw new ClienteNaoEncontadoException("Cliente não encontrado (id - "+ id +")");
		}
		 if (userNameExists(newCustomer.getUsername(),id)) {
			 throw new UserNameJaExisteException("username já existe!");
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