package com.ufrb.lardosidosos.domain.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ufrb.lardosidosos.domain.model.enums.StatusLembrete;
import com.ufrb.lardosidosos.domain.model.enums.TipoUsuario;

import lombok.Data;

@Data
@Entity
public class Lembrete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Morador morador;
	
	private @Enumerated(EnumType.STRING) TipoUsuario tipoUsuario;
	
	@ManyToOne
	private Usuario usuario;
	
	private String obs;
	
	private @Enumerated(EnumType.STRING) StatusLembrete statusLembrete;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	@ManyToOne
	private TipoLembrete tipoLembrete;
	
}
