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
import com.ufrb.lardosidosos.domain.model.enums.Parentesco;
import com.ufrb.lardosidosos.domain.repository.ContatoRepository;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/contato")
public class ContatoController {

	@Autowired
	private ContatoRepository contatoRepository;

	@Autowired
	private MoradorRepository moradorRepository;

	@GetMapping
	@ApiOperation(value = "Lista contatos", notes = "Retorna uma lista com todos os contatos dos moradores.")
	public List<Contato> listaContatos() {
		return contatoRepository.findAll();
	}

	@GetMapping("/{contatoId}")
	@ApiOperation(value = "Busca contato", notes = "Busca por um contato de um morador especificado pelo id do contato.")
	public ResponseEntity<Contato> buscaContato(
			@ApiParam(name = "contatoId", value = "Id do contato.", required = true, type = "long") @PathVariable Long contatoId) {
		Optional<Contato> contato = contatoRepository.findById(contatoId);
		if (contato.isPresent())
			return ResponseEntity.ok(contato.get());
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	@ApiOperation(value = "Cria contato", notes = "Cria um contato de um morador.")
	public Contato salvaDocumento(@Valid @RequestBody Contato contato) {
		Morador morador = moradorRepository.findById(contato.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));

		contato.setMorador(morador);
		contato.setParentesco(Parentesco.OUTRO);
		return contatoRepository.save(contato);
	}

	@PutMapping("/{contatoId}")
	@Transactional
	@ApiOperation(value = "Edita contato", notes = "Edita contato de um morador especificado pelo id do contato.")
	public ResponseEntity<Contato> atualizaContato(
			@ApiParam(name = "contatoId", value = "Id do contato.", required = true, type = "long") @PathVariable Long contatoId,
			@Valid @RequestBody Contato contato) {

		moradorRepository.findById(contato.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));

		contato.setId(contatoId);
		contato.setParentesco(Parentesco.OUTRO);

		contato = contatoRepository.save(contato);
		return ResponseEntity.ok(contato);
	}

	@DeleteMapping("/{contatoId}")
	@ApiOperation(value = "Deleta contato", notes = "Deleta contato de um morador especificado pelo id do contato.")
	public ResponseEntity<Void> excluiContato(
			@ApiParam(name = "contatoId", value = "Id do contato.", required = true, type = "long") @PathVariable Long contatoId) {
		if (!contatoRepository.existsById(contatoId)) {
			return ResponseEntity.notFound().build();
		}
		contatoRepository.deleteById(contatoId);
		return ResponseEntity.noContent().build();
	}
}
