package com.ufrb.lardosidosos.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ufrb.lardosidosos.domain.model.enums.Status;
import com.ufrb.lardosidosos.domain.model.enums.TipoAjuda;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class FichaAdmissao extends FichaSaude implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String queixaPrincipal;
	private String medicamento;
	
	@Enumerated(EnumType.STRING) 
	private TipoAjuda alimentacao;
	
	@Enumerated(EnumType.STRING) 
	private TipoAjuda higiene;
	
	@Enumerated(EnumType.STRING) 
	private TipoAjuda deambulacao;
	
	private Boolean cadeiraRodas;
	private Boolean acamado;
	
	@Enumerated(EnumType.STRING) 
	private TipoAjuda verbalizacao;
	
	private Boolean deficienciaVisual;
	private Boolean usoOculos;
	private Boolean deficienciaAuditiva;
	private Boolean usoAparelhoAuditivo;
	
	@Enumerated(EnumType.STRING) 
	private Status olfato;
	
	@Enumerated(EnumType.STRING) 
	private Status paladar;
	
	private Boolean peleHidratada;
	private Boolean peleRessecada;
	private Boolean peleIntegra;
	private Boolean peleLesoes;
	private Boolean peleFeridaCronica;
	private Boolean dispineiaEsforco;
	private Boolean dispineiaRepouso;
	private Boolean desconforto;
	private Boolean palpitacoes;
	private Boolean edemaMmi;
	private Boolean dorMmi;
	
	private String doresArticulares;
	private String edemaArticular;
	private String fratura;
	
	private Double peso;
	private Double altura;
	
	private String examesComplementares;
	private String evolucao;
}
