package com.ufrb.lardosidosos.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.model.RegistroSaude;
import com.ufrb.lardosidosos.domain.repository.RegistroSaudeRepository;

@Service
public class CadastroRegistroSaudeService {

	@Autowired
	private RegistroSaudeRepository registroSaudeRepository;

	@Transactional
	public RegistroSaude salvar(RegistroSaude registroSaude) {
		return registroSaudeRepository.save(registroSaude);
	}

	@Transactional
	public void excluir(Long registroSaudeId) {
		registroSaudeRepository.deleteById(registroSaudeId);
	}

}
