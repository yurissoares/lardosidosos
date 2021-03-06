package com.ufrb.lardosidosos.service;

import java.util.List;
import java.util.Optional;

import com.ufrb.lardosidosos.constant.InternalErrorMsg;
import com.ufrb.lardosidosos.dto.MoradorDTO;
import com.ufrb.lardosidosos.exception.NegocioException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.constant.NotFoundErrorMsg;
import com.ufrb.lardosidosos.entity.Morador;
import com.ufrb.lardosidosos.repository.IMoradorRepository;

@Service
public class MoradorService implements IMoradorService {

	private IMoradorRepository moradorRepository;
	private ModelMapper mapper;

	@Autowired
	public MoradorService(final IMoradorRepository moradorRepository) {
		this.mapper = new ModelMapper();
		this.moradorRepository = moradorRepository;
	}

	@Override
	public void verificaSeMoradorExiste(final Long id) {
		if (!this.moradorRepository.findById(id).isPresent())
			throw new NegocioException(NotFoundErrorMsg.NOT_FOUND_MORADOR.getValor(), HttpStatus.NOT_FOUND);
	}

	@Override
	public List<MoradorDTO> listar() {
		try {
			return this.mapper.map(this.moradorRepository.findAll(),new TypeToken<List<MoradorDTO>>() {}.getType());
		} catch(Exception e) {
			throw new NegocioException(InternalErrorMsg.INTERNAL_ERROR_MSG.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Boolean cadastrar(final MoradorDTO moradorDto) {
		try{
			Morador morador = this.mapper.map(moradorDto, Morador.class);
			this.moradorRepository.save(morador);
			return Boolean.TRUE;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public MoradorDTO buscar(final Long id) {
		try {
			Optional<Morador> moradorOptional = this.moradorRepository.findById(id);
			if (!moradorOptional.isPresent()) {
				throw new NegocioException(NotFoundErrorMsg.NOT_FOUND_MORADOR.getValor(), HttpStatus.NOT_FOUND);
			}
			return this.mapper.map(moradorOptional.get(), MoradorDTO.class);
		} catch(NegocioException ne) {
			throw ne;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean editar(final Long id, final MoradorDTO moradorDto) {
		try {
			this.buscar(id);
			moradorDto.setId(id);
			return this.cadastrar(moradorDto);
		} catch(NegocioException ne) {
			throw ne;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean excluir(final Long id) {
		try {
			this.buscar(id);
			this.moradorRepository.deleteById(id);
			return Boolean.TRUE;
		} catch(NegocioException ne) {
			throw ne;
		} catch(Exception e) {
			throw new NegocioException(InternalErrorMsg.INTERNAL_ERROR_MSG.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<MoradorDTO> buscarPorNome(final String nome) {
		try {
			List<Morador> moradores = moradorRepository.findByNomeContainingOrderByNomeAsc(nome);
			if (moradores.isEmpty()) {
				throw new NegocioException(NotFoundErrorMsg.NOT_FOUND_MORADOR.getValor(), HttpStatus.NOT_FOUND);
			}
			return this.mapper.map(moradores, new TypeToken<List<MoradorDTO>>() {}.getType());
		} catch(NegocioException ne) {
			throw ne;
		} catch(Exception e) {
			throw new NegocioException(InternalErrorMsg.INTERNAL_ERROR_MSG.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
