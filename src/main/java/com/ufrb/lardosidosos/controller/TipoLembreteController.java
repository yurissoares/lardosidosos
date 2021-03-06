package com.ufrb.lardosidosos.controller;

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

import com.ufrb.lardosidosos.entity.TipoLembrete;
import com.ufrb.lardosidosos.service.ITipoLembreteService;

@RestController
@RequestMapping("/tipolembrete")
@PreAuthorize("hasRole('ROLE_DIRETOR') or hasRole('ROLE_TECNICO')")
public class TipoLembreteController {

	@Autowired
	private ITipoLembreteService tipoLembreteService;
	
	@GetMapping
	public List<TipoLembrete> listar() {
		return this.tipoLembreteService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public TipoLembrete cadastrar(@Valid @RequestBody TipoLembrete tipoLembrete) {
		return this.tipoLembreteService.cadastrar(tipoLembrete);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TipoLembrete> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(this.tipoLembreteService.buscar(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TipoLembrete> editar(@PathVariable Long id, @Valid @RequestBody TipoLembrete tipoLembrete) {
		return ResponseEntity.ok(this.tipoLembreteService.editar(id, tipoLembrete));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.tipoLembreteService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
