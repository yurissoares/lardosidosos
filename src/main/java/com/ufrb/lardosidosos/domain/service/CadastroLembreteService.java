package com.ufrb.lardosidosos.domain.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.model.Lembrete;
import com.ufrb.lardosidosos.domain.model.enums.StatusLembrete;
import com.ufrb.lardosidosos.domain.repository.LembreteRepository;

@Service
public class CadastroLembreteService {

	@Autowired
	private LembreteRepository lembreteRepository;

	
	@Transactional
	public Lembrete salvar(Lembrete lembrete) {
		
		if(lembrete.getDataCriacao() == null) {
			lembrete.setDataCriacao(LocalDate.now());
		}
		
		if(lembrete.getStatusLembrete() == null) {
			lembrete.setStatusLembrete(StatusLembrete.PENDENTE);
		}
		
		return lembreteRepository.save(lembrete);			
	}
	
	@Transactional
	public void excluir(Long lembreteId) {
		lembreteRepository.deleteById(lembreteId);
	}

}