package br.com.maccommerce.singleregistryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"br.com.maccommerce.singleregistryservice"})
@EnableFeignClients("br.com.maccommerce.singleregistryservice")
@EnableDiscoveryClient
public class SingleRegistryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SingleRegistryServiceApplication.class, args);
	}

}
