package com.yourmenu.yourmenu_api.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CategorySaveDTO(
        @NotBlank
        String name
) {
}
