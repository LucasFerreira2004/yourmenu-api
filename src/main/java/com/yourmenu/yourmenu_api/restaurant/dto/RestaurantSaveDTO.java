package com.yourmenu.yourmenu_api.restaurant.dto;

import jakarta.validation.constraints.NotBlank;

public record RestaurantSaveDTO (
        @NotBlank (message = "o nome é obrigatório")
        String name,

        String slug,
        Integer deliveryTimeMin,
        Integer deliveryTimeMax,
        Boolean isOpen
){}
