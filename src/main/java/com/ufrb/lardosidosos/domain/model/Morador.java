package com.ufrb.lardosidosos.domain.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ufrb.lardosidosos.domain.model.enums.EstadoCivil;
import com.ufrb.lardosidosos.domain.model.enums.Estados;
import com.ufrb.lardosidosos.domain.model.enums.Parentesco;
import com.ufrb.lardosidosos.domain.model.enums.TipoAposentadoria;

import lombok.Data;

@Data
@Entity
public class Morador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private @NotBlank String nome;

	@NotNull
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dataEntrada;
	
	@NotNull
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dataNascimento;
	
	private @Enumerated(EnumType.STRING) EstadoCivil estadoCivil;
	private int qtdFilhos;
	private String naturalidade;
	
	/*ENDEREÇO*****/
	
	private @NotBlank String endLogradouro;
	private @NotBlank String endNumero;
	private @NotBlank String endBairro;
	private @NotBlank String endCidade;
	private @Enumerated(EnumType.STRING) Estados endEstado;
	private String endCep;
	/*FIM-ENDEREÇO*****/
	
	private @CPF String nmCpf;
	private String nmRg;
	private String nmCtps;
	private String nmBeneficio;
	private boolean ehAposentado;
	private @Enumerated(EnumType.STRING) TipoAposentadoria tipoAposentadoria;
	private boolean temEmprestimo;
	private String medicacoes;
	private String motivoEntrada;
	private @NotBlank String nomeResponsavel;
	
	/*ENDEREÇO*****/
	private @NotBlank String endLogradouroResponsavel;
	private @NotBlank String endNumeroResponsavel;
	private @NotBlank String endBairroResponsavel;
	private @NotBlank String endCidadeResponsavel;
	private @Enumerated(EnumType.STRING) Estados endEstadoResponsavel;
	private String endCepResponsavel;
	/*FIM-ENDEREÇO*****/

	private @CPF String nmCpfResponsavel;
	private String nmRgResponsavel;
	private String telResponsavel;
	private @Enumerated(EnumType.STRING) Parentesco parentescoResponsavel;
	private String obsResponsavel;

}
