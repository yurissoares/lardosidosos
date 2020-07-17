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

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.Documento;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.repository.DocumentoRepository;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/documento")
public class DocumentoController {

	@Autowired
	private DocumentoRepository documentoRepository;

	@Autowired
	private MoradorRepository moradorRepository;

	@GetMapping
	@ApiOperation(value = "Lista documentos", notes = "Retorna uma lista com todos os documentos dos moradores.")
	public List<Documento> listaDocumentos() {
		return documentoRepository.findAll();
	}

	@GetMapping("/{documentoId}")
	@ApiOperation(value = "Busca documento", notes = "Busca por documento de um morador pelo id.")
	public ResponseEntity<Documento> buscaDocumento(
			@ApiParam(name = "documentoId", value = "Id do documento.", required = true, type = "long") @PathVariable Long documentoId) {
		Optional<Documento> documento = documentoRepository.findById(documentoId);
		if (documento.isPresent())
			return ResponseEntity.ok(documento.get());
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	@ApiOperation(value = "Cria documento", notes = "Cria documento de um morador.")
	public Documento salvaDocumento(@Valid @RequestBody Documento documento) {

		Morador morador = moradorRepository.findById(documento.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));

		documento.setMorador(morador);
		return documentoRepository.save(documento);
	}

	@PutMapping("/{documentoId}")
	@Transactional
	@ApiOperation(value = "Edita documento", notes = "Edita documento de um morador especificado pelo id do documento.")
	public ResponseEntity<Documento> editaDocumento(
			@ApiParam(name = "documentoId", value = "Id do documento.", required = true, type = "long") @PathVariable Long documentoId,
			@Valid @RequestBody Documento documento) {
		moradorRepository.findById(documento.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));

		documento.setId(documentoId);
		documento = documentoRepository.save(documento);
		return ResponseEntity.ok(documento);
	}

	@DeleteMapping("/{documentoId}")
	@ApiOperation(value = "Deleta documento", notes = "Deleta documento de um morador especificado pelo id do documento.")
	public ResponseEntity<Void> excluiDocumento(
			@ApiParam(name = "documentoId", value = "Id do documento.", required = true, type = "long") @PathVariable Long documentoId) {
		if (!documentoRepository.existsById(documentoId)) {
			return ResponseEntity.notFound().build();
		}

		documentoRepository.deleteById(documentoId);
		return ResponseEntity.noContent().build();
	}
}
