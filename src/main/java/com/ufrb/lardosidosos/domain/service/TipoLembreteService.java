package com.ufrb.lardosidosos.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.constant.NotFoundMsg;
import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.TipoLembrete;
import com.ufrb.lardosidosos.domain.repository.ITipoLembreteRepository;

@Service
public class TipoLembreteService implements ITipoLembreteService {

	private ITipoLembreteRepository tipoLembreteRepository;
	
	@Autowired
	public TipoLembreteService(ITipoLembreteRepository tipoLembreteRepository) {
		this.tipoLembreteRepository = tipoLembreteRepository;
	}
	
	@Override
	public void verificaSeTpLembreteExiste(Long id) {
		if (!this.tipoLembreteRepository.findById(id).isPresent())
			throw new NegocioException(NotFoundMsg.NOT_FOUND_TP_LEMBRETE.getValor());
	}

	@Override
	public List<TipoLembrete> listar() {
		return this.tipoLembreteRepository.findAll();
	}

	@Override
	public TipoLembrete cadastrar(TipoLembrete tipoLembrete) {
		return this.tipoLembreteRepository.save(tipoLembrete);
	}

	@Override
	public TipoLembrete buscar(Long id) {
		Optional<TipoLembrete> tipoLembreteOptional = this.tipoLembreteRepository.findById(id);
		if (!tipoLembreteOptional.isPresent()) {
			throw new NegocioException(NotFoundMsg.NOT_FOUND_TP_LEMBRETE.getValor());
		}
		return tipoLembreteOptional.get();
	}

	@Override
	public TipoLembrete editar(Long id, TipoLembrete tipoLembrete) {
		this.buscar(id);
		tipoLembrete.setId(id);
		return this.cadastrar(tipoLembrete);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);
		this.tipoLembreteRepository.deleteById(id);
	}
}
