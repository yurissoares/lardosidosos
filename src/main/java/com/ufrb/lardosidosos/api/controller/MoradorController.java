package com.ufrb.lardosidosos.api.controller;

import java.util.List;

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

import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;
import com.ufrb.lardosidosos.domain.service.CadastroMoradorService;

@RestController
@RequestMapping("/morador")
public class MoradorController {

	@Autowired
	private MoradorRepository moradorRepository;
	
	@Autowired
	private CadastroMoradorService cadastroMoradorService;

	@GetMapping
	public List<Morador> listar() {
		return moradorRepository.findAll();
	}
	
	@GetMapping("/{moradorNome}")
	public ResponseEntity<List<Morador>> buscarPorNome(@PathVariable String moradorNome) {
		
		List<Morador> moradores = 
				moradorRepository.findByNomeContainingOrderByNomeAsc(moradorNome);
		
		if(moradores.isEmpty())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(moradores);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Morador salvar(@Valid @RequestBody Morador morador) {
		return cadastroMoradorService.salvar(morador);
	}
	
	@Transactional
	@PutMapping("/{moradorId}")
	public ResponseEntity<Morador> editar(
			@PathVariable Long moradorId,
			@Valid @RequestBody Morador morador) {
		
		if (!moradorRepository.existsById(moradorId))
			return ResponseEntity.notFound().build();
			
		morador.setId(moradorId);
		return ResponseEntity.ok(moradorRepository.save(morador));
	}
	
	@DeleteMapping("/{moradorId}")
	public ResponseEntity<Void> excluir(@PathVariable Long moradorId) {
		
		if (!moradorRepository.existsById(moradorId))
			return ResponseEntity.notFound().build();
		
		cadastroMoradorService.excluir(moradorId);
		return ResponseEntity.noContent().build();
	}
}
