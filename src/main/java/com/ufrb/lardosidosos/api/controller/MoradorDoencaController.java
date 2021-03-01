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

import com.ufrb.lardosidosos.domain.model.MoradorDoenca;
import com.ufrb.lardosidosos.domain.service.IMoradorDoencaService;

@RestController
@RequestMapping("/moradordoenca")
@PreAuthorize("hasRole('ROLE_DIRETOR') or hasRole('ROLE_ENFERMEIRO')")
public class MoradorDoencaController {

	@Autowired
	private IMoradorDoencaService moradorDoencaService;
	
	@GetMapping
	public List<MoradorDoenca> listar() {
		return this.moradorDoencaService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public MoradorDoenca cadastrar(@Valid @RequestBody MoradorDoenca moradorDoenca) {
		return this.moradorDoencaService.cadastrar(moradorDoenca);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MoradorDoenca> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(this.moradorDoencaService.buscar(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<MoradorDoenca> editar(@PathVariable Long id, @Valid @RequestBody MoradorDoenca moradorDoenca) {
		return ResponseEntity.ok(this.moradorDoencaService.editar(id, moradorDoenca));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.moradorDoencaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
