package com.ufrb.lardosidosos.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.Evento;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.model.TipoEvento;
import com.ufrb.lardosidosos.domain.repository.EventoRepository;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;

@RestController
@RequestMapping("/evento")
public class EventoController 
{
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private MoradorRepository moradorRepository;
	
	@GetMapping
	public List<Evento> listar() 
	{
		return eventoRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public Evento criar(@Valid @RequestBody Evento evento) 
	{
		Morador morador = moradorRepository.findById(evento.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		evento.setMorador(morador);
		evento.setData(LocalDateTime.now());
		evento.setTipo(TipoEvento.ENTRADA);
		return eventoRepository.save(evento);
	}
	
	@PutMapping("/{eventoId}")
	@Transactional
	public ResponseEntity<Evento> atualizar(@PathVariable Long eventoId, @Valid @RequestBody Evento evento)
	{
		moradorRepository.findById(evento.getMorador().getId())
			.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		evento.setId(eventoId);
		evento.setData(LocalDateTime.now());
		evento.setTipo(TipoEvento.ENTRADA);
		
		evento = eventoRepository.save(evento);
		return ResponseEntity.ok(evento);
	}
	
	@DeleteMapping("/{eventoId}")
	public ResponseEntity<Void> remover(@PathVariable Long eventoId)
	{
		if(!eventoRepository.existsById(eventoId)) 
		{
			return ResponseEntity.notFound().build();
		}
		
		eventoRepository.deleteById(eventoId);
		return ResponseEntity.noContent().build();
	}
}















