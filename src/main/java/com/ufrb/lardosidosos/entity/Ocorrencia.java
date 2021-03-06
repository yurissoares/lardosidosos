package com.ufrb.lardosidosos.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class Ocorrencia implements Serializable{

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	private Morador morador;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	private String obs;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private TipoOcorrencia tipoOcorrencia;
}

















