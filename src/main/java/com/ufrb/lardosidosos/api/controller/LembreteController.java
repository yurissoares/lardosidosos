package com.ufrb.lardosidosos.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ufrb.lardosidosos.domain.model.Lembrete;
import com.ufrb.lardosidosos.domain.service.ILembreteService;

@RestController
@RequestMapping("/lembrete")
@PreAuthorize("hasRole('DIRETOR')")
public class LembreteController {
	
	@Autowired
	private ILembreteService lembreteService;
	
	@GetMapping
	public List<Lembrete> listar() {
		return this.lembreteService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Lembrete cadastrar(@Valid @RequestBody Lembrete lembrete){
		return this.lembreteService.cadastrar(lembrete);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Lembrete> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(this.lembreteService.buscar(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Lembrete> editar(@PathVariable Long id, @Valid @RequestBody Lembrete lembrete) {
		return ResponseEntity.ok(this.lembreteService.editar(id, lembrete));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.lembreteService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/usuarioDestino/{usuarioId}")
	public ResponseEntity<List<Lembrete>> listarLembretesDoUsuarioDetinoPorUsuarioId(@PathVariable Long usuarioId) {
		return ResponseEntity.ok(this.lembreteService.listarLembretesUsuarioDestinoPorUsuarioId(usuarioId));
	}

	@GetMapping("/usuarioOrigem/{usuarioId}")
	public ResponseEntity<List<Lembrete>> listarLembretesDoUsuarioOrigemPorUsuarioId(@PathVariable Long usuarioId) {
		return ResponseEntity.ok(this.lembreteService.listarLembretesUsuarioOrigemPorUsuarioId(usuarioId));
	}
	
	@GetMapping("/morador/{moradorId}")
	public ResponseEntity<List<Lembrete>> listarLembretesDoMoradorPorMoradorId(@PathVariable Long moradorId) {
		return ResponseEntity.ok(this.lembreteService.listarLembreteMoradorPorMoradorId(moradorId));
	}
	
}
