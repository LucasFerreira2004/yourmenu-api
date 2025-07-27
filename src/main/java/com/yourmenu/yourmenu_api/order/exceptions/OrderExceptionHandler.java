package com.yourmenu.yourmenu_api.order.exceptions;

import com.yourmenu.yourmenu_api.shared.globalExceptions.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(RestaurantNotOperationException.class)
    public ResponseEntity<ErrorResponseDTO> handleRestaurantNotOperationException(RestaurantNotOperationException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponseDTO error = new ErrorResponseDTO(
                e.getField(),
                e.getMessage(),
                status.value(),
                status.getReasonPhrase()
        );
        return ResponseEntity.status(status).body(error);
    }
}
