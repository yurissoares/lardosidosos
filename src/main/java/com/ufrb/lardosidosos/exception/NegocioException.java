package com.ufrb.lardosidosos.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NegocioException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatus;

	public NegocioException(final String message, final HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
}
