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
import com.ufrb.lardosidosos.domain.model.Lembrete;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.model.TipoLembrete;
import com.ufrb.lardosidosos.domain.model.Usuario;
import com.ufrb.lardosidosos.domain.repository.LembreteRepository;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;
import com.ufrb.lardosidosos.domain.repository.TipoLembreteRepository;
import com.ufrb.lardosidosos.domain.repository.UsuarioRepository;

@RestController
@RequestMapping("/lembrete")
public class LembreteController {

	@Autowired
	private LembreteRepository repository;
	
	@Autowired
	private MoradorRepository moradorRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TipoLembreteRepository tipoLembreteRepository;
	
	@GetMapping
	public List<Lembrete> listar()
	{
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Lembrete> buscarPorId(
			@PathVariable Long id)
	{
		Optional<Lembrete> lembrete = repository.findById(id);
		
		if(lembrete.isPresent())
			return ResponseEntity.ok(lembrete.get());
		
		return ResponseEntity.notFound().build();

	}
	
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Lembrete salvar(@Valid @RequestBody Lembrete novoLembrete)
	{
		Morador morador = moradorRepository.findById(novoLembrete.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		Usuario usuario = usuarioRepository.findById(novoLembrete.getUsuario().getId())
				.orElseThrow(() -> new NegocioException("Usuário não encontrado."));
		
		TipoLembrete tipoLembrete = tipoLembreteRepository.findById(novoLembrete.getTipoLembrete().getId())
				.orElseThrow(() -> new NegocioException("Tipo de Lembrete não encontrado."));
		
		novoLembrete.setMorador(morador);
		novoLembrete.setUsuario(usuario);
		novoLembrete.setTipoLembrete(tipoLembrete);	
		return repository.save(novoLembrete);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Lembrete> editar(
			@PathVariable Long id,
			@Valid @RequestBody Lembrete lembrete)
	{
		moradorRepository.findById(lembrete.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		usuarioRepository.findById(lembrete.getUsuario().getId())
				.orElseThrow(() -> new NegocioException("Usuário encontrado."));
		
		tipoLembreteRepository.findById(lembrete.getTipoLembrete().getId())
				.orElseThrow(() -> new NegocioException("Tipo de Lembrete não encontrado."));
		
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		lembrete.setId(id);
		lembrete = repository.save(lembrete);
		return ResponseEntity.ok(lembrete);
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
