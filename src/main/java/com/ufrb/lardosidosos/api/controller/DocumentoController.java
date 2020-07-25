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
import com.ufrb.lardosidosos.domain.model.Documento;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.repository.DocumentoRepository;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/documento")
public class DocumentoController {

	@Autowired
	private DocumentoRepository repository;

	@Autowired
	private MoradorRepository moradorRepository;

	@ApiOperation(value = "Lista todos os documentos", notes = "Retorna uma lista com todos os documentos dos moradores.")
	@GetMapping
	List<Documento> listarDocumentos() {
		return repository.findAll();
	}

	@ApiOperation(value = "Busca um documento", notes = "Busca por documento de um morador pelo id.")
	@GetMapping("/{id}")
	public ResponseEntity<Documento> buscaDocumento(
			@ApiParam(name = "id", value = "Id do documento.", required = true, type = "long") @PathVariable Long id) {

		Optional<Documento> documento = repository.findById(id);

		if (documento.isPresent())
			return ResponseEntity.ok(documento.get());

		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Cria documento", notes = "Cria documento de um morador.")
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Documento salvaDocumento(@Valid @RequestBody Documento documento) {

		Morador morador = moradorRepository.findById(documento.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		documento.setMorador(morador);
		return repository.save(documento);
	}

	@ApiOperation(value = "Edita um documento", notes = "Edita um documento de um morador especificado pelo id do documento.")
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Documento> editaDocumento(
			@ApiParam(name = "id", value = "Id do documento.", required = true, type = "long") 
			@PathVariable Long id,
			@Valid @RequestBody Documento documento) {

		moradorRepository.findById(documento.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		documento.setId(id);
		documento = repository.save(documento);
		return ResponseEntity.ok(documento);
	}

	@ApiOperation(value = "Deleta um documento", notes = "Deleta um documento de um morador especificado pelo id do documento.")
	@DeleteMapping("/{id}")
	ResponseEntity<Void> excluiDocumento(
			@ApiParam(name = "id", value = "Id do documento.", required = true, type = "long") @PathVariable Long id) {

		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();

		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
