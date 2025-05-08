package com.yourmenu.yourmenu_api.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OpenDTO(
        @NotNull
        Boolean isOpen,

        @NotBlank (message = "o slug deve ser válido")
        String restaurantSlug
) { }
