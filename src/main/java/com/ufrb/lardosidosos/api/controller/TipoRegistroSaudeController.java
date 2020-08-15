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

import com.ufrb.lardosidosos.domain.model.TipoRegistroSaude;
import com.ufrb.lardosidosos.domain.repository.TipoRegistroSaudeRepository;

@RestController
@RequestMapping("/tiporegistrosaude")
public class TipoRegistroSaudeController 
{

	@Autowired
	private TipoRegistroSaudeRepository repository;
	
	@GetMapping
	public List<TipoRegistroSaude> listar()
	{
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TipoRegistroSaude> buscarPorId(
			@PathVariable Long id)
	{
		Optional<TipoRegistroSaude> tipoRegistroSaude = repository.findById(id);
		
		if(tipoRegistroSaude.isPresent())
			return ResponseEntity.ok(tipoRegistroSaude.get());
		
		return ResponseEntity.notFound().build();

	}
	
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public TipoRegistroSaude salvar(@Valid @RequestBody TipoRegistroSaude tipoRegistroSaude)
	{
		return repository.save(tipoRegistroSaude);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<TipoRegistroSaude> editar(
			@PathVariable Long id,
			@Valid @RequestBody TipoRegistroSaude tipoRegistroSaude)
	{
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		tipoRegistroSaude.setId(id);
		tipoRegistroSaude = repository.save(tipoRegistroSaude);
		return ResponseEntity.ok(tipoRegistroSaude);
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



























