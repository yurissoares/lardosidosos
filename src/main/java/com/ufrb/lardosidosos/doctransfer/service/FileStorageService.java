package com.ufrb.lardosidosos.doctransfer.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ufrb.lardosidosos.doctransfer.config.FileStorageConfig;
import com.ufrb.lardosidosos.doctransfer.exception.FileStorageException;
import com.ufrb.lardosidosos.doctransfer.exception.FileNotFoundException;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;

	@Autowired
	public FileStorageService(FileStorageConfig fileStorageConfig) {
		this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException("Não foi possível criar o diretório onde os arquivos serão armazenados.", e);
		}
	}

	public String storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Desculpe. O nome do arquivo contém erros. " + fileName);
			}

			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (Exception e) {
			throw new FileStorageException(
					"Não foi possível criar o arquivo " + fileName + ". Por favor, tente novamente.", e);
		}
	}
	
	public Resource loadFileAsResource(String fileName){
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			
			if(resource.exists()) {
				return resource;
			} else {
				throw new FileNotFoundException("Arquivo não encontrado" + fileName);
			}
		} catch (Exception e) {
			throw new FileNotFoundException("Arquivo não encontrado. " + fileName, e);
		}
	}
}
















