package com.ufrb.lardosidosos.doctransfer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ufrb.lardosidosos.domain.model.DocumentoRegistroSaude;
import com.ufrb.lardosidosos.domain.repository.DocumentoRegistroSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ufrb.lardosidosos.doctransfer.exception.FileNotFoundException;
import com.ufrb.lardosidosos.doctransfer.exception.FileStorageException;
import com.ufrb.lardosidosos.doctransfer.model.Arquivo;
import com.ufrb.lardosidosos.doctransfer.repository.ArquivoRepository;
import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.DocumentoMorador;
import com.ufrb.lardosidosos.domain.repository.DocumentoMoradorRepository;

@RestController
@RequestMapping("/arquivo")
public class ArquivoController {

	@Autowired
	private ArquivoRepository repository;
	
	@Autowired
	private DocumentoMoradorRepository documentoMoradorRepository;

	@Autowired
	private DocumentoRegistroSaudeRepository documentoRegistroSaudeRepository;
	
	@GetMapping("/documentomorador/{documentoId}")
	public ResponseEntity<List<Arquivo>> listarDocumentosMorador(@PathVariable Long documentoId)
	{
		documentoMoradorRepository.findById(documentoId)
			.orElseThrow(() -> new NegocioException("Documento não encontrado."));
		 
		 return ResponseEntity.ok(repository.findByDocumentoMoradorId(documentoId));
	}
	
	@GetMapping("/documentoregistrosaude/{documentoId}")
	public ResponseEntity<List<Arquivo>> listarDocumentoRegistroSaude(@PathVariable Long documentoId)
	{
		documentoRegistroSaudeRepository.findById(documentoId)
				.orElseThrow(() -> new NegocioException("Documento não encontrado."));

		return ResponseEntity.ok(repository.findByDocumentoRegistroSaudeId(documentoId));
	}

	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/upload")
	public Arquivo uploadDocMorador(
			@RequestParam("arquivo") MultipartFile arquivo,
			@RequestParam("documento_id") long id,
			@RequestParam("doc_tipo") char tipo)
	{
		
		Arquivo arq = null;
		
		if(tipo == 'd') {
			DocumentoMorador documentoMorador = documentoMoradorRepository.findById(id)
					.orElseThrow(() -> new NegocioException("Documento não encontrado."));
			try {
				arq = new Arquivo(documentoMorador, null, arquivo.getContentType(), arquivo.getBytes());
			} 
			catch (Exception e) {
				throw new FileStorageException("Não foi possível criar o arquivo. Por favor, tente novamente.", e);
			}
			return repository.save(arq);
		} 
		else if (tipo == 'r') 
		{
			DocumentoRegistroSaude documentoRegistroSaude = documentoRegistroSaudeRepository.findById(id)
					.orElseThrow(() -> new NegocioException("Documento não encontrado."));
			
			try {
				arq = new Arquivo(null, documentoRegistroSaude, arquivo.getContentType(), arquivo.getBytes());
			} catch (Exception e) {
				throw new FileStorageException("Não foi possível criar o arquivo. Por favor, tente novamente.", e);
			}

			return repository.save(arq);
		}
		else 
		{
			throw new NegocioException("Não foi possível criar o arquivo. O parâmetro doc_tipo deve ser um char d ou r.");
		}

	}

	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> download(@PathVariable Long id, HttpServletRequest request)
	{

		Arquivo arq = repository.findById(id)
				.orElseThrow(() -> new FileNotFoundException("Arquivo com o id: " + id + " não foi encontrado."));

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(arq.getTipoArquivo()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; id=\"" + arq.getId() + "\"")
				.body(new ByteArrayResource(arq.getData()));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
