package dev.thubas.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@EnableEncryptableProperties
@SpringBootApplication
public class WebShopApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class, args);
	}

}
