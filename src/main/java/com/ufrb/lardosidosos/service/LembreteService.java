package com.ufrb.lardosidosos.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.constant.NotFoundErrorMsg;
import com.ufrb.lardosidosos.exception.NegocioException;
import com.ufrb.lardosidosos.entity.Lembrete;
import com.ufrb.lardosidosos.entity.Morador;
import com.ufrb.lardosidosos.entity.RegistroSaude;
import com.ufrb.lardosidosos.entity.TipoLembrete;
import com.ufrb.lardosidosos.entity.Usuario;
import com.ufrb.lardosidosos.entity.enums.StatusLembrete;
import com.ufrb.lardosidosos.repository.ILembreteRepository;
import com.ufrb.lardosidosos.repository.IMoradorRepository;
import com.ufrb.lardosidosos.repository.IRegistroSaudeRepository;
import com.ufrb.lardosidosos.repository.ITipoLembreteRepository;
import com.ufrb.lardosidosos.repository.IUsuarioRepository;

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
					.orElseThrow(() -> new NegocioException(NotFoundErrorMsg.NOT_FOUND_MORADOR.getValor(), HttpStatus.NOT_FOUND));
		}
		Usuario usuarioOrigem = usuarioRepository.findById(lembrete.getUsuarioOrigem().getId())
				.orElseThrow(() -> new NegocioException(NotFoundErrorMsg.NOT_FOUND_USUARIO.getValor(), HttpStatus.NOT_FOUND));
		if (lembrete.getUsuarioDestino() != null) {
			usuarioDestino = usuarioRepository.findById(lembrete.getUsuarioDestino().getId())
					.orElseThrow(() -> new NegocioException(NotFoundErrorMsg.NOT_FOUND_USUARIO.getValor(), HttpStatus.NOT_FOUND));
		}
		TipoLembrete tipoLembrete = tipoLembreteRepository.findById(lembrete.getTipoLembrete().getId())
				.orElseThrow(() -> new NegocioException(NotFoundErrorMsg.NOT_FOUND_TP_LEMBRETE.getValor(), HttpStatus.NOT_FOUND));
		if (lembrete.getRegistroSaude() != null) {
			registroSaude = registroSaudeRepository.findById(lembrete.getRegistroSaude().getId())
					.orElseThrow(() -> new NegocioException(NotFoundErrorMsg.NOT_FOUND_REG_SAUDE.getValor(), HttpStatus.NOT_FOUND));
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
			throw new NegocioException("Lembrete não encontrado.", HttpStatus.NOT_FOUND);
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
			throw new NegocioException(NotFoundErrorMsg.NOT_FOUND_USUARIO.getValor(), HttpStatus.NOT_FOUND);
		}
		return this.lembreteRepository.findByUsuarioDestinoId(usuarioId);
	}

	@Override
	public List<Lembrete> listarLembretesUsuarioOrigemPorUsuarioId(Long usuarioId) {
		if (!usuarioRepository.findById(usuarioId).isPresent()) {
			throw new NegocioException(NotFoundErrorMsg.NOT_FOUND_USUARIO.getValor(), HttpStatus.NOT_FOUND);
		}
		return this.lembreteRepository.findByUsuarioOrigemId(usuarioId);
	}

	@Override
	public List<Lembrete> listarLembreteMoradorPorMoradorId(Long moradorId) {
		if (!moradorRepository.findById(moradorId).isPresent()) {
			throw new NegocioException(NotFoundErrorMsg.NOT_FOUND_MORADOR.getValor(), HttpStatus.NOT_FOUND);
		}
		return this.lembreteRepository.findByMoradorIdOrderByDataCriacaoDesc(moradorId);
	}
}