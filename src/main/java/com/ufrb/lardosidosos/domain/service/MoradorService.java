package com.ufrb.lardosidosos.domain.service;

import java.util.List;
import java.util.Optional;

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.constant.NotFoundMsg;
import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.repository.IMoradorRepository;

@Service
public class MoradorService implements IMoradorService {

	private IMoradorRepository moradorRepository;

	@Autowired
	public MoradorService(IMoradorRepository moradorRepository) {
		this.moradorRepository = moradorRepository;
	}

	@Override
	public void verificaSeMoradorExiste(Long id) {
		if (!this.moradorRepository.findById(id).isPresent())
			throw new NegocioException(NotFoundMsg.NOT_FOUND_MORADOR.getValor(), HttpStatus.NOT_FOUND);
	}

	@Override
	public List<Morador> listar() {
		return this.moradorRepository.findAll();
	}

	@Override
	public Morador cadastrar(Morador morador) {
		return this.moradorRepository.save(morador);
	}

	@Override
	public Morador buscar(Long id) {
		Optional<Morador> moradorOptional = this.moradorRepository.findById(id);
		if (!moradorOptional.isPresent()) {
			throw new NegocioException(NotFoundMsg.NOT_FOUND_MORADOR.getValor(), HttpStatus.NOT_FOUND);
		}
		return moradorOptional.get();
	}

	@Override
	public Morador editar(Long id, Morador morador) {
		this.buscar(id);
		morador.setId(id);
		return this.cadastrar(morador);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);
		this.moradorRepository.deleteById(id);
	}

	@Override
	public List<Morador> buscarPorNome(String nome) {
		List<Morador> moradores = moradorRepository.findByNomeContainingOrderByNomeAsc(nome);
		if (moradores.isEmpty()) {
			throw new NegocioException(NotFoundMsg.NOT_FOUND_MORADOR.getValor(), HttpStatus.NOT_FOUND);
		}
		return moradores;
	}

}
