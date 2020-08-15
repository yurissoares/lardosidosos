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
import com.ufrb.lardosidosos.domain.model.RegistroSaude;
import com.ufrb.lardosidosos.domain.model.TipoLembrete;
import com.ufrb.lardosidosos.domain.model.Usuario;
import com.ufrb.lardosidosos.domain.repository.LembreteRepository;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;
import com.ufrb.lardosidosos.domain.repository.RegistroSaudeRepository;
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
	
	@Autowired
	private RegistroSaudeRepository registroSaudeRepository;
	
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

	@GetMapping("/usuarioDestino/{usuarioId}")
	public ResponseEntity<List<Lembrete>> buscarPorUsuarioDestino(@PathVariable Long usuarioId)
	{
		usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new NegocioException("Usuário não encontrado."));

		return ResponseEntity.ok(repository.findByUsuarioDestinoId(usuarioId));
	}

	@GetMapping("/usuarioOrigem/{usuarioId}")
	public ResponseEntity<List<Lembrete>> buscarPorUsuarioOrigem(@PathVariable Long usuarioId)
	{
		usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new NegocioException("Usuário não encontrado."));

		return ResponseEntity.ok(repository.findByUsuarioOrigemId(usuarioId));
	}
	
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Lembrete salvar(@Valid @RequestBody Lembrete lembrete)
	{
		Morador morador = null;
		Usuario usuarioDestino = null;
		RegistroSaude registroSaude = null;
		
		if (lembrete.getMorador() != null) 
		{
			morador = moradorRepository.findById(lembrete.getMorador().getId())
					.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		}
		
		Usuario usuarioOrigem = usuarioRepository.findById(lembrete.getUsuarioOrigem().getId())
				.orElseThrow(() -> new NegocioException("Usuário não encontrado."));

		if (lembrete.getUsuarioDestino() != null) 
		{
		usuarioDestino = usuarioRepository.findById(lembrete.getUsuarioDestino().getId())
				.orElseThrow(() -> new NegocioException("Usuário não encontrado."));
		}
		
		TipoLembrete tipoLembrete = tipoLembreteRepository.findById(lembrete.getTipoLembrete().getId())
				.orElseThrow(() -> new NegocioException("Tipo de Lembrete não encontrado."));
		
		if (lembrete.getRegistroSaude() != null) 
		{
			registroSaude = registroSaudeRepository.findById(lembrete.getRegistroSaude().getId())
					.orElseThrow(() -> new NegocioException("RegistroSaude não encontrado."));
		}
		
		lembrete.setMorador(morador);
		lembrete.setUsuarioOrigem(usuarioOrigem);
		lembrete.setUsuarioDestino(usuarioDestino);
		lembrete.setTipoLembrete(tipoLembrete);	
		lembrete.setRegistroSaude(registroSaude);
		return repository.save(lembrete);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Lembrete> editar(
			@PathVariable Long id,
			@Valid @RequestBody Lembrete lembrete)
	{
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		if (lembrete.getMorador() != null) 
		{
			moradorRepository.findById(lembrete.getMorador().getId())
					.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		}

		usuarioRepository.findById(lembrete.getUsuarioOrigem().getId())
				.orElseThrow(() -> new NegocioException("Usuário não encontrado."));

		if (lembrete.getUsuarioDestino() != null) 
		{
			usuarioRepository.findById(lembrete.getUsuarioDestino().getId())
					.orElseThrow(() -> new NegocioException("Usuário não encontrado."));
		}
		
		tipoLembreteRepository.findById(lembrete.getTipoLembrete().getId())
				.orElseThrow(() -> new NegocioException("Tipo de Lembrete não encontrado."));
		
		if (lembrete.getRegistroSaude() != null) 
		{
			registroSaudeRepository.findById(lembrete.getRegistroSaude().getId())
					.orElseThrow(() -> new NegocioException("RegistroSaude não encontrado."));
		}
		
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
