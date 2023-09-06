package br.com.senai.cardapiosmktplaceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.cardapiosmktplaceapi.entity.Categoria;
import br.com.senai.cardapiosmktplaceapi.entity.enums.Status;
import br.com.senai.cardapiosmktplaceapi.entity.enums.TipoDeCategoria;
import br.com.senai.cardapiosmktplaceapi.repository.CategoriasRepository;
import br.com.senai.cardapiosmktplaceapi.repository.RestaurantesRepository;
import br.com.senai.cardapiosmktplaceapi.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriasRepository repository;

	@Autowired
	private RestaurantesRepository restaurantesRepository;

	@Override
	public Categoria salvar(Categoria categoria) {
		Categoria CategoriaEncontrada = repository.buscarPor(categoria.getNome(), categoria.getTipo());
		if (CategoriaEncontrada != null) {
			if (categoria.isPersistido()) {
				Preconditions.checkArgument(CategoriaEncontrada.equals(categoria),
						"O nome da categoria já esta em uso!");
			}

		}
		Categoria categoriaSalva = repository.save(categoria);
		return categoriaSalva;
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status) {
		Categoria categoriaEncontrada = repository.buscarPor(id);
		Preconditions.checkNotNull(categoriaEncontrada, "Não existe categoria vinculada ao id informado!");
		Preconditions.checkArgument(categoriaEncontrada.getStatus() != status,
				"O status já está salvo para a cetegoria!");
		this.repository.atualizarPor(id, status);

	}

	@Override
	public Page<Categoria> listarPor(String nome, Status status, TipoDeCategoria tipo, Pageable paginacao) {
		return repository.listarPor(nome + "%", status, tipo, paginacao);
	}

	@Override
	public Categoria buscarPor(Integer id) {
		Categoria categoriaEncontrada = repository.buscarPor(id);
		Preconditions.checkNotNull(categoriaEncontrada, "Não foi encontrada categoria para o id informado!");
		Preconditions.checkArgument(categoriaEncontrada.isAtiva(), "A categoria está inativa!");
		return categoriaEncontrada;
	}

	@Override
	public Categoria excluirPor(Integer id) {
		Categoria categoria = buscarPor(id);
		Long qtdDeRestaurantesVinculados = restaurantesRepository.contarPor(id);
		Preconditions.checkArgument(qtdDeRestaurantesVinculados == 0,
				"Não é possivel remover pois existem restaurantes vinculados!");
		this.repository.deleteById(id);
		return categoria;
	}

}
