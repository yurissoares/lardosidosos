package com.ufrb.lardosidosos.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

@RestController
@RequestMapping("/morador")
public class MoradorController {

	@Autowired
	private MoradorRepository repository;

	@GetMapping
	public List<Morador> listarMoradores() {
		return repository.findAll();
	}
	
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Morador novoMorador(@Valid @RequestBody Morador novoMorador) {
		return repository.save(novoMorador);
	}
	
	@GetMapping("/{nome}")
	public ResponseEntity<List<Morador>> buscaPorNome(@PathVariable String nome) {
		
		List<Morador> moradores = repository.findByNomeContainingOrderByNomeAsc(nome);
		
		if(moradores.isEmpty())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(moradores);
	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Morador> atualizaMorador(
			@PathVariable Long id,
			@Valid @RequestBody Morador morador) {
		
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
			
		morador.setId(id);

		morador = repository.save(morador);
		return ResponseEntity.ok(morador);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirMorador(@PathVariable Long id) {
		
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
