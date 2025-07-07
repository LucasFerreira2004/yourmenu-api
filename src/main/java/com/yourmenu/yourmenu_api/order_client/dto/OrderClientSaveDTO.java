package com.yourmenu.yourmenu_api.order_client.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record OrderClientSaveDTO(

        @NotNull(message = "O ID do pedido é obrigatório")
        Long orderId,

        @NotBlank(message = "O primeiro nome é obrigatório")
        @Size(min = 2, max = 50, message = "O primeiro nome deve ter entre 2 e 50 caracteres")
        String firstName,

        @NotBlank(message = "O sobrenome é obrigatório")
        @Size(min = 2, max = 50, message = "O sobrenome deve ter entre 2 e 50 caracteres")
        String lastName,

        @NotNull(message = "O telefone é obrigatório")
        @Digits(integer = 11, fraction = 0, message = "O telefone deve ter até 11 dígitos e sem casas decimais")
        Long phone
) {}
