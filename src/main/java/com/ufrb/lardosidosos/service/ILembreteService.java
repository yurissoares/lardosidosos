package com.ufrb.lardosidosos.service;

import java.util.List;

import com.ufrb.lardosidosos.entity.Lembrete;

public interface ILembreteService {

	public List<Lembrete> listar();

	public Lembrete cadastrar(final Lembrete lembrete);

	public Lembrete buscar(final Long id);

	public Lembrete editar(final Long id, final Lembrete lembrete);

	public void excluir(final Long id);

	public List<Lembrete> listarLembretesUsuarioDestinoPorUsuarioId(final Long usuarioId);

	public List<Lembrete> listarLembretesUsuarioOrigemPorUsuarioId(final Long usuarioId);

	public List<Lembrete> listarLembreteMoradorPorMoradorId(final Long moradorId);

}
