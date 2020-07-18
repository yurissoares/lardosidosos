package com.ufrb.lardosidosos.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.ufrb.lardosidosos.domain.model.enums.Parentesco;

import lombok.Data;

@Data
@Entity
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private @ManyToOne Morador morador;
	private @NotBlank @Size(max=50) String nome;
	private @Enumerated(EnumType.STRING) Parentesco parentesco;
	private String informacoes;

}
