package com.ufrb.lardosidosos.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.Despesa;
import com.ufrb.lardosidosos.domain.model.MoradorDespesa;
import com.ufrb.lardosidosos.domain.repository.IMoradorDespesaRepository;

@Service
public class MoradorDespesaService implements IMoradorDespesaService {

	private IMoradorDespesaRepository moradorDespesaRepository;
	private IMoradorService moradorService;
	private IDespesaService despesaService;

	@Autowired
	public MoradorDespesaService(IMoradorDespesaRepository moradorDespesaRepository, IMoradorService moradorService
			, IDespesaService despesaService) {
		
		this.moradorDespesaRepository = moradorDespesaRepository;
		this.moradorService = moradorService;
		this.despesaService = despesaService;
	}

	@Override
	public List<MoradorDespesa> listar() {
		return this.moradorDespesaRepository.findAll();
	}

	@Override
	public MoradorDespesa cadastrar(MoradorDespesa moradorDespesa) {
		this.moradorService.verificaSeMoradorExiste(moradorDespesa.getMorador().getId());
		Despesa despesa = this.despesaService.buscar(moradorDespesa.getDespesa().getId());
		
		moradorDespesa.setDespesa(despesa);

		moradorDespesa.setPrecoTotal(moradorDespesa.getQuantidade() * moradorDespesa.getDespesa().getPreco());
		return this.moradorDespesaRepository.save(moradorDespesa);
	}

	@Override
	public MoradorDespesa buscar(Long id) {
		Optional<MoradorDespesa> moradorDespesaOptional = this.moradorDespesaRepository.findById(id);
		if (!moradorDespesaOptional.isPresent()) {
			throw new NegocioException("MoradorDespesa não encontrada.", HttpStatus.NOT_FOUND);
		}
		return moradorDespesaOptional.get();
	}

	@Override
	public MoradorDespesa editar(Long id, MoradorDespesa moradorDespesa) {
		this.buscar(id);
		moradorDespesa.setId(id);
		return this.cadastrar(moradorDespesa);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);
		this.moradorDespesaRepository.deleteById(id);
	}

}
