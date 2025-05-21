package com.yourmenu.yourmenu_api.restaurantAdress.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RestaurantAdressDTO (
    @NotBlank
    String id,

    @NotBlank
    String restaurantId,

    @NotNull
    @NotBlank
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
){}
