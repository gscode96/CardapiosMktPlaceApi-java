package br.com.senai.cardapiosmktplaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.cardapiosmktplaceapi.entity.Cardapio;

@Repository
public interface CardapiosRepository extends JpaRepository<Cardapio, Integer> {
	
	@Query(value = "SELECT Count(c) FROM Cardapio c WHERE c.restaurante.id = :idDoRestaurante")
	public Long contarPor(Integer idDoRestaurante);

}
