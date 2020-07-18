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
import javax.validation.constraints.Size;

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

	private @NotBlank @Size(max=50) String nome;

	@NotNull
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataEntrada;
	
	@NotNull
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	private @Enumerated(EnumType.STRING) EstadoCivil estadoCivil;
	private int qtdFilhos;
	private @Size(max=50) String naturalidade;
	
	/*ENDEREÇO*****/
	
	private @NotBlank @Size(max=70) String endLogradouro;
	private @NotBlank @Size(max=70) String endNumero;
	private @NotBlank @Size(max=70) String endBairro;
	private @NotBlank @Size(max=70) String endCidade;
	private @Enumerated(EnumType.STRING) Estados endEstado;
	private @Size(max=10) String endCep;
	/*FIM-ENDEREÇO*****/
	
	private @CPF String nmCpf;
	private @Size(max=14) String nmRg;
	private @Size(max=50) String nmCtps;
	private @Size(max=50) String nmBeneficio;
	private boolean ehAposentado;
	private @Enumerated(EnumType.STRING) TipoAposentadoria tipoAposentadoria;
	private boolean temEmprestimo;
	private String medicacoes;
	private String motivoEntrada;
	private @NotBlank String nomeResponsavel;
	
	/*ENDEREÇO*****/
	private @NotBlank @Size(max=70) String endLogradouroResponsavel;
	private @NotBlank @Size(max=70) String endNumeroResponsavel;
	private @NotBlank @Size(max=70) String endBairroResponsavel;
	private @NotBlank @Size(max=70) String endCidadeResponsavel;
	private @Enumerated(EnumType.STRING) Estados endEstadoResponsavel;
	private @Size(max=10) String endCepResponsavel;
	/*FIM-ENDEREÇO*****/

	private @CPF String nmCpfResponsavel;
	private @Size(max=14) String nmRgResponsavel;
	private @Size(max=50) String telResponsavel;
	private @Enumerated(EnumType.STRING) Parentesco parentescoResponsavel;
	private String obsResponsavel;

}
