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

@Entity
public class Morador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@NotNull
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dataEntrada;
	
	@NotNull
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dataNascimento;

	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;

	private int qtdFilhos;

	private String naturalidade;
	
	/*ENDEREÇO*****/
	@NotBlank
	private String endLogradouro;
	
	@NotBlank
	private String endNumero;
	
	@NotBlank
	private String endBairro;
	
	@NotBlank
	private String endCidade;
	
	@Enumerated(EnumType.STRING)
	private Estados endEstado;

	@Size(min = 8, max = 8)
	private String endCep;
	/*FIM-ENDEREÇO*****/
	
	@CPF
	private String nmCpf;

	@Size(min = 12, max = 12)
	private String nmRg;

	private String nmCtps;
	private String nmBeneficio;

	private boolean ehAposentado;

	@Enumerated(EnumType.STRING)
	private TipoAposentadoria tipoAposentadoria;

	private boolean temEmprestimo;
	private String medicacoes;
	private String motivoEntrada;

	@NotBlank
	private String nomeResponsavel;

	/*ENDEREÇO*****/
	@NotBlank
	private String endLogradouroResponsavel;
	
	@NotBlank
	private String endNumeroResponsavel;
	
	@NotBlank
	private String endBairroResponsavel;
	
	@NotBlank
	private String endCidadeResponsavel;
	
	@Enumerated(EnumType.STRING)
	private Estados endEstadoResponsavel;

	@Size(min = 8, max = 8)
	private String endCepResponsavel;
	/*FIM-ENDEREÇO*****/

	@CPF
	private String nmCpfResponsavel;

	@Size(min = 12, max = 12)
	private String nmRgResponsavel;

	private String telResponsavel;

	@Enumerated(EnumType.STRING)
	private Parentesco parentescoResponsavel;

	private String obsResponsavel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public int getQtdFilhos() {
		return qtdFilhos;
	}

	public void setQtdFilhos(int qtdFilhos) {
		this.qtdFilhos = qtdFilhos;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getEndLogradouro() {
		return endLogradouro;
	}

	public void setEndLogradouro(String endLogradouro) {
		this.endLogradouro = endLogradouro;
	}

	public String getEndNumero() {
		return endNumero;
	}

	public void setEndNumero(String endNumero) {
		this.endNumero = endNumero;
	}

	public String getEndBairro() {
		return endBairro;
	}

	public void setEndBairro(String endBairro) {
		this.endBairro = endBairro;
	}

	public String getEndCidade() {
		return endCidade;
	}

	public void setEndCidade(String endCidade) {
		this.endCidade = endCidade;
	}

	public Estados getEndEstado() {
		return endEstado;
	}

	public void setEndEstado(Estados endEstado) {
		this.endEstado = endEstado;
	}

	public String getEndCep() {
		return endCep;
	}

	public void setEndCep(String endCep) {
		this.endCep = endCep;
	}

	public String getNmCpf() {
		return nmCpf;
	}

	public void setNmCpf(String nmCpf) {
		this.nmCpf = nmCpf;
	}

	public String getNmRg() {
		return nmRg;
	}

	public void setNmRg(String nmRg) {
		this.nmRg = nmRg;
	}

	public String getNmCtps() {
		return nmCtps;
	}

	public void setNmCtps(String nmCtps) {
		this.nmCtps = nmCtps;
	}

	public String getNmBeneficio() {
		return nmBeneficio;
	}

	public void setNmBeneficio(String nmBeneficio) {
		this.nmBeneficio = nmBeneficio;
	}

	public boolean isEhAposentado() {
		return ehAposentado;
	}

	public void setEhAposentado(boolean ehAposentado) {
		this.ehAposentado = ehAposentado;
	}

	public TipoAposentadoria getTipoAposentadoria() {
		return tipoAposentadoria;
	}

	public void setTipoAposentadoria(TipoAposentadoria tipoAposentadoria) {
		this.tipoAposentadoria = tipoAposentadoria;
	}

	public boolean isTemEmprestimo() {
		return temEmprestimo;
	}

	public void setTemEmprestimo(boolean temEmprestimo) {
		this.temEmprestimo = temEmprestimo;
	}

	public String getMedicacoes() {
		return medicacoes;
	}

	public void setMedicacoes(String medicacoes) {
		this.medicacoes = medicacoes;
	}

	public String getMotivoEntrada() {
		return motivoEntrada;
	}

	public void setMotivoEntrada(String motivoEntrada) {
		this.motivoEntrada = motivoEntrada;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getEndLogradouroResponsavel() {
		return endLogradouroResponsavel;
	}

	public void setEndLogradouroResponsavel(String endLogradouroResponsavel) {
		this.endLogradouroResponsavel = endLogradouroResponsavel;
	}

	public String getEndNumeroResponsavel() {
		return endNumeroResponsavel;
	}

	public void setEndNumeroResponsavel(String endNumeroResponsavel) {
		this.endNumeroResponsavel = endNumeroResponsavel;
	}

	public String getEndBairroResponsavel() {
		return endBairroResponsavel;
	}

	public void setEndBairroResponsavel(String endBairroResponsavel) {
		this.endBairroResponsavel = endBairroResponsavel;
	}

	public String getEndCidadeResponsavel() {
		return endCidadeResponsavel;
	}

	public void setEndCidadeResponsavel(String endCidadeResponsavel) {
		this.endCidadeResponsavel = endCidadeResponsavel;
	}

	public Estados getEndEstadoResponsavel() {
		return endEstadoResponsavel;
	}

	public void setEndEstadoResponsavel(Estados endEstadoResponsavel) {
		this.endEstadoResponsavel = endEstadoResponsavel;
	}

	public String getEndCepResponsavel() {
		return endCepResponsavel;
	}

	public void setEndCepResponsavel(String endCepResponsavel) {
		this.endCepResponsavel = endCepResponsavel;
	}

	public String getNmCpfResponsavel() {
		return nmCpfResponsavel;
	}

	public void setNmCpfResponsavel(String nmCpfResponsavel) {
		this.nmCpfResponsavel = nmCpfResponsavel;
	}

	public String getNmRgResponsavel() {
		return nmRgResponsavel;
	}

	public void setNmRgResponsavel(String nmRgResponsavel) {
		this.nmRgResponsavel = nmRgResponsavel;
	}

	public String getTelResponsavel() {
		return telResponsavel;
	}

	public void setTelResponsavel(String telResponsavel) {
		this.telResponsavel = telResponsavel;
	}

	public Parentesco getParentescoResponsavel() {
		return parentescoResponsavel;
	}

	public void setParentescoResponsavel(Parentesco parentescoResponsavel) {
		this.parentescoResponsavel = parentescoResponsavel;
	}

	public String getObsResponsavel() {
		return obsResponsavel;
	}

	public void setObsResponsavel(String obsResponsavel) {
		this.obsResponsavel = obsResponsavel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Morador other = (Morador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
