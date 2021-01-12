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

import com.ufrb.lardosidosos.domain.model.Despesa;
import com.ufrb.lardosidosos.domain.service.IDespesaService;

@RestController
@RequestMapping("/despesa")
@PreAuthorize("hasRole('DIRETOR')")
public class DespesaController {

	@Autowired
	private IDespesaService despesaService;
	
	@GetMapping
	public List<Despesa> listar() {
		return this.despesaService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Despesa cadastrar(@Valid @RequestBody Despesa despesa) {
		return this.despesaService.cadastrar(despesa);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Despesa> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(this.despesaService.buscar(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Despesa> editar(@PathVariable Long id, @Valid @RequestBody Despesa despesa) {
		return ResponseEntity.ok(this.despesaService.editar(id, despesa));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.despesaService.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
