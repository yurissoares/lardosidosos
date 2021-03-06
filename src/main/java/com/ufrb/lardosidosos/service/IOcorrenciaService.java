package com.ufrb.lardosidosos.service;

import java.util.List;

import com.ufrb.lardosidosos.entity.Ocorrencia;

public interface IOcorrenciaService {

	public List<Ocorrencia> listar();

	public Ocorrencia cadastrar(final Ocorrencia ocorrencia);

	public Ocorrencia buscar(final Long id);

	public Ocorrencia editar(final Long id, final Ocorrencia ocorrencia);

	public void excluir(final Long id);

	public List<Ocorrencia> listarOcorrenciaMorador(final Long moradorId);

	public void verificaSeOcorrenciaExiste(final Long id);

}
