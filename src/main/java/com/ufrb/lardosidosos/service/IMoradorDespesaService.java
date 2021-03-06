package com.ufrb.lardosidosos.service;

import java.util.List;

import com.ufrb.lardosidosos.entity.MoradorDespesa;

public interface IMoradorDespesaService {

	public List<MoradorDespesa> listar();

	public MoradorDespesa cadastrar(final MoradorDespesa moradorDespesa);

	public MoradorDespesa buscar(final Long id);

	public MoradorDespesa editar(final Long id, final MoradorDespesa moradorDespesa);

	public void excluir(final Long id);
}
