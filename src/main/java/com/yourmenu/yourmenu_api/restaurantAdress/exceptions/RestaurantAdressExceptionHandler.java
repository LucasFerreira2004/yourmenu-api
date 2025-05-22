package com.yourmenu.yourmenu_api.restaurantAdress.exceptions;

import com.yourmenu.yourmenu_api.shared.globalExceptions.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestaurantAdressExceptionHandler {
    @ExceptionHandler(RestaurantAdressNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleRestaurantNotFoundException(RestaurantAdressNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponseDTO error = new ErrorResponseDTO(
                e.getField(),
                e.getMessage(),
                status.value(),
                status.getReasonPhrase()
        );
        return ResponseEntity.status(status).body(error);
    }
}
