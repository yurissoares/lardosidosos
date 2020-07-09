package com.ufrb.lardosidosos.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

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

	private LocalDateTime dataEntrada;

	private LocalDateTime dataNascimento;

	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;

	@Max(value = 99)
	private int qtdFilhos;

	private String naturalidade;
	private String endereco;
	private String cidade;

	@Size(min = 8, max = 8)
	private String cep;

	@Enumerated(EnumType.STRING)
	private Estados estado;

	// novos
	@CPF
	@Size(min = 11, max = 11)
	private String nmCpf;

	@Size(min = 10, max = 10)
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

	@NotBlank
	private String endResponsavel;

	@NotBlank
	private String cidadeResponsavel;

	@NotBlank
	@Size(min = 8, max = 8)
	private String cepResponsavel;

	@Enumerated(EnumType.STRING)
	private Estados estadoResponsavel;

	@CPF
	@Size(min = 11, max = 11)
	private String nmCpfResponsavel;

	@Size(min = 10, max = 10)
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

	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDateTime getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDateTime dataNascimento) {
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Estados getEstado() {
		return estado;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
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

	public String getEndResponsavel() {
		return endResponsavel;
	}

	public void setEndResponsavel(String endResponsavel) {
		this.endResponsavel = endResponsavel;
	}

	public String getCidadeResponsavel() {
		return cidadeResponsavel;
	}

	public void setCidadeResponsavel(String cidadeResponsavel) {
		this.cidadeResponsavel = cidadeResponsavel;
	}

	public String getCepResponsavel() {
		return cepResponsavel;
	}

	public void setCepResponsavel(String cepResponsavel) {
		this.cepResponsavel = cepResponsavel;
	}

	public Estados getEstadoResponsavel() {
		return estadoResponsavel;
	}

	public void setEstadoResponsavel(Estados estadoResponsavel) {
		this.estadoResponsavel = estadoResponsavel;
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
