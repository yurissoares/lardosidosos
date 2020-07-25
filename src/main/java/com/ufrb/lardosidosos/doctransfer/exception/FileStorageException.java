package com.ufrb.lardosidosos.doctransfer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public FileStorageException(String mensagem){
		super(mensagem);
	}
	
	public FileStorageException(String mensagem, Throwable cause){
		super(mensagem, cause);
	}
	
}
