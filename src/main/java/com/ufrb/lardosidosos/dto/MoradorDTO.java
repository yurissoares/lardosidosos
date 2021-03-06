package com.ufrb.lardosidosos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ufrb.lardosidosos.entity.enums.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class MoradorDTO {

    private Long id;
    private String nome;

    private Sexo sexo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEntrada;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private EstadoCivil estadoCivil;
    private int qtdFilhos;
    private String naturalidade;
    private String endLogradouro;
    private String endNumero;
    private String endBairro;
    private String endCidade;
    private Estados endEstado;
    private String endCep;
    private String nmCpf;
    private String nmRg;
    private String nmCtps;
    private String nmBeneficio;
    private boolean ehAposentado;
    private TipoAposentadoria tipoAposentadoria;
    private boolean temEmprestimo;
    private double valorParcelaEmprestimo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataUltimaParcelaEmprestimo;

    private String medicacoes;
    private String motivoIngresso;
    private Situacao situacao;
    private String nomeResponsavel;
    private String endLogradouroResponsavel;
    private String endNumeroResponsavel;
    private String endBairroResponsavel;
    private String endCidadeResponsavel;
    private Estados endEstadoResponsavel;
    private String endCepResponsavel;
    private String nmCpfResponsavel;
    private String nmRgResponsavel;
    private String telResponsavel;
    private Parentesco parentescoResponsavel;
    private String obsResponsavel;
}
