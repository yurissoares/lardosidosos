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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/morador")
public class MoradorController {

	@Autowired
	private MoradorRepository repository;

	@ApiOperation(value = "Lista moradores", notes = "Retorna uma lista com todos os moradores.")
	@GetMapping
	public List<Morador> listarMoradores() {
		return repository.findAll();
	}
	
	@ApiOperation(value = "Cria novo morador", notes = "Cria um novo morador.")
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Morador novoMorador(@Valid @RequestBody Morador novoMorador) {
		return repository.save(novoMorador);
	}
	
	@ApiOperation(value = "Busca morador(es)", notes = "Busca por morador(es) especificado pelo nome.")
	@GetMapping("/{nome}")
	public ResponseEntity<List<Morador>> buscaMoradorPorNome(
			@ApiParam(name = "nome", value = "Nome do morador.", required = true, type = "String") 
			@PathVariable String nome) {
		
		List<Morador> morador = repository.findByNomeContainingOrderByNomeAsc(nome);
		
		if(morador.isEmpty())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(morador);
	}

	@ApiOperation(value = "Edita morador", notes = "Edita morador especificado pelo id.")
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Morador> atualizaMorador(
			@ApiParam(name = "id", value = "Id do morador.", required = true, type = "long") 
			@PathVariable Long id,
			@Valid @RequestBody Morador morador) {
		
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
			
		morador.setId(id);

		morador = repository.save(morador);
		return ResponseEntity.ok(morador);
	}
	
	@ApiOperation(value = "Deleta morador", notes = "Deleta morador especificado pelo id.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirMorador(
			@ApiParam(name = "id", value = "Id do morador.", required = true, type = "long") 
			@PathVariable Long id) {
		
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
