package com.ufrb.lardosidosos.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/morador")
public class MoradorController {

	@Autowired
	private MoradorRepository moradorRepository;

	@GetMapping
	@ApiOperation(value = "Lista moradores", notes = "Retorna uma lista com todos os moradores.")
	public List<Morador> listaMoradores() {
		return moradorRepository.findAll();
	}

	@GetMapping("/{moradorId}")
	@ApiOperation(value = "Busca morador", notes = "Busca por morador especificado pelo id.")
	public ResponseEntity<Morador> buscaMorador(
			@ApiParam(name = "moradorId", value = "Id do morador.", required = true, type = "long") @PathVariable Long moradorId) {
		Optional<Morador> morador = moradorRepository.findById(moradorId);
		if (morador.isPresent()) {
			return ResponseEntity.ok(morador.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	@ApiOperation(value = "Cria morador", notes = "Cria um morador.")
	public Morador salvaMorador(@Valid @RequestBody Morador morador) {
		
		return moradorRepository.save(morador);
	}

	@PutMapping("/{moradorId}")
	@Transactional
	@ApiOperation(value = "Edita morador", notes = "Edita morador especificado pelo id.")
	public ResponseEntity<Morador> atualizaMorador(
			@ApiParam(name = "moradorId", value = "Id do morador.", required = true, type = "long") @PathVariable Long moradorId,
			@Valid @RequestBody Morador morador) {
		
		if (!moradorRepository.existsById(moradorId)) {
			return ResponseEntity.notFound().build();
		}
		morador.setId(moradorId);

		morador = moradorRepository.save(morador);
		return ResponseEntity.ok(morador);
	}

	@DeleteMapping("/{moradorId}")
	@ApiOperation(value = "Deleta morador", notes = "Deleta morador especificado pelo id.")
	public ResponseEntity<Void> excluiMorador(
			@ApiParam(name = "moradorId", value = "Id do morador.", required = true, type = "long") @PathVariable Long moradorId) {
		if (!moradorRepository.existsById(moradorId)) {
			return ResponseEntity.notFound().build();
		}
		moradorRepository.deleteById(moradorId);
		return ResponseEntity.noContent().build();
	}

}
