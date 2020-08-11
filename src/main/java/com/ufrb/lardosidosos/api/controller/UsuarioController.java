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

import com.ufrb.lardosidosos.domain.model.Usuario;
import com.ufrb.lardosidosos.domain.repository.UsuarioRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/usuario")
public class UsuarioController 
{

	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	@ApiOperation(value = "Lista usuários", notes = "Retorna uma lista com todos os usuários.")
	@GetMapping
	public List<Usuario> listar()
	{
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/{usuarioId}")
	public ResponseEntity<Usuario> buscar(
			@PathVariable Long usuarioId)
	{
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
		
		if(usuario.isPresent())
			return ResponseEntity.ok(usuario.get());
		
		return ResponseEntity.notFound().build();

	}
	
	@ApiOperation(value = "Cria usuário", notes = "Cria um novo usuário.")
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Usuario criar(@Valid @RequestBody Usuario novoUsuario)
	{
		return usuarioRepository.save(novoUsuario);
	}
	
	@ApiOperation(value = "Edita usuario", notes = "Edita usuario especificado pelo id.")
	@Transactional
	@PutMapping("/{usuarioId}")
	public ResponseEntity<Usuario> editar(
			@ApiParam(name = "usuarioId", value = "Id do usuário.", required = true, type = "Long") 
			@PathVariable Long usuarioId, 
			@Valid 
			@RequestBody Usuario usuario)
	{
		if(!usuarioRepository.existsById(usuarioId))
		{
			return ResponseEntity.notFound().build();
		}
		
		usuario.setId(usuarioId);
		usuario = usuarioRepository.save(usuario);
		
		return ResponseEntity.ok(usuario);
	}
	
	@ApiOperation(value = "Deleta usuário", notes = "Deleta usuário especificado pelo id.")
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<Void> remover(
			@ApiParam(name = "usuarioId", value = "Id do usuário.", required = true, type = "Long")
			@PathVariable Long usuarioId)
	{
		if(!usuarioRepository.existsById(usuarioId))
		{
			return ResponseEntity.notFound().build();
		}
		
		usuarioRepository.deleteById(usuarioId);
		return ResponseEntity.noContent().build();
	}
}

