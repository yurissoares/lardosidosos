package com.ufrb.lardosidosos.api.controller;

import java.util.List;
import java.util.Optional;

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

import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;
import com.ufrb.lardosidosos.domain.service.CadastroMoradorService;

@RestController
@RequestMapping("/morador")
public class MoradorController {
	
	@Autowired
	private MoradorRepository moradorRepository;
	
	@Autowired
	private CadastroMoradorService moradorService;

	@GetMapping
	public List<Morador> listar() {
		return moradorRepository.findAll();
	}
	
	@GetMapping("/{moradorId}")
	public ResponseEntity<Morador> buscar(@PathVariable Long moradorId) {
		Optional<Morador> morador = moradorRepository.findById(moradorId);
		if(morador.isPresent()) {
			return ResponseEntity.ok(morador.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Morador adicionar(@Valid @RequestBody Morador morador) {
		return moradorService.salvar(morador);
	}
	
	@PutMapping("/{moradorId}")
	public ResponseEntity<Morador> atualizar(@PathVariable Long moradorId, @Valid @RequestBody Morador morador) {
		if(!moradorRepository.existsById(moradorId)) {
			return ResponseEntity.notFound().build();
		} 
		morador.setId(moradorId);
		morador = moradorService.salvar(morador);
		return ResponseEntity.ok(morador);
	}
	
	@DeleteMapping("/{moradorId}")
	public ResponseEntity<Void> remover(@PathVariable Long moradorId){
		if(!moradorRepository.existsById(moradorId)) {
			return ResponseEntity.notFound().build();
		} 
		moradorService.excluir(moradorId);
		return ResponseEntity.noContent().build();
	}
	
}
