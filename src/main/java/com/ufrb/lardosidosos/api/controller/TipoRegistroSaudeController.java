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

import com.ufrb.lardosidosos.domain.model.TipoRegistroSaude;
import com.ufrb.lardosidosos.domain.service.ITipoRegistroSaudeService;

@RestController
@RequestMapping("/tiporegistrosaude")
@PreAuthorize("hasRole('ROLE_DIRETOR') or hasRole('ROLE_ENFERMEIRO')")
public class TipoRegistroSaudeController {
	
	@Autowired
	private ITipoRegistroSaudeService tipoRegistroSaudeService;
	
	@GetMapping
	public List<TipoRegistroSaude> listar() {
		return this.tipoRegistroSaudeService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public TipoRegistroSaude cadastrar(@Valid @RequestBody TipoRegistroSaude tipoRegistroSaude) {
		return this.tipoRegistroSaudeService.cadastrar(tipoRegistroSaude);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TipoRegistroSaude> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(this.tipoRegistroSaudeService.buscar(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TipoRegistroSaude> editar(@PathVariable Long id, @Valid @RequestBody TipoRegistroSaude tipoRegistroSaude) {
		return ResponseEntity.ok(this.tipoRegistroSaudeService.editar(id, tipoRegistroSaude));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.tipoRegistroSaudeService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}



























