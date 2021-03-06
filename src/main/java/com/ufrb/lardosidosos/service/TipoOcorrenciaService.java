package com.ufrb.lardosidosos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.constant.NotFoundErrorMsg;
import com.ufrb.lardosidosos.exception.NegocioException;
import com.ufrb.lardosidosos.entity.TipoOcorrencia;
import com.ufrb.lardosidosos.repository.ITipoOcorrenciaRepository;

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
			throw new NegocioException(NotFoundErrorMsg.NOT_FOUND_TP_OCORRENCIA.getValor(), HttpStatus.NOT_FOUND);
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
			throw new NegocioException(NotFoundErrorMsg.NOT_FOUND_TP_OCORRENCIA.getValor(), HttpStatus.NOT_FOUND);
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
