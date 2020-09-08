package com.ufrb.lardosidosos.domain.model;

import java.io.Serializable;
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
import com.ufrb.lardosidosos.domain.model.enums.TipoDocumentoMorador;

import lombok.Data;

@Data
@Entity
public class DocumentoMorador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	private Morador morador;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataEntrega;
	
	@Enumerated(EnumType.STRING)
	private TipoDocumentoMorador tipoDocumentoMorador;

}
