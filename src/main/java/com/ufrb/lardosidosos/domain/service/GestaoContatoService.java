package com.ufrb.lardosidosos.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.Contato;
import com.ufrb.lardosidosos.domain.model.Parentesco;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.repository.ContatoRepository;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;

@Service
public class GestaoContatoService {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private MoradorRepository moradorRepository;
	
	public Contato criar(Contato contato) {
		Morador morador = moradorRepository.findById(contato.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		contato.setMorador(morador);
		contato.setParentesco(Parentesco.OUTRO);
		return contatoRepository.save(contato);
	}
}
