package com.ufrb.lardosidosos.domain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.constant.NotFoundMsg;
import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.Lembrete;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.model.RegistroSaude;
import com.ufrb.lardosidosos.domain.model.TipoLembrete;
import com.ufrb.lardosidosos.domain.model.Usuario;
import com.ufrb.lardosidosos.domain.model.enums.StatusLembrete;
import com.ufrb.lardosidosos.domain.repository.ILembreteRepository;
import com.ufrb.lardosidosos.domain.repository.IMoradorRepository;
import com.ufrb.lardosidosos.domain.repository.IRegistroSaudeRepository;
import com.ufrb.lardosidosos.domain.repository.ITipoLembreteRepository;
import com.ufrb.lardosidosos.domain.repository.IUsuarioRepository;

@Service
public class LembreteService implements ILembreteService {

	private ILembreteRepository lembreteRepository;
	private IMoradorRepository moradorRepository;
	private IUsuarioRepository usuarioRepository;
	private ITipoLembreteRepository tipoLembreteRepository;
	private IRegistroSaudeRepository registroSaudeRepository;

	@Autowired
	public LembreteService(ILembreteRepository lembreteRepository, IMoradorRepository moradorRepository,
			IUsuarioRepository usuarioRepository, ITipoLembreteRepository tipoLembreteRepository,
			IRegistroSaudeRepository registroSaudeRepository) {

		this.lembreteRepository = lembreteRepository;
		this.moradorRepository = moradorRepository;
		this.usuarioRepository = usuarioRepository;
		this.tipoLembreteRepository = tipoLembreteRepository;
		this.registroSaudeRepository = registroSaudeRepository;
	}

	@Override
	public List<Lembrete> listar() {
		return this.lembreteRepository.findAll();
	}

	@Override
	public Lembrete cadastrar(Lembrete lembrete) {
		Morador morador = null;
		Usuario usuarioDestino = null;
		RegistroSaude registroSaude = null;

		if (lembrete.getMorador() != null) {
			morador = moradorRepository.findById(lembrete.getMorador().getId())
					.orElseThrow(() -> new NegocioException(NotFoundMsg.NOT_FOUND_MORADOR.getValor()));
		}
		Usuario usuarioOrigem = usuarioRepository.findById(lembrete.getUsuarioOrigem().getId())
				.orElseThrow(() -> new NegocioException(NotFoundMsg.NOT_FOUND_USUARIO.getValor()));
		if (lembrete.getUsuarioDestino() != null) {
			usuarioDestino = usuarioRepository.findById(lembrete.getUsuarioDestino().getId())
					.orElseThrow(() -> new NegocioException(NotFoundMsg.NOT_FOUND_USUARIO.getValor()));
		}
		TipoLembrete tipoLembrete = tipoLembreteRepository.findById(lembrete.getTipoLembrete().getId())
				.orElseThrow(() -> new NegocioException(NotFoundMsg.NOT_FOUND_TP_LEMBRETE.getValor()));
		if (lembrete.getRegistroSaude() != null) {
			registroSaude = registroSaudeRepository.findById(lembrete.getRegistroSaude().getId())
					.orElseThrow(() -> new NegocioException(NotFoundMsg.NOT_FOUND_REG_SAUDE.getValor()));
		}

		lembrete.setMorador(morador);
		lembrete.setUsuarioOrigem(usuarioOrigem);
		lembrete.setUsuarioDestino(usuarioDestino);
		lembrete.setTipoLembrete(tipoLembrete);
		lembrete.setRegistroSaude(registroSaude);

		if (lembrete.getDataCriacao() == null) {
			lembrete.setDataCriacao(LocalDate.now());
		}
		if (lembrete.getStatusLembrete() == null) {
			lembrete.setStatusLembrete(StatusLembrete.PENDENTE);
		}
		return this.lembreteRepository.save(lembrete);
	}

	@Override
	public Lembrete buscar(Long id) {
		Optional<Lembrete> lembreteOptional = this.lembreteRepository.findById(id);
		if (!lembreteOptional.isPresent()) {
			throw new NegocioException("Lembrete não encontrado.");
		}
		return lembreteOptional.get();
	}

	@Override
	public Lembrete editar(Long id, Lembrete lembrete) {
		this.buscar(id);
		lembrete.setId(id);
		return this.cadastrar(lembrete);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);
		this.lembreteRepository.deleteById(id);
	}

	@Override
	public List<Lembrete> listarLembretesUsuarioDestinoPorUsuarioId(Long usuarioId) {
		if (!usuarioRepository.findById(usuarioId).isPresent()) {
			throw new NegocioException(NotFoundMsg.NOT_FOUND_USUARIO.getValor());
		}
		return this.lembreteRepository.findByUsuarioDestinoId(usuarioId);
	}

	@Override
	public List<Lembrete> listarLembretesUsuarioOrigemPorUsuarioId(Long usuarioId) {
		if (!usuarioRepository.findById(usuarioId).isPresent()) {
			throw new NegocioException(NotFoundMsg.NOT_FOUND_USUARIO.getValor());
		}
		return this.lembreteRepository.findByUsuarioOrigemId(usuarioId);
	}

	@Override
	public List<Lembrete> listarLembreteMoradorPorMoradorId(Long moradorId) {
		if (!moradorRepository.findById(moradorId).isPresent()) {
			throw new NegocioException(NotFoundMsg.NOT_FOUND_MORADOR.getValor());
		}
		return this.lembreteRepository.findByMoradorIdOrderByDataCriacaoDesc(moradorId);
	}
}