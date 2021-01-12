package com.ufrb.lardosidosos.api.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ufrb.lardosidosos.domain.model.FichaEvDiaria;
import com.ufrb.lardosidosos.domain.service.IFichaEvDiariaService;

@RestController
@RequestMapping("/fichaevdiaria")
@PreAuthorize("hasRole('DIRETOR')")
public class FichaEvDiariaController {
	
	@Autowired
	private IFichaEvDiariaService fichaEvDiariaService;
	
	@GetMapping
	public List<FichaEvDiaria> listar() {
		return fichaEvDiariaService.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public FichaEvDiaria cadastrar(@Valid @RequestBody FichaEvDiaria fichaEvDiaria) {
		return this.fichaEvDiariaService.cadastrar(fichaEvDiaria);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FichaEvDiaria> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(this.fichaEvDiariaService.buscar(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FichaEvDiaria> editar(@PathVariable Long id, @Valid @RequestBody FichaEvDiaria fichaEvDiaria) {
		return ResponseEntity.ok(this.fichaEvDiariaService.editar(id, fichaEvDiaria));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.fichaEvDiariaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/fichaadmissao")
	public FichaEvDiaria cadastrarComFichaAdmissao(@Valid @RequestBody FichaEvDiaria fichaEvDiaria) {
		return this.fichaEvDiariaService.cadastrarComFichaAdmissao(fichaEvDiaria);
	}
	
	@GetMapping("/morador/{moradorId}")
	public List<FichaEvDiaria> listarPorMorador(@PathVariable Long moradorId) {
		return this.fichaEvDiariaService.listarPorMorador(moradorId);
	}
	
	@GetMapping("/morador/datas")
	public List<FichaEvDiaria> listarPorMoradorPorData(@RequestParam("moradorId") Long moradorId, 
			@RequestParam("dtInicio") LocalDate dtInicio, @RequestParam("dtFinal") LocalDate dtFinal) {
		return this.fichaEvDiariaService.listarPorMoradorEntreDatas(moradorId, dtInicio, dtFinal);
	}
	
	@GetMapping("/fichaadmissao/morador/{moradorId}")
	public FichaEvDiaria buscarFichaAdmissaoPorMorador(@PathVariable Long moradorId) {
		return this.fichaEvDiariaService.buscarFichaAdmPorMorador(moradorId);
	}

}
