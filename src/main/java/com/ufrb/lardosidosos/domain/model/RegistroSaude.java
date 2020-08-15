package com.ufrb.lardosidosos.domain.model;

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
public class RegistroSaude {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataEntrega;
	
	private String descricao;
	
	@ManyToOne
	private Morador morador;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private TipoRegistroSaude tipoRegistroSaude;
}

















