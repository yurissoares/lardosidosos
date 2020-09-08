package com.ufrb.lardosidosos.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ufrb.lardosidosos.domain.model.enums.StatusLembrete;
import com.ufrb.lardosidosos.domain.model.enums.TipoUsuario;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class Lembrete implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCriacao;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataLembrete;
	
	@Enumerated(EnumType.STRING) 
	private StatusLembrete statusLembrete;
	
	@ManyToOne
	private Usuario usuarioOrigem;

	@ManyToOne
	private Usuario usuarioDestino;

	@ManyToOne
	private Morador morador;
	
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;
	
	@ManyToOne
	private TipoLembrete tipoLembrete;
	
	@ManyToOne
	private RegistroSaude registroSaude;

	private String obs;
	
}
