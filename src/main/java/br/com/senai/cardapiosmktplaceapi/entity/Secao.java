package br.com.senai.cardapiosmktplaceapi.entity;

import br.com.senai.cardapiosmktplaceapi.entity.enums.Status;
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

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "secoes")
@Entity(name = "Secao")
public class Secao {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	@Size(min = 3, max = 100, message = "O nome da seção não deve conter mais de 100 caracteres!")
	@NotBlank(message = "O nome da seção é obrigatório!")
	@Column(name = "nome")
	private String nome;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "O status da seção é obrigatório!")
	@Column(name = "status")
	private Status status;

	public Secao() {
		this.status = Status.A;

	}

	@Transient
	public boolean isPersistida() {
		return getId() != null && getId() > 0;

	}
	
	@Transient
	public boolean isAtiva () {
		return getStatus() == Status.A;
		
	}

}
