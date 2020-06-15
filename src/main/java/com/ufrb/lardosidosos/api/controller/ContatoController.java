package com.ufrb.lardosidosos.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		return contatoService.criar(contato);
	}
	
	
}
