package com.ufrb.lardosidosos.api.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/morador")
public class MoradorController {
	
	@Autowired
	private MoradorRepository moradorRepository;

	@GetMapping
	@Cacheable(value = "listarTodosMoradores")
	@ApiOperation(value = "Listar moradores", notes = "Retorna uma lista com todos os moradores.")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "pageNumber", dataType = "long", paramType = "query",
                value = "Resultado dividido por numero de páginas.", defaultValue = "0"),
        @ApiImplicitParam(name = "pageSize", dataType = "long", paramType = "query",
                value = "Número de moradores por página.", defaultValue = "10")})
	public Page<Morador> listar(@PageableDefault(page = 0, size = 10, sort = "nome", direction = Direction.ASC) Pageable paginacao) 
	{
		Page<Morador> moradores = moradorRepository.findAll(paginacao);

		return moradores;
	}
	
	@GetMapping("/{moradorId}")
	@ApiOperation(value = "Busca morador", notes = "Busca por morador especificado pelo id.")
	public ResponseEntity<Morador> buscar(
			@ApiParam(name = "moradorId", value = "Id do morador.", required = true, type = "long")
			@PathVariable Long moradorId) 
	{
		Optional<Morador> morador = moradorRepository.findById(moradorId);
		if(morador.isPresent()) 
		{
			return ResponseEntity.ok(morador.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	@CacheEvict(value = "listarTodosMoradores", allEntries = true)
	@ApiOperation(value = "Cria morador", notes = "Cria um morador.")
	public Morador adicionar(
			@Valid @RequestBody Morador morador) 
	{
		Morador moradorExistente = moradorRepository.findByNome(morador.getNome());
		if(moradorExistente != null && !moradorExistente.equals(morador)) {
			throw new NegocioException("Já existe um morador com este nome.");
		}
				
		morador.setDataEntrada(LocalDateTime.now());
		morador.setDataNascimento(LocalDateTime.now());
		return moradorRepository.save(morador);

	}
	
	@PutMapping("/{moradorId}")
	@Transactional
	@CacheEvict(value = "listarTodosMoradores", allEntries = true)
	@ApiOperation(value = "Edita morador", notes = "Edita morador especificado pelo id.")
	public ResponseEntity<Morador> atualizar(
			@ApiParam(name = "moradorId", value = "Id do morador.", required = true, type = "long")
			@PathVariable Long moradorId, @Valid @RequestBody Morador morador)
	{
		if(!moradorRepository.existsById(moradorId)) 
		{
			return ResponseEntity.notFound().build();
		} 
		morador.setId(moradorId);

		morador.setDataEntrada(LocalDateTime.now());
		morador.setDataNascimento(LocalDateTime.now());
		
		morador = moradorRepository.save(morador);
		return ResponseEntity.ok(morador);
	}
	
	@DeleteMapping("/{moradorId}")
	@CacheEvict(value = "listarTodosMoradores", allEntries = true)
	@ApiOperation(value = "Deleta morador", notes = "Deleta morador especificado pelo id.")
	public ResponseEntity<Void> remover(
			@ApiParam(name = "moradorId", value = "Id do morador.", required = true, type = "long")
			@PathVariable Long moradorId)
	{
		if(!moradorRepository.existsById(moradorId)) 
		{
			return ResponseEntity.notFound().build();
		} 
		moradorRepository.deleteById(moradorId);
		return ResponseEntity.noContent().build();
	}
	
}
