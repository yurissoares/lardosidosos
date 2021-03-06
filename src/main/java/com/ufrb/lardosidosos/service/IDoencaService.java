package com.ufrb.lardosidosos.service;

import java.util.List;

import com.ufrb.lardosidosos.entity.Doenca;

public interface IDoencaService {

	public List<Doenca> listar();

	public Doenca cadastrar(final Doenca doenca);

	public Doenca buscar(final Long id);

	public Doenca editar(final Long id, final  Doenca doenca);

	public void excluir(final Long id);

	public void verificaSeDoencaExiste(final Long id);

}
