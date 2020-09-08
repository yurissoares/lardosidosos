package com.ufrb.lardosidosos.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ufrb.lardosidosos.domain.model.AntecedentePessoal;
import com.ufrb.lardosidosos.domain.service.IAntecedentePessoalService;

@RestController
@RequestMapping("/antecedentepessoal")
public class AntecedentePessoalController {

	@Autowired
	private IAntecedentePessoalService antecedentePessoalService;
	
	@GetMapping
	public List<AntecedentePessoal> listar() {
		return this.antecedentePessoalService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public AntecedentePessoal cadastrar(@Valid @RequestBody AntecedentePessoal antecedentePessoal) {
		return this.antecedentePessoalService.cadastrar(antecedentePessoal);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AntecedentePessoal> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(this.antecedentePessoalService.buscar(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AntecedentePessoal> editar(@PathVariable Long id, @Valid @RequestBody AntecedentePessoal antecedentePessoal) {
		return ResponseEntity.ok(this.antecedentePessoalService.editar(id, antecedentePessoal));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.antecedentePessoalService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
