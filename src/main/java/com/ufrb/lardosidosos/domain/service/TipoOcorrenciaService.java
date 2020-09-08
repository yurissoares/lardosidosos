package com.ufrb.lardosidosos.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.constant.NotFoundMsg;
import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.TipoOcorrencia;
import com.ufrb.lardosidosos.domain.repository.ITipoOcorrenciaRepository;

@Service
public class TipoOcorrenciaService implements ITipoOcorrenciaService {

	private ITipoOcorrenciaRepository tipoOcorrenciaRepository;

	@Autowired
	public TipoOcorrenciaService(ITipoOcorrenciaRepository tipoOcorrenciaRepository) {
		this.tipoOcorrenciaRepository = tipoOcorrenciaRepository;
	}
	
	@Override
	public void verificaSeTpOcorrenciaExiste(Long id) {
		if (!this.tipoOcorrenciaRepository.findById(id).isPresent())
			throw new NegocioException(NotFoundMsg.NOT_FOUND_TP_OCORRENCIA.getValor());
	}
	
	@Override
	public List<TipoOcorrencia> listar() {
		return this.tipoOcorrenciaRepository.findAll();
	}

	@Override
	public TipoOcorrencia cadastrar(TipoOcorrencia tipoOcorrencia) {
		return this.tipoOcorrenciaRepository.save(tipoOcorrencia);
	}

	@Override
	public TipoOcorrencia buscar(Long id) {
		Optional<TipoOcorrencia> tipoOcorrenciaOptional = this.tipoOcorrenciaRepository.findById(id);
		if (!tipoOcorrenciaOptional.isPresent()) {
			throw new NegocioException(NotFoundMsg.NOT_FOUND_TP_OCORRENCIA.getValor());
		}
		return tipoOcorrenciaOptional.get();
	}

	@Override
	public TipoOcorrencia editar(Long id, TipoOcorrencia tipoOcorrencia) {
		this.buscar(id);
		tipoOcorrencia.setId(id);
		return this.cadastrar(tipoOcorrencia);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);
		this.tipoOcorrenciaRepository.deleteById(id);
	}
}
