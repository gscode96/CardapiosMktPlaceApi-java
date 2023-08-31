package br.com.senai.cardapiosmktplaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.cardapiosmktplaceapi.entity.Categoria;

@Repository
public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
	
	@Query(value = "SELECT c FROM Categoria c WHERE c.id = :id")
	public Categoria buscarPor(Integer id);

}
