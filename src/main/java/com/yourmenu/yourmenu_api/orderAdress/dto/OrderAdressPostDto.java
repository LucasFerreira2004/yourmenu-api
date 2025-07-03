package com.yourmenu.yourmenu_api.orderAdress.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record OrderAdressPostDto(
        @NotEmpty(message = "O pedido deve ser informado")
        @Size(min = 1, message = "O pedido deve ser informado")
        Long orderId,

        @NotEmpty(message = "A zona de entrega deve ser informada")
        @Size(min = 1, message = "O pedido deve ser informado")
        Long deliveryZoneId,

        @NotBlank(message = "A zona de entrega deve ser informada")
        @Size(min = 8, max = 8, message = "O cep deve ter 8 caracteres")
        Long cep,

        @NotBlank(message = "A rua deve ser informada")
        String street,

        @NotBlank(message = "O numero deve ser informado")
        @Length(min = 1, max = 20)
        String number,

        @Length(min = 1, max = 100, message = "O complemento deve ter no.maxcdn 100 caracteres")
        String complement,

        @Length(min = 1, max = 100, message = "A referência deve ter no máximo 100 caracteres")
        String reference
) {
}
