package com.yourmenu.yourmenu_api.globalExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponseDTO>> handleValidationException(MethodArgumentNotValidException e) {
        List<ErrorResponseDTO> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErrorResponseDTO(
                        error.getField(),
                        error.getDefaultMessage(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase()
                ))
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(UserNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponseDTO error = new ErrorResponseDTO(
                e.getField(),
                e.getMessage(),
                status.value(),
                status.getReasonPhrase()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(UserNotAuthenticatedException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(UserNotAuthenticatedException e) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorResponseDTO error = new ErrorResponseDTO(
                e.getField(),
                e.getMessage(),
                status.value(),
                status.getReasonPhrase()
        );
        System.out.println("passou aqui e vai retornar");
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DeniedAccessException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(DeniedAccessException e) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        ErrorResponseDTO error = new ErrorResponseDTO(
                e.getField(),
                e.getMessage(),
                status.value(),
                status.getReasonPhrase()
        );
        return ResponseEntity.status(status).body(error);
    }
}
