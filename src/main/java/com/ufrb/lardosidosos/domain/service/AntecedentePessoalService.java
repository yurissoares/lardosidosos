package com.ufrb.lardosidosos.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.constant.NotFoundMsg;
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
		if(!this.antecedentePessoalRepository.findById(id).isPresent()) throw new NegocioException(NotFoundMsg.NOT_FOUND_ANT_PESSOAL.getValor());
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
			throw new NegocioException(NotFoundMsg.NOT_FOUND_ANT_PESSOAL.getValor());
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
