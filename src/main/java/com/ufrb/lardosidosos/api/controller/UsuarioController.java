package com.ufrb.lardosidosos.api.controller;

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

import com.ufrb.lardosidosos.domain.model.Usuario;
import com.ufrb.lardosidosos.domain.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController 
{

	@Autowired 
	private UsuarioRepository repository;
	
	@GetMapping
	public List<Usuario> listar()
	{
		return repository.findAll();
	}
	
	/*@GetMapping("/{usuarioId}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long usuarioId)
	{
		Optional<Usuario> usuario = repository.findById(usuarioId);
		
		if(usuario.isPresent())
			return ResponseEntity.ok(usuario.get());
		
		return ResponseEntity.notFound().build();

	}*/

	@GetMapping("/{usuarioNome}")
	public ResponseEntity<List<Usuario>> buscarPorNome(@PathVariable String usuarioNome)
	{
		List<Usuario> usuarios = repository.findByNomeResumidoContainingOrderByNomeResumidoAsc(usuarioNome);

		if(usuarios.isEmpty())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(usuarios);

	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	@PostMapping
	public Usuario criar(@Valid @RequestBody Usuario novoUsuario)
	{
		return repository.save(novoUsuario);
	}
	
	@Transactional
	@PutMapping("/{usuarioId}")
	public ResponseEntity<Usuario> editar(
			@PathVariable Long usuarioId, 
			@Valid @RequestBody Usuario usuario)
	{
		if(!repository.existsById(usuarioId))
		{
			return ResponseEntity.notFound().build();
		}
		
		usuario.setId(usuarioId);
		usuario = repository.save(usuario);
		
		return ResponseEntity.ok(usuario);
	}
	
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<Void> excluir(@PathVariable Long usuarioId)
	{
		if(!repository.existsById(usuarioId))
		{
			return ResponseEntity.notFound().build();
		}
		
		repository.deleteById(usuarioId);
		return ResponseEntity.noContent().build();
	}
}

