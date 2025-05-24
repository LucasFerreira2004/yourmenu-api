package com.yourmenu.yourmenu_api.category.Exceptions;

import com.yourmenu.yourmenu_api.restaurantAddress.exceptions.RestaurantAddressNotFoundException;
import com.yourmenu.yourmenu_api.shared.globalExceptions.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CategoryExceptionHandler {
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCategoryNotFoundException(CategoryNotFoundException e) {
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
