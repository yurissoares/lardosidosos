package com.ufrb.lardosidosos.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.doctransfer.model.Arquivo;
import com.ufrb.lardosidosos.doctransfer.repository.IArquivoRepository;
import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.DocumentoMorador;
import com.ufrb.lardosidosos.domain.repository.IDocumentoMoradorRepository;

@Service
public class DocumentoMoradorService implements IDocumentoMoradorService {
	
	private IDocumentoMoradorRepository docMoradorRepository;
	private IMoradorService moradorService;
	private IArquivoRepository arquivoRepository;
	
	@Autowired
	public DocumentoMoradorService(IDocumentoMoradorRepository docMoradorRepository, IMoradorService moradorService, 
			IArquivoRepository arquivoRepository) {
		
		this.docMoradorRepository = docMoradorRepository;
		this.moradorService = moradorService;
		this.arquivoRepository = arquivoRepository;
	}

	@Override
	public List<DocumentoMorador> listar() {
		return this.docMoradorRepository.findAll();
	}

	@Override
	public DocumentoMorador cadastrar(DocumentoMorador docMorador) {
		this.moradorService.verificaSeMoradorExiste(docMorador.getMorador().getId());
		return this.docMoradorRepository.save(docMorador);
	}

	@Override
	public DocumentoMorador buscar(Long id) {
		Optional<DocumentoMorador> moradorOptional = this.docMoradorRepository.findById(id);
		if (!moradorOptional.isPresent()) {
			throw new NegocioException("Documento do morador não encontrado.", HttpStatus.NOT_FOUND);
		}
		return moradorOptional.get();
	}

	@Override
	public DocumentoMorador editar(Long id, DocumentoMorador docMorador) {
		this.buscar(id);
		docMorador.setId(id);
		return this.cadastrar(docMorador);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);

		List<Arquivo> arquivosDeletar = arquivoRepository.findAllByDocumentoMoradorId(id);
		arquivosDeletar.forEach(item -> arquivoRepository.deleteById(item.getId()));
		this.docMoradorRepository.deleteById(id);
	}
	
	@Override
	public List<DocumentoMorador> listarDocMorador(Long moradorId) {
		this.moradorService.verificaSeMoradorExiste(moradorId);
		return this.docMoradorRepository.findByMoradorId(moradorId);
	}
}
