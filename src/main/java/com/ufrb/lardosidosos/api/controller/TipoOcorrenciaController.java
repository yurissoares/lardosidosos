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

import com.ufrb.lardosidosos.domain.model.TipoOcorrencia;
import com.ufrb.lardosidosos.domain.repository.TipoOcorrenciaRepository;

@RestController
@RequestMapping("/tipoocorrencia")
public class TipoOcorrenciaController 
{

	@Autowired
	private TipoOcorrenciaRepository repository;
	
	@GetMapping
	public List<TipoOcorrencia> listar()
	{
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TipoOcorrencia> buscar(
			@PathVariable Long id)
	{
		Optional<TipoOcorrencia> tipoOcorrencia = repository.findById(id);
		
		if(tipoOcorrencia.isPresent())
			return ResponseEntity.ok(tipoOcorrencia.get());
		
		return ResponseEntity.notFound().build();

	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	@PostMapping
	public TipoOcorrencia salvar(@Valid @RequestBody TipoOcorrencia novoTipoOcorrencia)
	{
		return repository.save(novoTipoOcorrencia);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<TipoOcorrencia> editar(
			@PathVariable Long id,
			@Valid @RequestBody TipoOcorrencia tipoOcorrencia)
	{
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		tipoOcorrencia.setId(id);
		tipoOcorrencia = repository.save(tipoOcorrencia);
		return ResponseEntity.ok(tipoOcorrencia);
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



























