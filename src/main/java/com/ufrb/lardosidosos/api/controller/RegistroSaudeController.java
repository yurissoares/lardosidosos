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
import com.ufrb.lardosidosos.domain.model.RegistroSaude;
import com.ufrb.lardosidosos.domain.model.TipoRegistroSaude;
import com.ufrb.lardosidosos.domain.model.Usuario;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;
import com.ufrb.lardosidosos.domain.repository.RegistroSaudeRepository;
import com.ufrb.lardosidosos.domain.repository.TipoRegistroSaudeRepository;
import com.ufrb.lardosidosos.domain.repository.UsuarioRepository;

@RestController
@RequestMapping("/registrosaude")
public class RegistroSaudeController {

	@Autowired
	private RegistroSaudeRepository repository;
	
	@Autowired
	private MoradorRepository moradorRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TipoRegistroSaudeRepository tipoRegistroSaudeRepository;
	
	@GetMapping
	public List<RegistroSaude> listar()
	{
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RegistroSaude> buscarPorId(
			@PathVariable Long id)
	{
		Optional<RegistroSaude> registroSaude = repository.findById(id);
		
		if(registroSaude.isPresent())
			return ResponseEntity.ok(registroSaude.get());
		
		return ResponseEntity.notFound().build();

	}
	
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public RegistroSaude salvar(@Valid @RequestBody RegistroSaude registroSaude)
	{
		Morador morador = moradorRepository.findById(registroSaude.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		TipoRegistroSaude tipoRegistroSaude = tipoRegistroSaudeRepository.findById(registroSaude.getTipoRegistroSaude().getId())
				.orElseThrow(() -> new NegocioException("Tipo de RegistroSaude não encontrado."));
		
		Usuario usuario = usuarioRepository.findById(registroSaude.getUsuario().getId())
				.orElseThrow(() -> new NegocioException("Usuario não encontrado."));
		
		registroSaude.setUsuario(usuario);
		registroSaude.setMorador(morador);
		registroSaude.setTipoRegistroSaude(tipoRegistroSaude);	
		return repository.save(registroSaude);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<RegistroSaude> editar(
			@PathVariable Long id,
			@Valid @RequestBody RegistroSaude registroSaude)
	{
		moradorRepository.findById(registroSaude.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		tipoRegistroSaudeRepository.findById(registroSaude.getTipoRegistroSaude().getId())
				.orElseThrow(() -> new NegocioException("Tipo de RegistroSaude não encontrado."));
		
		 usuarioRepository.findById(registroSaude.getUsuario().getId())
				.orElseThrow(() -> new NegocioException("Usuario não encontrado."));
		
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		registroSaude.setId(id);
		registroSaude = repository.save(registroSaude);
		return ResponseEntity.ok(registroSaude);
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
