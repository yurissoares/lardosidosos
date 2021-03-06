package com.ufrb.lardosidosos.service;

import java.util.List;

import com.ufrb.lardosidosos.entity.TipoLembrete;

public interface ITipoLembreteService {

	public List<TipoLembrete> listar();

	public TipoLembrete cadastrar(final TipoLembrete tipoLembrete);

	public TipoLembrete buscar(final Long id);

	public TipoLembrete editar(final Long id, final TipoLembrete tipoLembrete);

	public void excluir(final Long id);

	public void verificaSeTpLembreteExiste(final Long id);

}
