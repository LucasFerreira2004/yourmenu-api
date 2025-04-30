package com.yourmenu.yourmenu_api.globalExceptions;

public record ErrorResponseDTO(String field, String message, Integer statusCode, String error) {
}
