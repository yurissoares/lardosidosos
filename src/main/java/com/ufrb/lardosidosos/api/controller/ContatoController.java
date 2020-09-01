package com.ufrb.lardosidosos.api.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
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

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.Contato;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.repository.ContatoRepository;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;

@RestController
@RequestMapping("/contato")
public class ContatoController {

	@Autowired
	private ContatoRepository repository;
	
	@Autowired
	private MoradorRepository moradorRepository;
	

	@GetMapping
	public List<Contato> listar() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Contato> buscar(@PathVariable Long id) {
		
		Optional<Contato> contato = repository.findById(id);
		if (contato.isPresent())
			return ResponseEntity.ok(contato.get());
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/morador/{moradorId}")
	public ResponseEntity<List<Contato>> listarContatosDoMorador(@PathVariable Long moradorId) 
	{
		moradorRepository.findById(moradorId).orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		return ResponseEntity.ok(repository.findByMoradorId(moradorId));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	@PostMapping
	public Contato salvar(@Valid @RequestBody Contato contato) {
		
		Morador morador = moradorRepository.findById(contato.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));

		contato.setMorador(morador);
		return repository.save(contato);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Contato> editar(
			@PathVariable Long id,
			@Valid @RequestBody Contato contato) {
		
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();

		contato.setId(id);
		contato = repository.save(contato);
		return ResponseEntity.ok(contato);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
