package br.com.senai.cardapiosmktplaceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.cardapiosmktplaceapi.entity.Cardapio;
import br.com.senai.cardapiosmktplaceapi.entity.Restaurante;
import br.com.senai.cardapiosmktplaceapi.entity.enums.Status;

@Repository
public interface CardapiosRepository extends JpaRepository<Cardapio, Integer> {
	
	@Query(value = "SELECT Count(c) FROM Cardapio c WHERE c.restaurante.id = :idDoRestaurante")
	public Long contarPor(Integer idDoRestaurante);
	
	@Query(value = "SELECT c FROM Cardapio c JOIN FETCH c.restaurante r JOIN FETCH c.opcoes oc JOIN FETCH oc.opcao o JOIN FETCH oc.secao s WHERE c.restaurante = :restaurante",
			countQuery = "SELECT Count(c) FROM Cardapio c WHERE c.restaurante = :restaurante AND o.status = 'A' ORDER BY oc.recomendado DESC, o.nome")
	public Page<Cardapio> listarPor(Restaurante restaurante, Pageable paginacao); 
	
	@Query(value = "SELECT c FROM Cardapio c JOIN FETCH c.restaurante r JOIN FETCH c.opcoes oc JOIN FETCH oc.opcao o JOIN FETCH oc.secao s WHERE c.id = :id AND o.status = 'A' ORDER BY oc.recomendado, o.nome")
	public Cardapio buscarPor(Integer id);
	
	
	@Query(value = "UPDATE Cardapio c SET c.status = :status WHERE c.id = :id")
	public void atualizarPor(Integer id, Status status);
}
