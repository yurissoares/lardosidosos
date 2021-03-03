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

    //TODO: Faltando mapear essa exception corretamente 02-03-2021
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ErrorResponse> handlerInternalAuthenticationServiceException(InternalAuthenticationServiceException iase) {
        ErrorResponseBuilder erro = ErrorResponse.builder();

        erro.httpStatus(HttpStatus.NOT_FOUND.value());
        erro.mensagem(iase.getMessage());
        erro.timeStamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(erro.build());
    }

}
