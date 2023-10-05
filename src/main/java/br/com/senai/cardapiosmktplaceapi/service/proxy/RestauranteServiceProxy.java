package br.com.senai.cardapiosmktplaceapi.service.proxy;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senai.cardapiosmktplaceapi.dto.Notificacao;
import br.com.senai.cardapiosmktplaceapi.entity.Categoria;
import br.com.senai.cardapiosmktplaceapi.entity.Restaurante;
import br.com.senai.cardapiosmktplaceapi.entity.enums.Status;
import br.com.senai.cardapiosmktplaceapi.service.RestauranteService;

@Service
public class RestauranteServiceProxy implements RestauranteService {
	
	@Autowired
	private RestauranteService service;
	
	@Value("${email.endereco-admin}")
	private String enderecoDeEmail;
	
	@Autowired
	private ProducerTemplate toEmail;
	
	@Override
	public Restaurante salvar(Restaurante restaurante) {
		Restaurante restauranteSalvo = service.salvar(restaurante);
		Notificacao notificacao = gerarNotificacaoPara(restauranteSalvo);
		this.toEmail.sendBody("direct:enviarEmail", notificacao);
		return restauranteSalvo;
	}
	
	private Notificacao gerarNotificacaoPara (Restaurante restaurante) {
		Notificacao notificacao = new Notificacao();
		notificacao.setDestinatario(enderecoDeEmail);
		notificacao.setTitulo("Restaurante criado/Atualizado");
		StringBuilder texto = new StringBuilder();
		texto.append("<p>Olá,</p>");
		texto.append("<p>O restaurante <b>")
			.append(restaurante.getNome() + "</b> foi criado ou atualizado no sistema</p>");
		texto.append("<p>Esse e-mail é automático. Não deve ser respondido.</p>");
		texto.append("<p>Atenciosamente,</p>");
		texto.append("<p><b>MktPlace SENAI </b></p>");
		notificacao.setMensagem(texto.toString());
		return notificacao;
		
		
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status) {
		this.service.atualizarStatusPor(id, status);
	}

	@Override
	public Page<Restaurante> listarPor(String nome, Categoria categoria, Pageable paginacao) {

		return service.listarPor(nome, categoria, paginacao);
	}

	@Override
	public Restaurante buscarPor(Integer id) {

		return service.buscarPor(id);
	}

	@Override
	public Restaurante excluirPor(Integer id) {

		return service.excluirPor(id);
	}

}
