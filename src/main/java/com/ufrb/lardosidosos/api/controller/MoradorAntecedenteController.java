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

import com.ufrb.lardosidosos.domain.model.MoradorAntecedente;
import com.ufrb.lardosidosos.domain.service.IMoradorAntecedenteService;

@RestController
@RequestMapping("/moradorantecedente")
@PreAuthorize("hasRole('DIRETOR')")
public class MoradorAntecedenteController {

	@Autowired
	private IMoradorAntecedenteService moradorAntecedenteService;
	
	@GetMapping
	public List<MoradorAntecedente> listar() {
		return this.moradorAntecedenteService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public MoradorAntecedente cadastrar(@Valid @RequestBody MoradorAntecedente moradorAntecedente) {
		return this.moradorAntecedenteService.cadastrar(moradorAntecedente);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MoradorAntecedente> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(this.moradorAntecedenteService.buscar(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<MoradorAntecedente> editar(@PathVariable Long id, @Valid @RequestBody MoradorAntecedente moradorAntecedente) {
		return ResponseEntity.ok(this.moradorAntecedenteService.editar(id, moradorAntecedente));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.moradorAntecedenteService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
