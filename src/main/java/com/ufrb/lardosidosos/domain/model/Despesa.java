package com.ufrb.lardosidosos.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.ufrb.lardosidosos.domain.model.enums.CategoriaDespesa;

import lombok.Data;

@Data
@Entity
public class Despesa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private CategoriaDespesa categoriaDespesa;
	
	@NotBlank
	private String descricao;

	private Double preco;

}
