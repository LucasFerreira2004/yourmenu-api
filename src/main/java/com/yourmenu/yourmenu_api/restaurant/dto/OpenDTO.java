package com.yourmenu.yourmenu_api.restaurant.dto;

import jakarta.validation.constraints.NotNull;

public record OpenDTO(
        @NotNull
        Boolean isOpen
) { }
