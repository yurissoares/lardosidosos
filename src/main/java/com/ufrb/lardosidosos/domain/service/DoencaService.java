package com.ufrb.lardosidosos.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.constant.NotFoundMsg;
import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.Doenca;
import com.ufrb.lardosidosos.domain.repository.IDoencaRepository;

@Service
public class DoencaService implements IDoencaService {
	
	private IDoencaRepository doencaRepository;
	
	@Autowired
	public DoencaService(IDoencaRepository doencaRepository) {
		this.doencaRepository = doencaRepository;
	}
	
	@Override
	public void verificaSeDoencaExiste(Long id) {
		if(!this.doencaRepository.findById(id).isPresent()) throw new NegocioException(NotFoundMsg.NOT_FOUND_DOENCA.getValor());
	}
	
	@Override
	public List<Doenca> listar() {
		return this.doencaRepository.findAll();
	}

	@Override
	public Doenca cadastrar(Doenca doenca) {
		return this.doencaRepository.save(doenca);
	}

	@Override
	public Doenca buscar(Long id) {
		Optional<Doenca> doencaOptional = this.doencaRepository.findById(id);
		if (!doencaOptional.isPresent()) {
			throw new NegocioException(NotFoundMsg.NOT_FOUND_DOENCA.getValor());
		}
		return doencaOptional.get();
	}

	@Override
	public Doenca editar(Long id, Doenca doenca) {
		this.buscar(id);
		doenca.setId(id);
		return this.cadastrar(doenca);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);
		this.doencaRepository.deleteById(id);
	}

}
