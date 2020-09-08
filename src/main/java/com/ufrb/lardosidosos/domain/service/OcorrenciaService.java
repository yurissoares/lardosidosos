package com.ufrb.lardosidosos.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.constant.NotFoundMsg;
import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.Ocorrencia;
import com.ufrb.lardosidosos.domain.repository.IOcorrenciaRepository;

@Service
public class OcorrenciaService implements IOcorrenciaService {

	private IOcorrenciaRepository ocorrenciaRepository;
	private IMoradorService moradorService;
	private ITipoOcorrenciaService tipoOcorrenciaService;
	private IUsuarioService usuarioService;

	@Autowired
	public OcorrenciaService(IOcorrenciaRepository ocorrenciaRepository, IMoradorService moradorService,
			ITipoOcorrenciaService tipoOcorrenciaService, IUsuarioService usuarioService) {

		this.ocorrenciaRepository = ocorrenciaRepository;
		this.moradorService = moradorService;
		this.tipoOcorrenciaService = tipoOcorrenciaService;
		this.usuarioService = usuarioService;
	}
	
	@Override
	public void verificaSeOcorrenciaExiste(Long id) {
		if (!this.ocorrenciaRepository.findById(id).isPresent())
			throw new NegocioException(NotFoundMsg.NOT_FOUND_OCORRENCIA.getValor());
	}

	@Override
	public List<Ocorrencia> listar() {
		return this.ocorrenciaRepository.findAll();
	}

	@Override
	public Ocorrencia cadastrar(Ocorrencia ocorrencia) {
		this.moradorService.verificaSeMoradorExiste(ocorrencia.getMorador().getId());
		this.tipoOcorrenciaService.verificaSeTpOcorrenciaExiste(ocorrencia.getTipoOcorrencia().getId());
		this.usuarioService.verificaSeUsuarioExiste(ocorrencia.getUsuario().getId());
		return this.ocorrenciaRepository.save(ocorrencia);
	}

	@Override
	public Ocorrencia buscar(Long id) {
		Optional<Ocorrencia> ocorrenciaOptional = this.ocorrenciaRepository.findById(id);
		if (!ocorrenciaOptional.isPresent()) {
			throw new NegocioException(NotFoundMsg.NOT_FOUND_OCORRENCIA.getValor());
		}
		return ocorrenciaOptional.get();
	}

	@Override
	public Ocorrencia editar(Long id, Ocorrencia ocorrencia) {
		this.buscar(id);
		ocorrencia.setId(id);
		return this.cadastrar(ocorrencia);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);
		this.ocorrenciaRepository.deleteById(id);
	}

	@Override
	public List<Ocorrencia> listarOcorrenciaMorador(Long moradorId) {
		this.moradorService.verificaSeMoradorExiste(moradorId);
		return this.ocorrenciaRepository.findByMoradorIdOrderByDataDesc(moradorId);
	}
}
