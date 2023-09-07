package br.com.senai.cardapiosmktplaceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.cardapiosmktplaceapi.entity.Categoria;
import br.com.senai.cardapiosmktplaceapi.entity.Opcao;
import br.com.senai.cardapiosmktplaceapi.entity.Restaurante;
import br.com.senai.cardapiosmktplaceapi.entity.enums.Status;
import br.com.senai.cardapiosmktplaceapi.repository.OpcoesCardapioRepository;
import br.com.senai.cardapiosmktplaceapi.repository.OpcoesRepository;
import br.com.senai.cardapiosmktplaceapi.service.OpcaoService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Service
public class OpcaoServiceImpl implements OpcaoService {

	@Autowired
	private OpcoesRepository repository;

	@Autowired
	private OpcoesCardapioRepository opcoesCardapioRepository;

	@Override
	public Opcao salvar(Opcao opcao) {
		Opcao opcaoBanco = repository.buscarPor(opcao.getNome());
		if (opcaoBanco != null) {
			Preconditions.checkArgument(opcaoBanco.equals(opcao), "O nome da seção já esta em uso!");

		}
		Opcao opcaoSalvar = repository.save(opcao);

		return opcaoSalvar;
	}

	@Override
	public Opcao excluirPor(Integer id) {
		Opcao opcaoBanco = buscarPor(id);
		Long qtdDeCardapiosVinculados = opcoesCardapioRepository.contarPor(id);
		Preconditions.checkArgument(qtdDeCardapiosVinculados == 0, "Existem cardápios vinculados a essa seção!");
		this.repository.deleteById(id);
		return opcaoBanco;
	}

	@Override
	public Page<Opcao> listarPor(String nome, Categoria categoria, Pageable paginacao) {

		return repository.listarPor(nome, categoria, paginacao);
	}

	@Override
	public Page<Opcao> listarPor(String nome, Restaurante restaurante, Pageable paginacao) {

		return repository.listarPor(nome, restaurante, paginacao);
	}
	
	@Override
	public Page<Opcao> listarPor(String nome,Pageable paginacao) {
		
		return repository.listarPor(nome, paginacao);
	}

	@Override
	public Opcao buscarPor(Integer id) {
		return null;
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status) {

	}

	

}
