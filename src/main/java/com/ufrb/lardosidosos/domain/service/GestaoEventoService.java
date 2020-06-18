package com.ufrb.lardosidosos.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.Evento;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.model.TipoEvento;
import com.ufrb.lardosidosos.domain.repository.EventoRepository;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;

@Service
public class GestaoEventoService {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private MoradorRepository moradorRepository;
	
	public Evento salvar(Evento evento) {
		Morador morador = moradorRepository.findById(evento.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		evento.setMorador(morador);
		evento.setData(LocalDateTime.now());
		evento.setTipo(TipoEvento.ENTRADA);
		return eventoRepository.save(evento);
	}

	public void excluir(Long id) {
		eventoRepository.deleteById(id);
	}
	
}
