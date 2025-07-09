package com.yourmenu.yourmenu_api.orderAdress.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record OrderAdressPostDto(
        @NotNull(message = "A zona de entrega deve ser informada")
        Long deliveryZoneId,

        @NotBlank(message = "O CEP deve ser informado")
        @Size(min = 8, max = 8, message = "O CEP deve conter exatamente 8 caracteres")
        String cep,

        @NotBlank(message = "A rua deve ser informada")
        String street,

        @NotBlank(message = "O número deve ser informado")
        @Size(min = 1, max = 20, message = "O número deve ter entre 1 e 20 caracteres")
        String number,

        @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres")
        String complement,

        @Size(max = 100, message = "A referência deve ter no máximo 100 caracteres")
        String reference

) {}
