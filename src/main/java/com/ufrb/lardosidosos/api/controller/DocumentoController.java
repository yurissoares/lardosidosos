package com.ufrb.lardosidosos.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ufrb.lardosidosos.doctransfer.model.UploadDoc;
import com.ufrb.lardosidosos.doctransfer.service.FileStorageService;
import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.Documento;
import com.ufrb.lardosidosos.domain.model.Morador;
import com.ufrb.lardosidosos.domain.repository.DocumentoRepository;
import com.ufrb.lardosidosos.domain.repository.MoradorRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/documento")
public class DocumentoController {

	@Autowired
	private DocumentoRepository repository;

	@Autowired
	private MoradorRepository moradorRepository;

	@Autowired
	private FileStorageService fileStorageService;

	@ApiOperation(value = "Lista todos os documentos", notes = "Retorna uma lista com todos os documentos dos moradores.")
	@GetMapping
	List<Documento> listarDocumentos() {
		return repository.findAll();
	}

	@ApiOperation(value = "Busca um documento", notes = "Busca por documento de um morador pelo id.")
	@GetMapping("/{id}")
	public ResponseEntity<Documento> buscaDocumento(
			@ApiParam(name = "id", value = "Id do documento.", required = true, type = "long") @PathVariable Long id) {

		Optional<Documento> documento = repository.findById(id);

		if (documento.isPresent())
			return ResponseEntity.ok(documento.get());

		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Cria documento", notes = "Cria documento de um morador.")
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Documento salvaDocumento(@Valid @RequestPart("data") String documento,
			@RequestPart(value = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		Documento docModel = mapper.readValue(documento, Documento.class);

		Morador morador = moradorRepository.findById(docModel.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));

		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/documento/file/downloadFile/").path(fileName).toUriString();

		UploadDoc upDoc = new UploadDoc(fileName, fileDownloadUri, file.getContentType(), file.getSize());

		docModel.setMorador(morador);
		docModel.setPathDoc(upDoc.getFileDownloadUri());

		return repository.save(docModel);
	}

	@ApiOperation(value = "Edita um documento", notes = "Edita um documento de um morador especificado pelo id do documento.")
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Documento> editaDocumento(
			@ApiParam(name = "id", value = "Id do documento.", required = true, type = "long") @PathVariable Long id,
			@Valid @RequestPart("data") String documento, @RequestPart(value = "file", required = false) MultipartFile file) 
					throws JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		Documento docModel = mapper.readValue(documento, Documento.class);

		moradorRepository.findById(docModel.getMorador().getId())
				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/documento/file/downloadFile/").path(fileName).toUriString();

		UploadDoc upDoc = new UploadDoc(fileName, fileDownloadUri, file.getContentType(), file.getSize());
		
		docModel.setPathDoc(upDoc.getFileDownloadUri());
		docModel.setId(id);
		docModel = repository.save(docModel);
		return ResponseEntity.ok(docModel);
	}

//	public ResponseEntity<Documento> editaDocumento(
//			@ApiParam(name = "id", value = "Id do documento.", required = true, type = "long") 
//			@PathVariable Long id,
//			@Valid @RequestBody Documento documento) {
//		
//		moradorRepository.findById(documento.getMorador().getId())
//				.orElseThrow(() -> new NegocioException("Morador não encontrado."));
//
//		documento.setId(id);
//		documento = repository.save(documento);
//		return ResponseEntity.ok(documento);
//	}

	@ApiOperation(value = "Deleta um documento", notes = "Deleta um documento de um morador especificado pelo id do documento.")
	@DeleteMapping("/{id}")
	ResponseEntity<Void> excluiDocumento(
			@ApiParam(name = "id", value = "Id do documento.", required = true, type = "long") @PathVariable Long id) {

		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();

		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
