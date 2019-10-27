package br.com.maccommerce.customerservice.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(generator = "CustomerIdSequence", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "CustomerIdSequence", sequenceName = "P_CUSTOMER_ID_SEQUENCE")
	private Integer id;

	private String username;// username de login

	@Size(min = 2,message = "O tamanho do nome deve ser maior que 2")
	private String name;
	
	private String email;

	private TipoPessoa tipoPessoa;
	
	private String cep;
	
	private String endereco;
	
	//descricao
	//comprasAnteriores
	//ultimo acesso
	
	
	@Past
	private Date birthDate;	
	
	public Customer() {
		super();
	}


	
	public Customer(@UniqueElements String username,
			@Size(min = 2, message = "O tamanho do nome deve ser maior que 2") String name, String email,
			TipoPessoa tipoPessoa, String cep, String endereco, @Past Date birthDate) {
		super();
		this.setUsername(username);
		this.name = name;
		this.email = email;
		this.tipoPessoa = tipoPessoa;
		this.cep = cep;
		this.endereco = endereco;
		this.birthDate = birthDate;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return String.format("Customer [id=%s, name=%s, email=%]", id, name, email);
	}



	

	
}
