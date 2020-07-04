package com.ufrb.lardosidosos.api.controller;

import java.time.LocalDateTime;
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
import com.ufrb.lardosidosos.domain.model.Documento;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.repository.DocumentoRepository;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;

@RestController
@RequestMapping("/documento")
public class DocumentoController 
{
	
	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Autowired
	private MoradorRepository moradorRepository;
	
	@GetMapping
	public List<Documento> listar() 
	{
		return documentoRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public Documento criar(@Valid @RequestBody Documento documento) 
	{
		Morador morador = moradorRepository.findById(documento.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		documento.setMorador(morador);
		documento.setData(LocalDateTime.now());
		return documentoRepository.save(documento);
	}
	
	@PutMapping("/{documentoId}")
	@Transactional
	public ResponseEntity<Documento> atualizar(@PathVariable Long documentoId, @Valid @RequestBody Documento documento)
	{
		moradorRepository.findById(documento.getMorador().getId())
			.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		documento.setId(documentoId);
		
		documento = documentoRepository.save(documento);
		return ResponseEntity.ok(documento);
	}
	
	@DeleteMapping("/{documentoId}")
	public ResponseEntity<Void> remover(@PathVariable Long documentoId)
	{
		if(!documentoRepository.existsById(documentoId)) 
		{
			return ResponseEntity.notFound().build();
		}
		
		documentoRepository.deleteById(documentoId);
		return ResponseEntity.noContent().build();
	}
}







































