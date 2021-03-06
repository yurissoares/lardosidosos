package com.ufrb.lardosidosos.service;

import java.util.List;

import com.ufrb.lardosidosos.entity.FichaEvDiaria;

public interface IFichaEvDiariaService {

	public List<FichaEvDiaria> listar();

	public FichaEvDiaria cadastrar(final FichaEvDiaria fichaEvDiaria);

	public FichaEvDiaria buscar(final Long id);

	public FichaEvDiaria editar(final Long id, final FichaEvDiaria fichaEvDiaria);

	public void excluir(final Long id);

	public FichaEvDiaria cadastrarComFichaAdmissao(final FichaEvDiaria fichaEvDiaria);
	
	public List<FichaEvDiaria> listarPorMorador(final Long moradorId);
	
	public List<FichaEvDiaria> listarPorMoradorEntreDatas(final Long moradorId, final String dtInicio, final String dtFinal);

	public FichaEvDiaria buscarFichaAdmPorMorador(final Long moradorId);

}
