package com.demo.backendtestjava;

import com.demo.backendtestjava.entities.Estabelecimento;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendTestJavaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BackendTestJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setQuantidadeDeVagas(6);
		System.out.println(estabelecimento.getQuantidadeDeVagas());
	}
}
