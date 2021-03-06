package com.ufrb.lardosidosos.service;

import java.util.List;

import com.ufrb.lardosidosos.entity.MoradorDoenca;

public interface IMoradorDoencaService {

	public List<MoradorDoenca> listar();

	public MoradorDoenca cadastrar(final MoradorDoenca moradorDoenca);

	public MoradorDoenca buscar(final Long id);

	public MoradorDoenca editar(final Long id, final MoradorDoenca moradorDoenca);

	public void excluir(final Long id);
}
