package com.ufrb.lardosidosos.controller;

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

import com.ufrb.lardosidosos.entity.Ocorrencia;
import com.ufrb.lardosidosos.service.IOcorrenciaService;

@RestController
@RequestMapping("/ocorrencia")
@PreAuthorize("hasRole('ROLE_DIRETOR') or hasRole('ROLE_ENFERMEIRO')")
public class OcorrenciaController {

	@Autowired
	private IOcorrenciaService ocorrenciaService;
	
	@GetMapping
	public List<Ocorrencia> listar() {
		return this.ocorrenciaService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Ocorrencia salvar(@Valid @RequestBody Ocorrencia ocorrencia) {	
		return this.ocorrenciaService.cadastrar(ocorrencia);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ocorrencia> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(this.ocorrenciaService.buscar(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Ocorrencia> editar(@PathVariable Long id, @Valid @RequestBody Ocorrencia ocorrencia) {
		return ResponseEntity.ok(this.ocorrenciaService.editar(id, ocorrencia));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.ocorrenciaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/morador/{moradorId}")
	public ResponseEntity<List<Ocorrencia>> listarOcorrenciasDoMorador(@PathVariable Long moradorId) {
		return ResponseEntity.ok(this.ocorrenciaService.listarOcorrenciaMorador(moradorId));
	}
	
	

}
