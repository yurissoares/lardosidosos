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

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.model.Ocorrencia;
import com.ufrb.lardosidosos.domain.model.TipoOcorrencia;
import com.ufrb.lardosidosos.domain.model.Usuario;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;
import com.ufrb.lardosidosos.domain.repository.OcorrenciaRepository;
import com.ufrb.lardosidosos.domain.repository.TipoOcorrenciaRepository;
import com.ufrb.lardosidosos.domain.repository.UsuarioRepository;

@RestController
@RequestMapping("/ocorrencia")
public class OcorrenciaController {

	@Autowired
	private OcorrenciaRepository repository;
	
	@Autowired
	private MoradorRepository moradorRepository;
	
	@Autowired
	private TipoOcorrenciaRepository tipoOcorrenciaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/morador/{moradorId}")
	public ResponseEntity<List<Ocorrencia>> listarOcorrenciasDoMorador(
			@PathVariable Long moradorId) 
	{
		moradorRepository.findById(moradorId).orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		return ResponseEntity.ok(repository.findByMoradorId(moradorId));
	}
	
	@GetMapping
	public List<Ocorrencia> listar()
	{
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ocorrencia> buscarPorId(
			@PathVariable Long id)
	{
		Optional<Ocorrencia> ocorrencia = repository.findById(id);
		
		if(ocorrencia.isPresent())
			return ResponseEntity.ok(ocorrencia.get());
		
		return ResponseEntity.notFound().build();

	}
	
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Ocorrencia salvar(@Valid @RequestBody Ocorrencia novaOcorrencia)
	{
		Morador morador = moradorRepository.findById(novaOcorrencia.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		TipoOcorrencia tipoOcorrencia = tipoOcorrenciaRepository.findById(novaOcorrencia.getTipoOcorrencia().getId())
				.orElseThrow(() -> new NegocioException("Tipo de Ocorrencia não encontrado."));
		
		Usuario usuario = usuarioRepository.findById(novaOcorrencia.getUsuario().getId())
				.orElseThrow(() -> new NegocioException("Usuario não encontrado."));
		
		novaOcorrencia.setUsuario(usuario);
		novaOcorrencia.setMorador(morador);
		novaOcorrencia.setTipoOcorrencia(tipoOcorrencia);	
		return repository.save(novaOcorrencia);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Ocorrencia> editar(
			@PathVariable Long id,
			@Valid @RequestBody Ocorrencia ocorrencia)
	{
		moradorRepository.findById(ocorrencia.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		tipoOcorrenciaRepository.findById(ocorrencia.getTipoOcorrencia().getId())
				.orElseThrow(() -> new NegocioException("Tipo de Ocorrencia não encontrado."));
		
		 usuarioRepository.findById(ocorrencia.getUsuario().getId())
				.orElseThrow(() -> new NegocioException("Usuario não encontrado."));
		
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		ocorrencia.setId(id);
		ocorrencia = repository.save(ocorrencia);
		return ResponseEntity.ok(ocorrencia);
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(
			@PathVariable Long id)
	{
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
