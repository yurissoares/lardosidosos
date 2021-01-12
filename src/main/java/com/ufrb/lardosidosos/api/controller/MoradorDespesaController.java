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

import com.ufrb.lardosidosos.domain.model.MoradorDespesa;
import com.ufrb.lardosidosos.domain.service.IMoradorDespesaService;

@RestController
@RequestMapping("/moradordespesa")
@PreAuthorize("hasRole('DIRETOR')")
public class MoradorDespesaController {

	@Autowired
	private IMoradorDespesaService moradorDespesaService;
	
	@GetMapping
	public List<MoradorDespesa> listar() {
		return this.moradorDespesaService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public MoradorDespesa cadastrar(@Valid @RequestBody MoradorDespesa moradorDespesa) {
		return this.moradorDespesaService.cadastrar(moradorDespesa);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MoradorDespesa> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(this.moradorDespesaService.buscar(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<MoradorDespesa> editar(@PathVariable Long id, @Valid @RequestBody MoradorDespesa moradorDespesa) {
		return ResponseEntity.ok(this.moradorDespesaService.editar(id, moradorDespesa));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.moradorDespesaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
