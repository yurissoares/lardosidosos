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

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.Contato;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.model.enums.Parentesco;
import com.ufrb.lardosidosos.domain.repository.ContatoRepository;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;

@RestController
@RequestMapping("/contato")
public class ContatoController 
{

	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private MoradorRepository moradorRepository;
	
	@GetMapping
	public List<Contato> listar() 
	{
		return contatoRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public Contato criar(@Valid @RequestBody Contato contato) 
	{
		Morador morador = moradorRepository.findById(contato.getMorador().getId())
			.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		contato.setMorador(morador);
		contato.setParentesco(Parentesco.OUTRO);
		return contatoRepository.save(contato);
	}
	
	@PutMapping("/{contatoId}")
	@Transactional
	public ResponseEntity<Contato> atualizar(@PathVariable Long contatoId, @Valid @RequestBody Contato contato) 
	{

		moradorRepository.findById(contato.getMorador().getId())
			.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		contato.setId(contatoId);
		contato.setParentesco(Parentesco.OUTRO);
		
		contato = contatoRepository.save(contato);
		return ResponseEntity.ok(contato);
	}
	
	@DeleteMapping("/{contatoId}")
	public ResponseEntity<Void> remover(@PathVariable Long contatoId)
	{
		if(!contatoRepository.existsById(contatoId)) 
		{
			return ResponseEntity.notFound().build();
		}
		contatoRepository.deleteById(contatoId);
		return ResponseEntity.noContent().build();
	}
}
