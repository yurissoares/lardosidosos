package com.ufrb.lardosidosos.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ufrb.lardosidosos.domain.model.Usuario;
import com.ufrb.lardosidosos.domain.service.IUsuarioService;

@RestController
@RequestMapping("/usuario")
@PreAuthorize("hasRole('ROLE_DIRETOR')")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping
	public List<Usuario> listar() {
		return this.usuarioService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Usuario cadastrar(@Valid @RequestBody Usuario usuario) {
		return this.usuarioService.cadastrar(usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> editar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		return ResponseEntity.ok(this.usuarioService.editar(id, usuario));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.usuarioService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{nome}")
	public ResponseEntity<List<Usuario>> buscarPorNome(@PathVariable String nome) {
		return ResponseEntity.ok(this.usuarioService.buscarPorNome(nome));
	}
}

