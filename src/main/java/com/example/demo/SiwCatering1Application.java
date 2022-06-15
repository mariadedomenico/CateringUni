package com.example.demo;

import static com.example.demo.model.Credentials.ADMIN_ROLE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Amministratore;
import com.example.demo.model.Credentials;
import com.example.demo.repository.CredentialsRepository;

@SpringBootApplication
public class SiwCatering1Application implements CommandLineRunner{
	@Autowired
	CredentialsRepository credRep;

	public static void main(String[] args) {
		SpringApplication.run(SiwCatering1Application.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		
	}
	
	


}
