package com.yourmenu.yourmenu_api.businessHours.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.yourmenu.yourmenu_api.businessHours.dto.SimpleErrorResponse;
import com.yourmenu.yourmenu_api.globalExceptions.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(BusinessHoursInvalidException.class)
    public ResponseEntity<SimpleErrorResponse> handleBusinessHoursInvalidException(BusinessHoursInvalidException e) {
        var status = HttpStatus.BAD_REQUEST;
        var error = new SimpleErrorResponse(e.getMessage());
        return ResponseEntity.status(status).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Throwable cause = ex.getCause();

        if (cause instanceof InvalidFormatException invalidFormatException && invalidFormatException.getTargetType().isEnum()) {
            String allowedValues = Arrays.stream(invalidFormatException.getTargetType().getEnumConstants())
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));
            String fieldName = invalidFormatException.getPath().isEmpty() ? "campo" : invalidFormatException.getPath().get(0).getFieldName();
            String message = String.format("Valor inválido para o campo '%s'. Valores permitidos são: %s", fieldName, allowedValues);
            return ResponseEntity.badRequest().body(new SimpleErrorResponse(message));
        }

        return ResponseEntity.badRequest().body(new SimpleErrorResponse("JSON inválido ou formato incorreto."));
    }

}
