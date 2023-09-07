package br.com.senai.cardapiosmktplaceapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OpcoesCardapioRepository {
	
	@Query(value = "SELECT Count(o) FROM OpcaoDoCardapio o WHERE o.secao.id = :idDaSecao")
	public Long contarPor(Integer idDoRestaurante);
	
}
