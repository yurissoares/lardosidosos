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

import com.ufrb.lardosidosos.domain.model.RegistroSaude;
import com.ufrb.lardosidosos.domain.service.IRegistroSaudeService;

@RestController
@RequestMapping("/registrosaude")
@PreAuthorize("hasRole('DIRETOR')")
public class RegistroSaudeController {

	@Autowired
	private IRegistroSaudeService registroSaudeService;
	
	@GetMapping
	public List<RegistroSaude> listar() {
		return this.registroSaudeService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public RegistroSaude cadastrar(@Valid @RequestBody RegistroSaude registroSaude) {
		return this.registroSaudeService.cadastrar(registroSaude);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RegistroSaude> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(this.registroSaudeService.buscar(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<RegistroSaude> editar(@PathVariable Long id, @Valid @RequestBody RegistroSaude registroSaude) {
		return ResponseEntity.ok(this.registroSaudeService.editar(id, registroSaude));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.registroSaudeService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/morador/{moradorId}")
	public ResponseEntity<List<RegistroSaude>> listarRegistroSaudePorMoradorId(@PathVariable Long moradorId) {
		return ResponseEntity.ok(this.registroSaudeService.listarRegistroSaudePorMorador(moradorId));
	}
	
}
