package com.ufrb.lardosidosos.doctransfer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public FileNotFoundException(String mensagem){
		super(mensagem);
	}
	
	public FileNotFoundException(String mensagem, Throwable cause){
		super(mensagem, cause);
	}
	
}
