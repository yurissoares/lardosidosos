package com.ufrb.lardosidosos.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotFoundErrorMsg {
	
	NOT_FOUND_USUARIO("Usuário não encontrado."),
	NOT_FOUND_REG_SAUDE("Registro de saúde não encontrado."),
	NOT_FOUND_ANT_PESSOAL("Antecedente Pessoal não encontrado."),
	NOT_FOUND_DOENCA("Doenca não encontrada."),
	NOT_FOUND_FICHA_ADMISSAO("Ficha de admissão não encontrada."),
	NOT_FOUND_TP_LEMBRETE("Lembrete não encontrada."),
	NOT_FOUND_TP_OCORRENCIA("Tipo de ocorrência não encontrada."),
	NOT_FOUND_TP_REG_SAUDE("Tipo de registro de saúde não encontrado."),
	NOT_FOUND_OCORRENCIA("Ocorrência não encontrado."),
	NOT_FOUND_MORADOR("Morador não encontrado."),
	NOT_FOUND_DESPESA("Despesa não encontrada.");
	
	private final String valor;
}
