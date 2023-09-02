package br.com.senai.cardapiosmktplaceapi.entity.composite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OpcaoDoCardapioId {
	
	@Column(name = "id_cardapior")
	private Integer idDoCardapio;
	
	@Column(name = "id_opcao")
	private Integer idDaOpcao;
}
