package br.com.senai.cardapiosmktplaceapi.entity;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.cardapiosmktplaceapi.entity.enums.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "cardapios")
@Entity(name = "Cardapio")
public class Cardapio {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	@Size(max = 100, message = "O tamanho do nome deve ser de até 100 caracteres")
	@NotBlank(message = "O nome é obrigatório")
	@Column(name = "nome")
	private String nome;

	@NotBlank(message = "A descrição do cardápio é obrigatória")
	@Column(name = "descricao")
	private String descricao;

	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "O status da cardápio é obrigatório!")
	@Column(name = "status")
	private Status status;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_restaurante")
	@NotNull(message = "O restaurante é obrigatório")
	private Restaurante restaurante;
	
	@OneToMany(mappedBy = "cardapio", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OpcaoDoCardapio> opcoes;
	

	public Cardapio() {
		this.status = Status.A;
		this.opcoes = new ArrayList<>();
	}

	@Transient
	public boolean isPersistido() {
		return getId() != null && getId() > 0;

	}

	@Transient
	public boolean isAtiva() {
		return getStatus() == Status.A;

	}

}
