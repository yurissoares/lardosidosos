package com.ufrb.lardosidosos.service;

import java.util.List;

import com.ufrb.lardosidosos.entity.DocumentoMorador;

public interface IDocumentoMoradorService {

	public List<DocumentoMorador> listar();

	public DocumentoMorador cadastrar(final DocumentoMorador docMorador);

	public DocumentoMorador buscar(final Long id);

	public DocumentoMorador editar(final Long id, final DocumentoMorador docMorador);

	public void excluir(final Long id);

	public List<DocumentoMorador> listarDocMorador(final Long moradorId);

}
