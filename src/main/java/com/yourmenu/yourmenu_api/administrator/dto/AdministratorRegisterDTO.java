package com.yourmenu.yourmenu_api.administrator.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AdministratorRegisterDTO (
        @NotBlank (message = "O nome não pode estar em branco")
        String username,
        String fullName,
        @Email(message = "O e-mail deve ser válido")
        @NotBlank(message = "O e-mail é obrigatório")
        String email,
        @NotBlank(message = "A senha é obrigatória")
        String password
) {}
