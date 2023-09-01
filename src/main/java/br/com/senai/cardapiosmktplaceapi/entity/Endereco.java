package br.com.senai.cardapiosmktplaceapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Embeddable // vai unificar com o restaurante "imbutido" pq nao existe essa tabela no banco
@Data
public class Endereco {

	@Size(max = 80, message = "O nome da cidade não deve conter mais de 80 caracteres!")
	@NotBlank(message = "A cidade é obrigatória!")
	@Column(name = "cidade")
	private String cidade;

	@Size(max = 200, message = "O logradouro não deve conter mais de 200 caracteres!")
	@NotBlank(message = "O logradouro é obrigatório!")
	@Column(name = "logradouro")
	private String logradouro;

	@Size(max = 50, message = "O bairro da cidade não deve conter mais de 50 caracteres!")
	@NotBlank(message = "O bairro é obrigatório!")
	@Column(name = "bairro")
	private String bairro;

	@Column(name = "complemento")
	private String complemento;
}
