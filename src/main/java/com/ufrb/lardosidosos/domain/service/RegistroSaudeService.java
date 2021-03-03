package com.ufrb.lardosidosos.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ufrb.lardosidosos.domain.constant.NotFoundMsg;
import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.RegistroSaude;
import com.ufrb.lardosidosos.domain.repository.IRegistroSaudeRepository;

@Service
public class RegistroSaudeService implements IRegistroSaudeService {

	private IRegistroSaudeRepository registroSaudeRepository;
	private IMoradorService moradorService;
	private IUsuarioService usuarioService;
	private ITipoRegistroSaudeService tipoRegistroSaudeService;

	@Autowired
	public RegistroSaudeService(IRegistroSaudeRepository registroSaudeRepository, IMoradorService moradorService,
			IUsuarioService usuarioService, ITipoRegistroSaudeService tipoRegistroSaudeService) {

		this.registroSaudeRepository = registroSaudeRepository;
		this.moradorService = moradorService;
		this.usuarioService = usuarioService;
		this.tipoRegistroSaudeService = tipoRegistroSaudeService;
	}

	@Override
	public void verificaSeRegSaudeExiste(Long id) {
		if (!this.registroSaudeRepository.findById(id).isPresent())
			throw new NegocioException(NotFoundMsg.NOT_FOUND_REG_SAUDE.getValor(), HttpStatus.NOT_FOUND);
	}

	@Override
	public List<RegistroSaude> listar() {
		return this.registroSaudeRepository.findAll();
	}

	@Override
	public RegistroSaude cadastrar(RegistroSaude regSaude) {
		this.moradorService.verificaSeMoradorExiste(regSaude.getMorador().getId());
		this.tipoRegistroSaudeService.verificaSeTpRegSaudeExiste(regSaude.getTipoRegistroSaude().getId());
		this.usuarioService.verificaSeUsuarioExiste(regSaude.getUsuario().getId());
		return this.registroSaudeRepository.save(regSaude);
	}

	@Override
	public RegistroSaude buscar(Long id) {
		Optional<RegistroSaude> registroSaudeOptional = this.registroSaudeRepository.findById(id);
		if (!registroSaudeOptional.isPresent()) {
			throw new NegocioException(NotFoundMsg.NOT_FOUND_REG_SAUDE.getValor(), HttpStatus.NOT_FOUND);
		}
		return registroSaudeOptional.get();
	}

	@Override
	public RegistroSaude editar(Long id, RegistroSaude registroSaude) {
		this.buscar(id);
		registroSaude.setId(id);
		return this.cadastrar(registroSaude);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);
		this.registroSaudeRepository.deleteById(id);
	}

	@Override
	public List<RegistroSaude> listarRegistroSaudePorMorador(@PathVariable Long moradorId) {
		this.moradorService.verificaSeMoradorExiste(moradorId);
		return this.registroSaudeRepository.findByMoradorIdOrderByDataDesc(moradorId);
	}

}
