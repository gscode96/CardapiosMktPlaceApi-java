package br.com.senai.cardapiosmktplaceapi.dto;

import java.math.BigDecimal;

import br.com.senai.cardapiosmktplaceapi.entity.Secao;
import br.com.senai.cardapiosmktplaceapi.entity.enums.Confirmacao;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovaOpcaoDoCardapio {
	
	@NotNull(message = "O id da opção é obrigatório!")
	@Positive(message = "O id da opção deve positivo!")
	private Integer idDaOpcao;
	
	
	@DecimalMin(value = "0.0", inclusive = false, message = "O preço não pode ser inferior a R$0.01" )
	@Digits(integer = 9, fraction = 2, message = "O preço deve possir o formato 'NNNNNNNNN.NN'")
	private BigDecimal preco;
	
	@NotNull(message = "O indicador de recomendação é obrigatório!")
	private Confirmacao recomendado;
	
	@NotNull(message = "A seção é obrigatória!")
	private Secao secao;
	
	
	
	
}
