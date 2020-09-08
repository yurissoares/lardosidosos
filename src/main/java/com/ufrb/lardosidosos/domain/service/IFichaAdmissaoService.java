package com.ufrb.lardosidosos.domain.service;

import java.util.List;

import com.ufrb.lardosidosos.domain.model.FichaAdmissao;

public interface IFichaAdmissaoService {

	public List<FichaAdmissao> listar();

	public FichaAdmissao cadastrar(final FichaAdmissao fichaAdmissao);

	public FichaAdmissao buscar(final Long id);

	public FichaAdmissao editar(final Long id, final FichaAdmissao fichaAdmissao);

	public void excluir(final Long id);

	public void verificaSeFichaAdmissaoExiste(final Long id);

}
