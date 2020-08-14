package com.ufrb.lardosidosos.doctransfer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.ufrb.lardosidosos.domain.model.Documento;
import com.ufrb.lardosidosos.domain.repository.DocumentoRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/arquivo")
public class ArquivoController {

	@Autowired
	private ArquivoRepository repository;
	
	@Autowired
	private DocumentoRepository docRepository;
	
	@ApiOperation(value = "Lista arquivos referentes ao documento", notes = "Retorna uma lista com todos os arquivos do documento")
	@GetMapping("/documento/{documentoId}")
	public ResponseEntity<List<Arquivo>> listarArquivosDoDocumento(
			@ApiParam(name = "documentoId", value = "Id do documento.", required = true, type = "long") 
			@PathVariable Long documentoId)
	{
		 docRepository.findById(documentoId)
			.orElseThrow(() -> new NegocioException("Documento não encontrado."));
		 
		 return ResponseEntity.ok(repository.findByDocumentoId(documentoId));
	}

	@ApiOperation(value = "Upload de uma imagem", notes = "Upload de uma nova imagem.")
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/upload")
	public Arquivo uploadArquivo(
			@ApiParam(name = "arquivo", value = "Arquivo que irá ser feito o upload.", required = true)
			@RequestParam("arquivo") MultipartFile arquivo, 
			@ApiParam(name = "documento_id", value = "id do documento.", required = true, type = "long")
			@RequestParam("documento_id") long id) {

		Documento documento = docRepository.findById(id)
				.orElseThrow(() -> new NegocioException("Documento não encontrado."));

			Arquivo arq = null;
			
			try {
				arq = new Arquivo(documento, arquivo.getContentType(), arquivo.getBytes());
			} catch (Exception e) {
				throw new FileStorageException("Não foi possível criar o arquivo. Por favor, tente novamente.", e);
			}

			return repository.save(arq);
		} 

	@ApiOperation(value = "Download de uma imagem", notes = "Download de uma imagem.")
	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> downloadArquivo(@PathVariable Long id, HttpServletRequest request) {

		Arquivo arq = repository.findById(id)
				.orElseThrow(() -> new FileNotFoundException("Arquivo com o id: " + id + " não foi encontrado."));

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(arq.getTipoArquivo()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; id=\"" + arq.getId() + "\"")
				.body(new ByteArrayResource(arq.getData()));

	}
	
	@ApiOperation(value = "Deleta arquivo", notes = "Deleta arquivo de um documento especificado pelo id do arquivo.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluiArquivo(
			@ApiParam(name = "id", value = "Id do arquivo.", required = true, type = "long") 
			@PathVariable Long id) {
		
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
