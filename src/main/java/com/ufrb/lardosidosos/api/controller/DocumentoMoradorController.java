package com.ufrb.lardosidosos.api.controller;

import java.util.List;
import java.util.Optional;

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

import com.ufrb.lardosidosos.doctransfer.model.Arquivo;
import com.ufrb.lardosidosos.doctransfer.repository.ArquivoRepository;
import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.DocumentoMorador;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.repository.DocumentoMoradorRepository;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;

@RestController
@RequestMapping("/docmorador")
public class DocumentoMoradorController {

	@Autowired
	private DocumentoMoradorRepository repository;

	@Autowired
	private MoradorRepository moradorRepository;
	
	@Autowired
	private ArquivoRepository arquivoRepository;
	
	@GetMapping
	public List<DocumentoMorador> listar() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<DocumentoMorador> buscar(@PathVariable Long id) {

		Optional<DocumentoMorador> documentoMorador = repository.findById(id);

		if (documentoMorador.isPresent())
			return ResponseEntity.ok(documentoMorador.get());

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/morador/{moradorId}")
	public ResponseEntity<List<DocumentoMorador>> listarDocDoMorador(@PathVariable Long moradorId)
	{
		 moradorRepository.findById(moradorId)
			.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		return ResponseEntity.ok(repository.findByMoradorId(moradorId));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	@PostMapping
	public DocumentoMorador salvar(@Valid @RequestBody DocumentoMorador documentoMorador) {

		Morador morador = moradorRepository.findById(documentoMorador.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		documentoMorador.setMorador(morador);
		return repository.save(documentoMorador);
	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<DocumentoMorador> editar(
			@PathVariable Long id,
			@Valid @RequestBody DocumentoMorador documentoMorador) {

		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		documentoMorador.setId(id);
		documentoMorador = repository.save(documentoMorador);
		return ResponseEntity.ok(documentoMorador);
	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		List<Arquivo> arquivosDeletar = arquivoRepository.findAllByDocumentoMoradorId(id);
		arquivosDeletar.forEach((item) -> {
			arquivoRepository.deleteById(item.getId());
		});

		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
