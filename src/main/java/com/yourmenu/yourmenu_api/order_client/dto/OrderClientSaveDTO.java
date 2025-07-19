package com.yourmenu.yourmenu_api.order_client.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record OrderClientSaveDTO(
        @NotBlank(message = "O nome nome é obrigatório")
        @Size(min = 2, max = 255, message = "O nome deve ter entre 2 e 255 caracteres")
        String name,

        @NotBlank(message = "O telefone é obrigatório")
        @Size(min = 10, max = 11, message = "O telefone deve ter entre 10 e 11 dígitos")
        @Digits(integer = 11, fraction = 0, message = "O telefone deve conter apenas dígitos")
        String phone
) {}
