package com.ufrb.lardosidosos.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ufrb.lardosidosos.domain.model.Evento;
import com.ufrb.lardosidosos.domain.model.TipoEvento;
import com.ufrb.lardosidosos.domain.repository.EventoRepository;
import com.ufrb.lardosidosos.domain.service.GestaoEventoService;

@RestController
@RequestMapping("/evento")
public class EventoController {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private GestaoEventoService eventoService;
	
	@GetMapping
	public List<Evento> listar() {
		return eventoRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Evento criar(@RequestBody Evento evento) {
		return eventoService.salvar(evento);
	}
	
	@PutMapping("/{eventoId}")
	public ResponseEntity<Evento> atualizar(@PathVariable Long eventoId, @RequestBody Evento evento){
		if(!eventoRepository.existsById(eventoId)) {
			return ResponseEntity.notFound().build();
		}
		
		evento.setId(eventoId);
		evento.setData(LocalDateTime.now());
		evento.setTipo(TipoEvento.ENTRADA);
		evento = eventoRepository.save(evento);
		
		return ResponseEntity.ok(evento);
	}
	
	@DeleteMapping("/{eventoId}")
	public ResponseEntity<Void> remover(@PathVariable Long eventoId){
		if(!eventoRepository.existsById(eventoId)) {
			return ResponseEntity.notFound().build();
		}
		
		eventoService.excluir(eventoId);
		return ResponseEntity.noContent().build();
	}
}















