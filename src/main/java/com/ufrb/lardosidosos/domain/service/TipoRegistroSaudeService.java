package com.ufrb.lardosidosos.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.constant.NotFoundMsg;
import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.TipoRegistroSaude;
import com.ufrb.lardosidosos.domain.repository.ITipoRegistroSaudeRepository;

@Service
public class TipoRegistroSaudeService implements ITipoRegistroSaudeService {

	private ITipoRegistroSaudeRepository tipoRegistroSaudeRepository;

	@Autowired
	public TipoRegistroSaudeService(ITipoRegistroSaudeRepository tipoRegistroSaudeRepository) {
		this.tipoRegistroSaudeRepository = tipoRegistroSaudeRepository;
	}

	@Override
	public void verificaSeTpRegSaudeExiste(Long id) {
		if (!this.tipoRegistroSaudeRepository.findById(id).isPresent())
			throw new NegocioException(NotFoundMsg.NOT_FOUND_TP_REG_SAUDE.getValor(), HttpStatus.NOT_FOUND);
	}

	@Override
	public List<TipoRegistroSaude> listar() {
		return this.tipoRegistroSaudeRepository.findAll();
	}

	@Override
	public TipoRegistroSaude cadastrar(TipoRegistroSaude tipoRegistroSaude) {
		return this.tipoRegistroSaudeRepository.save(tipoRegistroSaude);
	}

	@Override
	public TipoRegistroSaude buscar(Long id) {
		Optional<TipoRegistroSaude> tipoRegistroSaudeOptional = this.tipoRegistroSaudeRepository.findById(id);
		if (!tipoRegistroSaudeOptional.isPresent()) {
			throw new NegocioException(NotFoundMsg.NOT_FOUND_TP_REG_SAUDE.getValor(), HttpStatus.NOT_FOUND);
		}
		return tipoRegistroSaudeOptional.get();
	}

	@Override
	public TipoRegistroSaude editar(Long id, TipoRegistroSaude tipoRegistroSaude) {
		this.buscar(id);
		tipoRegistroSaude.setId(id);
		return this.cadastrar(tipoRegistroSaude);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);
		this.tipoRegistroSaudeRepository.deleteById(id);
	}
}
