package com.ufrb.lardosidosos.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.EstadoCivil;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;

@Service
public class CadastroMoradorService {

	@Autowired
	private MoradorRepository moradorRepository;
	
	public Morador salvar(Morador morador) {
		Morador moradorExistente = moradorRepository.findByNome(morador.getNome());
		if(moradorExistente != null && !moradorExistente.equals(morador)) {
			throw new NegocioException("Já existe um morador com este nome.");
		}
				
		morador.setEstadoCivil(EstadoCivil.OUTRO);
		morador.setDataEntrada(LocalDateTime.now());
		morador.setDataNascimento(LocalDateTime.now());
		return moradorRepository.save(morador);
	}
	
	public void excluir(Long id) {
		moradorRepository.deleteById(id);
	}
}
