package com.ufrb.lardosidosos.domain.service;

import java.util.List;

import com.ufrb.lardosidosos.domain.model.AntecedentePessoal;

public interface IAntecedentePessoalService {

	public List<AntecedentePessoal> listar();

	public AntecedentePessoal cadastrar(final AntecedentePessoal antecedentePessoal);

	public AntecedentePessoal buscar(final Long id);

	public AntecedentePessoal editar(final Long id, final  AntecedentePessoal antecedentePessoal);

	public void excluir(final Long id);

	public void verificaSeAntPessoalExiste(final Long id);

}
