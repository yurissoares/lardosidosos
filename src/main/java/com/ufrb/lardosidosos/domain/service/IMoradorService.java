package com.ufrb.lardosidosos.domain.service;

import java.util.List;

import com.ufrb.lardosidosos.domain.model.Morador;

public interface IMoradorService {
	
	public List<Morador> listar();
	
	public Morador cadastrar(final Morador morador);
	
	public Morador buscar(final Long id);
	
	public Morador editar(final Long id, final Morador morador);
	
	public void excluir(final Long id);
	
	public List<Morador> buscarPorNome(final String nome);
	
	public void verificaSeMoradorExiste(final Long id);
}
