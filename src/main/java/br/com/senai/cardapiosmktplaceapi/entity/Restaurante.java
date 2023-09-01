package br.com.senai.cardapiosmktplaceapi.entity;

import br.com.senai.cardapiosmktplaceapi.entity.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "restaurantes")
@Entity(name = "Restaurante")
public class Restaurante {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	@Column(name = "nome")
	@NotBlank(message = "O nome do restaurante é obrigatorio!")
	@Size(min = 3, max = 250, message = "O nome do restaurante deve conter entre 3 e 250 caracteres!")
	private String nome;

	@Column(name = "descricao")
	@NotBlank(message = "A descrição do restaurante é obrigatorio!")
	private String descricao;

	@Column(name = "status")
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "O status do restaurante é obrigatorio")
	private Status status;

	@Embedded // informa que é um valor imbutido
	@Valid // informa para validar os campos do endereco
	private Endereco endereco;

	@ManyToOne(fetch = FetchType.LAZY) // "informa relacionamento muitos para um e traz somente quando solicitado"
	@JoinColumn(name = "id_categoria") // faz a ligação entre as tabelas
	@NotNull(message = "A categoria é obrigatória!")
	private Categoria categoria;

	public Restaurante() {
		this.status = Status.A;

	}

	@Transient
	public boolean isPersistido() {
		return getId() != null && getId() > 0;

	}

	@Transient
	public boolean isAtivo() {
		return getStatus() == Status.A;

	}

}
