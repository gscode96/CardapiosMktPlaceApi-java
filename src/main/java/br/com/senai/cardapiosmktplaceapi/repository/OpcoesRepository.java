package br.com.senai.cardapiosmktplaceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.cardapiosmktplaceapi.entity.Categoria;
import br.com.senai.cardapiosmktplaceapi.entity.Opcao;
import br.com.senai.cardapiosmktplaceapi.entity.Restaurante;
import br.com.senai.cardapiosmktplaceapi.entity.enums.Status;

@Repository
public interface OpcoesRepository extends JpaRepository<Opcao, Integer> {
	
	@Query(value = "SELECT o FROM Opcao o WHERE Upper(o.nome) LIKE Upper (:nome) ORDER BY o.nome"
			,countQuery = "SELECT Count(o) FROM Opcao o WHERE Upper(o.nome) LIKE Upper (:nome)")
	public Page<Opcao> listarPor(String nome, Pageable paginacao);
	
	@Query(value = "SELECT o FROM Opcao o JOIN FETCH o.categoria WHERE Upper(o.nome) LIKE Upper (:nome) AND o.categoria = :categoria ORDER BY o.nome"
			,countQuery = "SELECT Count(o) FROM Opcao o WHERE Upper(o.nome) LIKE Upper (:nome)")
	public Page<Opcao> listarPor(String nome, Categoria categoria, Pageable paginacao);
	
	@Query(value = "SELECT o FROM Opcao o JOIN FETCH o.restaurante WHERE Upper(o.nome) LIKE Upper (:nome) AND o.restaurante = :restaurante ORDER BY o.nome"
			,countQuery = "SELECT Count(o) FROM Opcao o WHERE Upper(o.nome) LIKE Upper (:nome)")
	public Page<Opcao> listarPor(String nome, Restaurante restaurante, Pageable paginacao);
	
	
	@Query(value = "SELECT o FROM Opcao o WHERE Upper(o.nome) = Upper(:nome)")
	public Opcao buscarPor(String nome);
	
	@Query(value = "SELECT o FROM Opcao o JOIN FETCH o.categoria JOIN FETCH o.restaurante WHERE o.id = :id")
	public Opcao buscarPor(Integer id);
	
	@Modifying
	@Query(value = "UPDATE Opcao o SET o.status = :status WHERE o.id = :id")
	public void atualizarPor(Integer id, Status status);
	
	
	
}
