package br.com.senai.cardapiosmktplaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.cardapiosmktplaceapi.entity.OpcaoDoCardapio;

@Repository
public interface OpcoesCardapioRepository extends JpaRepository<OpcaoDoCardapio, Integer> {
	
	@Query(value = "SELECT Count(oc) FROM OpcaoDoCardapio oc WHERE oc.secao.id = :idDaOpcaoDoRestaurante")
	public Long contarPor(Integer idDaOpcaoDoRestaurante);
	
}
