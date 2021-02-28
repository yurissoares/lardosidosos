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

import com.ufrb.lardosidosos.domain.model.DocumentoRegistroSaude;
import com.ufrb.lardosidosos.domain.service.IDocumentoRegistroSaudeService;

@RestController
@RequestMapping("/docregsaude")
@PreAuthorize("hasRole('DIRETOR, ENFERMEIRO')")
public class DocumentoRegistroSaudeController {

	@Autowired
	private IDocumentoRegistroSaudeService docRegistroSaudeService;
	
	@GetMapping
	public List<DocumentoRegistroSaude> listar() {
		return this.docRegistroSaudeService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public DocumentoRegistroSaude cadastrar(@Valid @RequestBody DocumentoRegistroSaude docRegistroSaude) {
		return this.docRegistroSaudeService.cadastrar(docRegistroSaude);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DocumentoRegistroSaude> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(this.docRegistroSaudeService.buscar(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DocumentoRegistroSaude> editar(@PathVariable Long id, @Valid @RequestBody DocumentoRegistroSaude docRegistroSaude) {
		return ResponseEntity.ok(this.docRegistroSaudeService.editar(id, docRegistroSaude));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.docRegistroSaudeService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/regsaude/{regSaudeId}")
	public ResponseEntity<List<DocumentoRegistroSaude>> listarRegSaudeDoDoc(@PathVariable Long regSaudeId) {
		return ResponseEntity.ok(this.docRegistroSaudeService.listarDocDoRegSaude(regSaudeId));
	}
}
