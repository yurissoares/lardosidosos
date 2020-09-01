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

import com.ufrb.lardosidosos.domain.model.FichaSaude;
import com.ufrb.lardosidosos.domain.service.FichaSaudeService;

@RestController
@RequestMapping("/fichasaude")
public class FichaSaudeController {

	@Autowired
	private FichaSaudeService fichaSaudeService;
	
	@GetMapping
	public List<FichaSaude> listar() {
		return fichaSaudeService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public FichaSaude cadastrar(@Valid @RequestBody FichaSaude fichaSaude) {
		return fichaSaudeService.cadastrar(fichaSaude);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FichaSaude> buscar(@PathVariable Long id) {
		return fichaSaudeService.buscar(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FichaSaude> editar(@PathVariable Long id, @Valid @RequestBody FichaSaude fichaSaude) {
		return fichaSaudeService.editar(id, fichaSaude);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		return fichaSaudeService.excluir(id);
	}
}
