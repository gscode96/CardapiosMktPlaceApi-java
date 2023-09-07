package br.com.senai.cardapiosmktplaceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.google.common.base.Preconditions;

import br.com.senai.cardapiosmktplaceapi.entity.Categoria;
import br.com.senai.cardapiosmktplaceapi.entity.OpcaoDoCardapio;
import br.com.senai.cardapiosmktplaceapi.entity.Restaurante;
import br.com.senai.cardapiosmktplaceapi.entity.Secao;
import br.com.senai.cardapiosmktplaceapi.entity.enums.Status;
import br.com.senai.cardapiosmktplaceapi.repository.CardapiosRepository;
import br.com.senai.cardapiosmktplaceapi.repository.OpcoesCardapioRepository;
import br.com.senai.cardapiosmktplaceapi.repository.SecoesRepository;
import br.com.senai.cardapiosmktplaceapi.service.SecaoService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class SecaoServiceImpl implements SecaoService {

	@Autowired
	private SecoesRepository repository;
	
	@Autowired
	private OpcoesCardapioRepository opcoesCardapioRepository;

	@Override
	public Secao salvar(Secao secao) {
		Secao secaoBanco = repository.buscarPor(secao.getNome());
		if (secaoBanco != null) {
			if (secaoBanco.isPersistida()) {
				Preconditions.checkArgument(secaoBanco.equals(secao), "O nome da seção já esta em uso!");
			}
		}
		Secao secaoSalvar = repository.save(secao);

		return secaoSalvar;
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status) {

		Secao secaoBanco = repository.buscarPor(id);
		Preconditions.checkNotNull(secaoBanco, "O id não está vinculado a uma seção!");
		Preconditions.checkArgument(secaoBanco.getStatus() != status, "O status já esta salvo!");
		this.repository.atualizarPor(id, status);

	}

	@Override
	public Page<Secao> listarPor(String nome, Pageable paginacao) {

		return repository.listarPor("%" + nome + "%", paginacao);
	}

	@Override
	public Secao buscarPor(Integer id) {
		Secao secaoBanco = repository.buscarPor(id);
		Preconditions.checkNotNull(secaoBanco, "Não foi encontrada seção para o id informado!");
		Preconditions.checkArgument(secaoBanco.isAtiva(), "A seção está inativa!");
		return secaoBanco;
	}

	@Override
	public Secao excluirPor(Integer id) {
		Secao secaoBanco = buscarPor(id);
		Long qtdDeCardapiosVinculados = opcoesCardapioRepository.contarPor(id);
		Preconditions.checkArgument(qtdDeCardapiosVinculados == 0, "Existem cardápios vinculados a essa seção!");
		this.repository.deleteById(id);
		return secaoBanco;

	}

}
