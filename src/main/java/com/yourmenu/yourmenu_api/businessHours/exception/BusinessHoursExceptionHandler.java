package com.yourmenu.yourmenu_api.businessHours.exception;

import com.yourmenu.yourmenu_api.globalExceptions.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class BusinessHoursExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        List<ErrorResponseDTO> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorResponseDTO(
                        error.getField(),
                        error.getDefaultMessage(),
                        status.value(),
                        status.toString()))
                .toList();

        return new ResponseEntity<>(errors, status);
    }

}
