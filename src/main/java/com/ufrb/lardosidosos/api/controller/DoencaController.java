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

import com.ufrb.lardosidosos.domain.model.Doenca;
import com.ufrb.lardosidosos.domain.service.IDoencaService;

@RestController
@RequestMapping("/doenca")
@PreAuthorize("hasRole('DIRETOR, ENFERMEIRO')")
public class DoencaController {

	@Autowired
	private IDoencaService doencaService;
	
	@GetMapping
	public List<Doenca> listar() {
		return this.doencaService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Doenca cadastrar(@Valid @RequestBody Doenca doenca) {
		return this.doencaService.cadastrar(doenca);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Doenca> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(this.doencaService.buscar(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Doenca> editar(@PathVariable Long id, @Valid @RequestBody Doenca doenca) {
		return ResponseEntity.ok(this.doencaService.editar(id, doenca));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.doencaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
