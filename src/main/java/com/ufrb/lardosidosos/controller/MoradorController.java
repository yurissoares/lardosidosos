package com.ufrb.lardosidosos.controller;

import java.util.List;

import javax.validation.Valid;

import com.ufrb.lardosidosos.dto.MoradorDTO;
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
import org.springframework.web.bind.annotation.RestController;

import com.ufrb.lardosidosos.entity.Morador;
import com.ufrb.lardosidosos.service.IMoradorService;

@RestController
@RequestMapping("/morador")
@PreAuthorize("hasRole('ROLE_DIRETOR') or hasRole('ROLE_ASSISTENTE_SOCIAL')")
public class MoradorController {
	
	@Autowired
	private IMoradorService moradorService;

	@GetMapping
	public ResponseEntity<List<MoradorDTO>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(this.moradorService.listar());
	}

	@PostMapping
	public ResponseEntity<Boolean> cadastrar(@Valid @RequestBody final MoradorDTO moradorDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.moradorService.cadastrar(moradorDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Boolean> editar(@PathVariable final Long id, @Valid @RequestBody final MoradorDTO moradorDto) {
		return ResponseEntity.status(HttpStatus.OK).body(this.moradorService.editar(id, moradorDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluir(@PathVariable final Long id) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.moradorService.excluir(id));
	}
	
	@GetMapping("/{nome}")
	public ResponseEntity<List<MoradorDTO>> buscarPorNome(@PathVariable final String nome) {
		return ResponseEntity.status(HttpStatus.OK).body(this.moradorService.buscarPorNome(nome));
	}
}
