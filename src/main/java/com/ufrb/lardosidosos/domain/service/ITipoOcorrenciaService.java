package com.ufrb.lardosidosos.domain.service;

import java.util.List;

import com.ufrb.lardosidosos.domain.model.TipoOcorrencia;

public interface ITipoOcorrenciaService {

	public List<TipoOcorrencia> listar();

	public TipoOcorrencia cadastrar(final TipoOcorrencia tipoOcorrencia);

	public TipoOcorrencia buscar(final Long id);

	public TipoOcorrencia editar(final Long id, final TipoOcorrencia tipoOcorrencia);

	public void excluir(final Long id);

	public void verificaSeTpOcorrenciaExiste(final Long id);

}
