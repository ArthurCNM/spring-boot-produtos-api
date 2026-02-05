package com.example.demo.exception;


import com.example.demo.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Erro: Recurso não encontrado (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorResponseDTO(
                Instant.now(),
                status.value(),
                "Não Encontrado",
                e.getMessage(),
                request.getRequestURI()
        ));
    }

    // 2. Erro: Regra de Negócio (400) - Preço negativo, Nome vazio
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> handleBusinessException(BusinessException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new ErrorResponseDTO(
                Instant.now(),
                status.value(),
                "Violação de Regra de Negócio",
                e.getMessage(),
                request.getRequestURI()
        ));
    }

    // 3. Erro Genérico (500) - "Rede de segurança" para bugs inesperados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneralException(Exception e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(new ErrorResponseDTO(
                Instant.now(),
                status.value(),
                "Erro Interno do Servidor",
                "Ocorreu um erro inesperado. Tente novamente mais tarde.",
                request.getRequestURI()
        ));
    }
}