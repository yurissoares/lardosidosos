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

import com.ufrb.lardosidosos.domain.model.FichaAdmissao;
import com.ufrb.lardosidosos.domain.service.IFichaAdmissaoService;

@RestController
@RequestMapping("/fichaadmissao")
@PreAuthorize("hasRole('DIRETOR, ASSISTENTE_SOCIAL')")
public class FichaAdmissaoController {
	
	@Autowired
	private IFichaAdmissaoService fichaAdmissaoService;
	
	@GetMapping
	public List<FichaAdmissao> listar() {
		return fichaAdmissaoService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public FichaAdmissao cadastrar(@Valid @RequestBody FichaAdmissao fichaAdmissao) {
		return this.fichaAdmissaoService.cadastrar(fichaAdmissao);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FichaAdmissao> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(this.fichaAdmissaoService.buscar(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FichaAdmissao> editar(@PathVariable Long id, @Valid @RequestBody FichaAdmissao fichaAdmissao) {
		return ResponseEntity.ok(this.fichaAdmissaoService.editar(id, fichaAdmissao));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.fichaAdmissaoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/morador/{moradorId}")
	public ResponseEntity<FichaAdmissao> buscarPorMorador(@PathVariable Long moradorId) {
		return ResponseEntity.ok(this.fichaAdmissaoService.buscarPorMorador(moradorId));
	}
}
