package com.ufrb.lardosidosos.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class FichaEvDiaria extends FichaSaude implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "ficha_admissao_id", referencedColumnName = "id")
	private FichaAdmissao fichaAdmissao;
	
	private Integer vlPaInferior;
	private Integer vlPaSuperior;
	private Double vlTemperatura;
	private Integer vlSaturacao;
	private Integer vlHgt;
	private Integer vlFreqCardiaca;
	private Integer vlFreqRespiratoria;
	
}
