package br.com.senai.cardapiosmktplaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senai.cardapiosmktplaceapi.entity.Opcao;

@Repository
public interface OpcoesRepository extends JpaRepository<Opcao, Integer> {

}
