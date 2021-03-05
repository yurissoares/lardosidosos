package com.ufrb.lardosidosos.domain.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.constant.NotFoundMsg;
import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.FichaAdmissao;
import com.ufrb.lardosidosos.domain.repository.IFichaAdmissaoRepository;

@Service
public class FichaAdmissaoService implements IFichaAdmissaoService {

	private IFichaAdmissaoRepository fichaAdmissaoRepository;
	private IMoradorService moradorService;
	private IUsuarioService usuarioService;

	@Autowired
	public FichaAdmissaoService(IFichaAdmissaoRepository fichaAdmissaoRepository, IMoradorService moradorService,
			IUsuarioService usuarioService) {

		this.fichaAdmissaoRepository = fichaAdmissaoRepository;
		this.moradorService = moradorService;
		this.usuarioService = usuarioService;
	}
	
	@Override
	public void verificaSeFichaAdmissaoExiste(Long id) {
		if(!this.fichaAdmissaoRepository.findById(id).isPresent()) throw new NegocioException(NotFoundMsg.NOT_FOUND_FICHA_ADMISSAO.getValor(), HttpStatus.NOT_FOUND);
	}

	@Override
	public List<FichaAdmissao> listar() {
		return this.fichaAdmissaoRepository.findAll();
	}

	@Override
	public FichaAdmissao cadastrar(FichaAdmissao fichaAdmissao) {
		this.moradorService.verificaSeMoradorExiste(fichaAdmissao.getMorador().getId());

		Optional<FichaAdmissao> fichaAdmissaoMoradorOptional = this.fichaAdmissaoRepository
				.findByMoradorId(fichaAdmissao.getMorador().getId());
		if (fichaAdmissaoMoradorOptional.isPresent()) {
			throw new NegocioException("Ficha de admissão já cadastrada para o morador.", HttpStatus.BAD_REQUEST);
		}

		this.usuarioService.verificaSeUsuarioExiste(fichaAdmissao.getUsuario().getId());
		return this.fichaAdmissaoRepository.save(fichaAdmissao);
	}

	@Override
	public FichaAdmissao buscar(Long id) {
		Optional<FichaAdmissao> fichaAdmissaoOptional = this.fichaAdmissaoRepository.findById(id);
		if (!fichaAdmissaoOptional.isPresent()) {
			throw new NegocioException(NotFoundMsg.NOT_FOUND_FICHA_ADMISSAO.getValor(), HttpStatus.NOT_FOUND);
		}
		return fichaAdmissaoOptional.get();
	}

	@Override
	public FichaAdmissao editar(Long id, FichaAdmissao fichaAdmissao) {
		this.buscar(id);
		fichaAdmissao.setId(id);
		this.moradorService.verificaSeMoradorExiste(fichaAdmissao.getMorador().getId());
		this.usuarioService.verificaSeUsuarioExiste(fichaAdmissao.getUsuario().getId());
		
		return this.fichaAdmissaoRepository.save(fichaAdmissao);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);
		this.fichaAdmissaoRepository.deleteById(id);
	}
	
	@Override
	public FichaAdmissao buscarPorMorador(Long moradorId) {
		this.moradorService.verificaSeMoradorExiste(moradorId);
		
		Optional<FichaAdmissao> fichaAdmissaoOptional = this.fichaAdmissaoRepository.findByMoradorId(moradorId);
		if (!fichaAdmissaoOptional.isPresent()) {
			return null;
		}
		return fichaAdmissaoOptional.get();
	}
}
