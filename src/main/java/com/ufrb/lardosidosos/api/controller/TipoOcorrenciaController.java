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

import com.ufrb.lardosidosos.domain.model.TipoOcorrencia;
import com.ufrb.lardosidosos.domain.service.ITipoOcorrenciaService;

@RestController
@RequestMapping("/tipoocorrencia")
@PreAuthorize("hasRole('DIRETOR')")
public class TipoOcorrenciaController {
	
	@Autowired
	private ITipoOcorrenciaService tipoOcorrenciaService;
	
	@GetMapping
	public List<TipoOcorrencia> listar() {
		return this.tipoOcorrenciaService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public TipoOcorrencia cadastrar(@Valid @RequestBody TipoOcorrencia tipoOcorrencia) {
		return this.tipoOcorrenciaService.cadastrar(tipoOcorrencia);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TipoOcorrencia> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(this.tipoOcorrenciaService.buscar(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TipoOcorrencia> editar(@PathVariable Long id, @Valid @RequestBody TipoOcorrencia tipoOcorrencia) {
		return ResponseEntity.ok(this.tipoOcorrenciaService.editar(id, tipoOcorrencia));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.tipoOcorrenciaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}



























