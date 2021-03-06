package com.ufrb.lardosidosos.service;

import java.util.List;

import com.ufrb.lardosidosos.entity.TipoRegistroSaude;

public interface ITipoRegistroSaudeService {

	public List<TipoRegistroSaude> listar();

	public TipoRegistroSaude cadastrar(final TipoRegistroSaude tipoRegistroSaude);

	public TipoRegistroSaude buscar(final Long id);

	public TipoRegistroSaude editar(final Long id, final TipoRegistroSaude tipoRegistroSaude);

	public void excluir(final Long id);

	public void verificaSeTpRegSaudeExiste(final Long id);

}
