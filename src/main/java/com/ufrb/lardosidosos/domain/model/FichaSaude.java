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
import com.ufrb.lardosidosos.domain.model.enums.MotivoNaoPreenchida;
import com.ufrb.lardosidosos.domain.model.enums.TipoFichaSaude;

import lombok.Data;

@Data
@Entity
public class FichaSaude {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull 
	@ManyToOne
	private Morador morador;
	
	@NotNull
	@ManyToOne
	private Usuario usuario;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	@Enumerated(EnumType.STRING)
	private TipoFichaSaude tipoFichaSaude;
	
	private Boolean naoPreenchida;
	
	@Enumerated(EnumType.STRING)
	private MotivoNaoPreenchida motivoNaoPreenchida;
	
}
