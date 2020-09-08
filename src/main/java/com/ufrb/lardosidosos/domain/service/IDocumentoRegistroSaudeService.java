package com.ufrb.lardosidosos.domain.service;

import java.util.List;

import com.ufrb.lardosidosos.domain.model.DocumentoRegistroSaude;

public interface IDocumentoRegistroSaudeService {

	public List<DocumentoRegistroSaude> listar();

	public DocumentoRegistroSaude cadastrar(final DocumentoRegistroSaude docRegistroSaude);

	public DocumentoRegistroSaude buscar(final Long id);

	public DocumentoRegistroSaude editar(final Long id, final DocumentoRegistroSaude docRegistroSaude);

	public void excluir(final Long id);

	public List<DocumentoRegistroSaude> listarDocDoRegSaude(final Long regSaudeId);

}
