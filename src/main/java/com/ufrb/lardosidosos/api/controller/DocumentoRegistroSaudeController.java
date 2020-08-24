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

import com.ufrb.lardosidosos.doctransfer.model.Arquivo;
import com.ufrb.lardosidosos.doctransfer.repository.ArquivoRepository;
import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.DocumentoRegistroSaude;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.model.RegistroSaude;
import com.ufrb.lardosidosos.domain.model.Usuario;
import com.ufrb.lardosidosos.domain.repository.DocumentoRegistroSaudeRepository;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;
import com.ufrb.lardosidosos.domain.repository.RegistroSaudeRepository;
import com.ufrb.lardosidosos.domain.repository.UsuarioRepository;

@RestController
@RequestMapping("/docregsaude")
public class DocumentoRegistroSaudeController {

	@Autowired
	private DocumentoRegistroSaudeRepository repository;

	@Autowired
	private MoradorRepository moradorRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RegistroSaudeRepository registroSaudeRepository;

	@Autowired
	private ArquivoRepository arquivoRepository;

	@GetMapping
	public List<DocumentoRegistroSaude> listar() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<DocumentoRegistroSaude> buscar(@PathVariable Long id) {

		Optional<DocumentoRegistroSaude> documentoRegistroSaude = repository.findById(id);

		if (documentoRegistroSaude.isPresent())
			return ResponseEntity.ok(documentoRegistroSaude.get());

		return ResponseEntity.notFound().build();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	@PostMapping
	public DocumentoRegistroSaude salvar(@Valid @RequestBody DocumentoRegistroSaude documentoRegistroSaude) {

		Morador morador = moradorRepository.findById(documentoRegistroSaude.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		Usuario usuario = usuarioRepository.findById(documentoRegistroSaude.getUsuario().getId())
				.orElseThrow(() -> new NegocioException("Usuário não encontrado."));
		
		RegistroSaude registroSaude = registroSaudeRepository.findById(documentoRegistroSaude.getRegistroSaude().getId())
				.orElseThrow(() -> new NegocioException("RegistroSaude não encontrado."));
		
		documentoRegistroSaude.setMorador(morador);
		documentoRegistroSaude.setUsuario(usuario);
		documentoRegistroSaude.setRegistroSaude(registroSaude);
		return repository.save(documentoRegistroSaude);
	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<DocumentoRegistroSaude> editar(
			@PathVariable Long id,
			@Valid @RequestBody DocumentoRegistroSaude documentoRegistroSaude) {

		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		moradorRepository.findById(documentoRegistroSaude.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		usuarioRepository.findById(documentoRegistroSaude.getUsuario().getId())
				.orElseThrow(() -> new NegocioException("Usuário não encontrado."));
		
		registroSaudeRepository.findById(documentoRegistroSaude.getRegistroSaude().getId())
				.orElseThrow(() -> new NegocioException("RegistroSaude não encontrado."));
		
		documentoRegistroSaude.setId(id);
		documentoRegistroSaude = repository.save(documentoRegistroSaude);
		return ResponseEntity.ok(documentoRegistroSaude);
	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();

		List<Arquivo> arquivosDeletar = arquivoRepository.findAllByDocumentoRegistroSaudeId(id);
		arquivosDeletar.forEach((item) -> {
			arquivoRepository.deleteById(item.getId());
		});

		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
