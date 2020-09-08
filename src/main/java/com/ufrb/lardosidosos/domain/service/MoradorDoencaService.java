package com.ufrb.lardosidosos.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.MoradorDoenca;
import com.ufrb.lardosidosos.domain.repository.IMoradorDoencaRepository;

@Service
public class MoradorDoencaService implements IMoradorDoencaService {

	private IMoradorDoencaRepository moradorDoencaRepository;
	private IMoradorService moradorService;
	private IDoencaService doencaService;

	@Autowired
	public MoradorDoencaService(IMoradorDoencaRepository moradorDoencaRepository, IMoradorService moradorService
			, IDoencaService doencaService) {
		
		this.moradorDoencaRepository = moradorDoencaRepository;
		this.moradorService = moradorService;
		this.doencaService = doencaService;
	}

	@Override
	public List<MoradorDoenca> listar() {
		return this.moradorDoencaRepository.findAll();
	}

	@Override
	public MoradorDoenca cadastrar(MoradorDoenca moradorDoenca) {
		this.moradorService.verificaSeMoradorExiste(moradorDoenca.getMorador().getId());
		this.doencaService.verificaSeDoencaExiste(moradorDoenca.getDoenca().getId());
		return this.moradorDoencaRepository.save(moradorDoenca);
	}

	@Override
	public MoradorDoenca buscar(Long id) {
		Optional<MoradorDoenca> moradorDoencaOptional = this.moradorDoencaRepository.findById(id);
		if (!moradorDoencaOptional.isPresent()) {
			throw new NegocioException("Morador doença não encontrado.");
		}
		return moradorDoencaOptional.get();
	}

	@Override
	public MoradorDoenca editar(Long id, MoradorDoenca moradorDoenca) {
		this.buscar(id);
		moradorDoenca.setId(id);
		return this.cadastrar(moradorDoenca);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);
		this.moradorDoencaRepository.deleteById(id);
	}

}
