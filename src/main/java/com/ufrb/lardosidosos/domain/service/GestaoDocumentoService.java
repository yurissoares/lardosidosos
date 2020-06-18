package com.ufrb.lardosidosos.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.Documento;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.repository.DocumentoRepository;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;

@Service
public class GestaoDocumentoService {
	
	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Autowired
	private MoradorRepository moradorRepository;
	
	public Documento salvar(Documento documento) {
		Morador morador = moradorRepository.findById(documento.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		documento.setMorador(morador);
		return documentoRepository.save(documento);
	}
	
	public void excluir(Long id) {
		documentoRepository.deleteById(id);
	}
}
