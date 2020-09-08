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

import com.ufrb.lardosidosos.domain.model.DocumentoMorador;
import com.ufrb.lardosidosos.domain.service.IDocumentoMoradorService;

@RestController
@RequestMapping("/docmorador")
public class DocumentoMoradorController {

	@Autowired
	private IDocumentoMoradorService docMoradorService;
	
	@GetMapping
	public List<DocumentoMorador> listar() {
		return this.docMoradorService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public DocumentoMorador cadastrar(@Valid @RequestBody DocumentoMorador docMorador) {
		return this.docMoradorService.cadastrar(docMorador);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DocumentoMorador> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(this.docMoradorService.buscar(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DocumentoMorador> editar(@PathVariable Long id, @Valid @RequestBody DocumentoMorador docMorador) {
		return ResponseEntity.ok(this.docMoradorService.editar(id, docMorador));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.docMoradorService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/morador/{moradorId}")
	public ResponseEntity<List<DocumentoMorador>> listarDocMorador(@PathVariable Long moradorId) {
		return ResponseEntity.ok(this.docMoradorService.listarDocMorador(moradorId));
	}

}
