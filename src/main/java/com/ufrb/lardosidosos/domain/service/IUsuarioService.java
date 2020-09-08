package com.ufrb.lardosidosos.domain.service;

import java.util.List;

import com.ufrb.lardosidosos.domain.model.Usuario;

public interface IUsuarioService {

	public List<Usuario> listar();

	public Usuario cadastrar(final Usuario usuario);

	public Usuario buscar(final Long id);

	public Usuario editar(final Long id, final Usuario usuario);

	public void excluir(final Long id);

	public List<Usuario> buscarPorNome(final String nome);

	public void verificaSeUsuarioExiste(final Long id);

}
