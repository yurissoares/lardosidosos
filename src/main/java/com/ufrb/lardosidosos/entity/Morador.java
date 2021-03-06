package com.ufrb.lardosidosos.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ufrb.lardosidosos.constant.ValidityErrorMsg;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ufrb.lardosidosos.entity.enums.EstadoCivil;
import com.ufrb.lardosidosos.entity.enums.Estados;
import com.ufrb.lardosidosos.entity.enums.Parentesco;
import com.ufrb.lardosidosos.entity.enums.Sexo;
import com.ufrb.lardosidosos.entity.enums.Situacao;
import com.ufrb.lardosidosos.entity.enums.TipoAposentadoria;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "morador")
@Data
@NoArgsConstructor
public class Morador implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final String INVALID_CPF = "CPF Inválido";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank(message = "Informe um nome para o morador.")
	@Size(max = 50, message = ValidityErrorMsg.MAX_CHAR_SIZE_50)
	@Column(name = "nome")
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "sexo")
	private Sexo sexo;

	@NotNull(message = ValidityErrorMsg.DATE_NOT_BLANK)
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_entrada")
	private LocalDate dataEntrada;

	@NotNull(message = ValidityErrorMsg.DATE_NOT_BLANK)
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado_civil")
	private EstadoCivil estadoCivil;

	@Column(name = "qtd_filhos")
	private int qtdFilhos;

	@Size(max = 50, message = ValidityErrorMsg.MAX_CHAR_SIZE_50)
	@Column(name = "naturalidade")
	private String naturalidade;

	// endereço
	@Size(max = 70, message = ValidityErrorMsg.MAX_CHAR_SIZE_70)
	@Column(name = "end_logradouro")
	private String endLogradouro;

	@Size(max = 70, message = ValidityErrorMsg.MAX_CHAR_SIZE_70)
	@Column(name = "end_numero")
	private String endNumero;

	@Size(max = 70, message = ValidityErrorMsg.MAX_CHAR_SIZE_70)
	@Column(name = "end_bairro")
	private String endBairro;

	@Size(max = 70, message = ValidityErrorMsg.MAX_CHAR_SIZE_70)
	@Column(name = "end_cidade")
	private String endCidade;

	@Enumerated(EnumType.STRING)
	@Column(name = "end_estado")
	private Estados endEstado;

	@Size(max = 10, message = ValidityErrorMsg.MAX_CHAR_SIZE_10)
	@Column(name = "end_cep")
	private String endCep;
	// fim do endereço

	@CPF(message = INVALID_CPF)
	@Column(name = "nm_cpf")
	private String nmCpf;

	@NotBlank(message = "Insira um RG para o morador.")
	@Size(max = 20, message = ValidityErrorMsg.MAX_CHAR_SIZE_20)
	@Column(name = "nm_rg")
	private String nmRg;

	@Size(max = 50, message = ValidityErrorMsg.MAX_CHAR_SIZE_50)
	@Column(name = "nm_ctps")
	private String nmCtps;

	@Size(max = 50, message = ValidityErrorMsg.MAX_CHAR_SIZE_50)
	@Column(name = "nm_beneficio")
	private String nmBeneficio;

	@Column(name = "eh_aposentado")
	private boolean ehAposentado;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_aposentadoria")
	private TipoAposentadoria tipoAposentadoria;

	@Column(name = "tem_emprestimo")
	private boolean temEmprestimo;

	@Column(name = "valor_parcela_emprestimo")
	private double valorParcelaEmprestimo;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_ultima_parcela_emprestimo")
	private LocalDate dataUltimaParcelaEmprestimo;

	@Size(max = 255, message = ValidityErrorMsg.MAX_CHAR_SIZE_255)
	@Column(name = "medicacoes")
	private String medicacoes;

	@Size(max = 255, message = ValidityErrorMsg.MAX_CHAR_SIZE_255)
	@Column(name = "motivo_ingresso")
	private String motivoIngresso;

	@Enumerated(EnumType.STRING)
	@Column(name = "situacao")
	private Situacao situacao;

	// responsável
	@Size(max = 50, message = ValidityErrorMsg.MAX_CHAR_SIZE_50)
	@Column(name = "nome_responsavel")
	private String nomeResponsavel;

	// endereço
	@Size(max = 70, message = ValidityErrorMsg.MAX_CHAR_SIZE_70)
	@Column(name = "end_logradouro_responsavel")
	private String endLogradouroResponsavel;

	@Size(max = 70, message = ValidityErrorMsg.MAX_CHAR_SIZE_70)
	@Column(name = "end_numero_responsavel")
	private String endNumeroResponsavel;

	@Size(max = 70, message = ValidityErrorMsg.MAX_CHAR_SIZE_70)
	@Column(name = "end_bairro_responsavel")
	private String endBairroResponsavel;

	@Size(max = 70, message = ValidityErrorMsg.MAX_CHAR_SIZE_70)
	@Column(name = "end_cidade_responsavel")
	private String endCidadeResponsavel;

	@Enumerated(EnumType.STRING)
	@Column(name = "end_estado_responsavel")
	private Estados endEstadoResponsavel;

	@Size(max = 10, message = ValidityErrorMsg.MAX_CHAR_SIZE_10)
	@Column(name = "end_cep_responsavel")
	private String endCepResponsavel;
	// fim do endereço

	@CPF(message = INVALID_CPF)
	@Column(name = "nm_cpf_responsavel")
	private String nmCpfResponsavel;

	@Size(max = 20, message = ValidityErrorMsg.MAX_CHAR_SIZE_20)
	@Column(name = "nm_rg_responsavel")
	private String nmRgResponsavel;

	@Size(max = 20, message = ValidityErrorMsg.MAX_CHAR_SIZE_20)
	@Column(name = "tel_responsavel")
	private String telResponsavel;

	@Enumerated(EnumType.STRING)
	@Column(name = "parentesco_responsavel")
	private Parentesco parentescoResponsavel;

	@Column(name = "obs_responsavel")
	private String obsResponsavel;

}
