package com.ufrb.lardosidosos.service;

import java.util.List;

import com.ufrb.lardosidosos.entity.RegistroSaude;

public interface IRegistroSaudeService {

	public List<RegistroSaude> listar();

	public RegistroSaude cadastrar(final RegistroSaude registroSaude);

	public RegistroSaude buscar(final Long id);

	public RegistroSaude editar(final Long id, final RegistroSaude registroSaude);

	public void excluir(final Long id);

	public List<RegistroSaude> listarRegistroSaudePorMorador(final Long moradorId);

	public void verificaSeRegSaudeExiste(final Long id);

}
