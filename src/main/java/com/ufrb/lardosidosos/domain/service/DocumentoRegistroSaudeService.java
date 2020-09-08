package com.ufrb.lardosidosos.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.doctransfer.model.Arquivo;
import com.ufrb.lardosidosos.doctransfer.repository.IArquivoRepository;
import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.DocumentoRegistroSaude;
import com.ufrb.lardosidosos.domain.repository.IDocumentoRegistroSaudeRepository;

@Service
public class DocumentoRegistroSaudeService implements IDocumentoRegistroSaudeService {

	private IDocumentoRegistroSaudeRepository docRegSaudeRepository;
	private IMoradorService moradorService;
	private IUsuarioService usuarioService;
	private IRegistroSaudeService regSaudeService;
	private IArquivoRepository arquivoRepository;
	
	@Autowired
	public DocumentoRegistroSaudeService(IDocumentoRegistroSaudeRepository docRegSaudeRepository, IMoradorService moradorService, 
			IUsuarioService usuarioService, IRegistroSaudeService regSaudeService, IArquivoRepository arquivoRepository) {
		
		this.docRegSaudeRepository = docRegSaudeRepository;
		this.moradorService = moradorService;
		this.usuarioService = usuarioService;
		this.regSaudeService = regSaudeService;
		this.arquivoRepository = arquivoRepository;
	}

	@Override
	public List<DocumentoRegistroSaude> listar() {
		return this.docRegSaudeRepository.findAll();
	}

	@Override
	public DocumentoRegistroSaude cadastrar(DocumentoRegistroSaude docRegSaude) {
		this.moradorService.verificaSeMoradorExiste(docRegSaude.getMorador().getId());
		this.usuarioService.verificaSeUsuarioExiste(docRegSaude.getUsuario().getId());
		this.regSaudeService.verificaSeRegSaudeExiste(docRegSaude.getRegistroSaude().getId());
		return this.docRegSaudeRepository.save(docRegSaude);
	}

	@Override
	public DocumentoRegistroSaude buscar(Long id) {
		Optional<DocumentoRegistroSaude> docRegSaudeOptional = this.docRegSaudeRepository.findById(id);
		if (!docRegSaudeOptional.isPresent()) {
			throw new NegocioException("Documento de registro de saúde não encontrado.");
		}
		return docRegSaudeOptional.get();
	}

	@Override
	public DocumentoRegistroSaude editar(Long id, DocumentoRegistroSaude docRegSaude) {
		this.buscar(id);
		docRegSaude.setId(id);
		return this.cadastrar(docRegSaude);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);
		List<Arquivo> arquivosDeletar = arquivoRepository.findAllByDocumentoRegistroSaudeId(id);
		arquivosDeletar.forEach(item -> arquivoRepository.deleteById(item.getId()));
		this.docRegSaudeRepository.deleteById(id);
	}

	@Override
	public List<DocumentoRegistroSaude> listarDocDoRegSaude(Long regSaudeId) {
		this.regSaudeService.verificaSeRegSaudeExiste(regSaudeId);
		return this.docRegSaudeRepository.findByRegistroSaudeId(regSaudeId);
	}
}
