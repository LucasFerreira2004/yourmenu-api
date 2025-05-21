package com.yourmenu.yourmenu_api.restaurantAdress.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RestaurantAdressSaveDTO(
        @NotBlank
        String restaurantId,

        @NotNull @NotBlank
        Integer cep,

        @NotBlank
        String state,

        @NotBlank
        String city,

        @NotBlank
        String street,

        Integer number,

        @NotBlank
        String district,

        String complement,
        String reference
)
{ }
