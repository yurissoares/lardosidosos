package com.ufrb.lardosidosos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.exception.NegocioException;
import com.ufrb.lardosidosos.entity.MoradorAntecedente;
import com.ufrb.lardosidosos.repository.IMoradorAntecedenteRepository;

@Service
public class MoradorAntecedenteService implements IMoradorAntecedenteService {

	private IMoradorAntecedenteRepository moradorAntecedenteRepository;
	private IMoradorService moradorService;
	private IAntecedentePessoalService antecedentePessoalService;

	@Autowired
	public MoradorAntecedenteService(IMoradorAntecedenteRepository moradorAntecedenteRepository, IMoradorService moradorService
			, IAntecedentePessoalService antecedentePessoalService) {
		
		this.moradorAntecedenteRepository = moradorAntecedenteRepository;
		this.moradorService = moradorService;
		this.antecedentePessoalService = antecedentePessoalService;
	}

	@Override
	public List<MoradorAntecedente> listar() {
		return this.moradorAntecedenteRepository.findAll();
	}

	@Override
	public MoradorAntecedente cadastrar(MoradorAntecedente moradorAntecedente) {
		this.moradorService.verificaSeMoradorExiste(moradorAntecedente.getMorador().getId());
		this.antecedentePessoalService.verificaSeAntPessoalExiste(moradorAntecedente.getAntecedentePessoal().getId());
		return this.moradorAntecedenteRepository.save(moradorAntecedente);
	}

	@Override
	public MoradorAntecedente buscar(Long id) {
		Optional<MoradorAntecedente> moradorAntecedenteOptional = this.moradorAntecedenteRepository.findById(id);
		if (!moradorAntecedenteOptional.isPresent()) {
			throw new NegocioException("Morador antecedente não encontrado.", HttpStatus.NOT_FOUND);
		}
		return moradorAntecedenteOptional.get();
	}

	@Override
	public MoradorAntecedente editar(Long id, MoradorAntecedente moradorAntecedente) {
		this.buscar(id);
		moradorAntecedente.setId(id);
		return this.cadastrar(moradorAntecedente);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);
		this.moradorAntecedenteRepository.deleteById(id);
	}

}
