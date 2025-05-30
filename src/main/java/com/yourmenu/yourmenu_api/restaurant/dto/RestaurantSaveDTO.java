package com.yourmenu.yourmenu_api.restaurant.dto;

import jakarta.validation.constraints.NotBlank;

public record RestaurantSaveDTO (
        @NotBlank (message = "o nome é obrigatório")
        String name,

        Integer deliveryTimeMin,
        Integer deliveryTimeMax,
        Boolean isOpen,
        String profilePictureUrl,
        String bannerPictureUrl
        //isOpen foi ignorado, campos de imagem não foram implementados ainda.
){}
