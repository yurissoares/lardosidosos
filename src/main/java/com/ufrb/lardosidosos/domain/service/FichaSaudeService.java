package com.ufrb.lardosidosos.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.FichaSaude;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.model.Usuario;
import com.ufrb.lardosidosos.domain.repository.FichaSaudeRepository;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;
import com.ufrb.lardosidosos.domain.repository.UsuarioRepository;

@Service
public class FichaSaudeService {

	@Autowired
	private FichaSaudeRepository fichaSaudeRepository;
	@Autowired
	private MoradorRepository moradorRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<FichaSaude> listar() {
			return this.fichaSaudeRepository.findAll();
	}
	
	public FichaSaude cadastrar(FichaSaude fichaSaude) {
		Morador morador = moradorRepository.findById(fichaSaude.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		Usuario usuario = usuarioRepository.findById(fichaSaude.getUsuario().getId())
				.orElseThrow(() -> new NegocioException("Usuário não encontrado."));
		
		fichaSaude.setMorador(morador);
		fichaSaude.setUsuario(usuario);
		return this.fichaSaudeRepository.save(fichaSaude);
	}
	
	public ResponseEntity<FichaSaude> buscar(Long id) {
		Optional<FichaSaude> fichaSaudeOptional = this.fichaSaudeRepository.findById(id);
		if(!fichaSaudeOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(fichaSaudeOptional.get());
	}
	
	public ResponseEntity<FichaSaude> editar(Long id, FichaSaude fichaSaude) {
		this.buscar(id);
		fichaSaude.setId(id);
		this.cadastrar(fichaSaude);
		return ResponseEntity.ok(this.cadastrar(fichaSaude));
	}
	
	public ResponseEntity<Void> excluir(Long id) {
		if (!this.fichaSaudeRepository.existsById(id)) {
			return ResponseEntity.notFound().build();			
		}
		this.fichaSaudeRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}













