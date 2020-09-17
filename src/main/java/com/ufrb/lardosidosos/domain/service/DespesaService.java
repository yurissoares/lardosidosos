package com.ufrb.lardosidosos.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.Despesa;
import com.ufrb.lardosidosos.domain.repository.IDespesaRepository;

@Service
public class DespesaService implements IDespesaService {
	
	private IDespesaRepository despesaRepository;
	
	@Autowired
	public DespesaService(IDespesaRepository despesaRepository) {
		this.despesaRepository = despesaRepository;
	}
	
	@Override
	public List<Despesa> listar() {
		return this.despesaRepository.findAll();
	}

	@Override
	public Despesa cadastrar(Despesa despesa) {
		return this.despesaRepository.save(despesa);
	}

	@Override
	public Despesa buscar(Long id) {
		Optional<Despesa> despesaOptional = this.despesaRepository.findById(id);
		if (!despesaOptional.isPresent()) {
			throw new NegocioException("Despesa não encontrado.");
		}
		return despesaOptional.get();
	}

	@Override
	public Despesa editar(Long id, Despesa despesa) {
		this.buscar(id);
		despesa.setId(id);
		return this.cadastrar(despesa);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);
		this.despesaRepository.deleteById(id);
		
	}


}
