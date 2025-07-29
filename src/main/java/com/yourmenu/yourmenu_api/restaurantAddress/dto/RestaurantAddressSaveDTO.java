package com.yourmenu.yourmenu_api.restaurantAddress.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RestaurantAddressSaveDTO(
        @NotBlank(message = "O id do restaurante é obrigatório ser informado")
        String restaurantId,

        @NotBlank(message = "O cep é obrigatório ser informado")
        @Size(min = 8, max = 9, message = "O cep deve conter exatamente 8 caracteres")
        String cep,

        @NotBlank(message = "O estado é obrigatório ser informado")
        String state,

        @NotBlank(message = "A cidade é obrigatória ser informado")
        String city,

        @NotBlank(message = "A rua é obrigatória ser informado")
        String street,

        @NotNull(message = "O número é obrigatório ser informado")
        Integer number,

        @NotBlank
        String district,

        String complement,
        String reference
)
{ }
