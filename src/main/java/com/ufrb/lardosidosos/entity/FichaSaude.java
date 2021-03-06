package com.ufrb.lardosidosos.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ufrb.lardosidosos.entity.enums.MotivoNaoPreenchida;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class FichaSaude implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Morador morador;

	@ManyToOne
	private Usuario usuario;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	private Boolean naoPreenchida;
	
	@Enumerated(EnumType.STRING)
	private MotivoNaoPreenchida motivoNaoPreenchida;
	
}
