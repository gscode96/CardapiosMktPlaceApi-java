package br.com.senai.cardapiosmktplaceapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;

import br.com.senai.cardapiosmktplaceapi.repository.CategoriasRepository;
import br.com.senai.cardapiosmktplaceapi.repository.RestaurantesRepository;

@SpringBootApplication
public class InitApp {

	@Autowired
	private CategoriasRepository categoriasRepository;
	@Autowired
	private RestaurantesRepository restaurantesRepository;

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);

	}

	@Bean
	public Hibernate5JakartaModule jsonHibernate5Module() {

		return new Hibernate5JakartaModule();
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Funcionou");
		};

	}
}
