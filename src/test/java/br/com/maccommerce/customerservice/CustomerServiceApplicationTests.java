package br.com.maccommerce.customerservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.maccommerce.customerservice.entity.Customer;
import br.com.maccommerce.customerservice.entity.TipoPessoa;
import br.com.maccommerce.customerservice.repository.CustomerRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	public void registryShouldPersistData() {
		
		Customer customer = new Customer("userNameTest", "namePerson","name@gmail.com", TipoPessoa.PF, "60160120", "Rua da gloria", null);
		this.customerRepository.save(customer);
		
		assertThat(customer.getId()).isNotNull();
	}
	

	@Test
	public void deleteShouldRemoveData() {
		
		Customer customer = new Customer("userNameTest", "namePerson","name@gmail.com", TipoPessoa.PF, "60160120", "Rua da gloria", null);
		this.customerRepository.save(customer);
		this.customerRepository.delete(customer);
		
		assertThat(customerRepository.findById(customer.getId())).isEmpty();
		
	}
	
	@Test
	public void updateShouldChangeAndPersistData() {
		
		Customer customer = new Customer("userNameTest", "namePerson","name@gmail.com", TipoPessoa.PF, "60160120", "Rua da gloria", null);
		this.customerRepository.save(customer);
		
		customer.setUsername("newName");
		customer.setName("newPass");
		customer.setEmail("name@hotmail.com");
		customer.setTipoPessoa(TipoPessoa.PJ);
		customer.setCep("64120100");
		customer.setEndereco("Rua da consolacao");
		
		Customer customerChanged = this.customerRepository.save(customer);
		
		assertThat(customerChanged.getUsername()).isEqualTo("newName");
		assertThat(customerChanged.getName()).isEqualTo("newPass");
		assertThat(customerChanged.getEmail()).isEqualTo("name@hotmail.com");
		assertThat(customerChanged.getTipoPessoa()).isEqualTo(TipoPessoa.PJ);
		assertThat(customerChanged.getCep()).isEqualTo("64120100");
		assertThat(customerChanged.getEndereco()).isEqualTo("Rua da consolacao");
		
	}	
	
}
