package com.ufrb.lardosidosos.domain.model;

import java.io.Serializable;
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
import com.ufrb.lardosidosos.domain.model.enums.Sexo;
import com.ufrb.lardosidosos.domain.model.enums.Situacao;
import com.ufrb.lardosidosos.domain.model.enums.TipoAposentadoria;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Morador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	private String nome;

	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataEntrada;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;
	private int qtdFilhos;

	@Size(max = 50)
	private String naturalidade;

	// endereço
	@Size(max = 70)
	private String endLogradouro;

	@Size(max = 70)
	private String endNumero;

	@Size(max = 70)
	private String endBairro;

	@Size(max = 70)
	private String endCidade;

	@Enumerated(EnumType.STRING)
	private Estados endEstado;

	@Size(max = 10)
	private String endCep;
	// fim do endereço

	@CPF
	private String nmCpf;

	@Size(max = 20)
	private String nmRg;

	@Size(max = 50)
	private String nmCtps;

	@Size(max = 50)
	private String nmBeneficio;

	private boolean ehAposentado;

	@Enumerated(EnumType.STRING)
	private TipoAposentadoria tipoAposentadoria;

	private boolean temEmprestimo;
	private double valorParcelaEmprestimo;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataUltimaParcelaEmprestimo;

	private String medicacoes;
	private String motivoIngresso;

	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	// responsável
	@Size(max = 50)
	private String nomeResponsavel;

	// endereço
	@Size(max = 70)
	private String endLogradouroResponsavel;

	@Size(max = 70)
	private String endNumeroResponsavel;

	@Size(max = 70)
	private String endBairroResponsavel;

	@Size(max = 70)
	private String endCidadeResponsavel;

	@Enumerated(EnumType.STRING)
	private Estados endEstadoResponsavel;

	@Size(max = 10)
	private String endCepResponsavel;
	// fim do endereço

	@CPF
	private String nmCpfResponsavel;

	@Size(max = 20)
	private String nmRgResponsavel;

	@Size(max = 20)
	private String telResponsavel;

	@Enumerated(EnumType.STRING)
	private Parentesco parentescoResponsavel;

	private String obsResponsavel;

}
