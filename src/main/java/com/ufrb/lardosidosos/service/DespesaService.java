package com.ufrb.lardosidosos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.constant.NotFoundErrorMsg;
import com.ufrb.lardosidosos.exception.NegocioException;
import com.ufrb.lardosidosos.entity.Despesa;
import com.ufrb.lardosidosos.repository.IDespesaRepository;

@Service
public class DespesaService implements IDespesaService {
	
	private IDespesaRepository despesaRepository;
	
	@Autowired
	public DespesaService(IDespesaRepository despesaRepository) {
		this.despesaRepository = despesaRepository;
	}
	
	@Override
	public void verificaSeDespesaExiste(Long id) {
		if (!this.despesaRepository.findById(id).isPresent())
			throw new NegocioException(NotFoundErrorMsg.NOT_FOUND_DESPESA.getValor(), HttpStatus.NOT_FOUND);
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
			throw new NegocioException(NotFoundErrorMsg.NOT_FOUND_DESPESA.getValor(), HttpStatus.NOT_FOUND);
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
