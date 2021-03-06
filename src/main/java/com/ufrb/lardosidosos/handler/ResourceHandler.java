package com.ufrb.lardosidosos.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.ufrb.lardosidosos.exception.NegocioException;
import com.ufrb.lardosidosos.model.ErrorMapResponse;
import com.ufrb.lardosidosos.model.ErrorResponse.ErrorResponseBuilder;
import com.ufrb.lardosidosos.model.ErrorMapResponse.ErrorMapResponseBuilder;

import com.ufrb.lardosidosos.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMapResponse> handlerMethodArgumentNotValidException(final MethodArgumentNotValidException e) {

        Map<String, String> erros = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err -> {
            String campo = ((FieldError) err).getField();
            String mensagem = err.getDefaultMessage();
            erros.put(campo, mensagem);
        });

        ErrorMapResponseBuilder errorMap = ErrorMapResponse.builder();
        errorMap.erros(erros)
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .timeStamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap.build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMapResponse> handlerConstraintViolationException(final ConstraintViolationException e) {
        Map<String, String> erros = new HashMap<>();
        e.getConstraintViolations().forEach(err -> {
            String campo = err.getPropertyPath().toString();
            String mensagem = err.getMessageTemplate();
            erros.put(campo, mensagem);
        });

        ErrorMapResponseBuilder errorMap = ErrorMapResponse.builder();
        errorMap.erros(erros)
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .timeStamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap.build());
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<ErrorResponse> handlerNegocioException(NegocioException ne) {
        ErrorResponseBuilder error = ErrorResponse.builder();

        error.httpStatus(ne.getHttpStatus().value());
        error.mensagem(ne.getMessage());
        error.timeStamp(System.currentTimeMillis());

        return ResponseEntity.status(ne.getHttpStatus()).body(error.build());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorMapResponse> handlerInvalidFormatException(final InvalidFormatException e) {
        Map<String, String> erros = new HashMap<>();
        e.getPath().forEach(err -> {
            String campo = err.getFieldName();
            String mensagem = "Tipo inválido.";
            erros.put(campo, mensagem);
        });

        ErrorMapResponseBuilder errorMap = ErrorMapResponse.builder();
        errorMap.erros(erros)
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .timeStamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap.build());
    }

}
