package br.com.senai.cardapiosmktplaceapi.entity;

import br.com.senai.cardapiosmktplaceapi.entity.enums.Status;
import br.com.senai.cardapiosmktplaceapi.entity.enums.TipoDeCategoria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data // Gera o pojo total, getter e setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "categorias") // Declara para o jpa a tabela que será criada
@Entity(name = "Categoria")
public class Categoria {

	@Id // Declara como chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // gerar valor automatico do id
	@Column(name = "id") // Nome da coluna do banco
	@EqualsAndHashCode.Include
	private Integer id;

	@NotBlank(message = "O nome da categoria é obrigatório") // Para nao aceitar valores vazios
	@Size(max = 100, message = "O nome da categoria não deve conter mais de 100 caracteres") // Para limitar qtd de
																								// catacteres
	@Column(name = "nome")
	private String nome;

	@NotNull(message = "O tipo dacategoria é obrigatório")
	@Enumerated(value = EnumType.STRING) // mapear enum para string
	@Column(name = "tipo")
	private TipoDeCategoria tipo;

	@NotNull(message = "O status da categoria é obrigatório")
	@Enumerated(value = EnumType.STRING)
	@Column(name = "status")
	private Status status;

	public Categoria() {
		this.status = Status.A; // categoria sempre vai ser instanciada ativa

	}

	@Transient // Para ignorar no mapeamento jpa
	public boolean isPersistido() {
		return getId() != null && getId() > 0;

	}

	@Transient
	public boolean isAtiva() {
		return getStatus() == Status.A;

	}
}
