package com.ufrb.lardosidosos.service;

import java.util.List;

import com.ufrb.lardosidosos.entity.MoradorAntecedente;

public interface IMoradorAntecedenteService {

	public List<MoradorAntecedente> listar();

	public MoradorAntecedente cadastrar(final MoradorAntecedente moradorAntecedente);

	public MoradorAntecedente buscar(final Long id);

	public MoradorAntecedente editar(final Long id, final MoradorAntecedente moradorAntecedente);

	public void excluir(final Long id);
}
