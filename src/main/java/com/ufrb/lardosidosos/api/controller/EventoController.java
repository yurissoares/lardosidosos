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
	private EventoRepository eventoRepository;

	@Autowired
	private MoradorRepository moradorRepository;

	@GetMapping
	@ApiOperation(value = "Lista eventos", notes = "Retorna uma lista com todos os eventos dos moradores.")
	public List<Evento> listaEventos() {
		return eventoRepository.findAll();
	}

	@GetMapping("/{eventoId}")
	@ApiOperation(value = "Busca evento", notes = "Busca por um evento de um morador especificado pelo id do evento.")
	public ResponseEntity<Evento> buscaEvento(
			@ApiParam(name = "eventoId", value = "Id do evento.", required = true, type = "long") @PathVariable Long eventoId) {
		Optional<Evento> evento = eventoRepository.findById(eventoId);
		if (evento.isPresent())
			return ResponseEntity.ok(evento.get());
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	@ApiOperation(value = "Cria evento", notes = "Cria um evento de um morador.")
	public Evento salvaEvento(@Valid @RequestBody Evento evento) {
		Morador morador = moradorRepository.findById(evento.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));

		evento.setMorador(morador);
		return eventoRepository.save(evento);
	}

	@PutMapping("/{eventoId}")
	@Transactional
	@ApiOperation(value = "Edita evento", notes = "Edita evento de um morador especificado pelo id do evento.")
	public ResponseEntity<Evento> atualizaEvento(
			@ApiParam(name = "eventoId", value = "Id do evento.", required = true, type = "long") @PathVariable Long eventoId,
			@Valid @RequestBody Evento evento) {
		moradorRepository.findById(evento.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));

		evento.setId(eventoId);
		evento = eventoRepository.save(evento);
		return ResponseEntity.ok(evento);
	}

	@DeleteMapping("/{eventoId}")
	@ApiOperation(value = "Deleta evento", notes = "Deleta evento especificado pelo id do evento.")
	public ResponseEntity<Void> excluiEvento(
			@ApiParam(name = "eventoId", value = "Id do evento.", required = true, type = "long") @PathVariable Long eventoId) {
		if (!eventoRepository.existsById(eventoId)) {
			return ResponseEntity.notFound().build();
		}

		eventoRepository.deleteById(eventoId);
		return ResponseEntity.noContent().build();
	}
}
