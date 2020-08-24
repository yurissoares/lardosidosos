package com.ufrb.lardosidosos.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;

@Service
public class CadastroMoradorService {

	@Autowired
	private MoradorRepository moradorRepository;

	
	@Transactional
	public Morador salvar(Morador morador) {
		return moradorRepository.save(morador);			
	}
	
	@Transactional
	public void excluir(Long moradorId) {
		moradorRepository.deleteById(moradorId);
	}
}
