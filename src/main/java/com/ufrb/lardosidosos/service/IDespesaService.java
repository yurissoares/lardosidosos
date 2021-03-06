package com.ufrb.lardosidosos.service;

import java.util.List;

import com.ufrb.lardosidosos.entity.Despesa;

public interface IDespesaService {

	public List<Despesa> listar();

	public Despesa cadastrar(final Despesa despesa);

	public Despesa buscar(final Long id);

	public Despesa editar(final Long id, final Despesa despesa);

	public void excluir(final Long id);

	public void verificaSeDespesaExiste(final Long id);

}
