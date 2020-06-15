package com.ufrb.lardosidosos.api.controller;

import java.util.List;

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

import com.ufrb.lardosidosos.domain.model.Contato;
import com.ufrb.lardosidosos.domain.repository.ContatoRepository;
import com.ufrb.lardosidosos.domain.service.GestaoContatoService;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private GestaoContatoService contatoService;
	
	@GetMapping
	public List<Contato> listar() {
		return contatoRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Contato criar(@RequestBody Contato contato) {
		return contatoService.salvar(contato);
	}
	
	@PutMapping("/{contatoId}")
	public ResponseEntity<Contato> atualizar(@PathVariable Long contatoId, @Valid @RequestBody Contato contato) {
		if(!contatoRepository.existsById(contatoId)) {
			return ResponseEntity.notFound().build();
		}
		contato.setId(contatoId);
		contato = contatoService.salvar(contato);
		return ResponseEntity.ok(contato);
	}
	
	@DeleteMapping("/{contatoId}")
	public ResponseEntity<Void> remover(@PathVariable Long contatoId){
		if(!contatoRepository.existsById(contatoId)) {
			return ResponseEntity.notFound().build();
		}
		contatoService.excluir(contatoId);
		return ResponseEntity.noContent().build();
	}
}
