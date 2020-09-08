package com.ufrb.lardosidosos.domain.service;

import java.util.List;

import com.ufrb.lardosidosos.domain.model.FichaEvDiaria;

public interface IFichaEvDiariaService {

	public List<FichaEvDiaria> listar();

	public FichaEvDiaria cadastrar(final FichaEvDiaria fichaEvDiaria);

	public FichaEvDiaria buscar(final Long id);

	public FichaEvDiaria editar(final Long id, final FichaEvDiaria fichaEvDiaria);

	public void excluir(final Long id);

	public FichaEvDiaria cadastrarComFichaAdmissao(final FichaEvDiaria fichaEvDiaria);

}
