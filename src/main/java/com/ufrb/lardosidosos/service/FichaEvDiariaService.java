package com.ufrb.lardosidosos.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.exception.NegocioException;
import com.ufrb.lardosidosos.entity.FichaEvDiaria;
import com.ufrb.lardosidosos.repository.IFichaEvDiariaRepository;
import com.ufrb.lardosidosos.repository.IFichaSaudeRepository;

@Service
public class FichaEvDiariaService implements IFichaEvDiariaService {

	private IFichaEvDiariaRepository fichaEvDiariaRepository;
	private IMoradorService moradorService;
	private IUsuarioService usuarioService;
	private IFichaAdmissaoService fichaAdmissaoService;
	private IFichaSaudeRepository fichaSaudeRepository;

	@Autowired
	public FichaEvDiariaService(IFichaEvDiariaRepository fichaEvDiariaRepository, IMoradorService moradorService,
			IUsuarioService usuarioService, IFichaAdmissaoService fichaAdmissaoService,
			IFichaSaudeRepository fichaSaudeRepository) {

		this.fichaEvDiariaRepository = fichaEvDiariaRepository;
		this.moradorService = moradorService;
		this.usuarioService = usuarioService;
		this.fichaAdmissaoService = fichaAdmissaoService;
		this.fichaSaudeRepository = fichaSaudeRepository;
	}

	@Override
	public List<FichaEvDiaria> listar() {
		return this.fichaEvDiariaRepository.findAll();
	}

	@Override
	public FichaEvDiaria cadastrar(FichaEvDiaria fichaEvDiaria) {
		this.moradorService.verificaSeMoradorExiste(fichaEvDiaria.getMorador().getId());
		this.usuarioService.verificaSeUsuarioExiste(fichaEvDiaria.getUsuario().getId());
		return this.fichaEvDiariaRepository.save(fichaEvDiaria);
	}

	@Override
	public FichaEvDiaria buscar(Long id) {
		Optional<FichaEvDiaria> fichaEvDiariaOptional = this.fichaEvDiariaRepository.findById(id);
		if (!fichaEvDiariaOptional.isPresent()) {
			throw new NegocioException("Ficha de evolução diária não encontrada.", HttpStatus.NOT_FOUND);
		}
		return fichaEvDiariaOptional.get();
	}

	@Override
	public FichaEvDiaria editar(Long id, FichaEvDiaria fichaEvDiaria) {
		this.buscar(id);
		fichaEvDiaria.setId(id);
		return this.cadastrar(fichaEvDiaria);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);
		this.fichaEvDiariaRepository.deleteById(id);
	}

	@Override
	public FichaEvDiaria cadastrarComFichaAdmissao(FichaEvDiaria fichaEvDiaria) {
		this.fichaAdmissaoService.verificaSeFichaAdmissaoExiste(fichaEvDiaria.getFichaAdmissao().getId());
		
		if(!this.fichaSaudeRepository.findById(fichaEvDiaria.getFichaAdmissao().getId()).isPresent())
			throw new NegocioException("Ficha de saúde não encontrada.", HttpStatus.NOT_FOUND);

		this.moradorService.verificaSeMoradorExiste(fichaEvDiaria.getMorador().getId());
		this.usuarioService.verificaSeUsuarioExiste(fichaEvDiaria.getUsuario().getId());
		return this.fichaEvDiariaRepository.save(fichaEvDiaria);
	}
	
	@Override
	public List<FichaEvDiaria> listarPorMorador(Long moradorId) {
		this.moradorService.verificaSeMoradorExiste(moradorId);
		return this.fichaEvDiariaRepository.findByMoradorIdOrderByData(moradorId);
	}

	@Override
	public List<FichaEvDiaria> listarPorMoradorEntreDatas(final Long moradorId, final String dtInicio, final String dtFinal) {
		this.moradorService.verificaSeMoradorExiste(moradorId);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate localDateInicio = LocalDate.parse(dtInicio, formatter);
		LocalDate localDateFinal = LocalDate.parse(dtFinal, formatter);

		return this.fichaEvDiariaRepository.findByMoradorIdAndDataBetweenOrderByData(moradorId, localDateInicio, localDateFinal);
	}
	
	@Override
	public FichaEvDiaria buscarFichaAdmPorMorador(final Long moradorId) {
		FichaEvDiaria fevd = null;
		this.moradorService.verificaSeMoradorExiste(moradorId);
		
		List<FichaEvDiaria> fichas = this.listarPorMorador(moradorId);
		
		for (FichaEvDiaria fichaEvDiaria : fichas) {
			if(fichaEvDiaria.getFichaAdmissao() != null) {
				fevd = fichaEvDiaria;
			}
		}
		
		if(fevd == null) {
			throw new NegocioException("Morador não possui ficha de Admissão.", HttpStatus.NOT_FOUND);
		}

		return fevd;
	}

}
