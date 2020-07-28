package com.ufrb.lardosidosos.api.controller;

import java.util.List;
import java.util.Optional;

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

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.Contato;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.repository.ContatoRepository;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/contato")
public class ContatoController {

	@Autowired
	private ContatoRepository repository;
	
	@Autowired
	private MoradorRepository moradorRepository;
	
	@ApiOperation(value = "Lista contatos referente ao morador", notes = "Retorna uma lista com todos os contatos do morador")
	@GetMapping("/morador/{moradorId}")
	public ResponseEntity<List<Contato>> listarContatosDoMorador(
			@ApiParam(name = "moradorId", value = "Id do morador.", required = true, type = "long") 
			@PathVariable Long moradorId) 
	{
		moradorRepository.findById(moradorId).orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		return ResponseEntity.ok(repository.findByMoradorId(moradorId));
	}

	@ApiOperation(value = "Lista todos os contatos", notes = "Retorna uma lista com todos os contatos.")
	@GetMapping
	public List<Contato> listarContatos() {
		return repository.findAll();
	}
	
	@ApiOperation(value = "Cria um novo contato", notes = "Cria um novo contato de um morador.")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	@PostMapping
	public Contato salvarContato(@Valid @RequestBody Contato contato) {
		
		Morador morador = moradorRepository.findById(contato.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));

		contato.setMorador(morador);
		return repository.save(contato);
	}

	@ApiOperation(value = "Busca contato", notes = "Busca por um contato de um morador especificado pelo id do contato.")
	@GetMapping("/{id}")
	public ResponseEntity<Contato> buscaContato(
			@ApiParam(name = "id", value = "Id do contato.", required = true, type = "long") 
			@PathVariable Long id) {
		
		Optional<Contato> contato = repository.findById(id);
		if (contato.isPresent())
			return ResponseEntity.ok(contato.get());
		
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Edita contato", notes = "Edita contato de um morador especificado pelo id do contato.")
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Contato> atualizaContato(
			@ApiParam(name = "id", value = "Id do contato.", required = true, type = "long") 
			@PathVariable Long id,
			@Valid @RequestBody Contato contato) {
		
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();

		contato.setId(id);
		contato = repository.save(contato);
		return ResponseEntity.ok(contato);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta contato", notes = "Deleta contato de um morador especificado pelo id do contato.")
	public ResponseEntity<Void> excluiContato(
			@ApiParam(name = "contatoId", value = "Id do contato.", required = true, type = "long") 
			@PathVariable Long id) {
		
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
