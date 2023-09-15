package br.com.senai.cardapiosmktplaceapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.cardapiosmktplaceapi.entity.Categoria;
import br.com.senai.cardapiosmktplaceapi.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private MapConverter converter;
	
	@Autowired
	@Qualifier("categoriaServiceProxy")
	private CategoriaService service;
	
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable("id") Integer id) {
		Categoria categoriaEncontrada = service.buscarPor(id);
		return ResponseEntity.ok(converter.toJsonMap(categoriaEncontrada));
	}
}
