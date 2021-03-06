package com.ufrb.lardosidosos.service;

import java.util.List;

import com.ufrb.lardosidosos.entity.Contato;

public interface IContatoService {

	public List<Contato> listar();

	public Contato cadastrar(final Contato contato);

	public Contato buscar(final Long id);

	public Contato editar(final Long id, final Contato contato);

	public void excluir(final Long id);

	public List<Contato> listarContatosDoMorador(final Long moradaorId);

}
