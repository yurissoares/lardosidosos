package com.ufrb.lardosidosos.service;

import java.util.List;

import com.ufrb.lardosidosos.dto.MoradorDTO;
import com.ufrb.lardosidosos.entity.Morador;

public interface IMoradorService {
	
	public List<MoradorDTO> listar();
	
	public Boolean cadastrar(final MoradorDTO moradorDto);
	
	public MoradorDTO buscar(final Long id);
	
	public Boolean editar(final Long id, final MoradorDTO moradorDto);
	
	public Boolean excluir(final Long id);
	
	public List<MoradorDTO> buscarPorNome(final String nome);
	
	public void verificaSeMoradorExiste(final Long id);
}
