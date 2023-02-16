package dev.thubas.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableEncryptableProperties
@SpringBootApplication
public class WebShopApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class, args);
	}

}
