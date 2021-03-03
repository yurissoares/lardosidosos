package com.ufrb.lardosidosos.domain.service;

import java.util.List;
import java.util.Optional;

import com.ufrb.lardosidosos.domain.constant.NotFoundMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.AntecedentePessoal;
import com.ufrb.lardosidosos.domain.repository.IAntecedentePessoalRepository;

@Service
public class AntecedentePessoalService implements IAntecedentePessoalService {
	
	private IAntecedentePessoalRepository antecedentePessoalRepository;
	
	@Autowired
	public AntecedentePessoalService(IAntecedentePessoalRepository antecedentePessoalRepository) {
		this.antecedentePessoalRepository = antecedentePessoalRepository;
	}
	
	@Override
	public void verificaSeAntPessoalExiste(Long id) {
		Optional<AntecedentePessoal> AntecedentePessoalOptional = this.antecedentePessoalRepository.findById(id);
		if (!AntecedentePessoalOptional.isPresent()) {
			throw new NegocioException(NotFoundMsg.NOT_FOUND_ANT_PESSOAL.getValor(), HttpStatus.NOT_FOUND);
		}
	}
	
	@Override
	public List<AntecedentePessoal> listar() {
		return this.antecedentePessoalRepository.findAll();
	}

	@Override
	public AntecedentePessoal cadastrar(AntecedentePessoal antecedentePessoal) {
		return this.antecedentePessoalRepository.save(antecedentePessoal);
	}

	@Override
	public AntecedentePessoal buscar(Long id) {
		Optional<AntecedentePessoal> antecedentePessoalOptional = this.antecedentePessoalRepository.findById(id);
		if (!antecedentePessoalOptional.isPresent()) {
			throw new NegocioException(NotFoundMsg.NOT_FOUND_ANT_PESSOAL.getValor(), HttpStatus.NOT_FOUND);
		}
		return antecedentePessoalOptional.get();
	}

	@Override
	public AntecedentePessoal editar(Long id, AntecedentePessoal antecedentePessoal) {
		this.buscar(id);
		antecedentePessoal.setId(id);
		return this.cadastrar(antecedentePessoal);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);
		this.antecedentePessoalRepository.deleteById(id);
	}

}
