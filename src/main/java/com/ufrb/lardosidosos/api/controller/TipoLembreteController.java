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

import com.ufrb.lardosidosos.domain.model.TipoLembrete;
import com.ufrb.lardosidosos.domain.repository.TipoLembreteRepository;

@RestController
@RequestMapping("/tipolembrete")
public class TipoLembreteController {

	@Autowired
	public TipoLembreteRepository repository;
	
	@GetMapping
	public List<TipoLembrete> listar()
	{
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TipoLembrete> buscarPorId(
			@PathVariable Long id)
	{
		Optional<TipoLembrete> tipoLembrete = repository.findById(id);
		
		if(tipoLembrete.isPresent())
			return ResponseEntity.ok(tipoLembrete.get());
		
		return ResponseEntity.notFound().build();

	}
	
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public TipoLembrete salvar(@Valid @RequestBody TipoLembrete novoTipoLembrete)
	{
		return repository.save(novoTipoLembrete);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<TipoLembrete> editar(
			@PathVariable Long id,
			@Valid @RequestBody TipoLembrete tipoLembrete)
	{
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		tipoLembrete.setId(id);
		tipoLembrete = repository.save(tipoLembrete);
		return ResponseEntity.ok(tipoLembrete);
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(
			@PathVariable Long id)
	{
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
