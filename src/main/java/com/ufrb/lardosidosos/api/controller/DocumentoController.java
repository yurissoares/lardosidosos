package com.ufrb.lardosidosos.api.controller;

import java.util.List;

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

import com.ufrb.lardosidosos.domain.model.Documento;
import com.ufrb.lardosidosos.domain.repository.DocumentoRepository;
import com.ufrb.lardosidosos.domain.service.GestaoDocumentoService;

@RestController
@RequestMapping("/documento")
public class DocumentoController {
	
	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Autowired
	private GestaoDocumentoService documentoService;
	
	@GetMapping
	public List<Documento> listar() {
		return documentoRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Documento criar(@RequestBody Documento documento) {
		return documentoService.salvar(documento);
	}
	
	@PutMapping("/{documentoId}")
	public ResponseEntity<Documento> atualizar(@PathVariable Long documentoId, @RequestBody Documento documento){
		if(!documentoRepository.existsById(documentoId)) {
			return ResponseEntity.notFound().build();
		}
		documento.setId(documentoId);
		documento = documentoRepository.save(documento);
		
		return ResponseEntity.ok(documento);
	}
	
	@DeleteMapping("/{documentoId}")
	public ResponseEntity<Void> remover(@PathVariable Long documentoId){
		if(!documentoRepository.existsById(documentoId)) {
			return ResponseEntity.notFound().build();
		}
		
		documentoService.excluir(documentoId);
		return ResponseEntity.noContent().build();
	}
}







































