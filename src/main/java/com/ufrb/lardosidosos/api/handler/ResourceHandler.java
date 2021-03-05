package com.ufrb.lardosidosos.api.handler;

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.ErrorResponse.ErrorResponseBuilder;

import com.ufrb.lardosidosos.domain.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<ErrorResponse> handlerNegocioException(NegocioException ne) {
        ErrorResponseBuilder erro = ErrorResponse.builder();

        erro.httpStatus(ne.getHttpStatus().value());
        erro.mensagem(ne.getMessage());
        erro.timeStamp(System.currentTimeMillis());

        return ResponseEntity.status(ne.getHttpStatus()).body(erro.build());
    }

}
