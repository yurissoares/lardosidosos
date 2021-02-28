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

import com.ufrb.lardosidosos.domain.model.Contato;
import com.ufrb.lardosidosos.domain.service.IContatoService;

@RestController
@RequestMapping("/contato")
@PreAuthorize("hasRole('DIRETOR, ASSISTENTE_SOCIAL')")
public class ContatoController {

	@Autowired
	private IContatoService contatoService;
	
	@GetMapping
	public List<Contato> listar() {
		return this.contatoService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Contato cadastrar(@Valid @RequestBody Contato contato) {
		return this.contatoService.cadastrar(contato);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Contato> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(this.contatoService.buscar(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Contato> editar(@PathVariable Long id, @Valid @RequestBody Contato contato) {
		return ResponseEntity.ok(this.contatoService.editar(id, contato));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.contatoService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/morador/{moradorId}")
	public ResponseEntity<List<Contato>> listarContatosMorador(@PathVariable Long moradorId) {
		return ResponseEntity.ok(this.contatoService.listarContatosDoMorador(moradorId));
	}
	

	



}
