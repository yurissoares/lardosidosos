package com.ufrb.lardosidosos.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ufrb.lardosidosos.domain.model.enums.Parentesco;

import lombok.Data;

@Data
@Entity
public class Contato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull 
	@ManyToOne
	private Morador morador;
	
	@NotBlank
	@Size(max=50)
	private String nome;
	
	@Enumerated(EnumType.STRING) 
	private Parentesco parentesco;

	private String informacoes;

}
