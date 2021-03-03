package com.ufrb.lardosidosos.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.Contato;
import com.ufrb.lardosidosos.domain.repository.IContatoRepository;

@Service
public class ContatoService implements IContatoService {

	private IContatoRepository contatoRepository;
	private IMoradorService moradorService;
	
	@Autowired
	public ContatoService(IContatoRepository contatoRepository, IMoradorService moradorService) {
		this.contatoRepository = contatoRepository;
		this.moradorService = moradorService;
	}

	@Override
	public List<Contato> listar() {
		return this.contatoRepository.findAll();
	}

	@Override
	public Contato cadastrar(Contato contato) {
		this.moradorService.verificaSeMoradorExiste(contato.getMorador().getId());
		return this.contatoRepository.save(contato);
	}

	@Override
	public Contato buscar(Long id) {
		Optional<Contato> contatoOptional = this.contatoRepository.findById(id);
		if (!contatoOptional.isPresent()) {
			throw new NegocioException("Contato não encontrado.", HttpStatus.NOT_FOUND);
		}
		return contatoOptional.get();
	}

	@Override
	public Contato editar(Long id, Contato contato) {
		this.buscar(id);
		contato.setId(id);
		return this.cadastrar(contato);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);
		this.contatoRepository.deleteById(id);
	}

	@Override
	public List<Contato> listarContatosDoMorador(Long moradaorId) {
		this.moradorService.verificaSeMoradorExiste(moradaorId);
		return this.contatoRepository.findAllByMoradorId(moradaorId);
	}
}
