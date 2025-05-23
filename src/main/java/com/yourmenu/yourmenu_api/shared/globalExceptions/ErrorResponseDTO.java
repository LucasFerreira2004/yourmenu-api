package com.yourmenu.yourmenu_api.shared.globalExceptions;

public record ErrorResponseDTO(String field, String message, Integer statusCode, String error) {
}
