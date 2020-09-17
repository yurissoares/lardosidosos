package com.ufrb.lardosidosos.domain.service;

import java.util.List;

import com.ufrb.lardosidosos.domain.model.Despesa;

public interface IDespesaService {

	public List<Despesa> listar();

	public Despesa cadastrar(final Despesa despesa);

	public Despesa buscar(final Long id);

	public Despesa editar(final Long id, final Despesa despesa);

	public void excluir(final Long id);

}
