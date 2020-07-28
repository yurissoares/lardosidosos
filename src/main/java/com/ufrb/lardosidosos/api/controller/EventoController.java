package com.ufrb.lardosidosos.api.controller;

import java.util.List;
import java.util.Optional;

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
import com.ufrb.lardosidosos.domain.repository.EventoRepository;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/evento")
public class EventoController {

	@Autowired
	private EventoRepository repository;
	
	@Autowired
	private MoradorRepository moradorRepository;

	@ApiOperation(value = "Lista eventos", notes = "Retorna uma lista com todos os eventos dos moradores.")
	@GetMapping
	public List<Evento> listarEventos() {
		return repository.findAll();
	}
	
	@ApiOperation(value = "Cria um novo evento", notes = "Cria um novo evento de um morador.")
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Evento novoEvento(@Valid @RequestBody Evento novoEvento) {
		Morador morador = moradorRepository.findById(novoEvento.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));

		novoEvento.setMorador(morador);
		return repository.save(novoEvento);
	}

	@ApiOperation(value = "Busca evento", notes = "Busca por um evento de um morador especificado pelo id do evento.")
	@GetMapping("/{id}")
	public ResponseEntity<Evento> buscarEvento(
			@ApiParam(name = "id", value = "Id do evento.", required = true, type = "long") 
			@PathVariable Long id) {
		
		Optional<Evento> evento = repository.findById(id);
		
		if (evento.isPresent())
			return ResponseEntity.ok(evento.get());
		
		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Edita evento", notes = "Edita evento de um morador especificado pelo id do evento.")
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Evento> atualizaEvento(
			@ApiParam(name = "id", value = "Id do evento.", required = true, type = "long") 
			@PathVariable Long id,
			@Valid @RequestBody Evento evento) {
		moradorRepository.findById(evento.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));

		evento.setId(id);
		evento = repository.save(evento);
		return ResponseEntity.ok(evento);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta um evento", notes = "Deleta um evento especificado pelo id do evento.")
	public ResponseEntity<Void> excluiEvento(
			@ApiParam(name = "id", value = "Id do evento.", required = true, type = "long") 
			@PathVariable Long id) {
		
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();

		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
