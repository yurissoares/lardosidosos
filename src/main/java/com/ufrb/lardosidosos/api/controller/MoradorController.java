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

import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.service.IMoradorService;

@RestController
@RequestMapping("/morador")
public class MoradorController {
	
	@Autowired
	private IMoradorService moradorService;

	@GetMapping
//	@PreAuthorize("hasRole('TECNICO')")
	public List<Morador> listar() {
		return this.moradorService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	@PreAuthorize("hasRole('DIRETOR')")
	public Morador cadastrar(@Valid @RequestBody Morador morador) {
		return this.moradorService.cadastrar(morador);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Morador> editar(@PathVariable Long id, @Valid @RequestBody Morador morador) {
		return ResponseEntity.ok(this.moradorService.editar(id, morador));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('DIRETOR')")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.moradorService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{nome}")
	public ResponseEntity<List<Morador>> buscarPorNome(@PathVariable String nome) {
		return ResponseEntity.ok(this.moradorService.buscarPorNome(nome));
	}
}
