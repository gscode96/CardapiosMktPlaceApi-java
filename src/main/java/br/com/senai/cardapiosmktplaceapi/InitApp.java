package br.com.senai.cardapiosmktplaceapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import br.com.senai.cardapiosmktplaceapi.entity.Categoria;
import br.com.senai.cardapiosmktplaceapi.entity.enums.TipoDeCategoria;
import br.com.senai.cardapiosmktplaceapi.repository.CategoriasRepository;

@SpringBootApplication
public class InitApp {

	@Autowired
	private CategoriasRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);

	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			Categoria novaCategoria = new Categoria();
			novaCategoria.setNome("Temakeria");
			novaCategoria.setTipo(TipoDeCategoria.RESTAURANTE);
			this.repository.save(novaCategoria);

			System.out.println("Deu certo");

		};

	}
}
