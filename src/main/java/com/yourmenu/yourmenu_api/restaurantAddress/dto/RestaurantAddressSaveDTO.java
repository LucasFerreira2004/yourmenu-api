package com.yourmenu.yourmenu_api.restaurantAddress.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RestaurantAddressSaveDTO(
        @NotBlank
        String restaurantId,

        @NotNull
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
